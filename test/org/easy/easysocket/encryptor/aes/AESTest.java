package org.easy.easysocket.encryptor.aes;

import org.junit.Test;

import static org.junit.Assert.*;

public class AESTest {

    private void check(byte[] data) {
        AES des = new AES("1234567812345678".getBytes());
        byte[] encryptedData = des.encrypt(data, 0, data.length);
        byte[] decryptData = des.decrypt(encryptedData,0, encryptedData.length);
        assertArrayEquals(data, decryptData);
    }

    @Test
    public void test1() {
        check("1234".getBytes());
    }

    @Test
    public void test2() {
        check("12345678901234567980".getBytes());
    }

    @Test(expected = Exception.class)
    public void test3() {
        new AES(null);
    }

    @Test(expected = Exception.class)
    public void test4() {
        new AES(new byte[0]);
    }
}