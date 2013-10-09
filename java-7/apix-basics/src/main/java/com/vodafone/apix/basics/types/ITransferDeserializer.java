/**
 * 
 */
package com.vodafone.apix.basics.types;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Rainer.Bieniek@vodafone.com
 *
 */
public interface ITransferDeserializer<T> {
	public T deserialize(InputStream is) throws IOException;
}
