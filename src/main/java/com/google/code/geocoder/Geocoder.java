package com.google.code.geocoder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.code.geocoder.http.AppEngineUrlFetchServiceHttpFetcher;
import com.google.code.geocoder.http.HttpFetcher;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.LatLng;
import com.google.code.geocoder.model.LatLngBounds;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author <a href="mailto:panchmp@gmail.com">Michael Panchenko</a>
 */
public class Geocoder {
    private Log log = LogFactory.getLog(Geocoder.class);

    private static final String GEOCODE_REQUEST_URL = "http://maps.googleapis.com/maps/api/geocode/json?sensor=false";
    private static HttpFetcher httpFetcher = new AppEngineUrlFetchServiceHttpFetcher();
    
    public Geocoder() {
    }
    
    public GeocodeResponse geocode(final GeocoderRequest geocoderRequest) {
        try {
            final String url = getURL(geocoderRequest);    
            final String jsonResponse = httpFetcher.getResponseForUrl(url);
            final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            return gson.fromJson(jsonResponse, GeocodeResponse.class);
            
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }
    
	public HttpFetcher getHttpFetcher() {
		return httpFetcher;
	}
	
    protected String getURL(final GeocoderRequest geocoderRequest) throws UnsupportedEncodingException {
        if (log.isTraceEnabled()) {
            log.trace(geocoderRequest);
        }
        final String address = geocoderRequest.getAddress();
        final LatLngBounds bounds = geocoderRequest.getBounds();
        final String language = geocoderRequest.getLanguage();
        final String region = geocoderRequest.getRegion();
        final LatLng location = geocoderRequest.getLocation();

        String urlString = GEOCODE_REQUEST_URL;
        if (StringUtils.isNotBlank(address)) {
            urlString += "&address=" + URLEncoder.encode(address, "UTF-8");
        } else if (location != null) {
            urlString += "&latlng=" + URLEncoder.encode(location.toUrlValue(), "UTF-8");
        } else {
            throw new IllegalArgumentException("Address or location not defined");
        }
        if (StringUtils.isNotBlank(language)) {
            urlString += "&language=" + URLEncoder.encode(language, "UTF-8");
        }
        if (StringUtils.isNotBlank(region)) {
            urlString += "&region=" + URLEncoder.encode(region, "UTF-8");
        }
        if (bounds != null) {
            urlString += "&bounds=" + URLEncoder.encode(bounds.getSouthwest().toUrlValue() + "|" + bounds.getNortheast().toUrlValue(), "UTF-8");
        }

        if (log.isTraceEnabled()) {
            log.trace("url: " + urlString);
        }
        return urlString;
    }

}