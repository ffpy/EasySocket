package org.easy.easysocket;

import java.io.IOException;
import java.net.DatagramSocket;

/**
 * 发送器
 */
public class EasyDatagramSocketSender extends AbstractEasyDatagramSocket {

	EasyDatagramSocketSender(DatagramSocket socket) {
		super(socket);
	}

	public EasyDatagramSocketSender send(Packet packet) throws IOException {
		socket.send(packet.getPacket());
		return this;
	}
}
