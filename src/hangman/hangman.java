package hangman;
import java.util.Scanner;
import java.util.Random;

public class hangman {

    public static void main(String[] args) {
        System.out.println("HANGMAN");

        String[] words = {"python", "java", "javascript", "kotlin"};

        Random random = new Random();
        int randomIndex = random.nextInt(words.length);
        String secretWord = words[randomIndex];

        int attemptsLeft = 8;

        char[] guessedWord = new char[secretWord.length()];
        for (int i = 0; i < secretWord.length(); i++) {
            guessedWord[i] = '-';
        }

        Scanner scanner = new Scanner(System.in);

        while (attemptsLeft > 0) {
            System.out.println(new String(guessedWord));

            System.out.print("Input a letter:> ");

            char userGuess = scanner.next().charAt(0);

            boolean letterGuessed = false;

            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == userGuess) {
                    guessedWord[i] = userGuess;
                    letterGuessed = true;
                }
            }

            if (letterGuessed) {
            } else {
                System.out.println("That letter doesn't appear in the word");
                attemptsLeft--;
            }

            if (new String(guessedWord).equals(secretWord)) {
                System.out.println("Thanks for playing!\n" +
                        "We'll see how well you did in the next stage");
                break;
            }
        }

        scanner.close();
    }
}
