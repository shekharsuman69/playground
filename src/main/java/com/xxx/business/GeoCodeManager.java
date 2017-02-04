package com.xxx.business;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.xxx.util.GConstants;
import com.xxx.util.MapType;
import com.xxx.vo.GeoResponse;

/**
 * Business logic wrapper class which makes the reverse lookup call to different
 * map API's. It uses different mapper implementations to format the raw
 * response. Additionally, it stores the last 10 successful response from
 * different map API's. The last 10 is stored in the form of meta, which is
 * backed by ArrayBlockingQueue. ArrayBlockingQueue offers a fixed size
 * collection backed by Array. ArrayBlockingQueue is thread safe.
 * 
 * @author Shekhar Suman
 * @version 1.0
 * @since 2017-02-03
 */
public class GeoCodeManager {

	private static final Logger LOGGER = Logger.getLogger(GeoCodeManager.class);
	private static ArrayBlockingQueue<GeoResponse> meta = new ArrayBlockingQueue<>(10);

	/**
	 * The method perform reverse geocode lookup against differnt map sources.
	 * 
	 * @param latitude
	 *            user input latitude
	 * @param longitude
	 *            user input longitude
	 * @param mapType
	 *            user input map source
	 * @param requestedTime
	 *            time user request was received
	 * @return GeoResponse pojo
	 */
	public GeoResponse reverseLookup(double latitude, double longitude, MapType mapType, long requestedTime) {
		GeoResponse gResponse = null;
		try {
			String targetUrl;
			String paramValue = latitude + "," + longitude;
			if (mapType.getType().equals(GConstants.MS_BING)) {
				targetUrl = mapType.getBaseUrl().replaceAll(GConstants.BING_PATH_PARAM, paramValue) + "&key="
						+ mapType.getKey();
			} else {
				targetUrl = mapType.getBaseUrl() + "key=" + mapType.getKey() + "&" + mapType.getParam() + "="
						+ paramValue;
			}

			Client client = Client.create();
			WebResource webResource = client.resource(targetUrl);
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				gResponse = new GeoResponse();
				gResponse.setLatitude(latitude);
				gResponse.setLongitude(longitude);
				gResponse.setStatus(response.getStatus());
				gResponse.setRequestedTime(requestedTime);
			} else {
				String output = response.getEntity(String.class);
				gResponse = (GeoResponse) mapType.getMapper().mapRespone(output);
				if (gResponse != null) {
					gResponse.setLatitude(latitude);
					gResponse.setLongitude(longitude);
					gResponse.setStatus(response.getStatus());
					gResponse.setRequestedTime(requestedTime);
					setMeta(gResponse);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Error retrieving reverse Geocoding", e);
		}
		return gResponse;
	}

	private static void setMeta(GeoResponse response) {
		if (meta.size() >= GConstants.MAX_SIZE) {
			meta.poll();
			meta.offer(response);
		} else {
			meta.offer(response);
		}
	}

	public static ArrayBlockingQueue<GeoResponse> getMeta() {
		return meta;
	}

}
