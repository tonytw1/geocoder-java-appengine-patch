package com.google.code.geocoder.http;

public interface HttpFetcher {

	public String getResponseForUrl(String url);
	public void setProxy(String host, int port);
	
}
