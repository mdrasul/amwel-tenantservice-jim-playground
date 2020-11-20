/**
 * Copyright 2020 American Well Systems
 * All rights reserved.
 *
 * It is illegal to use, reproduce or distribute
 * any part of this Intellectual Property without
 * prior written authorization from American Well.
 */

package com.amwell.tenant.utils;

import com.amwell.tenant.exception.FrameworkConfigurationException;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

/**
 * General configurations mapped to the config.yaml file. If you add a key/value property or object to the
 * config.yaml, be sure to add a method here to parse the data. If the config should be handled on the fly,
 * e.g. continuous integration, include a system property for it in build.gradle under the tasks.withType(Test) section
 *
 * This config is where the magic lives in tying all moving parts of the framework together. Use what is relevant for
 * your project.
 *
 * @author chan.suom on 7/14/20
 */
@Slf4j
public class Config {

	private static Config instance = null;
	private JsonPath configJson;
	private String serviceUrl;


	/**
	 * Constructor
	 *
	 * @throws IOException exception
	 */
	public Config() throws IOException {

		configJson = parseConfigData();
		setServiceUrl();

	}

	/**
	 * Explicitly instantiate singleton Config object if one does not exist
	 *
	 * @return Config object
	 * @throws IOException exception
	 */
	public static Config getInstance() throws IOException {

		if (instance == null) {

			instance = new Config();

		}

		return instance;

	}

	/**
	 * Parses config.yaml and returns HashMap of file
	 *
	 * @return JsonPath
	 * @throws IOException exception
	 */
	private JsonPath parseConfigData() throws IOException {

		Gson gson = new Gson();
		Yaml yaml = new Yaml();
		HashMap<String, Object> configData;

		final String defaultFileName = "src/test/resources/config.yaml";

		log.debug("Locating configuration file, \"{}\"...", defaultFileName);

		try (BufferedReader reader = new BufferedReader(new FileReader(defaultFileName))) {

			log.debug("Configuration file, \"{}\" found. Parsing yaml file...", defaultFileName);
			configData = yaml.load(reader);

		} catch (IOException ex) {

			throw new IOException(ex.getMessage());

		}

		return JsonPath.given(gson.toJson(configData));

	}

	/*********************************************************************************************
	 * 								SERVICE SPECIFIC CONFIGS BEGIN                         		 *
	 *********************************************************************************************/

	/**
	 * Set the service URL from config.yaml or Gradle command line argument for Continuous Integration testing
	 */
	private void setServiceUrl() {

		log.debug("Setting service URL...");

		String url = System.getProperty( "serviceUrl", "");

		if (url.isEmpty()) {

			log.debug("Setting service url from configuration file...");

			url = Optional.ofNullable(configJson.getString("serviceUrl")).orElseThrow(
					() -> new FrameworkConfigurationException("\"serviceUrl\" value is required but not found in configuration file!"));

		} else {

			log.debug("Setting service url from command line argument \"serviceUrl\"...");

		}

		this.serviceUrl = url;

		log.debug("serviceUrl: {}", this.serviceUrl);

	}

	/**
	 * Get the service URL from config.yaml
	 *
	 * @return String url
	 */
	public String getServiceUrl() {

		log.debug("serviceUrl: {}", serviceUrl);

		return this.serviceUrl;

	}

	/**
	 * Get the ISS URL from config.yaml
	 *
	 * @return String url
	 */
	public String getIssUrl() {

		log.debug("Getting ISS URL from configuration file...");

		String url = Optional.ofNullable(configJson.getString("issUrl")).orElseThrow(
				() -> new FrameworkConfigurationException("\"issUrl\" value is required but not found in configuration file!"));

		log.debug("ISS URL: {}", url);

		return url;

	}

	/**
	 * Get the OAuth URL from config.yaml
	 *
	 * @return String url
	 */
	public String getOAuthUrl() {

		log.debug("Getting OAuth URL from configuration file...");

		String url = Optional.ofNullable(configJson.getString("oauthUrl")).orElseThrow(
				() -> new FrameworkConfigurationException("\"oauthUrl\" value is required but not found in configuration file!"));

		log.debug("OAuth URL: {}", url);

		return url;

	}

	/*********************************************************************************************
	 * 								SERVICE SPECIFIC CONFIGS END                         		 *
	 *********************************************************************************************/

}
