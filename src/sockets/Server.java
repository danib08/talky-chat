package sockets;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    ServerSocket server;
    Socket socket;
    DataInputStream in;
    boolean flag = true;

    public Server(int port) {
        this.flag = flag;

        try {
            server = new ServerSocket(port);
            System.out.println("Server connected and listening on port " + server.getLocalPort());

            socket = server.accept();
            System.out.println("Client accepted");

            in = new DataInputStream(socket.getInputStream());

            String message = "";

            while (this.flag) {
                try {
                    message = in.readUTF();
                    System.out.println(message);

                } catch (Exception i) {
                    System.out.println("An error occurred while trying to read a message " + i.getMessage());
                }
            }
            System.out.println("Client disconnected");
            in.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("An error occurred while trying to start the server: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a port:");
        int portEntered = scanner.nextInt();

        Server newServer = new Server(portEntered);
    }
}
