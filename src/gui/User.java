package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Runs the log in window for the users
 */
public class User extends Application { // Aqui se crea una clase, y se hereda de la clase Application

    /**
     * Loads the log in window for users and configures its settings
     * @param primaryStage top level container which is the log in window
     * @throws Exception throws exception if errors occur while trying to load the PrimaryStage
     */
    @Override
    public void start(Stage primaryStage) throws Exception{ // Esto es un metodo y se esta sobreescribiendo
        Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        primaryStage.setTitle("Talky Chat");
        primaryStage.setScene(new Scene(root, 370, 290));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Runs the above ( start() ) method
     * @param args main method predefined parameter
     */
    public static void main(String[] args) {
        launch(args);
    }
}
