package org.easy.easysocket.converter;

import java.io.*;

/**
 * 序列化对象转换器
 */
public class SerializeObjectConverter implements ObjectConverter {

    @Override
    public void fromObj(OutputStream os, Object o) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(o);
        oos.close();
    }

    @Override
    public <T> T toObj(InputStream is, Class<T> c) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            @SuppressWarnings("unchecked") T o = (T) ois.readObject();
            return o;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
