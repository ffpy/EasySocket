package org.easy.easysocket.encryptor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 加密器接口
 */
public interface Encryptor {

    /**
     * 加密流
     *
     * @param os 原始流
     * @return 带加密的流
     * @throws IOException IO异常
     */
    OutputStream encrypt(OutputStream os) throws IOException;

    /**
     * 解密流
     *
     * @param is 原始流
     * @return 带解密的流
     * @throws IOException IO异常
     */
    InputStream decrypt(InputStream is) throws IOException;
}
