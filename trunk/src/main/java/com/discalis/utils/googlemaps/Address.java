package com.discalis.utils.googlemaps;

/**
 * Address information.
 * 
 * @author isaac.salgueiro@discalis.com
 *
 */
public class Address {

	private final String raw;
	private String formatted;
	
	private int latitude = 0;
	private int longitude = 0;
	
	/**
	 * 
	 * 
	 * @param rawAddress Unnormalized address string. Add as much information as possible. 
	 */
	public Address(String rawAddress) {
		this.raw = rawAddress;
	}
	
	/**
	 * Unnormalized address string. Add as much information as possible.
	 * 
	 * @return
	 */
	public String getRawAddress() {
		return raw;
	}

	/**
	 * This address latitude.
	 * 
	 * @return
	 */
	public int getLatitude() {
		return latitude;
	}

	/**
	 * This address latitude.
	 * 
	 * @param latitude
	 */
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	/**
	 * This address longitude.
	 * 
	 * @return
	 */
	public int getLongitude() {
		return longitude;
	}

	/**
	 * This address longitude.
	 * 
	 * @param longitude
	 */
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * Returns the formatted address.
	 * 
	 * @return Formatted address.
	 * @since 0.0.4
	 */
	public String getFormatted() {
		return formatted;
	}

	public void setFormatted(String formatted) {
		this.formatted = formatted;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(raw);
		sb.append(" (lat: ");
		sb.append(latitude);
		sb.append(" / long: ");
		sb.append(longitude);
		sb.append(")");
		return sb.toString();
	}
}
