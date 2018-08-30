package org.easy.easysocket;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * 数据包
 */
public class Packet {
	private DatagramPacket packet;

	private Packet(DatagramPacket packet) {
		this.packet = packet;
	}

	public static Packet of(DatagramPacket packet) {
		return new Packet(packet);
	}

	public static Packet of(String host, int port, byte[] data) throws UnknownHostException {
		return of(new DatagramPacket(data, 0, data.length, InetAddress.getByName(host), port));
	}

	public static Packet ofStr(String host, int port, String s) throws UnknownHostException {
		return of(host, port, s.getBytes());
	}

	public static Packet ofStr(String host, int port, String s, Charset charset) throws UnknownHostException {
		return of(host, port, s.getBytes(charset));
	}

	public String getStr() {
		return new String(packet.getData(), packet.getOffset(), packet.getLength());
	}

	public String getStr(Charset charset) {
		return new String(packet.getData(), packet.getOffset(), packet.getLength(), charset);
	}

	public byte[] getData() {
		return packet.getData();
	}

	public int getOffset() {
		return packet.getOffset();
	}

	public int getLength() {
		return packet.getLength();
	}

	public int getPort() {
		return packet.getPort();
	}

	public InetAddress getAddress() {
		return packet.getAddress();
	}

	public SocketAddress getSocketAddress() {
		return packet.getSocketAddress();
	}

	public DatagramPacket getPacket() {
		return packet;
	}

	@Override
	public String toString() {
		return "Packet{" +
				"data=" + getData() +
				", offset=" + getOffset() +
				", length=" + getLength() +
				", port=" + getPort() +
				", address=" + getAddress() +
				", socketAddress=" + getSocketAddress() +
				'}';
	}
}
