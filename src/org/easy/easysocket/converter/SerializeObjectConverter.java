package org.easy.easysocket.converter;

import java.io.*;

/**
 * 序列化对象转换器
 */
public class SerializeObjectConverter implements ObjectConverter {
    @Override
    public InputStream fromObj(Object o) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(o);
        oos.close();
        return new ByteArrayInputStream(bos.toByteArray());
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
