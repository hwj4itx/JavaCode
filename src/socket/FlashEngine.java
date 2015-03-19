package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FlashEngine implements Runnable {
	private ServerSocket gameServer;
	
	public FlashEngine() {
		try {
			gameServer = new ServerSocket(8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			Socket client = null;
			while (true) {
				client = gameServer.accept();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
