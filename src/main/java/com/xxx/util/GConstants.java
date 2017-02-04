package com.xxx.util;

/**
 * This class encapsulates all the constant declaration used across application.
 * 
 * @author Shekhar Suman
 * @version 1.0
 * @since 2017-02-03
 *
 */
public class GConstants {

	/**
	 * Private constructor to avoid instantiation of this class. All the
	 * constants declared can be referenced directly.
	 */
	private GConstants() {

	}

	// Different map sources
	public static final String GOOGLE_MAP = "Google";
	public static final String MAP_QUEST = "MapQuest";
	public static final String MS_BING = "Bing";

	// Base URL for different map sources
	public static final String BING_PATH_PARAM = "<PATH_PARAM>";
	public static final String GOOGLE_BASE_URL = "https://maps.googleapis.com/maps/api/geocode/json?";
	public static final String MAPQUEST_BASE_URL = "http://www.mapquestapi.com/geocoding/v1/reverse?";
	public static final String BING_BASE_URL = "http://dev.virtualearth.net/REST/V1/Locations/" + BING_PATH_PARAM + "?o=json";

	// Consume key for different map sources
	public static final String GOOGLE_CONSUMER_KEY = "AIzaSyDsHM39-00RPTBATzzKVbLJ_J2CIsOvlio";
	public static final String MAPQUEST_CONSUMER_KEY = "DDtQZXyMg4VtojstiTUY4hq2936wUMHx";
	public static final String BING_CONSUMER_KEY = "Alaa8QkUfIdBfDGTP4ziCQgfolx63OtlKbzghkheeFXe9eFdzXmwFicoYiWn_tXK";

	// Name of the parameter different map sources accepts for reverse geocoding
	public static final String GOOGLE_PARAM = "latlng";
	public static final String MAPQUEST_PARAM = "location";
	public static final String BING_PARAM = "";

	// Additional constants
	public static final String MAPQUEST_SECRET_KEY = "f3feMeu24GKF32j0";
	public static final int MAX_SIZE = 10;

}
