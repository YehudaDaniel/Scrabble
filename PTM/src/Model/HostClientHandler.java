package Model;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import server.Tile;
import server.Word;
public class HostClientHandler implements ClientHandler, server.ClientHandler
{
    private PrintWriter myout;
    private Scanner myin;
    public ArrayList<String> clientsIPlist = new ArrayList<>();

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {

        System.out.println("Just Visited HostClientHandler!");
        myin = new Scanner(inFromclient);
        myout = new PrintWriter(outToClient);

        if (myin !=  null && myin.hasNext()) {

            String input = myin.next();
            int id = Integer.parseInt(input);
            input = myin.next();
            char question = input.charAt(0);

            if (question == '0') {

                ArrayList<Tile> tiles = new ArrayList<>();
                String clientIp = "localhost";
                clientsIPlist.add(clientIp);
                Host.host.incrementNumberOfClients();

                for (int v = 0; v < 7; v++) {
                    Tile t = Host.host.bag.getRand();
                    tiles.add(t);
                }

                Host.host.playerTilesMap.put(Host.host.ourOfClients, tiles);
                String s = String.valueOf(Host.host.ourOfClients);
                System.out.println("Last Stop Is HostClientHandler- Number Of Clients: " + s);
                System.out.println();
                myout.println(s);
            }

            

             if (Host.host.myturn == id) {
                if (question == '1') 
                {
                    input = input.substring(2);
                    String[] newClientInputParts = input.split(",");
                    boolean vertical;
                    int myrow;
                    int mycol;

                    if (newClientInputParts[0].equals("V"))
                        vertical = true;
                    else
                        vertical = false;

                    myrow = Integer.parseInt(newClientInputParts[1]);
                    myrow = Integer.parseInt(newClientInputParts[2]);

                    char[] array = newClientInputParts[3].toCharArray();
                    Tile[] tiles = new Tile[array.length];

                    for (int i = 0; i < array.length; i++) {
                        for (int j = 0; j < Host.host.playerTilesMap.get(id).size(); j++)
                        {
                            if (Host.host.playerTilesMap.get(id).get(j).letter == array[i])
                                tiles[i] = Host.host.playerTilesMap.get(id).get(j);
                        }
                    }
                    Word word = new Word(tiles, myrow, myrow, vertical);
                    int score = Host.host.placeWord(word);
                    String s = "";

                    if (score != 0) {
                        s += "t";

                        for (int i = 0; i < word.tiles.length; i++) {
                            Host.host.playerTilesMap.get(id).remove(word.tiles[i]);
                        }

                        for (int i = 0; i < array.length; i++) {
                            Tile t = Host.host.bag.getRand();
                            Host.host.playerTilesMap.get(id).add(t);
                        }
                        Host.host.myturn = 1 + (Host.host.myturn % Host.host.ourOfClients);

                        if (id == Host.host.ourOfClients) {
                            Host.host.setNumberOfRounds(Host.host.getNumberOfRounds() - 1);
                            if (Host.host.getNumberOfRounds() == 0)
                                Host.host.closeGame();
                        }
                        Host.host.updateAndNotify();
                    }
                    else {
                        s += "f";
                    }
                    myout.println(s);
                }

                if (question == '2') { 
                    String flag = String.valueOf(Host.host.challenge(input.substring(2)));
                    myout.println(flag);
                }
            }
            else if (question != '0') {
                System.out.println("Sorry it's not your turn.");
            }
            myout.flush();
        }
    }



    @Override
    public void close() {
        if (myin != null)
            myin.close();
        if (myout != null)
            myout.close();
    }
}