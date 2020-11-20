/**
 * Copyright 2020 American Well Systems
 * All rights reserved.
 *
 * It is illegal to use, reproduce or distribute
 * any part of this Intellectual Property without
 * prior written authorization from American Well.
 */

package com.amwell.tenant;

import com.amwell.tenant.utils.Config;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

/**
 * @author chan.suom on 8/12/20
 */
@Slf4j
public class BaseTest {

	protected static Config config;

	public BaseTest() throws IOException {

		config = Config.getInstance();

	}

	@BeforeSuite
	public void baseTestStart() {

		log.info("This is the BaseTest BeforeSuite...");

	}

	@AfterSuite
	protected void baseTestTearDown() {

		log.info("This is the BaseTest AfterSuite...");

	}

}
