package org.easy.easysocket;

import org.easy.easysocket.callback.OnAccept;
import org.easy.easysocket.converter.ObjectConverter;
import org.easy.easysocket.protocol.DataProtocol;
import org.easy.easysocket.protocol.DefaultDataProtocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executor;

public class EasyServerSocket {
	private ServerSocket serverSocket;
	private Executor executor;
	private DataProtocol dataProtocol = new DefaultDataProtocol();
    private ObjectConverter objectConverter;

	public EasyServerSocket(int port, Executor executor) throws IOException {
		this.serverSocket = new ServerSocket(port);
		this.executor = executor;
	}

    public void setDataProtocol(DataProtocol dataProtocol) {
        this.dataProtocol = dataProtocol;
    }

    public void setObjectConverter(ObjectConverter objectConverter) {
        this.objectConverter = objectConverter;
    }

    /**
	 * 监听
	 */
	public void listen(OnAccept onAccept) {
		for (;;) {
			try {
				EasySocket socket = new EasySocket(serverSocket.accept());
				socket.setDataProtocol(dataProtocol);
				socket.setObjectConverter(objectConverter);
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
