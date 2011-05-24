package com.google.code.geocoder.model;

import java.io.Serializable;

public class GeoAddress implements Serializable {	

	private static final long serialVersionUID = 1L;
	
	protected GeocoderLocationType locationType;
    protected String formattedAddress;
    protected LatLng location;
    protected LatLngBounds viewport;
}
