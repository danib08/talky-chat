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

    @FXML
    public void connect() {
        String username = usernameInput.getText();
        String ipAddress = ipAddressInput.getText();
        String portString = portInput.getText();

        boolean isInt = true;
        try {
            Integer.parseInt(portString);
        } catch (Exception e) {
            isInt = false;
        }

        if ((username.isEmpty() || ipAddress.isEmpty() || !isInt)) {
            System.out.println("Please enter correct values for all of the fields");
        } else {
            int port = Integer.parseInt(portString);

            Client newClient = new Client(ipAddress, port);
            MessageChecker msgChecker = new MessageChecker(newClient);


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

    public class MessageChecker {

        Client newClient;

        public MessageChecker(Client newClient) {
            this.newClient = newClient;
        }

        public Boolean isMessageEmpty() {
            boolean isEmpty = false;
            String string = messageField.getText();
            if (!string.equals("")) {
                isEmpty = true;
            }
            System.out.println(isEmpty);
            return isEmpty;
        }

        public String getMessage() {
            String message = messageField.getText();
            System.out.println(message);
            return message;
        }
    }
}
