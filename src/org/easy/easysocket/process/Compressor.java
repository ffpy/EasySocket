package org.easy.easysocket.process;

import java.io.IOException;
import java.io.InputStream;

/**
 * 压缩器接口
 */
public interface Compressor {

    InputStream compress(InputStream is) throws IOException;

    InputStream decompress(InputStream is) throws IOException;
}
