/**
 * 
 */
package com.vodafone.apix.basics.net.spi;

import com.vodafone.apix.basics.ApixRuntimeException;
import com.vodafone.apix.basics.net.HttpClientFactory;

/**
 * This interface must be implemented by any provider for {@link IHttpClientSpi} instances.
 * This approach is chosen due to the numerous ways in the Java ecosystem to manage object (java services,
 * OSGi, CDI, Spring and many more)
 * 
 * The {@link HttpClientFactory} instance uses the the java service lookup to find an instance of this
 * provider interface
 * 
 * @author Rainer.Bieniek@vodafone.com
 *
 */
public interface IHttpClientSpiProvider {
	/**
	 * This method is called to obtain a {@link IHttpClientSpi} instance
	 * 
	 * @return an {@link IHttpClientSpi} instance
	 * @throws ApixRuntimeException provider error
	 */
	public IHttpClientSpi provideClientInstance() throws ApixRuntimeException;
}
