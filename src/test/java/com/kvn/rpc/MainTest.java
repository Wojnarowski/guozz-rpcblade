package com.kvn.rpc;

import javax.annotation.Resource;

import com.czb.coupon.sdk.response.dto.CouponResponseDto;
import com.kvn.rpc.dto.*;
import com.kvn.rpc.service.HttpRpcServiceCouponTest;
import org.ipanda.common.utils.serialize.JsonHelper;
import org.ipanda.common.utils.wrap.Wrapper;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.kvn.SpringBaseTest;
import com.kvn.rpc.service.HttpRpcService;
import com.kvn.rpc.service.NettyRpcService;
import com.kvn.rpc.service.SocketRpcService;

/**
* @author 郭智忠
* @date 2017年11月13日 下午5:27:29
*/

public class MainTest extends SpringBaseTest {
	@Resource
	private SocketRpcService socketRpcService;
	@Resource
	private NettyRpcService nettyRpcService;
	@Resource
	private HttpRpcService httpRpcService;
	@Resource
	private HttpRpcServiceCouponTest httpRpcServiceCouponTest;

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
	
//	@Test
//	public void testHttp(){
//		FooRequest request = new FooRequest();
//		FooResponse response = httpRpcService.call1030(request);
//		System.out.println("===>" + JSON.toJSONString(response));
//	}
	
	@Test
	public void testNetty(){
		XmlRequest request = new XmlRequest();
		request.setAge(12);
		request.setName("kvn");
		XmlResponse response = nettyRpcService.call1030(request);
		System.out.println("===>" + JSON.toJSONString(response));
	}



    @Test
    public void testGetCouponById(){
		CouponRequestDto dto = new CouponRequestDto();
		dto.setCouponId(25146);
		Wrapper<CouponResponseDto> response = httpRpcServiceCouponTest.queryCouponByCouponId(dto);
        System.out.println("===>" + JsonHelper.toJson(response));
    }

}
