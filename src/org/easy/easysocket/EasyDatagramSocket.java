package org.easy.easysocket;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

public class EasyDatagramSocket {
	public static final int DEFAULT_BUFSIZE = 1024;

	public static EasyDatagramSocketSender sender() throws SocketException {
		return new EasyDatagramSocketSender(new DatagramSocket());
	}

	public static EasyDatagramSocketReceiver receiver(int port) throws SocketException {
		return new EasyDatagramSocketReceiver(new DatagramSocket(port));
	}

	public static void send(Packet packet) throws IOException {
		EasyDatagramSocketSender sender = sender();
		try {
			sender.send(packet);
		} finally {
			sender.close();
		}
	}

	public static Packet receive(int port) throws IOException {
		EasyDatagramSocketReceiver receiver = receiver(port);
		try {
			return receiver.receive();
		} finally {
			receiver.close();
		}
	}

	public static Packet receive(int port, int bufSize) throws IOException {
		EasyDatagramSocketReceiver receiver = receiver(port);
		try {
			return receiver.receive(bufSize);
		} finally {
			receiver.close();
		}
	}

	public static void receives(int port, EasyDatagramSocketReceiver.ReceiveListener l) throws IOException {
		EasyDatagramSocketReceiver receiver = receiver(port);
		try {
			receiver.receives(l);
		} finally {
			receiver.close();
		}
	}

	public static void receives(int port, int bufSize, EasyDatagramSocketReceiver.ReceiveListener l) throws IOException {
		EasyDatagramSocketReceiver receiver = receiver(port);
		try {
			receiver.receives(bufSize, l);
		} finally {
			receiver.close();
		}
	}
}
