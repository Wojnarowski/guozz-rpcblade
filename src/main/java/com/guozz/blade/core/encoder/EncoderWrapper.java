package com.guozz.blade.core.encoder;

/**
 * @author 郭智忠
 * @date 2017年12月14日 下午2:37:32
 */
public class EncoderWrapper implements Encoder {
	private final Encoder encoder;

	public EncoderWrapper(Encoder encoder) {
		if(encoder == null){
			throw new IllegalArgumentException("encoder == null");
		}
		this.encoder = encoder;
	}

	@Override
	public Object encode(Object arg) {
		return encoder.encode(doWrapper(arg));
	}

	private Object doWrapper(Object arg) {
		System.out.println("EncoderWrapper处理。。。。");
		return arg;
	}

}
