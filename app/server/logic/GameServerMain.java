package app.server.logic;

import app.init.Init;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServerMain {

    private static String layout = "xxxxxxxxxxxxxxxxxxxxxxxxx";

    public static void main(String[] args) {
        ArrayList<ServerThread> threadList = new ArrayList<>();
        try (ServerSocket serversocket = new ServerSocket(5000)){
            while(true) {
                System.out.println("waiting for connections");
                Socket socket = serversocket.accept();
                System.out.println("Client connected " + socket.getInetAddress());
                if (threadList.size() <= Init.AMOUNT_PLAYER) {
                    ServerThread serverThread = new ServerThread(socket, threadList);
                    threadList.add(serverThread);
                    serverThread.start();
                }
            }
        } catch (Exception e) {
            System.out.println("Error occured in main: " + e.getStackTrace());
        }
    }

    public static String getLayout() {
        return layout;
    }

    public static void setLayout(String layout) {
        GameServerMain.layout = layout;
    }
}
