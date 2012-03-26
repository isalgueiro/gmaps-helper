package com.discalis.utils.googlemaps;

/**
 * Execution error: service unavailable, error in client code...
 * 
 * @author isaac.salgueiro@discalis.com
 * 
 */
public class GeocodingRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 3826402781808735829L;

	public GeocodingRuntimeException(String message, Throwable t) {
		super(message, t);
	}
}
