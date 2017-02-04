package com.xxx.util;

import com.xxx.exceptions.InvalidInputException;

/**
 * This class is a common utility class. All the utility methods that are used
 * across application is declared here.
 * 
 * @author Shekhar Suman
 * @version 1.0
 * @since 2017-02-03
 *
 */
public class CommonUtil {

	/**
	 * Private constructor to avoid instantiation of this class. All the methods
	 * declared can be referenced directly.
	 */
	private CommonUtil() {

	}

	/**
	 * The method is used to format the input String into double.
	 * 
	 * @param input
	 * @return
	 * @throws InvalidInputException
	 */
	public static double getDouble(String input) throws InvalidInputException {
		try {
			return Double.parseDouble(input);
		} catch (NumberFormatException e) {
			throw new InvalidInputException("Invalid input:" + input);
		}
	}

	/**
	 * The method is used for boundary check against the input latitude.
	 * Inclusive boundary for latitude - [-90, 90]
	 * 
	 * @param latitude
	 * @throws InvalidInputException
	 */
	public static void validateLatitude(double latitude) throws InvalidInputException {
		if (latitude < -90 || latitude > 90) {
			throw new InvalidInputException(
					"Invalid input, Latitude must be between -90 and 90 degrees inclusive." + latitude);
		}
	}

	/**
	 * The method is used for boundary check against the input longitude.
	 * Inclusive boundary for latitude - [-180, 180]
	 * 
	 * @param longitude
	 * @throws InvalidInputException
	 */
	public static void validateLongitude(double longitude) throws InvalidInputException {
		if (longitude < -180 || longitude > 180) {
			throw new InvalidInputException(
					"Invalid input, Longitude must be between -90 and 90 degrees inclusive." + longitude);
		}
	}
}
