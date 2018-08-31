package org.easy.easysocket.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 数据传输协议
 */
public interface DataProtocol {

    int write(InputStream from, OutputStream to) throws IOException;

    InputStream read(InputStream from) throws IOException;
}
