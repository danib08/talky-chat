package sockets;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----- Server creation -----");
        System.out.println("Please enter a port for the server:");

        boolean isValid = true;
        String portString = scanner.nextLine();
        try {
            Integer.parseInt(portString);
        } catch (Exception e) {
            System.out.println("Please enter a valid port");
            isValid = false;
        }

        if (isValid) {
            int portEntered = Integer.parseInt(portString);
            Server newServer = new Server(portEntered);
            }
        }
    }
