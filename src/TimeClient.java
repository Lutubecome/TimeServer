import socketio.Socket;

import java.io.IOException;
import java.time.LocalTime;

public class TimeClient {

    private String host = "localhost";
    private int port = 25565;
    private Socket cSocket;

    TimeClient() throws IOException {

        cSocket = new Socket(host,port);

    }

    void verbinden(){

        System.out.println("Verbinden mit Server...");

        cSocket.connect();

        System.out.println("Verbunden mit Server! Warten auf eintreffende Nachrichten...");
    }

    void komunizieren() throws IOException {

        for (int i = 0; i< 10; i++){

            System.out.println(cSocket.readLine());

        }

        cSocket.write("over \n");

        System.out.println("Zehn Zeiteinheiten erfasst! Beende Programm...");

    }

    void beenden() throws IOException {

        cSocket.close();

    }

    static void main() throws IOException {

        TimeClient c = new TimeClient();

        c.verbinden();
        c.komunizieren();
        c.beenden();

    }

}
