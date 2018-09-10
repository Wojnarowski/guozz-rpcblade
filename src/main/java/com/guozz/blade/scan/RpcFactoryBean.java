package com.guozz.blade.scan;

import com.guozz.blade.proxy.RpcProxyFactory;
import org.springframework.beans.factory.FactoryBean;

/**
* @author 郭智忠
* @date 2017年11月13日 下午6:14:14
*/
public class RpcFactoryBean<T> implements FactoryBean<T> {
	private Class<T> rpcInterface;

	@Override
	public T getObject() throws Exception {
		return RpcProxyFactory.newInstance(rpcInterface);
	}

	@Override
	public Class<?> getObjectType() {
		return rpcInterface;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public Class<T> getRpcInterface() {
		return rpcInterface;
	}

	public void setRpcInterface(Class<T> rpcInterface) {
		this.rpcInterface = rpcInterface;
	}

}
