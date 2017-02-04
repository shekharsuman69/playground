package com.xxx.util;

import org.apache.log4j.Logger;

import com.xxx.exceptions.InvalidInputException;
import com.xxx.mapper.BingResponseMapper;
import com.xxx.mapper.GoogleResponseMapper;
import com.xxx.mapper.MapQuestResponseMapper;
import com.xxx.mapper.ResponseMapper;

/**
 * This class is used as an enumeration holder for different map sources that
 * can be used for reverse geocoding.Ex: Google, MapQuest, Microsoft Bing. In
 * addition the base URL, consumer key, parameter to be used and response mapper
 * is initialized in this class.
 * 
 * @author Shekhar Suman
 * @version 1.0
 * @since 2017-02-03
 *
 */
public enum MapType {

	GOOGLE(GConstants.GOOGLE_MAP, GConstants.GOOGLE_BASE_URL, GConstants.GOOGLE_CONSUMER_KEY, GConstants.GOOGLE_PARAM,	new GoogleResponseMapper()),
	MAPQUEST(GConstants.MAP_QUEST, GConstants.MAPQUEST_BASE_URL, GConstants.MAPQUEST_CONSUMER_KEY, GConstants.MAPQUEST_PARAM, new MapQuestResponseMapper()),
	BING(GConstants.MS_BING, GConstants.BING_BASE_URL, GConstants.BING_CONSUMER_KEY, GConstants.BING_PARAM, new BingResponseMapper());

	private static final Logger LOGGER = Logger.getLogger(MapType.class);
	private String type;
	private String baseUrl;
	private String key;
	private String param;
	private ResponseMapper<?> mapper;

	MapType(String type, String baseUrl, String key, String param, ResponseMapper<?> mapper) {
		this.type = type;
		this.baseUrl = baseUrl;
		this.key = key;
		this.param = param;
		this.mapper = mapper;
	}

	public String getType() {
		return type;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getKey() {
		return key;
	}

	public String getParam() {
		return param;
	}

	public ResponseMapper<?> getMapper() {
		return mapper;
	}

	/**
	 * The enum is initialized based on the input source.
	 * 
	 * @param source
	 *            user input source
	 * @return MapType enumeration object
	 * @throws InvalidInputException
	 */
	public static MapType parse(String source) throws InvalidInputException {
		for (MapType mapType : MapType.values()) {
			if (mapType.type.equals(source)) {
				return mapType;
			}
		}
		LOGGER.error("Invalid input for type:" + source);
		throw new InvalidInputException("Invalid input for map source:" + source);
	}
}
