package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sockets.Client;
import java.io.IOException;

public class Controller {

    @FXML
    TextField usernameInput;
    @FXML
    TextField ipAddressInput;
    @FXML
    TextField portInput;
    @FXML
    TextField messageField;
    @FXML
    TextArea textArea;

    public String username;
    public String ipAddress;
    public String portString;
    public int port;

    public static Client newClient;

    @FXML
    public void connect() {
        username = usernameInput.getText();
        ipAddress = ipAddressInput.getText();
        portString = portInput.getText();

        boolean isInt = true;
        try {
            Integer.parseInt(portString);
        } catch (Exception e) {
            isInt = false;
        }

        if ((username.isEmpty() || ipAddress.isEmpty() || !isInt)) {
            System.out.println("Please enter correct values for all of the fields");
        } else {
            port = Integer.parseInt(portString);

            newClient = new Client(ipAddress, port, username);
            String newUser = "±" + username;
            this.sendUsername(newUser);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChatWindow.fxml"));
            try {
                Parent chatWindow = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Talky Chat - " + username);
                stage.setResizable(false);
                stage.setScene(new Scene(chatWindow));
                stage.show();

            } catch (IOException e) {
                System.out.println("An error occurred while trying to load new window: " + e.getMessage());
            }
        }
    }

    public void sendUsername(String username) {
        newClient.sendMessage(username);
    }

    public void sendMessage() {
        String message;
        if (!messageField.getText().equals("")) {
            message = messageField.getText();
            messageField.clear();
            textArea.appendText("You: " + message);
            newClient.sendMessage(message);
        }
    }

    public void showIncoming(String message) {
        textArea.appendText(message);
    }
}
