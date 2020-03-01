package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {

    @FXML
    TextField messageField;
    @FXML
    TextArea textArea;
    @FXML
    MenuItem connectionMenu;


    @FXML
    void sendMessage() {
        textArea.setWrapText(true);

        String message = messageField.getText();
        textArea.appendText(message + "\n");
        messageField.clear();
    }

    @FXML
    void showConnection() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConnectionWindow.fxml"));
        try {
            Parent connectionWindow = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Connection");
            stage.setResizable(false);
            stage.setScene(new Scene(connectionWindow));
            stage.show();

        } catch (IOException e) {
            System.out.println("An error ocurred while trying to load new window: " + e.getMessage());
        }
    }
}
