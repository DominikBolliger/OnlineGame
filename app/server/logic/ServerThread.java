package app.server.logic;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class ServerThread extends Thread {
    private Socket socket;
    private ArrayList<ServerThread> threadList;
    private PrintWriter output;

    public ServerThread(Socket socket, ArrayList<ServerThread> threads) {
        this.socket = socket;
        this.threadList = threads;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(),true);
            initiSend();

            while(true) {
                String outputString = input.readLine();
                System.out.println("Server received " + outputString);
                GameServerMain.setLayout(outputString);
                printToALlClients(outputString);

            }
        } catch (Exception e) {
            System.out.println("Error occured " +e.getStackTrace());
        }
    }

    private void initiSend() {
            printToALlClients(GameServerMain.getLayout());
    }

    private void printToALlClients(String outputString) {
        for( ServerThread sT: threadList) {
            sT.output.println(outputString);
        }
    }

//    private void printToRecipients(String outputString) {
//        for( ServerThread sT: threadList) {
//            if (sT != this){
//                sT.output.println(outputString);
//                System.out.println("sent to: " + sT.socket.getInetAddress());
//            }
//        }
//    }
}