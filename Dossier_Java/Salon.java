import java.io.*;
import java.net.*;
import java.util.*;

public class Salon {
  private String nom;
  private List<Client> clients;
  // private Map<> clients;

  public Salon(String nom) {
    this.nom = nom;
    this.clients = new ArrayList<>();
  }

  public void ajouterClient(Client client) {
    this.clients.add(client);
  }

  public void retirerClient(Client client) {
    this.clients.remove(client);
  }
}
