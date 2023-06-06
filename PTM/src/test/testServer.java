package test;

import server.ClientHandler;
import server.MyServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class testServer {
    public static void main(String[] args) {
        // Create an instance of your ClientHandler implementation
        ClientHandler clientHandler = new ClientHandler() {
            @Override
            public void handleClient(InputStream inFromclient, OutputStream outToClient) {
                // *Need to change when we implement proper Client Handle*
                // This method will be called when a client connects to the server
            }

            @Override
            public void close() {
                // Implement the logic to close any resources used by the ClientHandler
            }
        };

        // Create an instance of MyServer and start the server
        int port = 8080; // Specify the port number for the server
        MyServer server = new MyServer(port, clientHandler);
        server.start();

        // Wait for the server to start accepting client connections
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Simulate client connections or perform other operations as needed

        // Close the server when done
        server.close();
    }
}
