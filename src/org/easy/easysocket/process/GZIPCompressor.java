package org.easy.easysocket.process;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPCompressor implements Compressor {
    private static final int BUF_SIZE = 1024;

    @Override
    public InputStream compress(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gos = new GZIPOutputStream(bos);
        byte[] buf = new byte[BUF_SIZE];
        for (;;) {
            int n = is.read(buf);
            if (n <= 0) break;
            gos.write(buf, 0, n);
        }
        gos.close();
        return new ByteArrayInputStream(bos.toByteArray());
    }

    @Override
    public InputStream decompress(InputStream is) throws IOException {
        return new GZIPInputStream(is);
    }
}
