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
        super(in);
        this.des = Objects.requireNonNull(des);
    }

    @Override
    protected int getBlockSize() {
        return DES.BLOCK_SIZE;
    }

    @Override
    protected byte[] decrpty(byte[] b, int off, int len) {
        return des.decrpty(b, off, len);
    }
}
