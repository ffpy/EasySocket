package org.easy.easysocket.old;

import java.net.DatagramSocket;
import java.util.Objects;

abstract class AbstractEasyDatagramSocket {
	protected DatagramSocket socket;

	AbstractEasyDatagramSocket(DatagramSocket socket) {
		this.socket = Objects.requireNonNull(socket);
	}

	public DatagramSocket getSocket() {
		return socket;
	}

	public void close() {
		socket.close();
	}
}
