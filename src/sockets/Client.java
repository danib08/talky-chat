package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Scanner msgScanner = new Scanner(System.in);
    Socket client;
    DataInputStream input;
    DataOutputStream output;

    public Client(String ipAddress, int port) {
            try {
                client = new Socket(ipAddress, port);
                System.out.println("Succesfully connected to the server");
                input = new DataInputStream(client.getInputStream());
                output = new DataOutputStream(client.getOutputStream());

            } catch (IOException e) {
                System.out.println("An error occurred while trying to connect to the server: " + e.getMessage());
            }
        this.sendMessage();
        this.receiveMessage();
    }

    public void sendMessage() {
        Thread send = new Thread(){
            public void run() {
                while (true) {
                    String message = msgScanner.nextLine();
                     try {
                         output.writeUTF(message);

                     } catch (IOException i) {
                         System.out.println("An error occurred while trying to send a message: " + i.getMessage());
                     }
                }
            }
        };
        send.start();
    }

    public void receiveMessage() {
        Thread receive = new Thread(){
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter an IPAddress:");
        String ipAddress = scanner.nextLine();

        System.out.println("Please enter a port:");
        int portEntered = scanner.nextInt();

        Client newClient = new Client(ipAddress, portEntered);
    }

}