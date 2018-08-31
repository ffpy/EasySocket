package org.easy.easysocket.protocol;

import java.io.*;

/**
 * 默认的数据传输协议
 * 在首部添加数据长度
 */
public class DefaultDataProtocol implements DataProtocol {
    private static final int BUF_SIZE = 1024;

    @Override
    public int write(InputStream from, OutputStream to) throws IOException {
        DataOutputStream dos = new DataOutputStream(to);
        dos.writeInt(from.available());
        byte[] buf = new byte[BUF_SIZE];
        for (;;) {
            int n = from.read(buf);
            if (n <= 0) break;
            dos.write(buf, 0, n);
        }
        dos.flush();
        return dos.size();
    }

    @Override
    public InputStream read(InputStream from) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataInputStream dis = new DataInputStream(from);
        int size = dis.readInt();
        byte[] buf = new byte[BUF_SIZE];
        for (int cnt = 0; cnt < size;) {
            int n = dis.read(buf, 0, Math.min(buf.length, size - cnt));
            if (n <= 0) break;
            bos.write(buf, 0, n);
            cnt += n;
        }
        bos.close();
        return new ByteArrayInputStream(bos.toByteArray());
    }
}
