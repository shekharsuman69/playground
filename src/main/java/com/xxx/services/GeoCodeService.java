package com.xxx.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.xxx.business.GeoCodeManager;
import com.xxx.exceptions.InvalidInputException;
import com.xxx.util.CommonUtil;
import com.xxx.util.MapType;
import com.xxx.vo.GeoRequest;
import com.xxx.vo.GeoResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * GeoCodeService serves as the entry point for geocode lookup. It can be used
 * for street address lookup or reverse lookup based on latitude and longitude.
 * The inputs are validated against the range.Jersey is the JAX-RS
 * implementation used. Currently, the response returned is GZIP compressed
 * using filters.
 * 
 * @author Shekhar Suman
 * @version 1.0
 * @since 2017-02-03
 *
 */
@Path("geocode")
public class GeoCodeService {

	private static final Logger LOGGER = Logger.getLogger(GeoCodeService.class);

	/**
	 * The method serves as the end point to perform the reverse geocode lookup
	 * against the input latitude and longitude. The user has a flexibility to
	 * choose between different map sources by providing the 'source' input. The
	 * possible values for 'source':['Google','MapQuest',Bing'].
	 * 
	 * @param input
	 *            request object
	 * @return Response object in JSON format, which comprises address details
	 */
	@POST
	@Path("v1/reverse")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReverseGeoCodeDetails(GeoRequest input) {
		GeoResponse response = null;
		long requestedTime = System.currentTimeMillis();
		GeoCodeManager gm = null;
		GeoRequest request = null;

		try {
			request = new GeoRequest(input);

			if (StringUtils.isEmpty(request.getLatitude()) || StringUtils.isEmpty(request.getLongitude())) {
				throw new InvalidInputException("latitude and longitude is mandatory input");
			}

			double latitude = CommonUtil.getDouble(request.getLatitude());
			double longitude = CommonUtil.getDouble(request.getLongitude());

			CommonUtil.validateLatitude(latitude);
			CommonUtil.validateLongitude(longitude);

			MapType mapType = MapType.parse(request.getSource());

			gm = new GeoCodeManager();
			response = gm.reverseLookup(latitude, longitude, mapType, requestedTime);

		} catch (Exception e) {
			LOGGER.error("Exception caught in getReverseGeoCodeDetails:" + e);
			response = handleError(e);
		}

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}

	/**
	 * The method serves as the end point to retrieve the last 10 successful
	 * reverse geocode lookup performed.
	 * 
	 * @return Response object in JSON format, which comprises address details
	 */
	@GET
	@Path("v1/meta")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReverseGeoCodeDetails() {
		try {
			return Response.ok(GeoCodeManager.getMeta(), MediaType.APPLICATION_JSON).build();

		} catch (Exception e) {
			LOGGER.error("Exception caught in getReverseGeoCodingDetails:" + e);
			return Response.ok(e.getMessage(), MediaType.APPLICATION_JSON).build();
		}
	}

	private GeoResponse handleError(Exception e) {
		GeoResponse response = new GeoResponse();
		response.setException(e.toString());
		response.setMessage(e.getMessage());
		return response;
	}

	/**
	 * Main method to test the services locally.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		GeoCodeManager gm = new GeoCodeManager();
		GeoRequest request = new GeoRequest();
		request.setLatitude("33.969601");
		request.setLongitude("-84.100033");
		// Put 'MapQuest' to test MapQuest response, 'Bing' to test response
		// from Microsoft Bing
		request.setSource("Google");
		MapType mapType = MapType.parse(request.getSource());
		double latitude = CommonUtil.getDouble(request.getLatitude());
		double longitude = CommonUtil.getDouble(request.getLongitude());
		GeoResponse response = gm.reverseLookup(latitude, longitude, mapType, System.currentTimeMillis());
		System.out.println("Response==>" + Response.ok(response, MediaType.APPLICATION_JSON).build());
	}

}
