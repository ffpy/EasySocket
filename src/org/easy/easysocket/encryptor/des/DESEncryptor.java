package org.easy.easysocket.encryptor.des;

import org.easy.easysocket.encryptor.Encryptor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * DES加密器
 */
public class DESEncryptor implements Encryptor {
    private final DES des;

    /**
     * @param key 密钥，长度必须为8的倍数
     */
    public DESEncryptor(String key) {
        des = new DES(key.getBytes());
    }

    @Override
    public OutputStream encrypt(OutputStream os) throws IOException {
        return new DESOutputStream(os, des);
    }

    @Override
    public InputStream decrypt(InputStream is) throws IOException {
        return new DESInputStream(is, des);
    }
}
