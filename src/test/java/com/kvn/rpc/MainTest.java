package com.kvn.rpc;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.kvn.SpringBaseTest;
import com.kvn.rpc.dto.FooRequest;
import com.kvn.rpc.dto.FooResponse;
import com.kvn.rpc.dto.XmlRequest;
import com.kvn.rpc.dto.XmlResponse;
import com.kvn.rpc.service.HttpRpcService;
import com.kvn.rpc.service.NettyRpcService;
import com.kvn.rpc.service.SocketRpcService;

/**
* @author wzy
* @date 2017年11月13日 下午5:27:29
*/

public class MainTest extends SpringBaseTest {
	@Resource
	private SocketRpcService socketRpcService;
	@Resource
	private NettyRpcService nettyRpcService;
	@Resource
	private HttpRpcService httpRpcService;

	@Test
	public void testFactoryBean(){
		XmlRequest request = new XmlRequest();
		request.setAge(12);
		request.setName("kvn");
		XmlResponse response = socketRpcService.call1030(request);
	}
	
	@Test
	public void testSocket(){
		XmlRequest request = new XmlRequest();
		request.setAge(12);
		request.setName("kvn");
		XmlResponse response = socketRpcService.call1030(request);
		System.out.println("===>" + JSON.toJSONString(response));
	}
	
	@Test
	public void testHttp(){
		FooRequest request = new FooRequest();
		FooResponse response = httpRpcService.call1030(request);
		System.out.println("===>" + JSON.toJSONString(response));
	}
	
	@Test
	public void testNetty(){
		XmlRequest request = new XmlRequest();
		request.setAge(12);
		request.setName("kvn");
		XmlResponse response = nettyRpcService.call1030(request);
		System.out.println("===>" + JSON.toJSONString(response));
	}

}
