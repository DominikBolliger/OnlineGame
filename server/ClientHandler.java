package server;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socketToClient;
    PrintWriter serverOut;
    BufferedReader serverIn;

    public ClientHandler(Socket socketToClient) {
        super();
        this.socketToClient = socketToClient;
    }

    public void run() {
        String line;
        BufferedReader is;
        PrintStream os;
        try {
            is = new BufferedReader(new InputStreamReader(socketToClient.getInputStream()));
            os = new PrintStream(socketToClient.getOutputStream());
            while (true) {
                line = is.readLine();
                System.out.println("Received " + line);
                int n = Integer.parseInt(line);
                if (n == -1) {
                    break;
                }
                if (n == 0) break;
                os.println("" + n * n);
            }

            System.out.println("Connection closed.");
            is.close();
            os.close();
            socketToClient.close();
        } catch (IOException e) {
            System.out.println(e);
        }
//        try {
//            os = socketToClient.getOutputStream();
//            PrintWriter out = new PrintWriter(os);
//            String ticket = "1;2;3";
//            out.println(ticket);
//            out.flush();
//            out.close();
//            socketToClient.close();
//        } catch (IOException e) {
//            System.err.println("ERROR -- Could not open connection to client " + socketToClient.getInetAddress().getHostAddress());
//        }
    }
}
