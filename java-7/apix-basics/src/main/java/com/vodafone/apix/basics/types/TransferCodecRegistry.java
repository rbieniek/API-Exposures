/**
 * 
 */
package com.vodafone.apix.basics.types;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

/**
 * @author Rainer.Bieniek@vodafone.com
 *
 */
public class TransferCodecRegistry {

	private static class TransferSerializerImpl<T> implements ITransferSerializer<T> {

		private ITransferCodec codec;
		private Class<? extends ITransferType> transferType;

		private TransferSerializerImpl(ITransferCodec codec, Class<? extends ITransferType> transferType) {
			this.codec = codec;
			this.transferType = transferType;
		}
		
		@Override
		public void serialize(T obj, OutputStream os) throws IOException {
			codec.serializeObject(obj, os, transferType);
		}
		
	}
	
	private static class TransferDeserializerImpl<T> implements ITransferDeserializer<T> {

		private ITransferCodec codec;
		private Class<? extends ITransferType> transferType;

		private TransferDeserializerImpl(ITransferCodec codec, Class<? extends ITransferType> transferType) {
			this.codec = codec;
			this.transferType = transferType;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T deserialize(InputStream is) throws IOException {
			return (T)codec.deserializeObject(is, transferType);
		}
		
		
	}
	
	private List<ITransferCodec> codecs = new LinkedList<>();
	
	/**
	 * 
	 */
	public TransferCodecRegistry() {
	}

	public void registerCodec(ITransferCodec codec) {
		this.codecs.add(codec);
	}

	public void unregisterCodec(ITransferCodec codec) {
		this.codecs.remove(codec);
	}

	public boolean canSerialize(final Class<?> objectType, final Class<? extends ITransferType> transferType) {
		return !Collections2.filter(codecs, new Predicate<ITransferCodec>() {

			@Override
			public boolean apply(ITransferCodec input) {
				return input.isSerilizationSupported(objectType, transferType);
			}
		}).isEmpty();
	}
	
	public boolean canDeserialize(final Class<?> objectType, final Class<? extends ITransferType> transferType) {
		return !Collections2.filter(codecs, new Predicate<ITransferCodec>() {

			@Override
			public boolean apply(ITransferCodec input) {
				return input.isDeserilizationSupported(objectType, transferType);
			}
		}).isEmpty();
	}
	
	public <T> ITransferSerializer<T> serializerForType(final T obj, final Class<? extends ITransferType> transferType) {
		ITransferSerializer<T> serializer = null;
		Iterator<ITransferCodec> serializeCodecs = Collections2.filter(codecs, new Predicate<ITransferCodec>() {

			@Override
			public boolean apply(ITransferCodec input) {
				return input.isSerilizationSupported(obj.getClass(), transferType);
			}
		}).iterator();
		
		if(serializeCodecs.hasNext()) {
			serializer = new TransferSerializerImpl<>(serializeCodecs.next(), transferType);
		}
		
 		return serializer;
	}
	
	public <T> ITransferDeserializer<T> deserializerForType(final T obj, final Class<? extends ITransferType> transferType) {
		ITransferDeserializer<T> deserializer = null;
		Iterator<ITransferCodec> deserializeCodecs = Collections2.filter(codecs, new Predicate<ITransferCodec>() {

			@Override
			public boolean apply(ITransferCodec input) {
				return input.isDeserilizationSupported(obj.getClass(), transferType);
			}
		}).iterator();
		
		if(deserializeCodecs.hasNext()) {
			deserializer = new TransferDeserializerImpl<>(deserializeCodecs.next(), transferType);
		}
		
 		return deserializer;
	}
}
