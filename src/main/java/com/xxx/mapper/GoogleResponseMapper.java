package com.xxx.mapper;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.xxx.vo.GeoResponse;

/**
 * The implementation of ResponseMapper interface, which transforms the string
 * response received from Google Maps API. Only few fields are extracted from
 * the full JSON returned back to the user.
 * 
 * @author Shekhar Suman
 * @version 1.0
 * @since 2017-02-03
 *
 */
public class GoogleResponseMapper implements ResponseMapper<GeoResponse> {

	private static final Logger LOGGER = Logger.getLogger(GoogleResponseMapper.class);

	@Override
	public GeoResponse mapRespone(String str) {
		GeoResponse response = null;
		try {
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(str);
			if (json != null) {
				JSONArray data = (JSONArray) json.get("results");
				if (data != null && !data.isEmpty()) {
					String address = (String) ((JSONObject) data.get(0)).get("formatted_address");
					if (address != null) {
						response = new GeoResponse();
						response.setAddress(address);
					}
				}
			}

		} catch (Exception e) {
			LOGGER.error("Error processing response from Google Maps:", e);
		}
		return response;
	}
}
