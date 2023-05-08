package com.kcci.socketclient;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientThread extends Thread {

    @Override
    public void run() {
        Socket socket = null;
        try {
            socket = new Socket();
        //    System.out.println("[연결 요청]");
            displayText("[연결 요청]");
            socket.connect(new InetSocketAddress("10.10.141.253", 5000));
        //    System.out.println("[연결 성공]");
            displayText("[연결 성공]");

            byte[] bytes = null;
            String message = null;

            OutputStream os = socket.getOutputStream();
            message = "Hello Server";
            bytes = message.getBytes("UTF-8");
            os.write(bytes);
            os.flush();
    //        System.out.println("[데이터 보내기 성공]");
            displayText("[데이터 보내기 성공]");
            InputStream is = socket.getInputStream();
            bytes = new byte[100];
            int readByteCount = is.read(bytes);
            message = new String(bytes, 0, readByteCount, "UTF-8");
//            System.out.println("[데이터 받기 성공]: " + message);
            displayText("[데이터 받기 성공]: " + message);
            os.close();
            is.close();
        } catch (Exception e) {
//            System.out.println("서버가 중지되었습니다");
            displayText("서버가 중지되었습니다");
        }

        if (!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e1) {
            }
        }
//        System.out.println("클라이언트가 종료되었습니다");
//        displayText("서버가 중지되었습니다");
    }
    synchronized void displayText(String text) {
        Log.d("displayText",text);
    }
}
