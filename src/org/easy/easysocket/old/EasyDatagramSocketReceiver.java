package org.easy.easysocket.old;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Objects;

/**
 * 接收器
 */
public class EasyDatagramSocketReceiver extends AbstractEasyDatagramSocket {

	EasyDatagramSocketReceiver(DatagramSocket socket) {
		super(socket);
	}

	public Packet receive() throws IOException {
		return receive(EasyDatagramSocket.DEFAULT_BUFSIZE);
	}

	public Packet receive(int bufSize) throws IOException {
		byte[] buf = new byte[bufSize];
		DatagramPacket packet = new DatagramPacket(buf, 0, buf.length);
		socket.receive(packet);
		return Packet.of(packet);
	}

	public void receiveAll(ReceiveListener l) throws IOException {
		receiveAll(EasyDatagramSocket.DEFAULT_BUFSIZE, l);
	}

	public void receiveAll(int bufSize, ReceiveListener l) throws IOException {
		Objects.requireNonNull(l);
		//noinspection InfiniteLoopStatement
		for (;;) {
			Packet packet = receive(bufSize);
			l.receive(packet);
		}
	}

	public interface ReceiveListener {
		void receive(Packet packet);
	}
}
