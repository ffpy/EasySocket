package org.easy.easysocket.encryptor.aes;

import org.easy.easysocket.encryptor.Encryptor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * AES加密器
 */
public class AESEncryptor implements Encryptor {
    private final AES aes;

    /**
     * @param key 密钥，长度必须为16的倍数
     */
    public AESEncryptor(String key) {
        aes = new AES(key.getBytes());
    }

    @Override
    public OutputStream encrypt(OutputStream os) throws IOException {
        return new AESOutputStream(os, aes);
    }

    @Override
    public InputStream decrypt(InputStream is) throws IOException {
        return new AESInputStream(is, aes);
    }
}
