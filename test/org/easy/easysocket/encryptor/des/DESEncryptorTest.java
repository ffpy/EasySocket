package org.easy.easysocket.encryptor.des;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DESEncryptorTest {

    @Test
    public void test() throws IOException {
        byte[] b = "123456789".getBytes();
        System.out.println("原数据：" + Arrays.toString(b));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DESOutputStream dos = new DESOutputStream(bos, new DES("12345678".getBytes()));
        dos.write(b);
        dos.close();
        b = bos.toByteArray();
        System.out.println("加密后：" + Arrays.toString(b));

        ByteArrayInputStream bis = new ByteArrayInputStream(b);
        DESInputStream dis = new DESInputStream(bis, new DES("12345678".getBytes()));
        byte[] buf = new byte[2];
        int n = dis.read(buf);
        b = Arrays.copyOf(buf, n);
        System.out.println("解密后：" + Arrays.toString(b));

        buf = new byte[2];
        n = dis.read(buf);
        b = Arrays.copyOf(buf, n);
        System.out.println("解密后：" + Arrays.toString(b));
        buf = new byte[2];
        n = dis.read(buf);
        b = Arrays.copyOf(buf, n);
        System.out.println("解密后：" + Arrays.toString(b));
        buf = new byte[2];
        n = dis.read(buf);
        b = Arrays.copyOf(buf, n);
        System.out.println("解密后：" + Arrays.toString(b));
        buf = new byte[2];
        n = dis.read(buf);
        b = Arrays.copyOf(buf, n);
        System.out.println("解密后：" + Arrays.toString(b));
    }
}