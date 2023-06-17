package frontend;

public class main {
    public static void main(String[] args) {
        // Create the Scrabble view
        ScrabbleView scrabbleView = new ScrabbleView();

        // Example usage: Add tiles to the game board
        scrabbleView.addTileToBoard(7, 7, "A");
        scrabbleView.addTileToBoard(7, 8, "B");
        scrabbleView.addTileToBoard(7, 9, "C");

        // Example usage: Update the player's tile rack
        String[] tiles = {"D", "E", "F", "G", "H"};
        scrabbleView.updateRack(tiles);

        // Example usage: Update the score, current player, and status
        scrabbleView.updateScore(100);
        scrabbleView.updateCurrentPlayer("Player 1");
        scrabbleView.updateStatus("Your turn");

        // Show the game board
        // You can call the appropriate methods on scrabbleView to update the game board and other components
    }
}
