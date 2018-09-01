package org.easy.easysocket.encryptor.des;

import org.easy.easysocket.encryptor.EncryptOutputStream;

import java.io.OutputStream;
import java.util.Objects;

/**
 * DES加密流
 */
class DESOutputStream extends EncryptOutputStream {
    /** DES加解密器 */
    private final DES des;


    DESOutputStream(OutputStream out, DES des) {
        super(out);
        this.des = Objects.requireNonNull(des);
    }

    @Override
    protected int getBlockSize() {
        return DES.BLOCK_SIZE - 1;
    }

    @Override
    protected byte[] encrypt(byte[] b, int off, int len) {
        return des.encrypt(b, off, len);
    }
}
