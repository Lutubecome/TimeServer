import socketio.ServerSocket;
import socketio.Socket;
import java.time.*;

import java.io.IOException;
import java.util.Date;


public class TimeServer {

    private int port = 25565;

    private ServerSocket sSocket;
    private Socket cSocket;

    TimeServer() throws IOException {

        sSocket = new ServerSocket(port);

        System.out.println("Warten auf Client...");

        cSocket = sSocket.accept();

        System.out.println("Client verbunden! Beginne mit senden der Zeit...");

    }

    public void komunizieren() throws IOException, InterruptedException {

        do {

            Date time = new Date();

            cSocket.write(time + "\n");

            Thread.sleep(1000);

            if (cSocket.dataAvailable() > 0 && cSocket.readLine().equalsIgnoreCase("over")) break;

        }while(true);

        System.out.println("Erfolgreich gesendet!");

    }

    public boolean beenden() {

        try {
            cSocket.close();
            sSocket.close();
        } catch (IOException e) {
            System.out.println("Fehler beim schließen der Sockets!");
            return false;
        }

        System.out.println("Sockets geschlossen. Server wird beendet!");

        return true;
    }

    static void main() throws IOException, InterruptedException {

        TimeServer s = new TimeServer();

        s.komunizieren();

        s.beenden();


    }

}
