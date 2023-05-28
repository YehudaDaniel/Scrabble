package Model;

import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler {
    //Implementation of reading and writing to/from the Server
    void handleClient(InputStream inFromClient, OutputStream outToClient);
    void close(); //close the communication to the server
}
