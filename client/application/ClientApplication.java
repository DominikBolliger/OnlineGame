package client.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ClientApplication extends Application {
    private static Rectangle2D screenSize;

    @Override
    public void start(Stage primaryStage) throws Exception {
        screenSize = Screen.getPrimary().getBounds();
        FXMLLoader loader = new FXMLLoader(ClientApplication.class.getResource("/client/view/Client-View.fxml"));
        Scene scene = new Scene(loader.load(), screenSize.getHeight()/2, screenSize.getHeight()/2);
        primaryStage.setScene(scene);
        primaryStage.setTitle("client");
        primaryStage.show();
    }
    public static Rectangle2D getScreenSize() {
        return screenSize;
    }
}
