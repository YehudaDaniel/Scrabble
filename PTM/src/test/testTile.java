package test;

import server.Tile;

public class testTile {
    public static void main(String[] args) {
        // Create an instance of the Tile.Bag
        Tile.Bag bag = Tile.Bag.getBag();

        // Test the getRand method
        System.out.println("Random Tiles:");
        for (int i = 0; i < 10; i++) {
            Tile tile = bag.getRand();
            if (tile != null) {
                System.out.println("Letter: " + tile.letter + ", Score: " + tile.score);
            } else {
                System.out.println("No more tiles available.");
                break;
            }
        }

        // Test the getTile method
        System.out.println("\nSpecific Tiles:");
        char[] letters = {'A', 'B', 'C', 'D', 'Z'};
        for (char letter : letters) {
            Tile tile = bag.getTile(letter);
            if (tile != null) {
                System.out.println("Letter: " + tile.letter + ", Score: " + tile.score);
            } else {
                System.out.println("No more tiles for letter: " + letter);
            }
        }

        // Test the put method
        System.out.println("\nPutting Tiles:");
        Tile tile1 = bag.getTile('A');
        Tile tile2 = bag.getTile('B');
        if (tile1 != null && tile2 != null) {
            bag.put(tile1);
            bag.put(tile2);
            System.out.println("Tile 1: Letter: " + tile1.letter + ", Score: " + tile1.score);
            System.out.println("Tile 2: Letter: " + tile2.letter + ", Score: " + tile2.score);
        } else {
            System.out.println("Not enough tiles available.");
        }

        // Test the size method
        int size = bag.size();
        System.out.println("\nBag Size: " + size);

        // Test the getQuantities method
        int[] quantities = bag.getQuantities();
        System.out.println("Tile Quantities:");
        for (int i = 0; i < quantities.length; i++) {
            char letter = (char) ('A' + i);
            System.out.println("Letter: " + letter + ", Quantity: " + quantities[i]);
        }
    }
}
