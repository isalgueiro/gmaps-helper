package com.discalis.utils.googlemaps;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Utility class for address geocoding.
 * 
 * @author isaac.salgueiro@discalis.com
 * 
 */
public class Geocoder {
	/**
	 * Minimal time between Google Maps API calls: one second.
	 * 
	 * @since 0.0.2
	 */
	private static final int OVER_QUERY_LIMIT_MIN_DELTA = 1000;

	/**
	 * Google Maps API URI
	 */
	private static final String GEOCODER_REQUEST_PREFIX_FOR_XML = "http://maps.google.com/maps/api/geocode/xml";

	private static final Logger logger = Logger.getLogger(Geocoder.class);
	private final XPath xpath = XPathFactory.newInstance().newXPath();
	private Document geocoderResultDocument = null;
	
	/**
	 * Singleton pattern.
	 * 
	 * @since 0.0.2
	 * @see #getInstance()
	 */
	private final static Geocoder INSTANCE = new Geocoder();

	/**
	 * Save a timestamp each time that an API call is executed.
	 * 
	 * @see #getInstance()
	 */
	private long lastUseTimestamp = 0;

	/**
	 * Singleton.
	 * 
	 * @since 0.0.2
	 */
	private Geocoder() {

	}

	/**
	 * Get a {@link Geocoder} instance. API calls are delayed at least one second between each other
	 * to avoid OVER_QUERY_LIMIT.
	 * 
	 * @return
	 * @since 0.0.2
	 */
	public static Geocoder getInstance() {
		return INSTANCE;
	}

	
	
	private void storeLatLong(Address address) throws GeocodingLibraryException {
		NodeList resultNodeList = null;
		try {
			resultNodeList = (NodeList) xpath.evaluate(
					"/GeocodeResponse/result[1]/geometry/location/*", geocoderResultDocument,
					XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			throw new GeocodingLibraryException("Google Maps API change!", e);
		}
		for (int i = 0; i < resultNodeList.getLength(); ++i) {
			Node node = resultNodeList.item(i);
			if ("lat".equals(node.getNodeName())) {
				address.setLatitude((int) (Float.parseFloat(node.getTextContent()) * 1E6));
			}
			if ("lng".equals(node.getNodeName())) {
				address.setLongitude((int) (Float.parseFloat(node.getTextContent()) * 1E6));
			}
		}
	}
	
	/**
	 * Geolocalize <code>address</code>. Passes {@link Address#getRawAddress()} to Google Maps API.
	 * <p>
	 * Since 0.0.2 version <b>this method is synchronized</b> to avoid concurrent executions. Also,
	 * response time is always, at least, one second. See {@link #getInstance()}.
	 * </p>
	 * 
	 * @param address
	 * @return
	 * @throws GeocodingLibraryException
	 */
	public synchronized Address geocode(Address address) throws GeocodingLibraryException {
		if (Calendar.getInstance().getTimeInMillis() - lastUseTimestamp < OVER_QUERY_LIMIT_MIN_DELTA) {
			try {
				Thread.sleep(OVER_QUERY_LIMIT_MIN_DELTA
						- (Calendar.getInstance().getTimeInMillis() - lastUseTimestamp));
			} catch (InterruptedException e) {
				logger.info("Execution interrupted");
				return null;
			}
		}
		URL url = null;
		try {
			url = new URL(GEOCODER_REQUEST_PREFIX_FOR_XML + "?address="
					+ URLEncoder.encode(address.getRawAddress(), "UTF-8") + "&sensor=false");
		} catch (MalformedURLException e) {
			throw new GeocodingLibraryException("Invalid URL", e);
		} catch (UnsupportedEncodingException e) {
			throw new GeocodingRuntimeException("Unsupported encoding: UTF-8", e);
		}
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			final InputSource geocoderResultInputSource = new InputSource(conn.getInputStream());
			geocoderResultDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(geocoderResultInputSource);
		} catch (IOException e) {
			throw new GeocodingRuntimeException("Google Maps response error", e);
		} catch (Exception e) {
			throw new GeocodingLibraryException("Google Maps request error", e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug(xmlToString(geocoderResultDocument));
		}
		if (geocoderResultDocument.getDocumentElement().getTextContent().contains(
				"OVER_QUERY_LIMIT")) {
			throw new GeocodingRuntimeException("OVER_QUERY_LIMIT from Google Maps API", null);
		}

		storeLatLong(address);
		
		lastUseTimestamp = Calendar.getInstance().getTimeInMillis();
		return address;
	}

	/**
	 * Utility method used to print XML.
	 * 
	 * @param node
	 * @return
	 */
	private String xmlToString(Node node) {
		try {
			final Source source = new DOMSource(node);
			final StringWriter stringWriter = new StringWriter();
			final Result result = new StreamResult(stringWriter);
			final TransformerFactory factory = TransformerFactory.newInstance();
			final Transformer transformer = factory.newTransformer();
			transformer.transform(source, result);
			return stringWriter.getBuffer().toString();
		} catch (TransformerConfigurationException e) {
			logger.warn("Unable to convert XML to String", e);
		} catch (TransformerException e) {
			logger.warn("Unable to convert XML to String", e);
		}
		return null;
	}
}
