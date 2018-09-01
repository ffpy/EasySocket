package org.easy.easysocket.encryptor;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class EncryptOutputStream extends FilterOutputStream {
    /** 加密缓冲区 */
    private byte[] buf;
    /** 已经写入加密缓冲区的字节数 */
    private int pos;

    public EncryptOutputStream(OutputStream out) {
        super(out);
        this.buf = new byte[getBlockSize()];
    }

    /**
     * 获取加密的块的大小
     *
     * @return 加密的块的大小
     */
    protected abstract int getBlockSize();

    /**
     * 加密数据块
     *
     * @param b   要加密的数据块
     * @param off 数据块的偏移量
     * @param len 数据块的长度
     * @return 加密后的数据块
     */
    protected abstract byte[] encrypt(byte[] b, int off, int len);

    @Override
    public void write(int b) throws IOException {
        byte[] buf = new byte[1];
        buf[0] = (byte) (b & 0xff);
        write(buf, 0, 1);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if ((off | len | (off + len) | (b.length - (off + len))) < 0) {
            throw new IndexOutOfBoundsException();
        }
        // 写入缓冲区
        while (len > 0) {
            int copyLen = Math.min(len, buf.length - pos);
            System.arraycopy(b, off, buf, pos, copyLen);
            off += copyLen;
            len -= copyLen;
            pos += copyLen;
            // 缓冲区已满，刷新缓冲区
            if (pos >= buf.length) {
                flushBuf();
            }
        }
    }

    @Override
    public void close() throws IOException {
        flushBuf();
        out.close();
    }

    /**
     * 刷新缓冲区，把缓冲区中的数据加密并写入到流中
     *
     * @throws IOException IO异常
     */
    private void flushBuf() throws IOException {
        if (pos <= 0) return;
        byte[] encryptBytes = encrypt(buf, 0, pos);
        out.write(encryptBytes);
        pos = 0;
    }
}
