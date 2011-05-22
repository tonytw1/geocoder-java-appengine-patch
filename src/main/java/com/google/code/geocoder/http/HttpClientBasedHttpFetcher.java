package com.google.code.geocoder.http;

import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpClientBasedHttpFetcher implements HttpFetcher {

    private static final HttpClient HTTP_CLIENT = new HttpClient(new MultiThreadedHttpConnectionManager());

	@Override
	public String getResponseForUrl(String url) {
		final GetMethod getMethod = new GetMethod(url);
		try {
			HTTP_CLIENT.executeMethod(getMethod);			
			final Reader reader = new InputStreamReader(getMethod.getResponseBodyAsStream(), getMethod.getResponseCharSet());
			
			final StringBuilder response = new StringBuilder();
			int read = reader.read();
			while(read > 0) {
				response.append(Character.toChars(read));
				read = reader.read();
			}
			return response.toString();
			
		} catch (Exception e) {
			return null;
			
		} finally {
			getMethod.releaseConnection();			
		}
	}

	@Override
	public void setProxy(String host, int port) {
		HTTP_CLIENT.getHostConfiguration().setProxy(host, port);		
	}
    
}
