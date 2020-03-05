/*
--------------------------------------- Code References ----------------------------------------
 * Up next, the links of several code examples of Threading and Sockets provided by the tutor of
 * CE1103. This project tooks inspiration from said code.
 * https://www.geeksforgeeks.org/socket-programming-in-java/
 * https://www.geeksforgeeks.org/introducing-threads-socket-programming-java/
 * http://journals.ecs.soton.ac.uk/java/tutorial/java/threads/simple.html
 */

package sockets;

import java.util.Scanner;

/**
 * Runs the server
 * @author Daniela Brenes
 */
public class Main {      //Aqui se crea una clase Maib

    public static void main(String[] args) {   // Aqui se crea el metodo main(String[] args)
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
            Server newServer = new Server(portEntered);     // Aqui se aplica instanciacion al crear un objeto Server
            }
        }
    }
