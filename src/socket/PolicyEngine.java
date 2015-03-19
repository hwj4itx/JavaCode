package socket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PolicyEngine implements Runnable {
	String xml = "<cross-domain-policy>"
			+ "<site-control permitted-cross-domain-policies=\"all\"/>"
			+ "<allow-access-from domain=\"*\" to-ports=\"*\"/>"
			+ "</cross-domain-policy>";
	
	ServerSocket policyServer;
	
	public PolicyEngine() {
		try {
			// 默认“先”访问843端口
			policyServer = new ServerSocket(843);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// 写回策略文件，注意以\0结尾
		try {
			Socket client = null;
			while (true) {
				client = policyServer.accept();
				OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
				writer.write(xml + "\0");
				writer.flush();
				client.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
