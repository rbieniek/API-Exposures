/**
 * 
 */
package com.vodafone.apix.basics.net;

/**
 * Factory for generating HTTP client instances.  
 * 
 * @author Rainer.Bieniek@vodafone.com
 *
 */
public class HttpClientFactory {


	/**
	 * 
	 */
	private HttpClientFactory() {
	}

	private static HttpClientFactory _instance = new HttpClientFactory();
	
	public static final HttpClientFactory instance() {
		return _instance;
	}
}

