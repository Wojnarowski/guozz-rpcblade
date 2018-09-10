package com.guozz.blade.core.decoder;

import com.alibaba.fastjson.JSON;

/**
 * @author 郭智忠
 * @date 2017年11月17日 上午11:04:40
 */
public class JsonDecoder implements Decoder {

	@Override
	public <T> T decode(String arg, Class<T> returnClass) {
		return JSON.parseObject(arg, returnClass);
	}

}
