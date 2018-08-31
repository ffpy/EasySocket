package org.easy.easysocket;

import org.easy.easysocket.protocol.JsonDataProtocol;
import org.easy.easysocket.protocol.SerializableDataProtocol;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EasySocketTest {

	@Test
	public void test() throws InterruptedException {
		new ServerThread().start();
		TimeUnit.MILLISECONDS.sleep(500);
		new ClientThread().start();
		TimeUnit.SECONDS.sleep(5);
	}

	private class ClientThread extends Thread {
		@Override
		public void run() {
			try {
				EasySocket socket = new EasySocket("192.168.0.244", 7777);
				socket.setDataProtocol(new JsonDataProtocol());
				int n;
//				for (;;) {
					n = socket.send(new Person("Hello", 20));
					System.out.println("ClientThread send " + n + " bytes");
//				}
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private class ServerThread extends Thread {
		@Override
		public void run() {
			try {
				EasyServerSocket serverSocket = new EasyServerSocket(7777,
						Executors.newFixedThreadPool(10));
				serverSocket.setDataProtocol(new JsonDataProtocol());
				serverSocket.listen(socket -> {
					Person p;
//					for (;;) {
						p = socket.receive(Person.class);
						System.out.println("ServerThread receive: " + p);
//					}
					socket.close();
					return false;
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}