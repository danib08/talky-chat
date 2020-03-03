package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

public class ClientThread extends Thread {
    DataInputStream input;
    DataOutputStream output;
    Socket clientSocket;
    Server server;
    int identifier;
    boolean isLoggedIn;

    public ClientThread(Server server, Socket clientSocket, int identifier, DataInputStream input, DataOutputStream output) {
        this.server = server;
        this.clientSocket = clientSocket;
        this.identifier = identifier;
        this.input = input;
        this.output = output;
        this.isLoggedIn = true;
    }

    @Override
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

                StringTokenizer string = new StringTokenizer(message, "$");
                String send = string.nextToken();
                String receiver = string.nextToken();
                int receiverInt = Integer.parseInt(receiver);

                for (ClientThread client : this.server.listOfClients) {
                    System.out.println(client.identifier);
                    if (client.identifier == receiverInt && client.isLoggedIn) {
                        client.output.writeUTF(this.identifier + ": " + send);
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
