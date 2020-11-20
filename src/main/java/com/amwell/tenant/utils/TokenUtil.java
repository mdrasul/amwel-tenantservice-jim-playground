/**
 * Copyright 2020 American Well Systems
 * All rights reserved.
 *
 * It is illegal to use, reproduce or distribute
 * any part of this Intellectual Property without
 * prior written authorization from American Well.
 */

package com.amwell.tenant.utils;

import com.amwell.tenant.service.RequestHeaders;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_MOVED_TEMP;

/**
 * @author chan.suom on 8/6/20
 */
@Slf4j
public class TokenUtil {

	private static final String TENANT_ID = "AMWL-QA02";
	private String encounterId;
	private Config config;

	/**
	 * Constructor
	 *
	 * @throws IOException exception
	 */
	public TokenUtil() throws IOException {

		config = Config.getInstance();
		this.encounterId = UUID.randomUUID().toString();

	}

	/**
	 * Constructor for use when you want to associate
	 * multiple users to the same video conference
	 *
	 * @param encounterId String
	 * @throws IOException excpetion
	 */
	public TokenUtil(String encounterId) throws IOException {

		config = Config.getInstance();
		this.encounterId = encounterId;

	}

	/**
	 * Get the encounter id
	 *
	 * @return String
	 */
	public String getEncounterId() {

		return this.encounterId;

	}

	/**
	 * Set the encounter id
	 *
	 * @param encounterId String
	 */
	public void setEncounterId(String encounterId) {

		this.encounterId = encounterId;

	}

	/**
	 * Get the patient context launch URL
	 *
	 * @return String
	 */
	public String getPatientLaunchUrl() {

		return getLaunchUrl(true);

	}

	/**
	 * Get the provider context launch URL
	 *
	 * @return String
	 */
	public String getProviderLaunchUrl() {

		return getLaunchUrl(false);

	}

	/**
	 * Get the context launch URL
	 *
	 * @param isPatient boolean true to construct URL for patient; false for provider
	 * @return String
	 */
	private String getLaunchUrl(boolean isPatient) {

		String user = (isPatient) ? "Patient" : "Provider";
		String app = (isPatient) ? "patient" : "practitioner";
		log.debug("Generating {} launch Url...", user);

		//context object
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("encounterId", encounterId);

		String token = Base64.getEncoder().encodeToString(Helper.convertObjectToJsonString(hashMap).getBytes());

		String url = String
				.format("%s/app/%s/epic/%s/launch?iss=%s&launch=%s",
						config.getServiceUrl(),
						TENANT_ID,
						app,
						config.getIssUrl(),
						token);

		log.debug("{} launch URL: {}", user, url);

		return url;

	}

	/**
	 * Get the patient Authorization header value
	 *
	 * @return String
	 * @throws UnsupportedEncodingException exception
	 */
	public String getPatientAuthorizationToken() throws UnsupportedEncodingException {

		return getAuthorizationToken(true);

	}

	/**
	 * Get the provider Authorization header value
	 *
	 * @return String
	 * @throws UnsupportedEncodingException exception
	 */
	public String getProviderAuthorizationToken() throws UnsupportedEncodingException {

		return getAuthorizationToken(false);

	}

	/**
	 * Parse the Authorization from the series of HTTP calls to response "location" header URLs
	 *
	 * @param isPatient boolean true to get token for patient; false for provider
	 * @return String
	 * @throws UnsupportedEncodingException exception
	 */
	private String getAuthorizationToken(boolean isPatient) throws UnsupportedEncodingException {

		String user = (isPatient) ? "Patient" : "Provider";
		log.debug("Getting {} visit URL...", user);

		String launchUrl = (isPatient) ? getPatientLaunchUrl() : getProviderLaunchUrl();
		String authorizationUrl;
		String redirectUrl;
		String visitUrl;

		log.debug("Sending {} request to {}", user, launchUrl);

		Response getAuthResp =  given().log().all().
				redirects().follow(false).
				when().get(launchUrl);

		if (getAuthResp.statusCode() != HTTP_MOVED_TEMP) {

			throw new IllegalArgumentException("Server status code is NOT " + HTTP_MOVED_TEMP + "! Cannot parse authorization location header!");

		}

		authorizationUrl = getAuthResp.getHeader(RequestHeaders.LOCATION_HEADER);

		log.debug("Sending {} request to {}", user, authorizationUrl);

		Response getRedirectResp =  given().log().all().
				redirects().follow(false).
				when().get(authorizationUrl);

		if (getRedirectResp.statusCode() != 307) {

			throw new IllegalArgumentException("Server status code is NOT 307! Cannot parse redirect location header!");

		}

		redirectUrl = URLDecoder.decode(getRedirectResp.getHeader(RequestHeaders.LOCATION_HEADER), "UTF-8");

		log.debug("Sending {} request to {}", user, redirectUrl);

		Response getVisitResp =  given().log().all().
				redirects().follow(false).
				when().get(redirectUrl);

		log.debug("getVisitResp status: {}", getVisitResp.statusCode());

		if (getVisitResp.statusCode() != HTTP_MOVED_TEMP) {

			throw new IllegalArgumentException("Server status code is NOT 307! Cannot parse visit location header!");

		}

		visitUrl = getVisitResp.getHeader(RequestHeaders.LOCATION_HEADER);
		log.debug("{} visitUrl: {}", user, visitUrl);

		String token = "Bearer " + visitUrl.substring(visitUrl.indexOf("token=") + 6,  visitUrl.indexOf("&sessionId="));

		log.debug("{} {}: {}", user, RequestHeaders.AUTHORIZATION_HEADER, token);

		return token;

	}

}
