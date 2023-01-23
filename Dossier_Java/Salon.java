import java.io.*;
import java.net.*;
import java.util.*;

public class Salon {
  private String nom;
  private Map<Socket, ClientHandler> clientThreads;

  public Salon(String nom) {
    this.nom = nom;
    this.clientThreads = new HashMap<>();
  }

  public void ajouterClient(Socket socket, ClientHandler client) {
    this.clientThreads.put(socket, client);
  }

  public void retirerClient(Socket socket, ClientHandler client) {
    for(Socket s : this.clientThreads.keySet()){
      if(s.equals(socket)){
        this.clientThreads.remove(socket);
      }
    }
  } 

  @Override
  public String toString() {
    return "Salon : "+ this.nom +" avec " + this.clientThreads.size()+" utilisateurs";
  }

  public String getNom() {
    return nom;
  }


  public Map<Socket, ClientHandler> getClientThreads() {
    return clientThreads;
  }

}
