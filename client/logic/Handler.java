package client.logic;

import java.io.*;
import java.net.Socket;

public class Handler extends Thread{
    private Socket clientSocket;
    private boolean run = true;

    @Override
    public void run() {
        Handler handler = new Handler();
        handler.setUp();
        handler.getData();
    }

    private void setUp() {
        try {
            clientSocket = new Socket("localhost", 54321);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getData() {
        String data = "";
        try {
            char[] buffer = new char[2048];
            int charsRead = 0;
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while ((charsRead = in.read(buffer)) != -1) {
                String message = new String(buffer).substring(0, charsRead);
                System.out.println(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[] components = data.split(";");
        for (String string : components){
            System.out.println(string);
        }

    }

    private void close() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void send() {
        try {
            clientSocket = new Socket("localhost", 54321);
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            out.writeBytes("test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
