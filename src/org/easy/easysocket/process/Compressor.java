package org.easy.easysocket.process;

import java.io.InputStream;

/**
 * 压缩器接口
 */
public interface Compressor {

    InputStream compress(InputStream is);

    InputStream decompress(InputStream is);
}
