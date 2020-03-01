package sockets;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Socket client;
    DataInputStream input;
    DataOutputStream output;
    boolean flag = true;

    public Client(String ipAddress, int port) {
        try {
            client = new Socket(ipAddress, port);
            System.out.println("Connected successfully");

            input = new DataInputStream(System.in);
            output = new DataOutputStream(client.getOutputStream());

            String sent = "";

            try {
                while (this.flag) {
                    sent = input.readLine();
                    output.writeUTF(sent);
                }
            } catch (IOException i) {
                System.out.println("An error occurred during communicaction: " + i.getMessage());
            }

            client.close();
            input.close();
            output.close();

        } catch (IOException e) {
            System.out.println("An error occurred while trying to connect to the server " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter an IP Address:");
        String addressEntered = scanner.nextLine();

        System.out.println("Please enter a port:");
        int portEntered = scanner.nextInt();

        Client newClient = new Client(addressEntered, portEntered);
    }
}
