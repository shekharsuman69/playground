package com.xxx.mapper;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.xxx.vo.GeoResponse;

/**
 * The implementation of ResponseMapper interface, which transforms the string
 * response received from MapQuest API. Only few fields are extracted from the
 * full JSON returned back to the user.
 * 
 * @author Shekhar Suman
 * @version 1.0
 * @since 2017-02-03
 * 
 */
public class MapQuestResponseMapper implements ResponseMapper<GeoResponse> {
	private static final Logger LOGGER = Logger.getLogger(MapQuestResponseMapper.class);

	@Override
	public GeoResponse mapRespone(String str) {
		GeoResponse meta = null;
		try {
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(str);
			if (json != null) {
				JSONArray results = (JSONArray) json.get("results");
				if (results != null && !results.isEmpty()) {
					JSONObject result = (JSONObject) results.get(0);
					if (result != null) {
						JSONArray locations = (JSONArray) result.get("locations");
						if (locations != null && !locations.isEmpty()) {
							JSONObject location = (JSONObject) locations.get(0);
							if (location != null) {
								String address = (String) (location.get("street")) + ", "
										+ (String) location.get("adminArea5") + ", "
										+ (String) location.get("adminArea3") + " "
										+ (String) location.get("postalCode");
								meta = new GeoResponse();
								meta.setAddress(address);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error processing response from MapQuest:", e);
		}
		return meta;
	}
}
