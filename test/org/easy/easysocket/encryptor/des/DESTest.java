package org.easy.easysocket.encryptor.des;

import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DESTest {

    @Test
    public void testEncrypt() {
        DES des = new DES("12345678".getBytes());
        byte[] b = "1234".getBytes(Charset.forName("UTF-8"));
        b = des.encrypt(b, 0, b.length);
        System.out.println(Arrays.toString(b));

        b = des.decrpty(b, 0, b.length);
        System.out.println(new String(b, 0, b.length, Charset.forName("UTF-8")));
    }

    @Test
    public void testDecrpty() {
    }
}