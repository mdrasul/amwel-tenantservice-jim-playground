/*
 * Copyright 2020 American Well Systems
 * All rights reserved.
 *
 * It is illegal to use, reproduce or distribute
 * any part of this Intellectual Property without
 * prior written authorization from American Well.
 */

package com.amwell.tenant.exception;

/**
 * Exception thrown when framework is not properly configured
 *
 * @author David.Desmond
 */
public class FrameworkConfigurationException extends RuntimeException {

    public FrameworkConfigurationException(String msg) {
        super(msg);
    }

}
