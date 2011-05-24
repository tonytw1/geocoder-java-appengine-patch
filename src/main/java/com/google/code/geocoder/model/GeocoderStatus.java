package com.google.code.geocoder.model;

import java.io.Serializable;

/**
 * @author <a href="mailto:panchmp@gmail.com">Michael Panchenko</a>
 */
public enum GeocoderStatus implements Serializable {
	
    ERROR,
    INVALID_REQUEST,
    OK,
    OVER_QUERY_LIMIT,
    REQUEST_DENIED,
    UNKNOWN_ERROR,
    ZERO_RESULTS;

    public String value() {
        return name();
    }

    public static GeocoderStatus fromValue(String v) {
        return valueOf(v);
    }

}