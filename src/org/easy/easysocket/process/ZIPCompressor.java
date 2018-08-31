package org.easy.easysocket.process;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZIPCompressor implements Compressor {
    private static final int BUF_SIZE = 1024;

    @Override
    public InputStream compress(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(bos);
        zos.putNextEntry(new ZipEntry("0"));
        byte[] buf = new byte[BUF_SIZE];
        for (;;) {
            int n = is.read(buf);
            if (n <= 0) break;
            zos.write(buf, 0, n);
        }
        zos.closeEntry();
        zos.close();
        return new ByteArrayInputStream(bos.toByteArray());
    }

    @Override
    public InputStream decompress(InputStream is) throws IOException {
        ZipInputStream zis = new ZipInputStream(is);
        zis.getNextEntry();
        return zis;
    }
}
