package hangman;
import java.util.Scanner;


public class hangman {
    public static void main(String[] args) {
        System.out.println("HANGMAN");
        System.out.println("The game will be available soon.");

        String secretWord = "bimba";

        System.out.print("Guess the word:> ");

        Scanner scanner = new Scanner(System.in);
        String userGuess = scanner.nextLine();

        if (userGuess.equalsIgnoreCase(secretWord)) {
            System.out.println("You survived!");
        } else {
            System.out.println("You lost!");
        }
        scanner.close();
    }
}
