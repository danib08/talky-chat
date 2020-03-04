package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    ServerSocket server;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    public ArrayList<ClientThread> listOfClients;

    public Server(int port) {
        this.listOfClients = new ArrayList<>();

        try {
            server = new ServerSocket(port);
            System.out.println("Server connected and listening on port " + server.getLocalPort());

            while (true) {
                socket = server.accept();
                System.out.println("Incoming client: " + socket);

                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                String tempUsername = Integer.toString(socket.getPort());

                System.out.println("Assigning thread to client...");
                ClientThread newClientThread = new ClientThread(this, socket, tempUsername, in, out);

                System.out.println("Adding this client to active client list");
                listOfClients.add(newClientThread);

                System.out.println("Client connected succesfully");
                newClientThread.start();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to start the server: " + e.getMessage());
        }
    }
}
