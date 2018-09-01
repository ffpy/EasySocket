package org.easy.easysocket;

import org.easy.easysocket.converter.JsonObjectConverter;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EasySocketTest {

    @Test
    public void test() throws InterruptedException {
        new ServerThread().start();
        TimeUnit.MILLISECONDS.sleep(1000);
        new ClientThread().start();
        TimeUnit.SECONDS.sleep(5);
    }

    private class ClientThread extends Thread {
        @Override
        public void run() {
            try {
                EasySocket socket = new EasySocket("127.0.0.1", 7777);
                socket.setObjectConverter(new JsonObjectConverter());
                socket.setCompressor(null);

                int n = socket.send("ababababababababababababababababababababababababababababababababab");
                System.out.println("ClientThread send " + n + " bytes");
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class ServerThread extends Thread {
        @Override
        public void run() {
            try {
                EasyServerSocket serverSocket = new EasyServerSocket(7777,
                        Executors.newFixedThreadPool(10));
                serverSocket.setObjectConverter(new JsonObjectConverter());
                serverSocket.setCompressor(null);

                serverSocket.listen(socket -> {
//                    String s = socket.receive(String.class);
                    byte[] b = socket.read();
                    String s = new String(b, Charset.forName("UTF-8"));
                    System.out.println("ServerThread receive: " + s);
                    socket.close();
                    return false;
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}