import java.io.BufferedReader;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client extends Thread{
    private String nomUT;
    private Socket s;

    public Client(String nomUT, String ip) {
        this.nomUT = nomUT;
    }


    public void connectionServer(String ip, int numPort) throws UnknownHostException, IOException{
        // this.s = new Socket("127.0.0.1", 5555);
        this.s = new Socket(ip, numPort);
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
        try {
            this.connectionServer("172.28.88.22", 5555);
            this.write("test");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
