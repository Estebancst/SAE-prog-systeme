import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Thread{
    private String nomUT;
    private Socket s;

    public Client(String nomUT, String ip) {
        this.nomUT = nomUT;
    }
    

    public void connectionServer(String ip, int numPort) throws UnknownHostException, IOException{
        this.s = new Socket("127.0.0.1", 5555);
        // Socket socket = new Socket(ip, numPort);        
    }
    public void quitServer() throws IOException{
        this.s.close();
    }

    public void write(String message) throws IOException{
        PrintWriter writer = new PrintWriter(this.s.getOutputStream());
        writer.println(message);
        writer.flush();
    }
    public void read() throws IOException{
        InputStreamReader stream  = new InputStreamReader(this.s.getInputStream());
        BufferedReader reader = new BufferedReader(stream);
        String message = reader.readLine();
        System.out.println(message);
    }

    @Override
    public void run() {
    }
    
}