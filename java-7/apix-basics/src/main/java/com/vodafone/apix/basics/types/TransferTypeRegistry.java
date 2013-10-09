/**
 * 
 */
package com.vodafone.apix.basics.types;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Rainer.Bieniek@vodafone.com
 *
 */
public class TransferTypeRegistry {

	private Set<Class<? extends ITransferType>> protectedMappings = new HashSet<>();
	private Map<Class<? extends ITransferType>, String> mappings = new HashMap<>();
	
	/**
	 * 
	 */
	private TransferTypeRegistry() {
		protectedMappings.add(ITransferType.class);
		protectedMappings.add(IJsonTransferType.class);
		protectedMappings.add(IWWWUrlFormEncodedTransferType.class);
		
		mappings.put(ITransferType.class, "application/octet-stream");
		mappings.put(IJsonTransferType.class, "application/json");
		mappings.put(IWWWUrlFormEncodedTransferType.class, "application/x-www-form-urlencoded");
	}

	private static TransferTypeRegistry _instance = new TransferTypeRegistry();
	
	public static TransferTypeRegistry instance() {
		return _instance;
	}
	
	public String mimeTypeForTransfer(Class<? extends ITransferType> type) {
		String mimeType = null;
		
		if(type != null) {
			mimeType = mappings.get(type);
		}

		return mimeType;
	}
	
	public void registerMapping(Class<? extends ITransferType> type, String mimeType) {
		if(!protectedMappings.contains(type)) {
			mappings.put(type, mimeType);
		}
	}
	
	public void unregisterMapping(Class<? extends ITransferType> type) {
		if(!protectedMappings.contains(type)) {
			mappings.remove(type);
		}
	}
}
