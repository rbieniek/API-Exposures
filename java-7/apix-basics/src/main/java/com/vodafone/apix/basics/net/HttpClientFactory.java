/**
 * 
 */
package com.vodafone.apix.basics.net;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vodafone.apix.basics.ApixRuntimeException;
import com.vodafone.apix.basics.net.impl.HttpClientImpl;
import com.vodafone.apix.basics.net.spi.IHttpClientSpi;
import com.vodafone.apix.basics.net.spi.IHttpClientSpiProvider;

/**
 * Factory for generating HTTP client instances.  
 * 
 * @author Rainer.Bieniek@vodafone.com
 *
 */
public class HttpClientFactory {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientFactory.class);

	private List<IHttpClientSpiProvider> providers = new LinkedList<>();

	/**
	 * 
	 */
	private HttpClientFactory() {
		try {
			Iterator<IHttpClientSpiProvider> loaderIt = ServiceLoader.load(IHttpClientSpiProvider.class).iterator();
			
			while(loaderIt.hasNext())
				providers.add(loaderIt.next());
		} catch(Exception e) {
			logger.error("failed to initialize ", e);
		}
	}

	private static HttpClientFactory _instance = null;
	
	/**
	 * Obtain the singular instance. The instance creation is delayed until the instance is requested.
	 * 
	 * @return
	 */
	public static final HttpClientFactory instance() {
		if(_instance == null)
			_instance = new HttpClientFactory();
		
		return _instance;
	}

	/**
	 * create a client instance.
	 * 
	 * @return an initialized HTTP client instance.
	 * @throws ApixRuntimeException throw if a client instance cannot be created.
	 */
	public IHttpClient createClient() throws ApixRuntimeException {
		IHttpClient httpClient = null;
		
		for(IHttpClientSpiProvider provider : providers) {
			try {
				IHttpClientSpi spi = provider.provideClientInstance();
				
				if(spi != null) {
					httpClient = new HttpClientImpl(spi);
					break;
				}
			} catch(Exception e) {
				logger.info("failed to create HTTP client", e);
			}
		}
		
		if(httpClient == null)
			throw new ApixRuntimeException("Cannot create HTTP client");
		
		return httpClient;
	}
}

