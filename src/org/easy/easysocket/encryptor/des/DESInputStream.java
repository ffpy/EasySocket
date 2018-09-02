package org.easy.easysocket.encryptor.des;

import org.easy.easysocket.encryptor.EncryptInputStream;

import java.io.InputStream;
import java.util.Objects;

/**
 * DES解密流
 */
class DESInputStream extends EncryptInputStream {
    /** DES加解密器 */
    private final DES des;

    DESInputStream(InputStream in, DES des) {
        super(in, des.getBlockSize());
        this.des = Objects.requireNonNull(des);
    }

    @Override
    protected byte[] decrypt(byte[] b, int off, int len) {
        return des.decrypt(b, off, len);
    }
}
