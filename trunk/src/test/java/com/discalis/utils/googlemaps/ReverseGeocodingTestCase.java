package com.discalis.utils.googlemaps;

import org.apache.log4j.Logger;
import org.junit.Test;

public class ReverseGeocodingTestCase {

	private final static Logger logger = Logger.getLogger(ReverseGeocodingTestCase.class);

	@Test
	public void test() throws GeocodingLibraryException {
		logger.info("unit test start");
		final Geocoder geocoder = Geocoder.getInstance();
		Address address = geocoder.reverseGeocode(1234, 5678);
		logger.debug("Unexistant address: " + address);
		if (address.getFormatted() != null) {
			throw new GeocodingLibraryException("Unit test failure");
		}
		address = geocoder.reverseGeocode(42.2261931, -8.7391438);
		logger.debug("Existant address: " + address);
		if (!address.getFormatted().equals("Avenida de Beiramar, 97, 36208 Vigo, Spain")) {
			throw new GeocodingLibraryException("Unit test failure");
		}
		address = geocoder.reverseGeocode(42, -8);
		logger.debug("Approximate address: " + address);
		if (!"OU-1214".equals(address.getStreet()) || address.getNumber() != null) {
			throw new GeocodingLibraryException("Unit test failure");
		}
		logger.info("unit test end");
	}
}
