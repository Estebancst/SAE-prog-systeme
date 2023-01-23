class ReceiverClient
!!!130178.java!!!	ReceiverClient(inout in : BufferedReader)
    this.in = in;
!!!130306.java!!!	run() : void
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
