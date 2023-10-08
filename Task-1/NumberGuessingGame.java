import java.util.Scanner;
import java.util.Random;

//////////////////////////////////////////////////////////////////////////
//
//  Class name :         NumberGuessingGame
//  Description :        This class implements a simple number guessing game.
//                       The player tries to guess a randomly generated number
//                       within a specified range. The game provides hints and
//                       tracks the number of attempts and total score.
//  Author :             Navnath Jadhav
//  Date :               01/10/2023
//  Update Date :        08/10/2023
//
//////////////////////////////////////////////////////////////////////////

public class NumberGuessingGame {

    // Entry Point function
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int minRange = 1;
        int maxRange = 100;
        int totalRounds = 3; // Number of rounds to play
        int maxAttempts = 10; // Maximum number of attempts per round
        int totalScore = 0;

        // ANSI escape codes are used to change text colors in the console output.
        // Here's a brief explanation of the ANSI escape codes used in this program:
        // - "\u001B[32m": Sets the text color to green. The text after this code will be displayed in green.
        // - "\u001B[33m": Sets the text color to yellow. The text after this code will be displayed in yellow.
        // - "\u001B[31m": Sets the text color to red. The text after this code will be displayed in red.
        // - "\u001B[0m": Resets the text color to the default color. This is used to end the color change sequence.

        // Display welcome message in green color
        System.out.println("\u001B[32m********************************************\u001B[0m");
        System.out.println("\u001B[32m*   Welcome to the Guess the Number Game   *\u001B[0m");
        System.out.println("\u001B[32m*       Can you guess the secret number?   *\u001B[0m");
        System.out.println("\u001B[32m********************************************\u001B[0m");


        // Loop through the rounds
        for (int round = 1; round <= totalRounds; round++) {
            int secretNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;

            // Allow the player to guess the number within a limited number of attempts
            while (attempts < maxAttempts) {
                // Prompt user for input and change text color to yellow
                System.out.print("\u001B[33m* Round " + round + " - Attempt " + (attempts + 1) + "/" + maxAttempts +
                                 ": Enter your guess: \u001B[0m");
                int guess = scanner.nextInt();
                attempts++;

                // Check if the guess is correct, too high, or too low
                if (guess == secretNumber) {
                    // Display success message in green color
                    System.out.println("\u001B[32m* Congratulations! You guessed the number in " + attempts + " attempts. *\u001B[0m");
                    totalScore += maxAttempts - attempts + 1;
                    break;
                } else if (guess < secretNumber) {
                    // Display hint message in red color
                    System.out.println("\u001B[31m* Higher! Try again.                         *\u001B[0m");
                } else {
                    // Display hint message in red color
                    System.out.println("\u001B[31m* Lower! Try again.                          *\u001B[0m");
                }
            }

            // Check if the player has run out of attempts
            if (attempts == maxAttempts) {
                // Display out of attempts message in red color
                System.out.println("\u001B[31m* Out of attempts! The secret number was: " + secretNumber + "     *\u001B[0m");
            }

            // Prompt for the next round or end the game
            if (round < totalRounds) {
                // Prompt user for input and change text color to yellow
                System.out.print("\u001B[33m* Do you want to play the next round? (yes/no): \u001B[0m");
                String playAgain = scanner.next().toLowerCase();
                if (!playAgain.equals("yes")) {
                    break;
                }
            }
        }

        // Display game over message and total score in green color
        System.out.println("\u001B[32m********************************************\u001B[0m");
        System.out.println("\u001B[32m*              Game Over!                   *\u001B[0m");
        System.out.println("\u001B[32m*            Total Score: " + totalScore + "              *\u001B[0m");
        System.out.println("\u001B[32m********************************************\u001B[0m");

        // Close the scanner
        scanner.close();
    }
}
