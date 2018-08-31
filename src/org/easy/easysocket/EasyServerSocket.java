package org.easy.easysocket;

import org.easy.easysocket.callback.OnAccept;
import org.easy.easysocket.protocol.DataProtocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executor;

public class EasyServerSocket {
	private ServerSocket serverSocket;
	private Executor executor;
	private DataProtocol dataProtocol;

	public EasyServerSocket(int port, Executor executor) throws IOException {
		this(port, executor, null);
	}

	public EasyServerSocket(int port, Executor executor, DataProtocol dataProtocol) throws IOException {
		this.serverSocket = new ServerSocket(port);
		this.executor = executor;
		this.dataProtocol = dataProtocol;
	}

	public void setDataProtocol(DataProtocol dataProtocol) {
		this.dataProtocol = dataProtocol;
	}

	/**
	 * 监听
	 */
	public void listen(OnAccept onAccept) {
		for (;;) {
			try {
				EasySocket socket = new EasySocket(serverSocket.accept(), dataProtocol);
				if (executor == null) {
					onAccept.accept(socket);
				} else {
					executor.execute(() -> {
						try {
							onAccept.accept(socket);
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭
	 */
	public void close() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
