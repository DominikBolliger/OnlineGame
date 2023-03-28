package orig;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Diese Klasse kommuniziert mit dem Client. Sie erzeugt die einzelnen
 * String für das Glücksticket. Die Klasse wird in einem neuen Thread
 * vom Orig.OrakelServer aufgerufen.
 *
 * @author Andreas Wassmer
 * @version 1.0
 */

public class ClientHandler extends Thread {
    private Socket socketToClient;

    public ClientHandler(Socket socketToClient) {
        super();
        this.socketToClient = socketToClient;
    }

    public void run() {
        Orakel orakel = new Orakel();
        OutputStream os;
        try {
            os = socketToClient.getOutputStream();
            PrintWriter out = new PrintWriter(os);
            String ticket = orakel.getLuckySpell() + ";" + orakel.getLuckyNumber() + ";" + orakel.getLuckySymbol();
            System.out.println("send");
            out.println(ticket);
            out.flush();
            out.close();
            socketToClient.close();
        } catch (IOException e) {
            System.err.println("ERROR -- Could not open connection to client " + socketToClient.getInetAddress().getHostAddress());
        }
    }
}
