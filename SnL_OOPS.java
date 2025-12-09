import java.util.Random;

// Class defining the Player (Encapsulation)
class Player {
    private String name;
    private int position;

    public Player(String name) {
        this.name = name;
        this.position = 0;
    }

    public void move(int steps) {
        this.position += steps;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }
}

// Class defining the Game Board logic
class GameBoard {
    private final int WIN_POINT = 100;

    public boolean checkWin(Player p) {
        return p.getPosition() == WIN_POINT;
    }

    // Method to handle special tiles (Snakes/Ladders logic)
    public void checkSpecialTiles(Player p) {
        int pos = p.getPosition();
        
        if (pos > WIN_POINT) {
            System.out.print(" (Overshot! Stay put)");
            p.setPosition(pos - (pos - WIN_POINT)); // Logic to stay at previous spot (simplified)
            // Ideally needs previous position tracking, but simplified for demo
            p.setPosition(100 - (pos - 100)); // Bounce back logic
        }

        switch (pos) {
            case 10:
                System.out.print(" [LADDER! Up to 30]");
                p.setPosition(30);
                break;
            case 50:
                System.out.print(" [LADDER! Up to 70]");
                p.setPosition(70);
                break;
            case 99:
                System.out.print(" [SNAKE! Down to 10]");
                p.setPosition(10);
                break;
        }
    }
}

public class SnakeAndLadder_Level2 {
    public static void main(String[] args) {
        System.out.println("--- Snake and Ladder: OOP Edition ---");
        
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        GameBoard board = new GameBoard();
        Random dice = new Random();
        
        Player currentPlayer = p1;

        while (true) {
            int roll = dice.nextInt(6) + 1;
            System.out.print(currentPlayer.getName() + " rolled " + roll);
            
            currentPlayer.move(roll);
            board.checkSpecialTiles(currentPlayer);
            
            System.out.println(" -> New Position: " + currentPlayer.getPosition());

            if (board.checkWin(currentPlayer)) {
                System.out.println("\nWINNER: " + currentPlayer.getName() + " wins the game!");
                break;
            }

            // Switch Object reference
            if(currentPlayer == p1) currentPlayer = p2;
            else currentPlayer = p1;
        }
    }
}
