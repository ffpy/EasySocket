package org.easy.easysocket.encryptor.des;

import org.junit.Test;

import static org.junit.Assert.*;

public class DESTest {

    private void check(byte[] data) {
        DES des = new DES("12345678".getBytes());
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
        new DES(null);
    }

    @Test(expected = Exception.class)
    public void test4() {
        new DES(new byte[0]);
    }
}