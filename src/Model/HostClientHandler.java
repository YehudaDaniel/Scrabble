package Model;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class HostClientHandler implements ClientHandler
{
    public PrintWriter o;
    public Scanner i;

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient)
    {
        i = new Scanner(inFromclient);
        o = new PrintWriter(outToClient);
        String input = i.next();
        int Myid = Integer.parseInt(input);
        input = i.next();
        char question = input.charAt(0);
        input = input.substring(2);

        if (question == '0') {
            ArrayList<Character> tiles = new ArrayList<>();
            Host.host.numberOfClients++;

            for (int j = 0; j < 26; j++ ) {
                Tile t = Host.host.bag.getRand();
                tiles.add(t.letter);
            }

            Host.host.playerTilesMap.put(Host.host.numberOfClients, tiles);
            o.println(String.valueOf(Host.host.numberOfClients));
        }

        if (Host.host.turn == Myid) {
            if (question == '1') //if we put a word, we take back from the nag the amount of tiles in the word
            {
                boolean v;
                int r;
                int c;
                input = i.next();

                if (input.equals("V "))
                    v = true;
                else
                    v = false;

                input = i.next();
                r = Integer.parseInt(input);
                input = i.next();
                c = Integer.parseInt(input);

                input = i.next();
                char[] array = input.toCharArray();
                Tile[] tiles = new Tile[array.length];

                for (int i = 0; i < array.length; i++) {
                    tiles[i] = Tile.Bag.getBag().getTile(array[i]);
                }

                Word Myword = new Word(tiles, r, c, v);
                int score = Host.host.placeWord(Myword);
                String MYs = "";

                if (score != 0) {
                    MYs += "t ";

                    for (int i = 0; i < Myword.tiles.length; i++) {
                        Host.host.playerTilesMap.get(Myid).remove(Myword.tiles[i].letter);
                    }

                    for (int i = 0; i < array.length; i++) {
                        Tile t = Host.host.bag.getRand();
                        Host.host.playerTilesMap.get(Myid).add(t.letter);
                    }
                    Host.host.turn = (1 + Host.host.turn) % Host.host.numberOfClients;

                    if (Myid == Host.host.numberOfClients) {
                        Host.host.rounds--;
                        if (Host.host.rounds ==0)
                            Host.host.closeGame();
                    }
                }
                else {
                    MYs += "f ";
                }
                o.println(MYs);
            }

            if (question == '2') {
                boolean flag = Host.host.challenge(input);
                o.println(String.valueOf(flag));
            }
            o.flush();
        }
       else {
           System.out.println("Sorry it's not your turn.");
        }
    }

    @Override
    public void close() {
        if (i != null)
            i.close();
        if (o != null)
            o.close();
    }
}
