package Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import server.Word;

public class Client implements Player {

    private int clientId;
    private Socket socket;
    private InetAddress ip;
    private int port;
    private PrintWriter writer;
    private Scanner scan;
    private boolean isConnected = false;

    private ArrayList<Character> tiles = new ArrayList<>();

    public Client(int port, InetAddress ip){
        this.ip = ip;
        this.port = port;
        this.runClient();
    }


    public void runClient(){
        try{
            this.socket = new Socket(ip, port);
        }catch(IOException e){
            e.printStackTrace();
            return;
        }

        writer = null;
        try{
            writer = new PrintWriter(socket.getOutputStream());
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try{
            scan = new Scanner(socket.getInputStream());
        }catch(IOException e){
            throw new RuntimeException(e);
        }

        writer.println("9 0");
        this.clientId = Integer.parseInt(scan.next());
        while(scan.hasNext()){
            String input = scan.next();
            char letter = input.charAt(0);
            tiles.add(letter);
        }

        isConnected = true;
    }


    public void query(String query){
        writer.println(query);
        writer.flush();
    }

    public void closeClient(){
        try{
            scan.close();
            writer.close();
            this.socket.close();
            isConnected = false;
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void playType(int type) {};

    @Override
    public int placeWord(Word word) {
        this.query(clientId + " " + "1," + word.toString());

        if(scan.next().equals("t"));
    }

    @Override
    public boolean challenge(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'challenge'");
    }
    
}
