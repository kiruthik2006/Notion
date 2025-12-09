import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

class InvalidTurnException extends Exception {
    public InvalidTurnException(String message) {
        super(message);
    }
}

public class SnakeAndLadder_Level3 {
    static int position = 0;
    static final int WIN_POINT = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random dice = new Random();
        
        System.out.println("--- Snake and Ladder: Exception Handling Edition ---");
        System.out.println("Hit '1' to roll the dice. Hit '0' to quit.");

        while (position < WIN_POINT) {
            try {
                System.out.print("\nYour Turn (Current Pos: " + position + ") > ");
                
                // 1. Handling Input format exceptions
                int action = scanner.nextInt(); 

                // 2. Handling Logical validation via Custom/Throwing Exception
                if (action != 1 && action != 0) {
                    throw new InvalidTurnException("Invalid Input! Please enter 1 to roll or 0 to quit.");
                }

                if (action == 0) {
                    System.out.println("Game Exited by user.");
                    break;
                }

                // Simulating calculation delay (Handling InterruptedException)
                System.out.print("Rolling");
                for(int i=0; i<3; i++) {
                    System.out.print(".");
                    Thread.sleep(300); 
                }

                int roll = dice.nextInt(6) + 1;
                System.out.println(" You rolled a " + roll);

                position += roll;

                // Basic board logic
                if (position == 25) { 
                    position = 50; 
                    System.out.println("LADDER! Jumped to 50.");
                }
                else if (position == 99) { 
                    position = 10; 
                    System.out.println("SNAKE! Fell to 10.");
                }

                if (position >= WIN_POINT) {
                    System.out.println("CONGRATULATIONS! You reached the finish line.");
                }

            } catch (InputMismatchException e) {
                // Catches if user types "A", "roll", etc. instead of a number
                System.out.println("Error: expecting a number (1 or 0). Please try again.");
                scanner.next(); // Clear the invalid input from buffer
            } catch (InvalidTurnException e) {
                // Catches our custom logic error
                System.out.println(e.getMessage());
            } catch (InterruptedException e) {
                // Catches Thread.sleep issues
                System.out.println("Game flow interrupted.");
            } catch (Exception e) {
                // Catch-all for unexpected errors
                System.out.println("An unexpected error occurred.");
            }
        }
        scanner.close();
    }
}
