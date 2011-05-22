package com.google.code.geocoder.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;


public class AppEngineUrlFetchServiceHttpFetcher implements HttpFetcher {

	@Override
	public String getResponseForUrl(String url) {
        URLFetchService urlFetchService = (URLFetchService) URLFetchServiceFactory.getURLFetchService(); 
        try {
        	HTTPRequest httpRequest = new HTTPRequest(new URL(url));
        
        	HTTPResponse response = urlFetchService.fetch(httpRequest);
        	if (response.getResponseCode() == HttpURLConnection.HTTP_OK) {
        		return readResponseBody(response);
        	}
        	return null;
        	
        } catch (Exception e) {
        	return null;
        }
    }
	
	@Override
	public void setProxy(String host, int port) {
		throw new UnsupportedOperationException();
	}
	
	private String readResponseBody(HTTPResponse response) {
       try {
    	   InputStream inputStream = new ByteArrayInputStream(response.getContent());
    	   InputStreamReader input = new InputStreamReader(inputStream, "UTF-8");    	   
    	   BufferedReader reader = new BufferedReader(input);
    	   
    	   try {
    		   StringBuilder result = new StringBuilder();       
    		   String line = null;
    		   while ((line = reader.readLine()) != null) {
    			   result.append(line);
    		   }
    		   return result.toString();
    		   
    	   } finally {
    		   inputStream.close();
    		   input.close();
    		   reader.close();    	   
    	   }
    	   
       } catch (Exception e) {
    	   return null;
       }
	}
	
}
