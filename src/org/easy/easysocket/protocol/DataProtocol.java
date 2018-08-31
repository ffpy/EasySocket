package org.easy.easysocket.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 数据交换协议
 */
public interface DataProtocol {

	int send(OutputStream os, Object o) throws IOException;

	<T> T receive(InputStream is, Class<T> oClass) throws IOException;
}
