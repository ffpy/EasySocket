package org.easy.easysocket.converter;

import java.io.IOException;
import java.io.InputStream;

/**
 * 对象转换器
 */
public interface ObjectConverter {

    InputStream fromObj(Object o) throws IOException;

    <T> T toObj(InputStream is, Class<T> c) throws IOException;
}
