package org.easy.easysocket.process;

import java.io.InputStream;

/**
 * 加密器接口
 */
public interface Encryptor {

    InputStream encrypt(InputStream is);

    InputStream decrypt(InputStream is);
}
