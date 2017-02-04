package com.xxx.vo;

import java.io.Serializable;

/**
 * GeoResponse is the response object returned to the user in the form of JSON.
 * The response consists of - latitude, longitude, resolved address, status
 * code, exception, exception message and user requested time.Any new output
 * needs to be added here and populated accordingly.
 * 
 * @author Shekhar Suman
 * @version 1.0
 * @since 2017-02-03
 *
 */
public class GeoResponse implements Serializable {

	private static final long serialVersionUID = 201702030630L;
	private double latitude;
	private double longitude;
	private String address;
	private long requestedTime;
	private int status;
	private String exception;
	private String message;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getRequestedTime() {
		return requestedTime;
	}

	public void setRequestedTime(long requestedTime) {
		this.requestedTime = requestedTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
