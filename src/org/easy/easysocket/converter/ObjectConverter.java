package org.easy.easysocket.converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 对象转换器
 */
public interface ObjectConverter {

    void fromObj(OutputStream os, Object o) throws IOException;

    <T> T toObj(InputStream is, Class<T> c) throws IOException;
}
