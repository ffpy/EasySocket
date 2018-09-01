package org.easy.easysocket;

import org.easy.easysocket.callback.OnAccept;
import org.easy.easysocket.converter.ObjectConverter;
import org.easy.easysocket.converter.SerializeObjectConverter;
import org.easy.easysocket.process.Compressor;
import org.easy.easysocket.process.Encryptor;
import org.easy.easysocket.process.GZIPCompressor;
import org.easy.easysocket.protocol.DataProtocol;
import org.easy.easysocket.protocol.DefaultDataProtocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executor;

public class EasyServerSocket {
	private ServerSocket serverSocket;
	private Executor executor;
	private DataProtocol dataProtocol = new DefaultDataProtocol();
    private ObjectConverter objectConverter = new SerializeObjectConverter();
    private Compressor compressor = new GZIPCompressor();
    private Encryptor encryptor;

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

    public void setCompressor(Compressor compressor) {
        this.compressor = compressor;
    }

    public void setEncryptor(Encryptor encryptor) {
        this.encryptor = encryptor;
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
				socket.setCompressor(compressor);
				socket.setEncryptor(encryptor);

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
