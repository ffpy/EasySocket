package org.easy.easysocket.encryptor.aes;

import org.easy.easysocket.encryptor.EncryptOutputStream;

import java.io.OutputStream;
import java.util.Objects;

/**
 * DES加密流
 */
class AESOutputStream extends EncryptOutputStream {
    /** DES加解密器 */
    private final AES aes;


    AESOutputStream(OutputStream out, AES aes) {
        super(out, aes.getBlockSize() - 1);
        this.aes = Objects.requireNonNull(aes);
    }

    @Override
    protected byte[] encrypt(byte[] b, int off, int len) {
        return aes.encrypt(b, off, len);
    }
}
