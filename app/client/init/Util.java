package app.client.init;

public class Util {
    public static void sleep(){
        try {
            Thread.sleep(Init.GAMESPEED);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
