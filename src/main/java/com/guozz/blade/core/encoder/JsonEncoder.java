package com.guozz.blade.core.encoder;

import com.alibaba.fastjson.JSON;

/**
 * @author 郭智忠
 * @date 2017年11月17日 上午11:04:40
 */
public class JsonEncoder implements Encoder {

	@Override
	public Object encode(Object arg) {
		return JSON.toJSONString(arg);
	}

}
