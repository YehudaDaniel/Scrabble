package Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Scanner;

import server.Board;
import server.Word;
import server.MyServer;
import server.Tile;


public class Host extends Observable implements Player {

    public int ourOfClients = 0;
    public Socket myhostSocket;
    public int ourserverPort;
    public Tile.Bag bag;
    public Board myboard;
    public InetAddress serverIp;
    public MyServer myClientServer;
    public FacadeServer facadeServer;
    public static Host host = null;
    public HashMap<Integer, ArrayList<Tile>> playerTilesMap;
    public int ourid;
    public int myturn;
    public int ourrounds = 0;


    private Host(InetAddress ip, int serverPort, int hostPort, int rounds) {

        this.ourid = 1;
        this.myturn = 1;
        this.ourrounds = rounds;
        this.ourserverPort = serverPort;
        this.serverIp = ip;
        this.bag = Tile.Bag.getBag();
        this.myboard = Board.getBoard();
        this.playerTilesMap = new HashMap<>();
        this.ourOfClients = 1;
        this.playerTilesMap.put(ourid , new ArrayList<Tile>());
        for (int j = 0; j < 7; j++) {
            this.playerTilesMap.get(ourid).add(bag.getRand());
        }
        this.myClientServer = new MyServer(hostPort, new HostClientHandler());
        this.myClientServer.start();
      
    }

    public static Host getHost(InetAddress ip, int serverPort, int hostPort, int rounds)
    {
        if (host == null) {
            host = new Host(ip, serverPort, hostPort, rounds);
        }
        return host;
    }

    @Override
    public void actionPlay(int type) {}
    public void setNumberOfRounds(int rounds) {
        this.ourrounds = rounds;
    }
    public int getNumberOfRounds()
    {
        return this.ourrounds;
    }

    public int placeWord1(Word word) {
        int myscore = this.myboard.tryPlaceWord(word);

        if (myscore != 0) {
            for(int i = 0; i < word.tiles.length; i++) {
                this.playerTilesMap.get(ourid).remove(playerTilesMap.get(1).indexOf(word.tiles[i]));
            }
            for (int i = 0; i < word.tiles.length; i++) {
                Tile t = bag.getRand();
                this.playerTilesMap.get(ourid).add(t);
            }
            this.myturn = 1 + (this.myturn % this.ourOfClients);
            //updateAndNotify();
        }
        return myscore;
    }

    public void closeGame() {
        this.myClientServer.close();
        System.out.println("GAME OVER!");
    }

    public boolean challenge(String s) {
        try {
            myhostSocket = new Socket(serverIp, ourserverPort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            PrintWriter outToServer = new PrintWriter(myhostSocket.getOutputStream());
            String my = "";
            my += "C," + "alice_in_wonderland.txt,HarryPotter.txt,TheMatrix.txt,newFile.txt," + s;
            outToServer.println(my);
            outToServer.flush();
        } catch (IOException e) {throw new RuntimeException(e);}

        try {
            boolean flag = false;
            Scanner in = new Scanner(myhostSocket.getInputStream());
            if (in.next().equals("true")) {
                flag = true;
            }
            return flag;
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    public void incrementNumberOfClients() {
        this.ourOfClients++;
        updateAndNotify();
        System.out.println("Host's Number Of Clients: " + this.ourOfClients);
    }

    public void updateAndNotify() {
        setChanged();
        notifyObservers();
    }

	@Override
	public int placeWord(Word word) {
		// TODO Auto-generated method stub
		return 0;
	}
}