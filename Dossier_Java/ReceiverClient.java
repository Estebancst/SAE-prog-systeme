import java.io.*;
import java.net.*;
import java.util.*;


public class ReceiverClient implements Runnable {

  // change the color of the text
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  
  private BufferedReader in;

  public ReceiverClient(BufferedReader in) {
    this.in = in;
  }

  public void run() {
    try {
      while (true) {
        String msg = this.in.readLine();
        if (msg == null) {
          break;
        }
        else {
          if (msg.contains("[MP]")) {
            System.out.println(ANSI_RED + msg + ANSI_RESET);
          }
          else {
            System.out.println(msg);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}