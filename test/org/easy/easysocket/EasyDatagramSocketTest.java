package org.easy.easysocket;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class EasyDatagramSocketTest {

	@Test
	public void test() throws InterruptedException, IOException {
		new Thread(() -> {
			try {
				Packet packet = EasyDatagramSocket.receive(7777);
				System.out.println(packet);
				System.out.println(packet.getStr());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();

		TimeUnit.MILLISECONDS.sleep(500);

		EasyDatagramSocket.send(Packet.ofStr("127.0.0.1", 7777, "hello"));

		TimeUnit.SECONDS.sleep(1);
	}
}