package com.kvn.rpc;

import javax.annotation.Resource;

import com.czb.coupon.sdk.request.CouponRequest;
import com.czb.coupon.sdk.response.dto.CouponResponseDto;
import com.czb.gas.sdk.dto.GasInfoDo;
import com.czb.gas.sdk.dto.OilNumDo;
import com.kvn.rpc.dto.*;
import com.kvn.rpc.service.*;
import org.ipanda.common.sdk.api.request.dto.PageDto;
import org.ipanda.common.utils.serialize.JsonHelper;
import org.ipanda.common.utils.wrap.CommonPageWrapper;
import org.ipanda.common.utils.wrap.Wrapper;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.kvn.SpringBaseTest;

import java.util.List;

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
	private HttpRpcServicePostForm httpRpcServiceCouponTest;
	@Resource
	private HttpRpcServicePostJson httpRpcServicePostJson;
	@Resource
	private HttpRpcServiceGet httpRpcServiceGet;

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
    public void testPostForm(){
		CouponRequestDto dto = new CouponRequestDto();
		dto.setCouponId(25146);
		Wrapper<CouponResponseDto> response = httpRpcServiceCouponTest.queryCouponByCouponId(dto);
        System.out.println("===>" + JsonHelper.toJson(response));
    }

	@Test
	public void testPostJson(){
		CouponRequest request = new CouponRequest();
		com.czb.coupon.sdk.request.dto.CouponRequestDto dto = new com.czb.coupon.sdk.request.dto.CouponRequestDto();
		dto.setUserId(5);
		request.setContent(dto);
		PageDto pageDto = new PageDto();
		pageDto.setPageIndex(1);
		pageDto.setPageSize(10);
		request.setPage(pageDto);

		CommonPageWrapper<List<CouponResponseDto>> response = httpRpcServicePostJson.queryListWithPage(request);
		System.out.println("===>" + JsonHelper.toJson(response));
	}

	@Test
	public void testGetNoParam(){
		Wrapper<List<OilNumDo>> wrapper = httpRpcServiceGet.getOilNumList();
		System.out.println("===>" + JsonHelper.toJson(wrapper));
	}

	@Test
	public void testGetHaveParam(){
		GasInfoRequest request = new GasInfoRequest();
		request.setGasId("my000001126");
		GasInfoDo gasInfoDo = httpRpcServiceGet.getGasInfoByGasId(request);
		System.out.println("===>" + JsonHelper.toJson(gasInfoDo));
	}

}
