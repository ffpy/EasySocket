package org.easy.easysocket.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPCompressor implements Compressor {

    @Override
    public OutputStream compress(OutputStream os) throws IOException {
        return new GZIPOutputStream(os);
    }

    @Override
    public InputStream decompress(InputStream is) throws IOException {
        return new GZIPInputStream(is);
    }
}
