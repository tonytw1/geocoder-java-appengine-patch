package com.google.code.geocoder.model;

import java.io.Serializable;

/**
 * @author <a href="mailto:panchmp@gmail.com">Michael Panchenko</a>
 */
public enum GeocoderLocationType implements Serializable {
		
    APPROXIMATE,
    GEOMETRIC_CENTER,
    RANGE_INTERPOLATED,
    ROOFTOP;

    public String value() {
        return name();
    }

    public static GeocoderLocationType fromValue(String v) {
        return valueOf(v);
    }
}