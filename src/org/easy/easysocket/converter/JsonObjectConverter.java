package org.easy.easysocket.converter;

import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * JSON对象转换器
 */
public class JsonObjectConverter implements ObjectConverter {
	private Charset charset = Charset.forName("UTF-8");

    @Override
    public InputStream fromObj(Object o) throws IOException {
        byte[] b = new Gson().toJson(o).getBytes(charset);
        ByteArrayInputStream bis = new ByteArrayInputStream(b);
        bis.close();
        return bis;
    }

    @Override
    public <T> T toObj(InputStream is, Class<T> c) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(is);
        char[] buf = new char[1024];
        for (;;) {
            int n = isr.read(buf);
            if (n <= 0) break;
            sb.append(buf, 0, n);
        }
        isr.close();
        return new Gson().fromJson(sb.toString(), c);
    }
}
