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
        boolean[] guessedLetters = new boolean[26];

        Scanner scanner = new Scanner(System.in);

        while (attemptsLeft > 0) {
            displayCurrentWord(secretWord, guessedLetters);
            System.out.print("Input a letter:> ");
            char userGuess = scanner.next().charAt(0);

            if (guessedLetters[userGuess - 'a']) {
                System.out.println("No improvements");
                continue;
            }

            guessedLetters[userGuess - 'a'] = true;

            if (secretWord.contains(String.valueOf(userGuess))) {
            } else {
                System.out.println("That letter doesn't appear in the word");
                attemptsLeft--;

                if (attemptsLeft > 0) {
                    System.out.println("No improvements");
                }
            }

            if (guessedWord(secretWord, guessedLetters)) {
                System.out.println("Thanks for playing!\n" +
                        "We'll see how well you did in the next stage");
                break;
            }
        }
    }

    private static void displayCurrentWord(String secretWord, boolean[] guessedLetters) {
        for (char letter : secretWord.toCharArray()) {
            if (guessedLetters[letter - 'a']) {
                System.out.print(letter);
            } else {
                System.out.print("-");
            }
        }
        System.out.println();
    }

    private static boolean guessedWord(String secretWord, boolean[] guessedLetters) {
        for (char letter : secretWord.toCharArray()) {
            if (!guessedLetters[letter - 'a']) {
                return false;
            }
        }
        return true;
    }
}