package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Manages the creation of a server and it's clients connection
 * @author Daniela Brenes
 */
public class Server {  // Aqui se crea una clase Server

    // Aqui se aplica encapsulamiento al usar la palabra reservada private
    private ServerSocket server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    /**
     * ArrayList containing all ClientThreads associated with each client
     */
    public ArrayList<ClientThread> listOfClients;

    /**
     * Creates a Server on the desired port and accepts incoming clients
     * @param port The port where the socket will be created
     */
    public Server(int port) {
        this.listOfClients = new ArrayList<>();  // Este es un atributo de la clase Server

        try {
            server = new ServerSocket(port);
            System.out.println("Server connected and listening on port " + server.getLocalPort());

            while (true) {
                socket = server.accept();
                System.out.println("Incoming client: " + socket);

                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                System.out.println("Assigning thread to client...");

                // Aqui hay instanciacion, ya que se crea un nuevo objeto ClientThread
                ClientThread newClientThread = new ClientThread(this, socket, in, out);

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
