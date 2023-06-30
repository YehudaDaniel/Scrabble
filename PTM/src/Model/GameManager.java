package Model;

import java.net.InetAddress;

public class GameManager
{
    public Host host;

    public GameManager(InetAddress ip, int serverPort, int hostPort, int rounds) {
        this.host = Host.getHost(ip, serverPort, hostPort, rounds);
    }

    public void runGame() {
        public void runGame() {
            // Check if the host is ready
            if (host.isReady()) {
                // Start the game
                host.startGame();

                // Run the game loop for the specified number of rounds
                for (int i = 0; i < host.getRounds(); i++) {
                    // Perform game logic for each round
                    host.playRound();
                }

                // End the game
                host.endGame();
            } else {
                System.out.println("The host is not ready. Please ensure all necessary configurations are set up.");
            }
        }
    }
}

