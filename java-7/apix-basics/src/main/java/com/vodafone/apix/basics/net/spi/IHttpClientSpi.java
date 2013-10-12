/**
 * 
 */
package com.vodafone.apix.basics.net.spi;

import java.io.IOException;
import java.net.URI;

import com.vodafone.apix.basics.ApixRuntimeException;
import com.vodafone.apix.basics.net.EHttpMethods;
import com.vodafone.apix.basics.net.IMethodResult;
import com.vodafone.apix.basics.net.RequestHeaders;
import com.vodafone.apix.basics.types.ITransferDeserializer;
import com.vodafone.apix.basics.types.ITransferSerializer;

/**
 * This interface is implemented by any concrete client implementation which does the actual HTTP callout.

 * @author Rainer.Bieniek@vodafone.com
 *
 */
public interface IHttpClientSpi {
	public <I,O> IMethodResult<O> executeMethod(URI uri,
			EHttpMethods method, 
			RequestHeaders requestHeaders,
			String contentType, 
			ITransferSerializer<I> requestPayloadSerializer,
			String accept,
			ITransferDeserializer<O> responsePayloadDeserializer) throws IOException, ApixRuntimeException; 

}
