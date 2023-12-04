package hangman;
import java.util.Scanner;
import java.util.Random;

public class hangman {
    public static void main(String[] args) {
        System.out.println("HANGMAN");
        System.out.println("The game will be available soon.");

        String[] words = {"python", "java", "javascript", "kotlin"};

        Random random = new Random();
        int randomIndex = random.nextInt(words.length);
        String secretWord = words[randomIndex];

        StringBuilder hint = new StringBuilder(secretWord.substring(0, 2));
        for (int i = 2; i < secretWord.length(); i++) {
            hint.append("-");
        }

        System.out.print("Guess the word " + hint + ": > ");

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
