package app.server.logic;


import app.server.modell.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;


public class ServerThread extends Thread {
    private Socket socket;
    private List<Player> playerList;
    private PrintWriter output;
    private BufferedReader input;

    public ServerThread(Socket socket, List<Player> players) {
        this.socket = socket;
        this.playerList = players;
        try {
            this.output = new PrintWriter(socket.getOutputStream(), true);
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            newPlayerSend();
            activePlayersSend();
            while (true) {
                String outputString = input.readLine();
                int playerNumber = getPlayerNumber();
                System.out.println("Server received " + outputString + " from Player: " + playerNumber);
                sendPositionsToPlayers(playerNumber, outputString);
            }
        } catch (Exception e) {
            System.out.println("Error occured " + e.getStackTrace());
        }
    }

    private void newPlayerSend() {
        for (Player player : playerList){
            if (player.getThread() != this){
                player.getThread().output.println("NewPlayer;" + getPlayerNumber());
            }
        }
    }

    private void activePlayersSend(){
        Player thisPlayer = getPlayer();
        for (Player player : playerList){
            if (player != getPlayer()){
                thisPlayer.getThread().output.println("ActivePlayer;" + player.getPlayerNumber());
            }
        }
    }

    private void sendPositionsToPlayers(int playerNumber, String pos) {
        for (Player player : playerList){
            if (player.getThread() != this){
                player.getThread().output.println("PlayerInfo;" + playerNumber + ";Pos;" + pos);
            }
        }
    }

    private int getPlayerNumber() {
        int playerNumber = 0;
        for (Player player : playerList) {
            if (player.getThread() == this){
                playerNumber = player.getPlayerNumber();
            }
        }
        return playerNumber;
    }

    private Player getPlayer(){
        Player thisPlayer = null;
        for (Player player : playerList){
            if (player.getThread() == this) {
                thisPlayer = player;
            }
        }
        return thisPlayer;
    }

    private void printToALlClients(String outputString) {
        for (Player player : playerList) {
            player.getThread().output.println(outputString);
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