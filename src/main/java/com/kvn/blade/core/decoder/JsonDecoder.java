package com.kvn.blade.core.decoder;

import com.alibaba.fastjson.JSON;

/**
 * @author wzy
 * @date 2017年11月17日 上午11:04:40
 */
public class JsonDecoder implements Decoder {

	@Override
	public <T> T decode(String arg, Class<T> returnClass) {
		return JSON.parseObject(arg, returnClass);
	}

}
