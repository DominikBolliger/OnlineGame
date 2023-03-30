package app.server.logic;

import app.init.Init;
import app.server.modell.Player;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServerMain {

    public static void main(String[] args) {
        List<Player> playerList = new ArrayList<>();
        try (ServerSocket serversocket = new ServerSocket(5000)){
            while(true) {
                System.out.println("waiting for connections");
                Socket socket = serversocket.accept();
                System.out.println("Client connected " + socket.getInetAddress());
                if (playerList.size() <= Init.AMOUNT_PLAYER) {
                    Player player = new Player(playerList.size() + 1, new ServerThread(socket, playerList));
                    playerList.add(player);
                    player.getThread().start();
                }
            }
        } catch (Exception e) {
            System.out.println("Error occured in main: " + e.getStackTrace());
        }
    }
}
