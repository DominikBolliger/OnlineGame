package orig;

import java.net.*;
import java.io.*;

public class OrakelClient {
    private static final String HOSTNAME = "localhost";

    private static final int PORTNUMBER = 54321;

    private Socket clientSocket;

    public static void main(String argv[]) {
        OrakelClient sc = new OrakelClient();
        try {
            sc.setUp(HOSTNAME, PORTNUMBER);
            sc.communicate();
            sc.close();
        } catch (UnknownHostException e) {
            System.out.println("ERROR -- Host unknown!");
        } catch (IOException e) {
            System.out.println("ERROR -- Cannot connect to port");
        }
    }

    private void setUp(String serveraddress, int serverport) throws UnknownHostException, IOException {
        clientSocket = new Socket(serveraddress, serverport);
    }

    private void communicate() throws IOException {
        InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
        BufferedReader in = new BufferedReader(isr);
        String spell = in.readLine();

        String[] components = spell.split(";");

        System.out.println("Spruch: " + components[0]);
        System.out.println("Zahl  : " + components[1]);
        System.out.println("Symbol: " + components[2]);
    }

    private void close() throws IOException {
        clientSocket.close();
    }
}
