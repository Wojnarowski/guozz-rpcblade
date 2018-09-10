package com.kvn.blade.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.kvn.blade.scan.RpcHostConfigurer;
import com.kvn.blade.util.SpringContextUtils;
import org.ipanda.common.utils.serialize.JsonHelper;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.kvn.blade.anno.Addition;
import com.kvn.blade.anno.DecodeBy;
import com.kvn.blade.anno.EncodeBy;
import com.kvn.blade.anno.RpcService;
import com.kvn.blade.core.ExtensionLoader;
import com.kvn.blade.core.RemoteInfo;
import com.kvn.blade.core.decoder.Decoder;
import com.kvn.blade.core.encoder.Encoder;
import com.kvn.blade.core.sender.Sender;
import com.kvn.blade.util.AdditionPropParser;

/**
 * @author 郭智忠
 * @date 2017年11月14日 上午10:04:36
 */
public class RpcProxyFactory {

	public static <T> T newInstance(Class<T> rpcInterface) {
		return (T) Proxy.newProxyInstance(rpcInterface.getClassLoader(), new Class[] { rpcInterface }, new RpcProxy());
	}
	
	private static class RpcProxy implements InvocationHandler {
		private static final ExtensionLoader<Encoder> encoder = ExtensionLoader.getExtensionLoader(Encoder.class);
		private static final ExtensionLoader<Sender> sender = ExtensionLoader.getExtensionLoader(Sender.class);
		private static final ExtensionLoader<Decoder> decoder = ExtensionLoader.getExtensionLoader(Decoder.class);
		
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
			
			System.out.println("class:" + method.getDeclaringClass().getName());
			System.out.println("method:" + method.getName());
			System.out.println("args:" + JSON.toJSONString(args));
			System.out.println("return:" + method.getReturnType().getName());
			
			
			RpcService sendType = method.getDeclaringClass().getAnnotation(RpcService.class);
			Addition addition = method.getAnnotation(Addition.class);
			RemoteInfo remoteInfo = new RemoteInfo(this.getRealHost(sendType,sendType.host()), AdditionPropParser.parse(addition),method.getReturnType());
			Encoder targetEncoder = getEncoder(args[0]);
			Sender targetSender = getSender(sendType);
			Decoder targetDecoder = getDecoder(method);
			
			// 编码 --> 发送 --> 解码
			if(targetSender == null){
				throw new IllegalStateException("不支持的protocol:" + sendType.protocol());
			}
			
			Object msg = args[0];
			if(targetEncoder != null){
				msg = targetEncoder.encode(msg);
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
				RpcHostConfigurer rpcHostConfigurer = SpringContextUtils.getBean(sendType.host(),RpcHostConfigurer.class);
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
