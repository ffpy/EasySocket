package org.easy.easysocket.protocol;

import java.io.*;

/**
 * 序列化数据交换协议
 */
public class SerializableDataProtocol implements DataProtocol {
	@Override
	public int send(OutputStream os, Object o) throws IOException {
		DataOutputStream dos = new DataOutputStream(os);
		ObjectOutputStream oos = new ObjectOutputStream(dos);
		oos.writeObject(o);
		os.flush();
		return dos.size();
	}

	@Override
	public <T> T receive(InputStream is, Class<T> oClass) throws IOException {
		ObjectInputStream ois = new ObjectInputStream(is);
		try {
			//noinspection unchecked
			return (T) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
