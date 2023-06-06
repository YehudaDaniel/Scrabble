package test;

import backend.Board;
import backend.Tile;
import backend.Word;

public class testBoard {
    private static backend.Tile[] get(String s) {
        backend.Tile[] ts = new backend.Tile[s.length()];
        int i = 0;
        for (char c : s.toCharArray()) {
            ts[i] = backend.Tile.Bag.getBag().getTile(c);
            i++;
        }
        return ts;
    }

    public static void main(String[] args) {
        backend.Board board = backend.Board.getBoard();
        if (board != Board.getBoard())
            System.out.println("Board should be a Singleton (-5)");

        backend.Tile.Bag bag = backend.Tile.Bag.getBag();
        backend.Tile[] ts = new backend.Tile[10];
        for (int i = 0; i < ts.length; i++)
            ts[i] = bag.getRand();

        backend.Word w0 = new backend.Word(ts, 0, 6, true);
        backend.Word w1 = new backend.Word(ts, 7, 6, false);
        backend.Word w2 = new backend.Word(ts, 6, 7, true);
        backend.Word w3 = new backend.Word(ts, -1, 7, true);
        backend.Word w4 = new backend.Word(ts, 7, -1, false);
        backend.Word w5 = new backend.Word(ts, 0, 7, true);
        backend.Word w6 = new backend.Word(ts, 7, 0, false);

        if (board.boardLegal(w0) || board.boardLegal(w1) || board.boardLegal(w2) || board.boardLegal(w3) || board.boardLegal(w4) || !board.boardLegal(w5) || !board.boardLegal(w6))
            System.out.println("Your boardLegal function is wrong (-10)");

        for (Tile t : ts)
            bag.put(t);

        backend.Word horn = new backend.Word(get("HORN"), 7, 5, false);
        if (board.tryPlaceWord(horn) != 14)
            System.out.println("Problem in placeWord for the 1st word (-10)");

        backend.Word farm = new backend.Word(get("FA_M"), 5, 7, true);
        if (board.tryPlaceWord(farm) != -1)
            System.out.println("Problem in placeWord for the 2nd word (-10)");

        backend.Word farm2 = new Word(get("FAARM"), 4, 7, true);
        if (board.tryPlaceWord(farm2) != 10)
            System.out.println("Problem in placeWord for the 2nd word (-10)");

        System.out.println("Done testing Board.");
    }
}
