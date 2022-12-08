import java.io.*;
import java.net.Socket;
public class ClientS {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("172.28.88.22", 5555);
            InputStreamReader stream = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(stream);
            String message = reader.readLine();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
