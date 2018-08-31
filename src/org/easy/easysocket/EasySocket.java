package org.easy.easysocket;

import org.easy.easysocket.process.Compressor;
import org.easy.easysocket.process.Encryptor;
import org.easy.easysocket.protocol.DataProtocol;
import org.easy.easysocket.converter.ObjectConverter;
import org.easy.easysocket.protocol.DefaultDataProtocol;

import java.io.*;
import java.net.Socket;
import java.nio.channels.Channel;

public class EasySocket {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private ObjectConverter objectConverter;
    private Compressor compressor;
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
        InputStream is = objectConverter.fromObj(o);
        if (encryptor != null) is = encryptor.encrypt(is);
        if (compressor != null) is = compressor.compress(is);
        return dataProtocol.write(is, outputStream);
    }

    /**
     * 写入数据
     */
    public void write(byte[] b, int off, int len) throws IOException {
        outputStream.write(b, off, len);
    }

    /**
     * 接收对象
     */
    public <T> T receive(Class<T> c) throws IOException {
        InputStream is = dataProtocol.read(inputStream);
        if (compressor != null) is = compressor.decompress(is);
        if (encryptor != null) is = encryptor.decrypt(is);
        return objectConverter.toObj(is, c);
    }

    /**
     * 读取数据
     */
    public int read(byte[] b, int off, int len) throws IOException {
        return inputStream.read(b, off, len);
    }

    /**
     * 获取输入流
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * 获取输出流
     */
    public OutputStream getOutputStream() {
        return outputStream;
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
}
