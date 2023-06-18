package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScrabbleView {
    private JFrame frame;
    private JPanel boardPanel;
    private JPanel rackPanel;
    private JButton endTurnButton;
    private JButton challengeButton;
    private JLabel scoreLabel;
    private JLabel currentPlayerLabel;
    private JLabel statusLabel;

    public ScrabbleView() {
        initializeUI();
        addListeners();
    }

    private void initializeUI() {
        // Frame
        frame = new JFrame("Scrabble Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Game board panel
        boardPanel = new JPanel(new GridLayout(15, 15));
        frame.add(boardPanel, BorderLayout.CENTER);

        // Tile rack panel
        rackPanel = new JPanel();
        frame.add(rackPanel, BorderLayout.SOUTH);

        // Buttons
        endTurnButton = new JButton("End Turn");
        challengeButton = new JButton("Challenge");

        // Labels
        scoreLabel = new JLabel("Score: ");
        currentPlayerLabel = new JLabel("Current Player: ");
        statusLabel = new JLabel("Status: ");

        // Score panel
        JPanel scorePanel = new JPanel();
        scorePanel.add(scoreLabel);

        // Player panel
        JPanel playerPanel = new JPanel();
        playerPanel.add(currentPlayerLabel);

        // Status panel
        JPanel statusPanel = new JPanel();
        statusPanel.add(statusLabel);

        // Controls panel
        JPanel controlsPanel = new JPanel();
        controlsPanel.add(endTurnButton);
        controlsPanel.add(challengeButton);

        // Sidebar panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.add(scorePanel);
        sidebarPanel.add(playerPanel);
        sidebarPanel.add(statusPanel);
        sidebarPanel.add(controlsPanel);

        frame.add(sidebarPanel, BorderLayout.EAST);

        // Set frame properties
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addListeners() {
        // End turn button listener
        endTurnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle end turn button action
                // Notify the game logic that the player has ended their turn
                // gameLogic.endTurn();
            }
        });

        // Challenge button listener
        challengeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle challenge button action
                // Notify the game logic that the player has challenged the current word
                // gameLogic.challengeWord();
            }
        });
    }

    public void addTileToBoard(int row, int col, String tile) {
        // TODO: Implement adding a tile to the game board
        JLabel tileLabel = new JLabel(tile);
        tileLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tileLabel.setOpaque(true);
        tileLabel.setBackground(Color.WHITE);
        tileLabel.setPreferredSize(new Dimension(40, 40));
        tileLabel.setHorizontalAlignment(JLabel.CENTER);
        tileLabel.setVerticalAlignment(JLabel.CENTER);
        boardPanel.add(tileLabel, row * 15 + col);
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    public void removeTileFromBoard(int row, int col) {
        // TODO: Implement removing a tile from the game board
        Component[] components = boardPanel.getComponents();
        int index = row * 15 + col;
        if (index >= 0 && index < components.length) {
            boardPanel.remove(index);
            boardPanel.revalidate();
            boardPanel.repaint();
        }
    }

    public void updateRack(String[] tiles) {
        rackPanel.removeAll();

        for (String tile : tiles) {
            JLabel tileLabel = new JLabel(tile);
            tileLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            tileLabel.setOpaque(true);
            tileLabel.setBackground(Color.WHITE);
            tileLabel.setPreferredSize(new Dimension(40, 40));
            tileLabel.setHorizontalAlignment(JLabel.CENTER);
            tileLabel.setVerticalAlignment(JLabel.CENTER);
            rackPanel.add(tileLabel);
        }

        rackPanel.revalidate();
        rackPanel.repaint();
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void updateCurrentPlayer(String playerName) {
        currentPlayerLabel.setText("Current Player: " + playerName);
    }

    public void updateStatus(String status) {
        statusLabel.setText("Status: " + status);
    }

    public String getCurrentPlayer() {
        // TODO: Implement getting the current player's name
        return "";
    }

    public void displayError(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ScrabbleView();
            }
        });
    }
}
