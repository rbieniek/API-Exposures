/**
 * 
 */
package com.vodafone.apix.basics.types;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Rainer.Bieniek@vodafone.com
 *
 */
public interface ITransferSerializer<T> {
	public void serialize(T obj, OutputStream os) throws IOException;
}
