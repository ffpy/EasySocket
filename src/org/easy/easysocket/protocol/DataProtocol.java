package org.easy.easysocket.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 数据传输协议，解决粘读问题
 * 数据以块为单位进行发送和接收
 */
public interface DataProtocol {

    /**
     * 往指定流写入字节数组
     *
     * @param b   待写入的字节数组
     * @param off 写入字节数组的偏移量
     * @param len 写入字节数组的长度
     * @param os  写入的流
     * @return 写入的字节数
     * @throws IOException IO异常
     */
    int write(byte[] b, int off, int len, OutputStream os) throws IOException;

    /**
     * 从流读出字节数组
     *
     * @param is 读取的流
     * @return 读取的字节数组
     * @throws IOException IO异常
     */
    byte[] read(InputStream is) throws IOException;
}
