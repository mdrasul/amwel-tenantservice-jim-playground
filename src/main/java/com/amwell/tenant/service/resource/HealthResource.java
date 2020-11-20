/**
 * Copyright 2020 American Well Systems
 * All rights reserved.
 *
 * It is illegal to use, reproduce or distribute
 * any part of this Intellectual Property without
 * prior written authorization from American Well.
 */

package com.amwell.tenant.service.resource;

import com.amwell.tenant.service.RequestHeaders;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * @author corey.zwart on 8/26/20
 */
@Slf4j
public class HealthResource extends BaseResource {

	private static final String HEALTH_CHECK_ENDPOINT = "/healthcheck";
	private Optional<String> healthCheckStatus = Optional.empty();

	/**
	 * Constructor
	 *
	 * @throws IOException exception
	 */
	public HealthResource() throws IOException {
	}

	/**
	 * Request to get health status of service layer
	 * Success server response example:
	 * {"status":"UP"}
	 *
	 * @param requestHeaders RequestHeaders
	 * @return Response 200/OK
	 */
	public Response getHealthCheck(RequestHeaders requestHeaders) {

		log.debug("Sending request for service health status...");

		Response resp = given().log().all().
				headers(requestHeaders.getRequestHeaders()).
				when().get(HEALTH_CHECK_ENDPOINT);

		if (resp.statusCode() == HTTP_OK) {

			this.healthCheckStatus = Optional.of(resp.path("status"));

		}

		return resp;

	}
	/**
	 * Get the health status of video lite
	 *
	 * @return String
	 */
	public String getStatus() {

		log.debug("Getting the health status...");
		return this.healthCheckStatus.orElseThrow(() -> new IllegalArgumentException("Health status is NOT set!"));

	}

}
