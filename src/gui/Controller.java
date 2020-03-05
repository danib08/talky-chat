package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sockets.Client;    // se aplica modularidad ya que se importa una clase de uno de los paquetes creados
import java.io.IOException;

/**
 * This class is mostly in charge of the GUI logic
 */
public class Controller { // se crea la clase Controller

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

    /**
     * This method is in charge to create a new client each time the log in button is pressed
     */
    @FXML
    public void connect() { // Se crea el metodo connect() de la clase Controller
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

            newClient = new Client(ipAddress, port, username); // Instancacion ya que se crea un objeto Client
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

    /**
     * Calls the Client class method sendMessage() to send the client username to the server
     * @param username identifies the new client
     */
    public void sendUsername(String username) {
        newClient.sendMessage(username);
    }

    /**
     * Sends a message to another client (using the server as medium)
     */
    public void sendMessage() {
        String message;
        if (!messageField.getText().equals("")) {
            message = messageField.getText();
            messageField.clear();
            textArea.appendText("You: " + message);
            newClient.sendMessage(message);
        }
    }
}
