package app.init;

public class Util {
    public static void sleep(){
        try {
            Thread.sleep(Init.GAME_SPEED);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
