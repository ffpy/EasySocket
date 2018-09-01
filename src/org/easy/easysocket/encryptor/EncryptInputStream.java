package org.easy.easysocket.encryptor;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class EncryptInputStream extends FilterInputStream {
    /** 解密缓冲区 */
    private final byte[] buf;
    /** 缓冲区的有效字节数 */
    private int size;
    /** 读取缓冲区 */
    private final byte[] rbuf;

    protected EncryptInputStream(InputStream in) {
        super(in);
        this.buf = new byte[getBlockSize()];
        this.rbuf = new byte[getBlockSize()];
    }

    /**
     * 获取解密的块的大小
     *
     * @return 解密的块的大小
     */
    protected abstract int getBlockSize();

    /**
     * 解密数据块
     *
     * @param b   要解密的数据块
     * @param off 数据块的偏移量
     * @param len 数据块的长度
     * @return 解密后的数据块
     */
    protected abstract byte[] decrpty(byte[] b, int off, int len);

    @Override
    public int read() throws IOException {
        int len = read(rbuf, 0, 1);
        if (len <= 0) return -1;
        return rbuf[0] & 0xff;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException("Null buffer for read");
        } else if ((off | len | (off + len) | (b.length - (off + len))) < 0) {
            throw new IndexOutOfBoundsException();
        }

        int cnt = 0;
        // 复制解密缓冲区中已有的数据
        if (size > 0) {
            int copyLen = Math.min(len, size);
            System.arraycopy(buf, 0, b, off, copyLen);
            if (size > copyLen) {
                System.arraycopy(buf, copyLen, buf, 0, size - copyLen);
            }
            off += copyLen;
            len -= copyLen;
            size -= copyLen;
            cnt += copyLen;
        }

        if (len > 0) {
            for (;;) {
                int n = in.read(rbuf);
                if (n <= 0) break;
                byte[] decrptyBytes = decrpty(rbuf, 0, n);
                int copyLen = Math.min(len, decrptyBytes.length);
                System.arraycopy(decrptyBytes, 0, b, off, copyLen);
                off += copyLen;
                len -= copyLen;
                cnt += copyLen;

                if (len <= 0) {
                    int remainLen = decrptyBytes.length - copyLen;
                    // 把多余的数据保存到缓冲区
                    if (remainLen > 0) {
                        System.arraycopy(decrptyBytes, copyLen, buf, size, remainLen);
                        size += remainLen;
                    }
                    break;
                }
            }
        }

        return cnt;
    }

    @Override
    public long skip(long n) throws IOException {
        return read(new byte[(int) n]);
    }

    @Override
    public int available() throws IOException {
        return in.available() > 0 ? 1 : 0;
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public synchronized void mark(int readlimit) {

    }

    @Override
    public synchronized void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }
}
