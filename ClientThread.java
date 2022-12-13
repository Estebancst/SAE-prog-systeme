import java.io.*;
import java.net.*;
import java.util.*;


public class ClientThread extends Thread {
  private Socket clientSocket;
  private String username;
  private BufferedReader in;
  private BufferedWriter out;
  private Map<Socket, ClientThread> clientThreads;

  public ClientThread(String username, Socket clientSocket, Map<Socket, ClientThread> clientThreads) throws IOException {
    this.username = username;
    this.clientSocket = clientSocket;
    this.clientThreads = clientThreads;
    this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    this.out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
  }

  public void run() {
    try {
      while (true) {

        String message = this.in.readLine();
        System.out.println(this.username + " : " + message);

        if (message.startsWith("/")) {
          this.getCommand(message);
        }
        else if (message.startsWith("@")) {
          this.sendPrivateMessage(this.username, message);
        }
        else {
          for (ClientThread thread : clientThreads.values()) {
            if (thread != this) {
              thread.sendMessage(this.username, message);
            }
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getUserName() {
    return this.username;
  }

  public void sendMessage(String userName, String message) {
    try {
      out.write(userName + " : " + message);
      out.newLine();
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void sendCommandMessage(String message) {
    try {
      out.write(message);
      out.newLine();
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void getCommand(String message) {
    if (message.equals("/quit")) {
      try {
        this.clientSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    else if (message.equals("/nbusers")) {
      this.sendCommandMessage("Il y a " + this.clientThreads.size() + " utilisateurs connectés");
    }
    else if (message.equals("/users")) {
      String users = "";
      for (ClientThread thread : clientThreads.values()) {
        users += thread.getUserName() + " ";
      }
      this.sendCommandMessage("Liste des utilisateurs connectés: " + users);
    }
    else if (message.equals("/uptime")) {
      this.sendCommandMessage("Le serveur est en ligne depuis " + System.currentTimeMillis() + " ms");
    }
    else if (message.equals("/help")) {
      this.sendCommandMessage("Liste des commandes: /quit, /nbusers, /users, /uptime, /help");
    }
    else {
      this.sendCommandMessage("Unknown command");
    }
  }

  public void sendPrivateMessage(String userName, String message) {
    String[] parts = message.split(" ");
    String user = parts[0].substring(1);
    String msg = "[MP] ";
    for (int i = 1; i < parts.length; i++) {
      msg += parts[i] + " ";
    }
    for (ClientThread thread : clientThreads.values()) {
      if (thread.getUserName().equals(user)) {
        thread.sendMessage(userName, msg);
      }
    }
  }
}
