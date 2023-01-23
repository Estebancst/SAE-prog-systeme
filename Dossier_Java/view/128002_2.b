class Client
!!!128002.java!!!	connectionServer(in serverAdress : String, in port : int, inout scanner : Scanner) : void
        this.socket = new Socket(serverAdress, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.nomUtilisateur = this.verifName(scanner);
!!!128130.java!!!	quitServer() : void
        this.socket.close();
!!!128258.java!!!	verifName(inout sc : Scanner) : String
        String nomUtil = this.nomUtilisateur;
        boolean nameVerified = false;
        while (!nameVerified) {
            System.out.println("Enter your username : ");
            nomUtil = sc.nextLine();
            this.out.write(nomUtil);
            this.out.newLine();
            this.out.flush();
            String msg = this.in.readLine();
            System.out.println(msg);
            if (!msg.equals("This name is already taken, please choose another name")) {
            nameVerified = true;
            }
        }
        return nomUtil;
!!!128386.java!!!	write(in message : String) : void
        PrintWriter writer = new PrintWriter(this.socket.getOutputStream());
        writer.println(message);
        writer.flush();
!!!128514.java!!!	read() : void
        InputStreamReader stream  = new InputStreamReader(this.socket.getInputStream());
        BufferedReader reader = new BufferedReader(stream);
        String message = reader.readLine();
        System.out.println(message);
!!!128642.java!!!	start(inout sc : Scanner) : void
        try {
            new Thread(new ReceiverClient(this.in)).start();
            System.out.println("");
            System.out.println("");
            while(true){
                String mess = sc.nextLine();
                this.out.write(mess);
                this.out.newLine();
                this.out.flush();
                if (mess.equals("/quit")) {
                    // this.quitServer();
                    break;
                }
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
!!!128770.java!!!	main(inout args : String [[]]) : void
        Scanner sc = new Scanner(System.in);
        Client client = new Client();
        client.connectionServer("127.0.0.1", 5555, sc);
        client.start(sc);

