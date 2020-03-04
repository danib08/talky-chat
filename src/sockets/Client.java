package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
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
        this.receiveMessage();
    }

    public void sendMessage(String message) {
        try {
            output.writeUTF(message);
        } catch (IOException i) {
            System.out.println("An error occurred while trying to send a message: " + i.getMessage());
        }
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

    }

}