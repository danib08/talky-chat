package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * Thread that manages all client communication on the server's side
 * @author DanielaBrenes
 */
public class ClientThread extends Thread { // Aqui se crea una clase ClientThread y se hereda de la clase Thread
    DataOutputStream output;   //Aqui se tienen algunos atributos de la clase ClientThread
    DataInputStream input;
    Socket clientSocket;
    Server server;
    String username;
    boolean isLoggedIn;

    /**
     * Creates a ClientThread object
     * @param server server where the clients are connected
     * @param clientSocket socket obtained by the server from the clients
     * @param input represents data input values to receive messages from clients
     * @param output represents data output values to send messages to the clients
     */
    public ClientThread(Server server, Socket clientSocket, DataInputStream input, DataOutputStream output) {
        this.server = server;
        this.clientSocket = clientSocket;
        this.username = null;
        this.input = input;
        this.output = output;
        this.isLoggedIn = true;
    }

    /**
     * Checks for username information from the client and also manages message sending to another client
     */
    @Override       // Aqui se hace sobreescritura sobre un metodo de la clase Thread
    public void run() {
        String message;

        while (isLoggedIn) {
            try {
                message = input.readUTF();

                if (message.equals("%logout%")) {
                    this.isLoggedIn = false;
                    this.clientSocket.close();
                    break;
                }

                else if (message.substring(0,1).equals("±")) {
                    this.username = message.substring(1);
                }

                else {
                    StringTokenizer string = new StringTokenizer(message, "$");
                    String send = string.nextToken();
                    String receiver = string.nextToken();

                    for (ClientThread client : this.server.listOfClients) {
                        if (client.username.equals(receiver) && client.isLoggedIn) {
                            client.output.writeUTF("They: " + send);
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println("An error occurred during communicaction: " + e.getMessage());
            }
        }

        try {
            this.input.close();
            this.output.close();

        } catch (IOException i) {
            System.out.println(i.getMessage());
        }
    }

}
