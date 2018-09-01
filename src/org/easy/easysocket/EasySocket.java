package org.easy.easysocket;

import org.easy.easysocket.converter.ObjectConverter;
import org.easy.easysocket.converter.SerializeObjectConverter;
import org.easy.easysocket.process.Compressor;
import org.easy.easysocket.process.Encryptor;
import org.easy.easysocket.process.GZIPCompressor;
import org.easy.easysocket.protocol.DataProtocol;
import org.easy.easysocket.protocol.DefaultDataProtocol;

import java.io.*;
import java.net.Socket;
import java.nio.channels.Channel;

public class EasySocket {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private ObjectConverter objectConverter = new SerializeObjectConverter();
    private Compressor compressor = new GZIPCompressor();
    private Encryptor encryptor;
    private DataProtocol dataProtocol = new DefaultDataProtocol();

    public EasySocket(String host, int port) throws IOException {
        this(new Socket(host, port));
    }

    public EasySocket(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
    }

    public void setObjectConverter(ObjectConverter objectConverter) {
        this.objectConverter = objectConverter;
    }

    public void setCompressor(Compressor compressor) {
        this.compressor = compressor;
    }

    public void setEncryptor(Encryptor encryptor) {
        this.encryptor = encryptor;
    }

    public void setDataProtocol(DataProtocol dataProtocol) {
        this.dataProtocol = dataProtocol;
    }

    /**
     * 发送对象
     */
    public int send(Object o) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        OutputStream os = wrapOutputStream(bos);
        objectConverter.fromObj(os, o);
        os.close();
        return write(bos.toByteArray());
    }

    /**
     * 写入数据
     */
    public int write(byte[] b) throws IOException {
        return write(b, 0, b.length);
    }

    /**
     * 写入数据
     */
    public int write(byte[] b, int off, int len) throws IOException {
        return dataProtocol.write(b, off, len, outputStream);
    }

    /**
     * 接收对象
     */
    public <T> T receive(Class<T> c) throws IOException {
        InputStream is = new ByteArrayInputStream(read());
        is = wrapInputStream(is);
        return objectConverter.toObj(is, c);
    }

    /**
     * 读取数据
     */
    public byte[] read() throws IOException {
        return dataProtocol.read(inputStream);
    }

    /**
     * 获取输入流
     */
    public InputStream getInputStream() throws IOException {
        return wrapInputStream(inputStream);
    }

    /**
     * 获取输出流
     */
    public OutputStream getOutputStream() throws IOException {
        return wrapOutputStream(outputStream);
    }

    /**
     * 获取通道
     */
    public Channel getChannel() {
        return socket.getChannel();
    }

    /**
     * 关闭连接
     */
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 包装输入流
     *
     * @param is 原始输入流
     * @return 包装后的输入流
     * @throws IOException IO异常
     */
    private InputStream wrapInputStream(InputStream is) throws IOException {
        if (compressor != null) is = compressor.decompress(is);
        if (encryptor != null) is = encryptor.decrypt(is);
        return is;
    }

    /**
     * 包装输出流
     *
     * @param os 原始输出流
     * @return 包装后的输出流
     * @throws IOException IO异常
     */
    private OutputStream wrapOutputStream(OutputStream os) throws IOException {
        if (compressor != null) os = compressor.compress(os);
        if (encryptor != null) os = encryptor.encrypt(os);
        return os;
    }
}
