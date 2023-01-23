class Server
!!!131202.java!!!	Server()
        this.clientThreads = new HashMap<>();
        this.salons = new ArrayList<>();
!!!131330.java!!!	start(in port : int) : ServerSocket
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);
        return serverSocket;
!!!131458.java!!!	main(inout args : String [[]]) : void
        Server server = new Server();
        Salon salon = new Salon("general");
        server.salons.add(salon);
        try {
            ServerSocket serverSocket = server.start(5555);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler thread = new ClientHandler(clientSocket, salon.getClientThreads(), server.salons, server.salons.get(0));
                thread.start();
                System.out.println("Client added to the list of clients");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
