package org.easy.easysocket.protocol;

import java.io.*;

/**
 * 默认的数据传输协议
 * 在首部添加数据长度
 */
public class DefaultDataProtocol implements DataProtocol {
    private static final int BUF_SIZE = 1024;

    @Override
    public int write(byte[] b, int off, int len, OutputStream os) throws IOException {
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeInt(len);
        dos.write(b, off, len);
        dos.flush();
        return dos.size();
    }

    @Override
    public byte[] read(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataInputStream dis = new DataInputStream(is);
        int size = dis.readInt();
        byte[] buf = new byte[BUF_SIZE];
        for (int cnt = 0; cnt < size;) {
            int n = dis.read(buf, 0, Math.min(buf.length, size - cnt));
            if (n <= 0) break;
            bos.write(buf, 0, n);
            cnt += n;
        }
        bos.close();
        return bos.toByteArray();
    }
}
