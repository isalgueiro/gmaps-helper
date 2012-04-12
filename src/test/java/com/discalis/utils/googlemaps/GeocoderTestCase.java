package com.discalis.utils.googlemaps;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.discalis.utils.googlemaps.Address;
import com.discalis.utils.googlemaps.Geocoder;

public class GeocoderTestCase {

	private final static Logger logger = Logger.getLogger(GeocoderTestCase.class);
	
	@Test
	public void test() throws Exception {
		logger.info("Unit test start");
		final Geocoder geocoder = Geocoder.getInstance();
		Address address = new Address("Rúa do Rouco, Nº 2, 36002, Pontevedra, Pontevedra");
		geocoder.geocode(address);
		logger.debug(address);
		if (!"2".equals(address.getNumber())) {
			throw new GeocodingLibraryException("Unit test failed");
		}
		if ((address.getLatitude() != 42431396) || (address.getLongitude() != -8642202)) {
			throw new Exception("Geocoded address with unknown lat/long: " + address);
		}
		address = new Address("canovas del castillo, 1 planta 2 local 1, 36202, vigo, pontevedra");
		geocoder.geocode(address);
		logger.debug(address);
		if (!"Vigo".equals(address.getLocality())) {
			throw new GeocodingLibraryException("Unit test failed");
		}
		if ((address.getLatitude() != 42237071) || (address.getLongitude() != -8727469)) {
			throw new Exception("Geocoded address with unknown lat/long: " + address);
		}
		logger.info("Unit test end");
	}
}
