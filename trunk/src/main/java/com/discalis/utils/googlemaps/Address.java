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
	
	private String number;
	private String street;
	private String locality;
	private String province;
	private String state;
	private String country;
	private String postalCode;
	

	/**
	 * @return the state
	 * @since 1.0.1
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 * @since 1.0.1
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the number
	 * @since 1.0.1
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 * @since 1.0.1
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the street
	 * @since 1.0.1
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 * @since 1.0.1
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the locality
	 * @since 1.0.1
	 */
	public String getLocality() {
		return locality;
	}

	/**
	 * @param locality the locality to set
	 * @since 1.0.1
	 */
	public void setLocality(String locality) {
		this.locality = locality;
	}

	/**
	 * @return the province
	 * @since 1.0.1
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 * @since 1.0.1
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the country
	 * @since 1.0.1
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 * @since 1.0.1
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the postalCode
	 * @since 1.0.1
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 * @since 1.0.1
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * 
	 * 
	 * @param rawAddress Unnormalized address string. Add as much information as possible. 
	 */
	public Address(String rawAddress) {
		this.raw = rawAddress;
	}
	
	/**
	 * Unnormalized address string.
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
		sb.append("\n\tformatted: ");
		sb.append(formatted);
		sb.append("\n\tnumber: ");
		sb.append(number);
		sb.append("\n\tstreet: ");
		sb.append(street);
		sb.append("\n\tpostalCode: ");
		sb.append(postalCode);
		sb.append("\n\tlocality: ");
		sb.append(locality);
		sb.append("\n\tprovince: ");
		sb.append(province);
		sb.append("\n\tstate: ");
		sb.append(state);
		sb.append("\n\tcountry: ");
		sb.append(country);

		return sb.toString();
	}
}
