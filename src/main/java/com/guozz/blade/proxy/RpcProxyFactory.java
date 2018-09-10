package com.guozz.blade.proxy;

import com.alibaba.fastjson.JSON;
import com.guozz.blade.util.AdditionPropParser;
import com.guozz.blade.anno.Addition;
import com.guozz.blade.anno.DecodeBy;
import com.guozz.blade.anno.EncodeBy;
import com.guozz.blade.anno.RpcService;
import com.guozz.blade.core.ExtensionLoader;
import com.guozz.blade.core.RemoteInfo;
import com.guozz.blade.core.decoder.Decoder;
import com.guozz.blade.core.encoder.Encoder;
import com.guozz.blade.core.sender.Sender;
import com.guozz.blade.scan.RpcHostConfigurer;
import org.ipanda.common.utils.config.SpringHelper;
import org.ipanda.common.utils.serialize.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 郭智忠
 * @date 2017年11月14日 上午10:04:36
 */
public class RpcProxyFactory {

	private static final Logger logger = LoggerFactory.getLogger(RpcProxyFactory.class);

	public static <T> T newInstance(Class<T> rpcInterface) {
		return (T) Proxy.newProxyInstance(rpcInterface.getClassLoader(), new Class[] { rpcInterface }, new RpcProxy());
	}
	
	private static class RpcProxy implements InvocationHandler {
		private static final ExtensionLoader<Encoder> encoder = ExtensionLoader.getExtensionLoader(Encoder.class);
		private static final ExtensionLoader<Sender> sender = ExtensionLoader.getExtensionLoader(Sender.class);
		private static final ExtensionLoader<Decoder> decoder = ExtensionLoader.getExtensionLoader(Decoder.class);
        //private static final ExtensionLoader<HttpSendService> httpServiceSender = ExtensionLoader.getExtensionLoader(HttpSendService.class);
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			String methodName = method.getName();
			if("toString".equals(methodName)){
				return this.toString();
			}
			if("hashCode".equals(methodName)){
				return this.hashCode();
			}
			if("getClass".equals(methodName)){
				return method.getDeclaringClass();
			}
			Assert.isTrue(args == null || args.length == 1, "[rpcMethod:" + methodName + "]参数个数只能有一个");

			logger.info("class:" + method.getDeclaringClass().getName());
			logger.info("method:" + method.getName());
			logger.info("args:" + JSON.toJSONString(args));
			logger.info("return:" + method.getReturnType().getName());
			
			
			RpcService sendType = method.getDeclaringClass().getAnnotation(RpcService.class);
			Addition addition = method.getAnnotation(Addition.class);
			RemoteInfo remoteInfo = new RemoteInfo(this.getRealHost(sendType,sendType.host()), AdditionPropParser.parse(addition),method.getReturnType());
			Encoder targetEncoder=null;
			if(!remoteInfo.getAdditionProps().get("requestType").equals("GET")||(remoteInfo.getAdditionProps().get("requestType").equals("GET")&&args!=null)){
				targetEncoder = getEncoder(args[0]);
			}

			Sender targetSender = getSender(sendType);
			Decoder targetDecoder = getDecoder(method);
			
			// 编码 --> 发送 --> 解码
			if(targetSender == null){
				throw new IllegalStateException("不支持的protocol:" + sendType.protocol());
			}

			Object msg =null;
			if(!remoteInfo.getAdditionProps().get("requestType").equals("GET")||(remoteInfo.getAdditionProps().get("requestType").equals("GET")&&args!=null)){
				 msg = args[0];
				if(targetEncoder != null){
					msg = targetEncoder.encode(msg);
				}
			}

			String rlt = targetSender.send(msg, remoteInfo);

			if(sendType.protocol().equals("http")){
				return JsonHelper.toBean(rlt,method.getReturnType());
			}


			if(targetDecoder == null){
				return rlt;
			}
			return targetDecoder.decode(rlt, method.getReturnType());
		}

		private String getRealHost(RpcService sendType,String host) {
			if(sendType.configHost()==false){
				return host;
			}else{
				RpcHostConfigurer rpcHostConfigurer = (RpcHostConfigurer)SpringHelper.getBean(sendType.host());;
				if(null==rpcHostConfigurer){
					throw new RuntimeException("请配置相应的host，并注入");
				}
				if(null==rpcHostConfigurer.getServiceHostDomain()){
					throw new RuntimeException("请配置相应的host，并注入");
				}
				return rpcHostConfigurer.getServiceHostDomain();
			}
		}

		private Sender getSender(RpcService sendType) {
			if(sendType == null){
				throw new RuntimeException("@RpcService缺失");
			}
			return sender.getExtension(sendType.protocol());
		}

		private Decoder getDecoder(Method method) {
			DecodeBy decodeBy = method.getReturnType().getAnnotation(DecodeBy.class);
			if(decodeBy == null){
				return null;
			}
			return decoder.getExtension(decodeBy.value());
		}

		private Encoder getEncoder(Object reqParam) {
			EncodeBy encodeBy = reqParam.getClass().getAnnotation(EncodeBy.class);
			if(encodeBy == null){
				return null;
			}
			return encoder.getExtension(encodeBy.value());
		}

	}

}
