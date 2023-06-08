package test;

import server.Tile;
import server.Word;

import java.util.Arrays;

public class testWord {
    public static void main(String[] args) {
        // Create an instance of the Tile.Bag
        Tile.Bag bag = Tile.Bag.getBag();

        // Create an array of tiles
        Tile[] tiles = new Tile[5];
        for (int i = 0; i < 5; i++) {
            Tile tile = bag.getRand();
            if (tile != null) {
                tiles[i] = tile;
            } else {
                System.out.println("Not enough tiles available.");
                return;
            }
        }

        // Create a vertical word
        Word verticalWord = new Word(tiles, 2, 3, true);
        System.out.println("Vertical Word:");
        System.out.println("Tiles: " + Arrays.toString(verticalWord.getTiles()));
        System.out.println("Row: " + verticalWord.getRow());
        System.out.println("Column: " + verticalWord.getCol());
        System.out.println("Vertical: " + verticalWord.isVertical());

        // Create a horizontal word
        Word horizontalWord = new Word(tiles, 4, 1, false);
        System.out.println("\nHorizontal Word:");
        System.out.println("Tiles: " + Arrays.toString(horizontalWord.getTiles()));
        System.out.println("Row: " + horizontalWord.getRow());
        System.out.println("Column: " + horizontalWord.getCol());
        System.out.println("Vertical: " + horizontalWord.isVertical());

        // Test the equals method
        Word word1 = new Word(tiles, 2, 3, true);
        Word word2 = new Word(tiles, 2, 3, true);
        Word word3 = new Word(tiles, 4, 1, false);
        System.out.println("\nEquality Test:");
        System.out.println("word1 equals word2: " + word1.equals(word2));
        System.out.println("word1 equals word3: " + word1.equals(word3));
    }
}
