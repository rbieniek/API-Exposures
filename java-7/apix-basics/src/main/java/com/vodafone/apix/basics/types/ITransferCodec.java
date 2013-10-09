/**
 * 
 */
package com.vodafone.apix.basics.types;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This interface is 
 * 
 * @author Rainer.Bieniek@vodafone.com
 *
 */
public interface ITransferCodec {
	boolean isSerilizationSupported(Class<?> objectType, Class<? extends ITransferType> transferType);
	boolean isDeserilizationSupported(Class<?> objectType, Class<? extends ITransferType> transferType);

	void serializeObject(Object obj, OutputStream os, Class<? extends ITransferType> transferType) throws IOException;
	Object deserializeObject(InputStream is, Class<? extends ITransferType> transferType) throws IOException;
}
