package app.client.logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigData {
    private static int port;
    private static String IP;

    public static void loadData(){
        Properties prop = new Properties();
        String fileName = "C:\\OnlineGame\\app.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        port = Integer.valueOf(prop.getProperty("PORT"));
        IP = prop.getProperty("IP");
    }

    public static int getPort() {
        return port;
    }

    public static String getIP() {
        return IP;
    }
}
