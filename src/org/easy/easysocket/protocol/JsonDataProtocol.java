package org.easy.easysocket.protocol;

import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.Charset;

public class JsonDataProtocol implements DataProtocol {
	private Charset charset = Charset.forName("UTF-8");

	@Override
	public int send(OutputStream os, Object o) throws IOException {
		byte[] bytes = obj2Bytes(o);
		DataOutputStream dos = new DataOutputStream(os);
		dos.writeInt(bytes.length);
		dos.write(bytes);
		dos.flush();
		return dos.size();
	}

	@Override
	public <T> T receive(InputStream is, Class<T> oClass) throws IOException {
		DataInputStream dis = new DataInputStream(is);
		int size = dis.readInt();
		byte[] bytes = new byte[size];
		int offset = 0;
		for (;;) {
			int n = dis.read(bytes, offset, size - offset);
			if (n <= 0) break;
			offset += n;
		}
		return bytes2Obj(bytes, size, oClass);
	}

	private byte[] obj2Bytes(Object o) {
		return new Gson().toJson(o).getBytes(charset);
	}

	private <T> T bytes2Obj(byte[] bytes, int n, Class<T> objClass) {
		String json = new String(bytes, 0, n, charset);
		System.out.println(json.length());
		return new Gson().fromJson(json, objClass);
	}
}
