package orig;

import java.net.*;
import java.io.*;

/**
 * Diese Klasse stellt den eigentlichen Server dar. Sie startet den Server am
 * Socketport HOST_PORT. Dieser wartet dann auf eine ankommende Verbindung.
 * Sobald sich ein Client verbindet, wird ein neuer Thread gestartet, welcher
 * die Kommunikation mit dem Client übernimmt.
 * <p>
 * Der Code nutzt das Beipiel aus dem Lehrmittel (S. 32ff)
 *
 * @author Andreas Wassmer
 * @version 1.0
 */

public class OrakelServer extends Thread {
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
