import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private Map<Socket, ClientHandler> clientThreads;
    private List<Salon> salons;

    public Server() {
        this.clientThreads = new HashMap<>();
        this.salons = new ArrayList<>();
    }

    public ServerSocket start(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);
        return serverSocket;
    }

    public static void main(String[] args) {
        Server server = new Server();
        Salon salon = new Salon("general");
        server.salons.add(salon);
        try {
            ServerSocket serverSocket = server.start(5555);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler thread = new ClientHandler(clientSocket, server.clientThreads, server.salons, server.salons.get(0));
                thread.start();
                System.out.println("Client added to the list of clients");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
