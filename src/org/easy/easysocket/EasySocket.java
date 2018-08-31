package org.easy.easysocket;

import org.easy.easysocket.protocol.DataProtocol;

import java.io.*;
import java.net.Socket;
import java.nio.channels.Channel;

public class EasySocket {
	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;
	private DataProtocol dataProtocol;

	public EasySocket(String host, int port) throws IOException {
		this(host, port, null);
	}

	public EasySocket(String host, int port, DataProtocol dataProtocol) throws IOException {
		this(new Socket(host, port), dataProtocol);
	}

	public EasySocket(Socket socket) throws IOException {
		this(socket, null);
	}

	public EasySocket(Socket socket, DataProtocol dataProtocol) throws IOException {
		this.socket = socket;
		this.inputStream = socket.getInputStream();
		this.outputStream = socket.getOutputStream();
		this.dataProtocol = dataProtocol;
	}

	public void setDataProtocol(DataProtocol dataProtocol) {
		this.dataProtocol = dataProtocol;
	}

	/**
	 * 写入对象
	 */
	public int send(Object o) throws IOException {
		checkDataProtocol();
		return dataProtocol.send(outputStream, o);
	}

	/**
	 * 读取对象
	 */
	public <T> T receive(Class<T> oClass) throws IOException {
		checkDataProtocol();
		return dataProtocol.receive(inputStream, oClass);
	}

	/**
	 * 获取输入流
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * 获取输出流
	 */
	public OutputStream getOutputStream() {
		return outputStream;
	}

	/**
	 * 获取通道
	 */
	public Channel getChannel() {
		return socket.getChannel();
	}

	/**
	 * 关闭连接
	 */
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void checkDataProtocol() {
		if (dataProtocol == null)
			throw new RuntimeException("dataProtocol not exists");
	}
}
