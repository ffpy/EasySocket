package org.easy.easysocket;

import org.easy.easysocket.converter.JsonObjectConverter;
import org.easy.easysocket.converter.SerializeObjectConverter;
import org.easy.easysocket.process.GZIPCompressor;
import org.easy.easysocket.process.ZIPCompressor;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EasySocketTest {

    @Test
    public void test() throws InterruptedException {
        new ServerThread().start();
        TimeUnit.MILLISECONDS.sleep(500);
        new ClientThread().start();
        TimeUnit.SECONDS.sleep(5);
    }

    private class ClientThread extends Thread {
        @Override
        public void run() {
            try {
                EasySocket socket = new EasySocket("127.0.0.1", 7777);
                socket.setObjectConverter(new JsonObjectConverter());
//                socket.setObjectConverter(new SerializeObjectConverter());

                socket.setCompressor(new ZIPCompressor());
//                socket.setCompressor(new GZIPCompressor());

                int n = socket.send(new Person("Hello", 20));
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
//                serverSocket.setObjectConverter(new SerializeObjectConverter());

                serverSocket.setCompressor(new ZIPCompressor());
//                serverSocket.setCompressor(new GZIPCompressor());

                serverSocket.listen(socket -> {
                    Person p = socket.receive(Person.class);
                    System.out.println("ServerThread receive: " + p);
                    socket.close();
                    return false;
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}