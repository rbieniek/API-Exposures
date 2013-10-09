/**
 * 
 */
package com.vodafone.apix.basics.types;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rainer.Bieniek@vodafone.com
 *
 */
public class TransferTypeRegistryTest {

	public interface ITestTransferType extends ITransferType {
		
	}
	public interface ITest2TransferType extends ITransferType {
		
	}
	
	@Test
	public void knownMappings() {
		Assert.assertEquals("application/octet-stream", 
				TransferTypeRegistry.instance().mimeTypeForTransfer(ITransferType.class));
		Assert.assertEquals("application/json", 
				TransferTypeRegistry.instance().mimeTypeForTransfer(IJsonTransferType.class));
		Assert.assertEquals("application/x-www-form-urlencoded", 
				TransferTypeRegistry.instance().mimeTypeForTransfer(IWWWUrlFormEncodedTransferType.class));
	}

	@Test
	public void protectKnownMappingsFromRedefinition() {
		TransferTypeRegistry.instance().registerMapping(ITransferType.class, "foo/bar");
		TransferTypeRegistry.instance().registerMapping(IJsonTransferType.class, "foo/bar");
		TransferTypeRegistry.instance().registerMapping(IWWWUrlFormEncodedTransferType.class, "foo/bar");

		Assert.assertEquals("application/octet-stream", 
				TransferTypeRegistry.instance().mimeTypeForTransfer(ITransferType.class));
		Assert.assertEquals("application/json", 
				TransferTypeRegistry.instance().mimeTypeForTransfer(IJsonTransferType.class));
		Assert.assertEquals("application/x-www-form-urlencoded", 
				TransferTypeRegistry.instance().mimeTypeForTransfer(IWWWUrlFormEncodedTransferType.class));
	}


	@Test
	public void protectKnownMappingsFromRemoval() {
		TransferTypeRegistry.instance().unregisterMapping(ITransferType.class);
		TransferTypeRegistry.instance().unregisterMapping(IJsonTransferType.class);
		TransferTypeRegistry.instance().unregisterMapping(IWWWUrlFormEncodedTransferType.class);

		Assert.assertEquals("application/octet-stream", 
				TransferTypeRegistry.instance().mimeTypeForTransfer(ITransferType.class));
		Assert.assertEquals("application/json", 
				TransferTypeRegistry.instance().mimeTypeForTransfer(IJsonTransferType.class));
		Assert.assertEquals("application/x-www-form-urlencoded", 
				TransferTypeRegistry.instance().mimeTypeForTransfer(IWWWUrlFormEncodedTransferType.class));
	}

	@Test
	public void unknownMapping() {
		Assert.assertNull(TransferTypeRegistry.instance().mimeTypeForTransfer(ITestTransferType.class));
		Assert.assertNull(TransferTypeRegistry.instance().mimeTypeForTransfer(ITest2TransferType.class));
		Assert.assertNull(TransferTypeRegistry.instance().mimeTypeForTransfer(null));
	}
	
	@Test
	public void registerMapping() {
		Assert.assertNull(TransferTypeRegistry.instance().mimeTypeForTransfer(ITestTransferType.class));
		TransferTypeRegistry.instance().registerMapping(ITestTransferType.class, "foo/bar");
		Assert.assertEquals("foo/bar", TransferTypeRegistry.instance().mimeTypeForTransfer(ITestTransferType.class));
	}
	
	@Test
	public void unregisterMapping() {
		Assert.assertNull(TransferTypeRegistry.instance().mimeTypeForTransfer(ITest2TransferType.class));
		TransferTypeRegistry.instance().registerMapping(ITest2TransferType.class, "foo/bar");
		Assert.assertEquals("foo/bar", TransferTypeRegistry.instance().mimeTypeForTransfer(ITest2TransferType.class));
		TransferTypeRegistry.instance().unregisterMapping(ITest2TransferType.class);
		Assert.assertNull(TransferTypeRegistry.instance().mimeTypeForTransfer(ITest2TransferType.class));
	}
}
