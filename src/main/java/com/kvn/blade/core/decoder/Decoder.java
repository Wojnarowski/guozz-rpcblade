package com.kvn.blade.core.decoder;
/**
* @author wzy
* @date 2017年11月16日 下午6:08:31
*/
public interface Decoder {
	<T> T decode(String arg, Class<T> returnClass);
}
