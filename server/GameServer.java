package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer extends Thread {
    private static final int HOST_PORT = 54321;

    public static void main(String[] args) throws IOException {
        ServerSocket srvSocket = new ServerSocket(HOST_PORT);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                srvSocket.close();
                System.out.println("The server is shut down!");
            } catch (IOException e) {
                System.err.println("ERROR -- Could not close socket");
            }
        }));

        while (true) {
            System.out.println("Server running, waiting for connection...");
            Socket socketToClient = srvSocket.accept();
            System.out.println("Connection accepted from " + socketToClient.getInetAddress().getHostAddress());
            ClientHandler handler = new ClientHandler(socketToClient);
            handler.start();
        }
    }
}
