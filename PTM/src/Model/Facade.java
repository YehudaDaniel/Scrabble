package Model;

import server.Board;
import server.DictionaryManager;
import server.MyServer;
import server.Tile;

public class Facade
{
    public Board myboard;
    public Tile.Bag mybag;
    public DictionaryManager ourdictionaryManager;
    public MyServer myServer;

    public Facade(int port, ClientHandler ch) {
        this.mybag = Tile.Bag.getBag();
        this.myboard = Board.getBoard();
        this.ourdictionaryManager = new DictionaryManager();
        this.myServer = new MyServer(port, (server.ClientHandler) ch);
    }
}
