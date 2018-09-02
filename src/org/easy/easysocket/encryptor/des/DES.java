package org.easy.easysocket.encryptor.des;

import org.easy.easysocket.encryptor.CipherHelper;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES加解密器
 */
class DES extends CipherHelper {

    /**
     * @param key 密钥，长度必须为8的倍数
     */
    DES(byte[] key) {
        super("DES", 8, key);
    }

    @Override
    protected SecretKey generateKey(byte[] key) {
        try {
            DESKeySpec desKeySpec = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            return keyFactory.generateSecret(desKeySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
