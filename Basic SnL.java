import java.util.Random;

public class SnakeAndLadder_Level1 {

    public static void main(String[] args) {
        System.out.println("--- Snake and Ladder: Operator Edition ---");
        
        int player1Position = 0;
        int player2Position = 0;
        boolean isPlayer1Turn = true;
        int winPoint = 100;
        
        Random dice = new Random();

        // Game Loop using logical operators
        while (player1Position < winPoint && player2Position < winPoint) {
            
            int roll = dice.nextInt(6) + 1; // Arithmetic operator
            
            if (isPlayer1Turn) {
                System.out.print("Player 1 rolls: " + roll);
                player1Position += roll; // Assignment operator
                
                // Simple board logic using Relational Operators
                // Ladder at 10 goes to 30
                if (player1Position == 10) { 
                    System.out.print(" [LADDER! +20]");
                    player1Position += 20; 
                } 
                // Snake at 99 goes to 10
                else if (player1Position == 99) {
                    System.out.print(" [SNAKE! -89]");
                    player1Position = 10;
                }
                
                // Ensure exact win logic using Comparison
                if (player1Position > winPoint) {
                    player1Position -= roll; // Bounce back if overshot
                }

                System.out.println(" -> Pos: " + player1Position);
                
                if (player1Position == winPoint) {
                    System.out.println("\nPLAYER 1 WINS!");
                    break;
                }
                
            } else {
                // Player 2 Logic (Identical structure)
                System.out.print("Player 2 rolls: " + roll);
                player2Position += roll;
                
                if (player2Position == 10) { 
                    System.out.print(" [LADDER!]");
                    player2Position = 30; 
                } else if (player2Position == 99) {
                    System.out.print(" [SNAKE!]");
                    player2Position = 10;
                }

                if (player2Position > winPoint) {
                    player2Position -= roll;
                }
                
                System.out.println(" -> Pos: " + player2Position);
                
                if (player2Position == winPoint) {
                    System.out.println("\nPLAYER 2 WINS!");
                    break;
                }
            }
            
            // Toggle turn using NOT operator
            isPlayer1Turn = !isPlayer1Turn;
        }
    }
}
