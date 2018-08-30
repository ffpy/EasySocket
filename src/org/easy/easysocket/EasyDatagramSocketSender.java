package org.easy.easysocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 发送器
 */
public class EasyDatagramSocketSender extends AbstractEasyDatagramSocket {

	EasyDatagramSocketSender(DatagramSocket socket) {
		super(socket);
	}

	public EasyDatagramSocketSender send(String host, int port, byte[] data) throws IOException {
		DatagramPacket dp = new DatagramPacket(data, 0, data.length, InetAddress.getByName(host), port);
		socket.send(dp);
		return this;
	}

	public EasyDatagramSocketSender send(Packet packet) throws IOException {
		socket.send(packet.getPacket());
		return this;
	}
}
