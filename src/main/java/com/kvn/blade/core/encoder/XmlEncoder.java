package com.kvn.blade.core.encoder;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 * @author 郭智忠
 * @date 2017年11月17日 上午11:04:40
 */
public class XmlEncoder implements Encoder {
	

	@Override
	public Object encode(Object arg) {
		if(arg == null){
			return null;
		}
		
		try(StringWriter writer = new StringWriter()) {
			JAXBContext context = JAXBContext.newInstance(arg.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.marshal(arg, writer);
			System.out.println("使用XmlEncoder编码：" + arg);
			return writer.toString();
		} catch (Exception e) {
			throw new IllegalStateException("xml编码异常", e);
		}
	}

}
