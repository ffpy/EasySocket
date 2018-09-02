package org.easy.easysocket.encryptor.des;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DESEncryptorTest {
    private static final String password = "12345678";

    @Test
    public void test1() throws IOException {
        // 原数据
        byte[] data = "123456789".getBytes();
        // 加密数据
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DESOutputStream dos = new DESOutputStream(bos, new DES(password.getBytes()));
        dos.write(data);
        dos.close();
        byte[] encryptedData = bos.toByteArray();
        // 解密数据
        ByteArrayInputStream bis = new ByteArrayInputStream(encryptedData);
        DESInputStream dis = new DESInputStream(bis, new DES(password.getBytes()));
        byte[] buf = new byte[1024];
        int n = dis.read(buf);
        byte[] decryptedData = Arrays.copyOf(buf, n);
        // 判断加解密后是否为原数据
        assertArrayEquals(data, decryptedData);
    }

    @Test
    public void test2() throws IOException {
        final int blockSize = 2;
        // 原数据
        byte[] data = "123456789".getBytes();
        // 加密数据
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DESOutputStream dos = new DESOutputStream(bos, new DES(password.getBytes()));
        for (int i = 0; i < data.length; i += blockSize) {
            dos.write(data, i, Math.min(blockSize, data.length - i));
        }
        dos.close();
        byte[] encryptedData = bos.toByteArray();
        // 解密数据
        ByteArrayInputStream bis = new ByteArrayInputStream(encryptedData);
        DESInputStream dis = new DESInputStream(bis, new DES(password.getBytes()));
        byte[] buf = new byte[blockSize];
        int i = 0;
        for (;;) {
            int n = dis.read(buf);
            if (n <= 0) break;
            byte[] decryptedData = Arrays.copyOf(buf, n);
            // 判断加解密后是否为原数据
            byte[] dataBuf = new byte[n];
            System.arraycopy(data, i, dataBuf, 0, n);
            assertArrayEquals(dataBuf, decryptedData);

            i += n;
        }
    }
}