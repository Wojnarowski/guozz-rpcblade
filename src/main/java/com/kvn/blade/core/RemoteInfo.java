package com.kvn.blade.core;

import java.util.Properties;

/**
* @author 郭智忠
* @date 2017年11月17日 下午5:00:17
*/
public class RemoteInfo {
	private String host;
	private Properties additionProps;
	
	public RemoteInfo(String host, Properties additionProps) {
		super();
		this.host = host;
		this.additionProps = additionProps;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Properties getAdditionProps() {
		return additionProps;
	}
	public void setAdditionProps(Properties additionProps) {
		this.additionProps = additionProps;
	}
	
}
