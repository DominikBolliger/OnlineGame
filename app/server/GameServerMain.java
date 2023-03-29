package app.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServerMain {
    public static void main(String[] args) {
        ArrayList<ServerThread> threadList = new ArrayList<>();
        try (ServerSocket serversocket = new ServerSocket(5000)){
            while(true) {
                System.out.println("waiting for connections");
                Socket socket = serversocket.accept();
                System.out.println("Client connected " + socket.getInetAddress());
                ServerThread serverThread = new ServerThread(socket, threadList);
                threadList.add(serverThread);
                serverThread.start();
            }
        } catch (Exception e) {
            System.out.println("Error occured in main: " + e.getStackTrace());
        }
    }
}
