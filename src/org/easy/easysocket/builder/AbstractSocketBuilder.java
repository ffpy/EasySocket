package org.easy.easysocket.builder;

import org.easy.easysocket.callback.OnConnect;
import org.easy.easysocket.callback.OnError;
import org.easy.easysocket.process.*;

import java.util.concurrent.TimeUnit;

public abstract class AbstractSocketBuilder<T> {

	public T timeout(int timeout, TimeUnit unit) {
		return (T) this;
	}

	/**
	 * 心跳检测
	 */
	public T heartbeat(boolean b) {
		return (T) this;
	}

	/**
	 * 加密
	 */
	public T encrypt(Encryptor encryptor) {
		return (T) this;
	}

	/**
	 * 解密
	 */
	public T decrypt(Decryptor decryptor) {
		return (T) this;
	}

	/**
	 * 压缩
	 */
	public T compress(Compressor compressor) {
		return (T) this;
	}

	/**
	 * 解压
	 */
	public T decompress(Decompressor decompressor) {
		return (T) this;
	}

	/**
	 * 预处理
	 */
	public T preprocess() {
		return (T) this;
	}

	/**
	 * 连接回调
	 */
	public T onConnect(OnConnect onConnect) {
		return (T) this;
	}

	/**
	 * 错误回调
	 */
	public T onError(OnError onError) {
		return (T) this;
	}
}
