package com.guozz.rpc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 郭智忠
 * @date 2017年11月20日 上午11:27:06
 */
public class SocketServer {

	public static void main(String[] args) throws Exception {
		// 1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
		ServerSocket serverSocket = new ServerSocket(8091);// 1024-65535的某个端口
		// 2、调用accept()方法开始监听，等待客户端的连接
		Socket socket = serverSocket.accept();
		// 3、获取输入流，并读取客户端信息
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String info = null;
		while ((info = br.readLine()) != null && !"".equals(info)) {
			System.out.println("我是服务器，客户端说：" + info);
		}
		System.out.println("接收完毕！");
		socket.shutdownInput();// 关闭输入流
		// 4、获取输出流，响应客户端的请求
		OutputStream os = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		pw.write(returnXml());
		pw.flush();
		System.out.println("关闭！");

		// 5、关闭资源
		pw.close();
		os.close();
		br.close();
		isr.close();
		is.close();
		socket.close();
		serverSocket.close();
	}

	private static String returnXml() {
		String rtn = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "<xmlResponse>" + "    <code>200</code>" + "    <msg>成功</msg>"
				+ "</xmlResponse>\r\n";
		System.out.println("返回：" + rtn);
		return rtn;
	}

}
