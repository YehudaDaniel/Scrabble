package Model;
import server.MyServer;
import server.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class threadPool extends MyServer {
    private final int PLAYERS = 4;
    int myport ;
    ExecutorService threadPool = Executors.newFixedThreadPool(PLAYERS);
    public threadPool(int port, ClientHandler ch) {
        super(port,(server.ClientHandler) ch);
       this.myport=port;
    }

    public void start(){ 

        try {
            ServerSocket server = new ServerSocket(myport);
            server.setSoTimeout(1000);
            boolean stop = false;
            while (!stop) {
                try {
                    Socket myClient = server.accept();
                    ch.handleClient(myClient.getInputStream(), myClient.getOutputStream()); 
                    threadPool.execute((Runnable) ch); 
                    ch.close();
                    myClient.close();
                }catch (SocketTimeoutException e) {}

            }
            server.close();

        }catch (IOException e){};

    }

}