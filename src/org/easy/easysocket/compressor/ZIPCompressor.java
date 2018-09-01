package org.easy.easysocket.compressor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * ZIP压缩器
 */
public class ZIPCompressor implements Compressor {

    @Override
    public OutputStream compress(OutputStream os) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(os);
        zos.putNextEntry(new ZipEntry("0"));
        return zos;
    }

    @Override
    public InputStream decompress(InputStream is) throws IOException {
        ZipInputStream zis = new ZipInputStream(is);
        zis.getNextEntry();
        return zis;
    }
}
