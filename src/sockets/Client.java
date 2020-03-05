package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Represents a client and its ways of communication
 */
public class Client {  // Aqui se crea una clase
    // Aqui se aplica encapsulamiento al usar la palabra reservada private, y se tienen algunos atributos de la clase
    private Socket client;
    private DataInputStream input;
    private DataOutputStream output;
    private String username;

    /**
     * Creates a client with the desired ipAddresses and the port, along with its username
     * @param ipAddress Indicates which computer to connect to
     * @param port Indicates which port to connect to
     * @param username String that identifies the client
     */
    public Client(String ipAddress, int port, String username) {
        this.username = username;  // username es un atributo de la clase Client
            try {
                client = new Socket(ipAddress, port);
                System.out.println("Succesfully connected to the server");
                input = new DataInputStream(client.getInputStream());
                output = new DataOutputStream(client.getOutputStream());

            } catch (IOException e) {
                System.out.println("An error occurred while trying to connect to the server: " + e.getMessage());
            }
        this.receiveMessage();
    }

    /**
     * Sends a message to the server so it can make it arrive to a different client
     * @param message Message to send to a specific client
     */
    public void sendMessage(String message) {   // Este es un metodo de la clase Client()
        try {
            output.writeUTF(message);
        } catch (IOException i) {
            System.out.println("An error occurred while trying to send a message: " + i.getMessage());
        }
    }

    /**
     * Constantly checks for messages from another client and shows them
     */
    public void receiveMessage() {      // Este es un metodo de la clase Client()
        Thread receive = new Thread() {
            public void run() {
                while (true) {
                    try {
                        String message = input.readUTF();
                        System.out.println(message);
                    } catch (IOException i) {
                        System.out.println("An error occurred while trying to receive a message: " + i.getMessage());
                    }
                }
            }

        };
        receive.start();
    }
}
