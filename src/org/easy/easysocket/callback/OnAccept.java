package org.easy.easysocket.callback;

import org.easy.easysocket.EasySocket;

import java.io.IOException;

public interface OnAccept {
	/**
	 *
	 * @param socket
	 * @return 返回true则停止监听，否则继续监听
	 */
	boolean accept(EasySocket socket) throws Exception;
}
