package org.easy.easysocket.converter;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * JSON对象转换器
 */
public class JsonObjectConverter implements ObjectConverter {
    private static final int BUF_SIZE = 1024;
	private Charset charset = Charset.forName("UTF-8");

    @Override
    public void fromObj(OutputStream os, Object o) throws IOException {
        byte[] b = new Gson().toJson(o).getBytes(charset);
        os.write(b);
        os.close();
    }

    @Override
    public <T> T toObj(InputStream is, Class<T> c) throws IOException {
        StringBuilder sb = new StringBuilder();
        byte[] buf = new byte[BUF_SIZE];
        for (;;) {
            int n = is.read(buf);
            if (n <= 0) break;
            sb.append(new String(buf, 0, n, charset));
        }
        return new Gson().fromJson(sb.toString(), c);
    }
}
