package com.xxx.mapper;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.xxx.vo.GeoResponse;

/**
 * The implementation of ResponseMapper interface, which transforms the string
 * response received from Bing Maps API. Only few fields are extracted from the
 * full JSON returned back to the user.
 * 
 * @author Shekhar Suman
 * @version 1.0
 * @since 2017-02-03
 *
 */
public class BingResponseMapper implements ResponseMapper<GeoResponse> {

	private static final Logger LOGGER = Logger.getLogger(BingResponseMapper.class);

	@Override
	public GeoResponse mapRespone(String str) {
		GeoResponse meta = null;
		try {
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(str);
			if (json != null) {
				JSONArray resourceSets = (JSONArray) json.get("resourceSets");
				if (resourceSets != null && !resourceSets.isEmpty()) {
					JSONObject resourceSet = (JSONObject) resourceSets.get(0);
					if (resourceSet != null) {
						JSONArray resources = (JSONArray) resourceSet.get("resources");
						if (resources != null && !resources.isEmpty()) {
							JSONObject resource = (JSONObject) resources.get(0);
							if (resource != null) {
								String address = (String) (resource.get("name"));
								meta = new GeoResponse();
								meta.setAddress(address);
							}
						}
					}
				}
			}

		} catch (Exception e) {
			LOGGER.error("Error processing response from Bing Maps:", e);
		}
		return meta;
	}

}
