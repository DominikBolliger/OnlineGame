package app.client.logic;

import app.client.model.Enemy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientRunnable implements Runnable {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public ClientRunnable(Socket socket) {
        try {
            this.socket = socket;
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

        try {
            while (true) {
                String response = input.readLine();
                if (response.startsWith("PlayerInfo")) {
                    String[] cont = response.split(";");
                    movePlayer(Integer.valueOf(cont[1]), Integer.valueOf(cont[3]), Integer.valueOf(cont[4]));
                } else if (response.startsWith("NewPlayer")) {
                    String[] cont = response.split(";");
                    createNewPlayer(Integer.parseInt(cont[1]));
                } else if (response.startsWith("ActivePlayer")) {
                    String[] cont = response.split(";");
                    createNewPlayer(Integer.parseInt(cont[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void movePlayer(Integer playerNumber, Integer x, Integer y) {
        for (Enemy enemy : Game.getEnemyList()){
            if (enemy.getEnemyNumber() == playerNumber){
                enemy.setX(x);
                enemy.setY(y);
            }
        }
    }

    private void createNewPlayer(int playerNumber) {
        Game.setEnemyList(new Enemy(100, 100, 10, playerNumber));
    }

}