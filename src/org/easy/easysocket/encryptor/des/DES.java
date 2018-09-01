package org.easy.easysocket.encryptor.des;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * DES加解密器
 */
class DES {
    /** 块的大小 */
    public static final int BLOCK_SIZE = 8;
    /** 算法类型 */
    private static final String ALGORITHM_TYPE = "DES";
    /** 随机数源 */
    private final SecureRandom random;
    /** 密钥 */
    private final SecretKey key;

    /**
     * @param password 密钥，长度必须是8的倍数
     */
    DES(byte[] password) {
        try {
            random = new SecureRandom();
            DESKeySpec desKeySpec = new DESKeySpec(password);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_TYPE);
            key = keyFactory.generateSecret(desKeySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密数据
     *
     * @param b 要加密的字节数组
     * @return 加密后的字节数组
     */
    public byte[] encrypt(byte[] b, int off, int len) {
        try {
            return getCipher(Cipher.ENCRYPT_MODE).doFinal(b, off, len);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密数据
     *
     * @param b 要解密的字节数组
     * @return 解密后的字节数组
     */
    public byte[] decrpty(byte[] b, int off, int len) {
        try {
            return getCipher(Cipher.DECRYPT_MODE).doFinal(b, off, len);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Cipher getCipher(int mode) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance(ALGORITHM_TYPE);
        cipher.init(mode, key, random);
        return cipher;
    }
}
