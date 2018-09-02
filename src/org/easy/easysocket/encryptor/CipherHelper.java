package org.easy.easysocket.encryptor;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 加密辅助类
 */
public abstract class CipherHelper {
    /** 块的大小 */
    private int blockSize;
    /** 算法类型 */
    private String algorithmType;
    /** 随机数源 */
    private final SecureRandom random;
    /** 密钥 */
    private final SecretKey key;

    /**
     * @param algorithmType 算法类型
     * @param blockSize     块大小
     * @param key           密钥
     */
    public CipherHelper(String algorithmType, int blockSize, byte[] key) {
        this.algorithmType = algorithmType;
        this.blockSize = blockSize;
        this.random = new SecureRandom();
        this.key = generateKey(key);
    }

    /**
     * 根据密钥数组生成加解密密钥
     *
     * @param key 密钥数组
     * @return 加解密密钥
     */
    protected abstract SecretKey generateKey(byte[] key);

    /**
     * 获取块大小
     *
     * @return 块大小
     */
    public int getBlockSize() {
        return blockSize;
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
    public byte[] decrypt(byte[] b, int off, int len) {
        try {
            return getCipher(Cipher.DECRYPT_MODE).doFinal(b, off, len);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Cipher getCipher(int mode) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance(algorithmType);
        cipher.init(mode, key, random);
        return cipher;
    }
}
