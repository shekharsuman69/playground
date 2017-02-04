package com.xxx.vo;

import com.xxx.util.GConstants;

/**
 * GeoRequest is the value object to capture the POST request for geocode
 * lookup. The value object comprises of latitude, longitude and source.. Any
 * new post parameter should be added here.
 * 
 * @author Shekhar Suman
 * @version 1.0
 * @since 2017-02-03
 *
 */
public class GeoRequest {

	private String latitude;
	private String longitude;
	private String source;

	/**
	 * Default constructor
	 */
	public GeoRequest() {
		super();
	}

	/**
	 * Custom constructor to build the object using POST request parameters.
	 * 
	 * @param input
	 *            POST request parameter
	 */
	public GeoRequest(GeoRequest input) {
		this.latitude = input.getLatitude();
		this.longitude = input.getLongitude();
		this.source = input.getSource() == null ? GConstants.GOOGLE_MAP : input.getSource();
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
