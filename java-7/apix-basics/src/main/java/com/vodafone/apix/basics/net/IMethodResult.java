/**
 * 
 */
package com.vodafone.apix.basics.net;

import java.net.URI;
import java.util.Date;

/**
 * This interface models the result of a HTTP method call
 * 
 * @author Rainer.Bieniek@vodafone.com
 *
 */
public interface IMethodResult<T> {
	/**
	 * get the status code
	 * @return the RFC 2616 HTTP status code
	 */
	public int getStatusCode();
	
	/**
	 * the status line
	 * 
	 * @return the RFC 2616 response status line
	 */
	public String getStatusLine();
	
	/**
	 * the result payload
	 * 
	 * @return the result payload if any was returned by the call. <code>null</code> otherwise
	 */
	public T getPayload();
	
	/**
	 * the location response header value
	 * 
	 * @return the value of the location header if returned. <code>null</code> otherwise
	 */
	public URI getLocation();
	
	/**
	 * the timestamp when the resource was modified the last time.
	 * @return
	 */
	public Date getLastModified();
}
