package org.easy.easysocket.encryptor.aes;

import org.easy.easysocket.encryptor.EncryptInputStream;

import java.io.InputStream;
import java.util.Objects;

/**
 * AES解密流
 */
class AESInputStream extends EncryptInputStream {
    /** AES加解密器 */
    private final AES aes;

    AESInputStream(InputStream in, AES aes) {
        super(in, aes.getBlockSize());
        this.aes = Objects.requireNonNull(aes);
    }

    @Override
    protected byte[] decrypt(byte[] b, int off, int len) {
        return aes.decrypt(b, off, len);
    }
}
