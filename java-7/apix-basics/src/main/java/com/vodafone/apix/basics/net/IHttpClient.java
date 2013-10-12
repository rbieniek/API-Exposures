/**
 * 
 */
package com.vodafone.apix.basics.net;

import java.io.IOException;
import java.net.URI;

import com.vodafone.apix.basics.ApixRuntimeException;
import com.vodafone.apix.basics.types.ITransferType;

/**
 * Interface definition of the HTT client
 * 
 * @author Rainer.Bieniek@vodafone.com
 *
 */
public interface IHttpClient {
	/**
	 * Execute a HTTP method request onto the APIX gateway
	 * @param uri the request URI onto which is acted. Must not be <code>null</code>
	 * @param method the request method to be executed. Must not be <code>null</code>
	 * @param requestHeaders headers to be set on the request. May be <code>null</code>
 	 * @param requestPayload payload to be sent to the remote APIX gateway.
 	 * <ul>
 	 * <li>If the request method is {@link EHttpMethods#POST} or  {@link EHttpMethods#PUT} then this value must be <code>not null</code></li>
 	 * <li>If the request method is {@link EHttpMethods#GET} or  {@link EHttpMethods#DELETE} then this value must be <code>null</code></li>
 	 * </ul>
	 * @param requestTranserType the request payload transfer type
 	 * <ul>
 	 * <li>If the request method is {@link EHttpMethods#POST} or  {@link EHttpMethods#PUT} then this value must be <code>not null</code></li>
 	 * <li>If the request method is {@link EHttpMethods#GET} or  {@link EHttpMethods#DELETE} then this value must be <code>null</code></li>
 	 * </ul>
	 * @param responsePayloadType the response payload type. The payload conversion is only attempted if this parameter is <code>non-null</code>
	 * @param responseTransferType the response payload transfer type. The payload conversion is only attempted if this parameter is <code>non-null</code>
	 * <ul>
	 * <li>If this value is given, it is converted into the value for the 'Accept' request header with a default value of <code>application/octet-stream</code>. 
	 * <li>If this value is not given, the 'Accept' header is generated based on the given HTTP method
	 * <ul>
	 * <li>If the HTTP method <code>GET</GET> is used then the default value <code>application/octet-stream</code> is chosen and the 'Accept' header is generated</li>
	 * <li>For any other HTTP methods, the 'Accept' header is not generated</li>
	 * </ul>
	 * </li>
	 * </ul>
	 * @return the method result entity. 
	 * @throws IOException thrown if the network connectivity between the local client and the remote APIX gateway fails
	 * @throws APixRuntimeException thrown with within client-code.
	 */
	public <I,O> IMethodResult<O> executeMethod(URI uri,
			EHttpMethods method, 
			RequestHeaders requestHeaders,
			I requestPayload, 
			ITransferType requestTranserType, 
			Class<O> responsePayloadType, 
			ITransferType responseTransferType) throws IOException, ApixRuntimeException; 
}
