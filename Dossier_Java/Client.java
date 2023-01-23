import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client{
    private String nomUtilisateur;
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;


    public void connectionServer(String serverAdress, int port, Scanner scanner) throws UnknownHostException, IOException {
        this.socket = new Socket(serverAdress, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.nomUtilisateur = this.verifName(scanner);
    }

    public void quitServer() throws IOException{
        this.socket.close();
    }

    public String verifName(Scanner sc) throws IOException {
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
    }

    public void write(String message) throws IOException{
        PrintWriter writer = new PrintWriter(this.socket.getOutputStream());
        writer.println(message);
        writer.flush();
    }

    public void read() throws IOException{
        InputStreamReader stream  = new InputStreamReader(this.socket.getInputStream());
        BufferedReader reader = new BufferedReader(stream);
        String message = reader.readLine();
        System.out.println(message);
    }

    public void start(Scanner sc) {
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
    }



    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Client client = new Client();
        client.connectionServer("127.0.0.1", 5555, sc);
        client.start(sc);

    }
}
