
import java.util.Scanner;

public class HangmanGame {
    private static final String[] WORDS = { "apple", "banana", "orange", "watermelon", "strawberry" ,"papaya"};
    private static final int MAX_TRIES = 6;

    private String word;
    private StringBuilder guessedLetters;
    private int remainingTries;

    public HangmanGame() {
        word = getRandomWord();
        guessedLetters = new StringBuilder();
        remainingTries = MAX_TRIES;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Hangman!");

        while (true) {
            displayGameState();

            System.out.print("Enter a letter: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Invalid input. Please enter a single letter.");
                continue;
            }

            char letter = input.charAt(0);

            if (guessedLetters.indexOf(Character.toString(letter)) != -1) {
                System.out.println("You've already guessed that letter. Try again.");
                continue;
            }

            guessedLetters.append(letter);

            if (word.indexOf(letter) == -1) {
                remainingTries--;
                System.out.println("Incorrect guess. You have " + remainingTries + " tries remaining.");

                if (remainingTries == 0) {
                    displayGameState();
                    System.out.println("Game over! You ran out of tries. The word was: " + word);
                    break;
                }
            } else {
                if (isWordGuessed()) {
                    displayGameState();
                    System.out.println("Congratulations! You guessed the word: " + word);
                    break;
                }
            }
        }

        scanner.close();
    }

    private void displayGameState() {
        System.out.println();

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);

            if (guessedLetters.indexOf(Character.toString(letter)) != -1) {
                System.out.print(letter + " ");
            } else {
                System.out.print("_ ");
            }
        }

        System.out.println();
    }

    private boolean isWordGuessed() {
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);

            if (guessedLetters.indexOf(Character.toString(letter)) == -1) {
                return false;
            }
        }

        return true;
    }

    private String getRandomWord() {
        int index = (int) (Math.random() * WORDS.length);
        return WORDS[index];
    }

    public static void main(String[] args) {
        HangmanGame game = new HangmanGame();
        game.play();
    }
}
