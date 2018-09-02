package org.easy.easysocket.encryptor.aes;

import org.easy.easysocket.encryptor.CipherHelper;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class AES extends CipherHelper {

    /**
     * @param key 密钥，长度必须为16的倍数
     */
    AES(byte[] key) {
        super("AES/ECB/PKCS5Padding", 16, key);
    }

    @Override
    protected SecretKey generateKey(byte[] key) {
        return new SecretKeySpec(key, "AES");
    }
}
