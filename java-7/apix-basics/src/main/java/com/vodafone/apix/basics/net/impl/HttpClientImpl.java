/**
 * 
 */
package com.vodafone.apix.basics.net.impl;

import java.io.IOException;
import java.net.URI;

import com.vodafone.apix.basics.ApixRuntimeException;
import com.vodafone.apix.basics.net.EHttpMethods;
import com.vodafone.apix.basics.net.IHttpClient;
import com.vodafone.apix.basics.net.IMethodResult;
import com.vodafone.apix.basics.net.RequestHeaders;
import com.vodafone.apix.basics.net.spi.IHttpClientSpi;
import com.vodafone.apix.basics.types.ITransferType;

/**
 * @author Rainer.Bieniek@vodafone.com
 *
 */
public class HttpClientImpl implements IHttpClient {

	private IHttpClientSpi spi;
	
	/**
	 * 
	 */
	public HttpClientImpl(IHttpClientSpi spi) {
		this.spi = spi;
	}

	/* (non-Javadoc)
	 * @see com.vodafone.apix.basics.net.IHttpClient#executeMethod(java.net.URI, com.vodafone.apix.basics.net.EHttpMethods, com.vodafone.apix.basics.net.RequestHeaders, java.lang.Object, com.vodafone.apix.basics.types.ITransferType, java.lang.Class, com.vodafone.apix.basics.types.ITransferType)
	 */
	@Override
	public <I, O> IMethodResult<O> executeMethod(URI uri, EHttpMethods method,
			RequestHeaders requestHeaders, I requestPayload,
			ITransferType requestTranserType, Class<O> responsePayloadType,
			ITransferType responseTransferType) throws IOException,
			ApixRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

}
