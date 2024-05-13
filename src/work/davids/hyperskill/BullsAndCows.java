package work.davids.hyperskill;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Class to represent the Bulls and Cows game with enhanced error handling and game setup.
 */
public class BullsAndCows {

    private String secretCode;
    private final int codeLength;
    private final int possibleSymbolsRange;

    /**
     * Constructor to initialize the game settings.
     * @param length Length of the secret code.
     * @param range Total possible symbols to use in the code.
     */
    public BullsAndCows(int length, int range) {
        this.codeLength = length;
        this.possibleSymbolsRange = range;
    }

    /**
     * Generates a secret code with unique characters based on the specified length and range.
     * Throws IllegalArgumentException if input values are not within expected limits.
     */
    public void generateSecretCode() {
        if (codeLength > possibleSymbolsRange) {
            throw new IllegalArgumentException("Error: It's not possible to generate a code with a length of " + codeLength +
                    " with only " + possibleSymbolsRange + " unique symbols.");
        }
        if (possibleSymbolsRange > 36) {
            throw new IllegalArgumentException("Error: The maximum number of possible symbols in the code is 36 (0-9, a-z).");
        }

        Random random = new Random();
        Set<Character> characters = new LinkedHashSet<>();
        String possibleCharacters = "0123456789abcdefghijklmnopqrstuvwxyz".substring(0, possibleSymbolsRange);

        while (characters.size() < codeLength) {
            characters.add(possibleCharacters.charAt(random.nextInt(possibleSymbolsRange)));
        }

        StringBuilder secretCodeBuilder = new StringBuilder();
        for (Character ch : characters) {
            secretCodeBuilder.append(ch);
        }
        secretCode = secretCodeBuilder.toString();
        System.out.println("The secret is prepared: " + "*".repeat(codeLength) +
                " (" + possibleCharacters.charAt(0) + "-" +
                possibleCharacters.charAt(possibleCharacters.length() - 1) + ").");
    }

    /**
     * Starts the game and processes user guesses.
     */
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Okay, let's start a game!");
        int turn = 1;
        boolean guessed = false;

        while (!guessed) {
            System.out.println("Turn " + turn + ":");
            String guess = scanner.next();

            if (guess.length() != codeLength) {
                System.out.println("Error: Your guess must be exactly " + codeLength + " characters long.");
                continue;
            }

            String result = gradeGuess(guess);
            System.out.println("Grade: " + result);

            if (result.equals(codeLength + " bulls")) {
                System.out.println("Congratulations! You guessed the secret code.");
                guessed = true;
            }
            turn++;
        }
        scanner.close();
    }

    /**
     * Grades the user's guess against the secret code, counting bulls and cows.
     * @param guess User's guess of the code.
     * @return Grading result as a string.
     */
    private String gradeGuess(String guess) {
        int bulls = 0, cows = 0;

        for (int i = 0; i < codeLength; i++) {
            char guessChar = guess.charAt(i);
            if (guessChar == secretCode.charAt(i)) {
                bulls++;
            } else if (secretCode.contains(String.valueOf(guessChar))) {
                cows++;
            }
        }

        return (bulls == 0 && cows == 0) ? "None" :
                (bulls > 0 ? bulls + " bulls" : "") +
                        (cows > 0 ? (bulls > 0 ? " and " : "") + cows + " cows" : "");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        int length = scanner.nextInt();
        System.out.println("Input the number of possible symbols in the code:");
        int range = scanner.nextInt();

        try {
            BullsAndCows game = new BullsAndCows(length, range);
            game.generateSecretCode();
            game.startGame();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }
}
