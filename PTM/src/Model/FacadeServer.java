package Model;

import java.awt.print.Book;
import java.net.InetAddress;
import server.MyServer;
import server.DictionaryManager;
import server.BookScrabbleHandler;

public class FacadeServer {
    public DictionaryManager dictionaryManager;
    public MyServer myServer;

    public FacadeServer(int port) {
        this.dictionaryManager = DictionaryManager.get();
        this.myServer = new MyServer(port, new BookScrabbleHandler());
        this.myServer.start();
    }

    public void close()
    {
        this.myServer.close();
    }
}
