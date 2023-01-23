class Salon
!!!130434.java!!!	Salon(in nom : String)
    this.nom = nom;
    this.clientThreads = new HashMap<>();
!!!130562.java!!!	ajouterClient(inout socket : Socket, inout client : ClientHandler) : void
    this.clientThreads.put(socket, client);
!!!130690.java!!!	retirerClient(inout socket : Socket, inout client : ClientHandler) : void
    for(Socket s : new ArrayList<>(this.clientThreads.keySet())){
      if(s.equals(socket)){
        this.clientThreads.remove(socket);
      }
    }
!!!130818.java!!!	toString() : String
    return "Salon : "+ this.nom +" avec " + this.clientThreads.size()+" utilisateurs";
!!!130946.java!!!	getNom() : String
    return nom;
!!!131074.java!!!	getClientThreads() : ClientHandler
    return clientThreads;
