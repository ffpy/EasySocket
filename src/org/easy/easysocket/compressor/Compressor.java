package org.easy.easysocket.compressor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 压缩器接口
 */
public interface Compressor {

    /**
     * 压缩流
     *
     * @param os 原始流
     * @return 带压缩的流
     * @throws IOException IO异常
     */
    OutputStream compress(OutputStream os) throws IOException;

    /**
     * 解压缩流
     *
     * @param is 原始流
     * @return 带解压缩的流
     * @throws IOException IO异常
     */
    InputStream decompress(InputStream is) throws IOException;
}
