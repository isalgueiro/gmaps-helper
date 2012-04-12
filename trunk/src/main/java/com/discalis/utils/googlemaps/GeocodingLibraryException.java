package com.discalis.utils.googlemaps;

/**
 * Library bug! Please report so we can fix it.
 * 
 * @author isaac.salgueiro@discalis.com
 *
 */
public class GeocodingLibraryException extends Exception {

	private static final long serialVersionUID = 6769147326749200165L;

	public GeocodingLibraryException(String message, Throwable t) {
		super(message, t);
	}
	
	/**
	 * 
	 * @param message
	 * @since 1.0.1
	 */
	public GeocodingLibraryException(String message) {
		super(message);
	}
}
