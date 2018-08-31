package org.easy.easysocket.process;

import java.io.IOException;
import java.io.InputStream;

/**
 * 加密器接口
 */
public interface Encryptor {

    InputStream encrypt(InputStream is) throws IOException;

    InputStream decrypt(InputStream is) throws IOException;
}
