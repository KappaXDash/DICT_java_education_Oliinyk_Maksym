package hangman;

import java.util.Scanner;
import java.util.Random;

public class hangman {

    public static void main(String[] args) {
        boolean playAgain = true;
        Scanner scanner = new Scanner(System.in);

        while (playAgain) {
            System.out.println("HANGMAN");
            System.out.print("Type \"play\" to play the game, \"exit\" to quit: ");
            String userInput = scanner.next();

            if (userInput.equals("play")) {
                playGame();
            } else if (userInput.equals("exit")) {
                System.out.println("Goodbye!");
                playAgain = false;
            } else {
                System.out.println("Invalid input. Please enter \"play\" or \"exit\".");
            }
        }
    }

    private static void playGame() {
        String[] words = {"python", "java", "javascript", "kotlin"};
        Random random = new Random();
        int randomIndex = random.nextInt(words.length);
        String secretWord = words[randomIndex];

        int attemptsLeft = 8;
        boolean[] guessedLetters = new boolean[26];

        Scanner scanner = new Scanner(System.in);

        while (attemptsLeft > 0) {
            displayCurrentWord(secretWord, guessedLetters);
            System.out.print("Input a letter: > ");
            String userInput = scanner.next();

            if (userInput.length() != 1) {
                System.out.println("You should input a single letter");
                continue;
            }

            char userGuess = userInput.charAt(0);

            if (!Character.isLowerCase(userGuess)) {
                System.out.println("Please enter a lowercase English letter");
                continue;
            }

            if (guessedLetters[userGuess - 'a']) {
                System.out.println("You've already guessed this letter");
                continue;
            }

            guessedLetters[userGuess - 'a'] = true;

            if (secretWord.contains(String.valueOf(userGuess))) {
                System.out.println();
            } else {
                System.out.println("That letter doesn't appear in the word");
                attemptsLeft--;

                if (attemptsLeft > 0) {
                    System.out.println();
                } else {
                    System.out.println("You lost!");
                    break;
                }
            }

            if (guessedWord(secretWord, guessedLetters)) {
                System.out.println("You guessed the word " + secretWord + "\nYou survived!");
                break;
            }
        }
    }

    private static void displayCurrentWord(String secretWord, boolean[] guessedLetters) {
        System.out.print("Current word: ");
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