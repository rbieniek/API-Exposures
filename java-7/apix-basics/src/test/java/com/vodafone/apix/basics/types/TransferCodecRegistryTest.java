/**
 * 
 */
package com.vodafone.apix.basics.types;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Rainer.Bieniek@vodafone.com
 *
 */
public class TransferCodecRegistryTest {

	private static ITransferCodec writeOnlyCodec;
	private static ITransferCodec readOnlyCodec;
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void beforeClass() {
		writeOnlyCodec = Mockito.mock(ITransferCodec.class);
		Mockito.when(writeOnlyCodec.isSerilizationSupported(Mockito.any(Class.class), 
				Mockito.any(ITransferType.class.getClass()))).thenReturn(false);
		Mockito.when(writeOnlyCodec.isSerilizationSupported(Object.class, IJsonTransferType.class)).thenReturn(true);
		Mockito.when(writeOnlyCodec.isDeserilizationSupported(Mockito.any(Class.class), 
				Mockito.any(ITransferType.class.getClass()))).thenReturn(false);
		
		readOnlyCodec = Mockito.mock(ITransferCodec.class);
		Mockito.when(readOnlyCodec.isDeserilizationSupported(Mockito.any(Class.class), 
				Mockito.any(ITransferType.class.getClass()))).thenReturn(false);
		Mockito.when(readOnlyCodec.isDeserilizationSupported(Object.class, IJsonTransferType.class)).thenReturn(true);
		Mockito.when(readOnlyCodec.isSerilizationSupported(Mockito.any(Class.class), 
				Mockito.any(ITransferType.class.getClass()))).thenReturn(false);
		
		TransferCodecRegistry.instance().registerCodec(readOnlyCodec);
		TransferCodecRegistry.instance().registerCodec(writeOnlyCodec);
	}

	private Object testObject;
	private ByteArrayOutputStream baos;
	private ByteArrayInputStream bais;
	private Object inputObject;
	
	@Before
	public void before() throws Exception {
		testObject = new Object();
		baos = new ByteArrayOutputStream();
		
		bais = new ByteArrayInputStream(new byte[0]);
		inputObject = new Object();
		
		Mockito.when(readOnlyCodec.deserializeObject(bais, IJsonTransferType.class)).thenReturn(inputObject);
	}
	
	@Test
	public void writeSupportedType() throws IOException {
		Assert.assertTrue(TransferCodecRegistry.instance().canSerialize(Object.class, IJsonTransferType.class));
		
		ITransferSerializer<Object> serializer = TransferCodecRegistry.instance().serializerForType(Object.class, 
				IJsonTransferType.class);
		Assert.assertNotNull(serializer);
		
		serializer.serialize(testObject, baos);
		Mockito.verify(writeOnlyCodec).serializeObject(testObject, baos, IJsonTransferType.class);
	}
	
	@Test
	public void writeUnsupportedType() {
		Assert.assertFalse(TransferCodecRegistry.instance().canSerialize(Object.class, ITransferType.class));
		Assert.assertFalse(TransferCodecRegistry.instance().canSerialize(Object.class, IWWWUrlFormEncodedTransferType.class));
		Assert.assertFalse(TransferCodecRegistry.instance().canSerialize(String.class, IJsonTransferType.class));
		
		Assert.assertNull(TransferCodecRegistry.instance().serializerForType(Object.class, ITransferType.class));
		Assert.assertNull(TransferCodecRegistry.instance().serializerForType(Object.class, IWWWUrlFormEncodedTransferType.class));
		Assert.assertNull(TransferCodecRegistry.instance().serializerForType(String.class, IJsonTransferType.class));
	}

	@Test
	public void readSupportedType() throws IOException {
		Assert.assertTrue(TransferCodecRegistry.instance().canDeserialize(Object.class, IJsonTransferType.class));
		
		ITransferDeserializer<Object> serializer = TransferCodecRegistry.instance().deserializerForType(Object.class, 
				IJsonTransferType.class);
		Assert.assertNotNull(serializer);
		
		Object o = serializer.deserialize(bais);
		Assert.assertEquals(inputObject, o);
		Mockito.verify(readOnlyCodec).deserializeObject(bais, IJsonTransferType.class);
	}

	@Test
	public void readUnsupportedType() {
		Assert.assertFalse(TransferCodecRegistry.instance().canDeserialize(Object.class, ITransferType.class));
		Assert.assertFalse(TransferCodecRegistry.instance().canDeserialize(Object.class, IWWWUrlFormEncodedTransferType.class));
		Assert.assertFalse(TransferCodecRegistry.instance().canDeserialize(String.class, IJsonTransferType.class));
		
		Assert.assertNull(TransferCodecRegistry.instance().deserializerForType(Object.class, ITransferType.class));
		Assert.assertNull(TransferCodecRegistry.instance().deserializerForType(Object.class, IWWWUrlFormEncodedTransferType.class));
		Assert.assertNull(TransferCodecRegistry.instance().deserializerForType(String.class, IJsonTransferType.class));
	}
}
