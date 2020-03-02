package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    ServerSocket server;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    int i = 0;

    public Server(int port) {

        try {
            server = new ServerSocket(port);
            System.out.println("Server connected and listening on port " + server.getLocalPort());

            while (true) {
                System.out.println("wuju");
                socket = server.accept();
                System.out.println("Incoming client: " + socket);

                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                System.out.println("Assigning thread to client...");
                ClientThread newClient = new ClientThread(socket, i, in, out);

                //System.out.println("Adding this client to active client list");

                newClient.start();
                i++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to start the server: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a port:");
        int portEntered = scanner.nextInt();

        Server Server = new Server(portEntered);

    }
}
