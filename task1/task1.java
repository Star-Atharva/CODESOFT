package task1;
import java.util.Random;
import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int totalScore = 0;
        int round = 1;
        System.out.println("Number guessing game");
        boolean playAgain = true;
        while (playAgain) {
            int numberToGuess = rand.nextInt(100) + 1;
            int maxAttempts = 5;
            int attempts = 0;
            boolean guessedCorrectly = false;
            System.out.println("\nRound " + round);
            System.out.println("Guess a number between 1 and 100");
            System.out.println("You have " + maxAttempts + " attempts");
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = sc.nextInt();
                attempts++;
                if (userGuess == numberToGuess) {
                    System.out.println("✅ Correct! You guessed it in " + attempts + " attempts.");
                    int score = (maxAttempts - attempts + 1) * 10;
                    totalScore += score;

                    System.out.println("Score this round: " + score);
                    guessedCorrectly = true;
                    break;

                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Too low! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("❌ Out of attempts! The number was: " + numberToGuess);
            }

            System.out.println("Total Score: " + totalScore);

            // Ask to play again
            System.out.print("Do you want to play another round? (yes/no): ");
            String choice = sc.next();

            if (!choice.equalsIgnoreCase("yes")) {
                playAgain = false;
            }

            round++;
        }

        System.out.println("\n===== GAME OVER =====");
        System.out.println("Final Score: " + totalScore);

        sc.close();
    }
}