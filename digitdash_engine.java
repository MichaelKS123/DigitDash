package com.michaelsemera.digitdash;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Core game engine handling number generation and guess validation
 * 
 * @author Michael Semera
 */
public class GameEngine {
    
    private int targetNumber;
    private Difficulty currentDifficulty;
    private final SecureRandom random;
    private final List<Integer> guessHistory;
    private int attemptCount;
    
    public GameEngine() {
        this.random = new SecureRandom();
        this.guessHistory = new ArrayList<>();
        this.attemptCount = 0;
    }
    
    /**
     * Start a new game with specified difficulty
     * 
     * @param difficulty The difficulty level
     */
    public void startNewGame(Difficulty difficulty) {
        this.currentDifficulty = difficulty;
        this.targetNumber = generateRandomNumber(
            difficulty.getMinRange(), 
            difficulty.getMaxRange()
        );
        this.guessHistory.clear();
        this.attemptCount = 0;
    }
    
    /**
     * Generate cryptographically secure random number within range
     * 
     * @param min Minimum value (inclusive)
     * @param max Maximum value (inclusive)
     * @return Random number
     */
    private int generateRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
    
    /**
     * Process a player's guess
     * 
     * @param guess The player's guess
     * @return Result of the guess
     */
    public GuessResult makeGuess(int guess) {
        attemptCount++;
        guessHistory.add(guess);
        
        if (guess < targetNumber) {
            return GuessResult.TOO_LOW;
        } else if (guess > targetNumber) {
            return GuessResult.TOO_HIGH;
        } else {
            return GuessResult.CORRECT;
        }
    }
    
    /**
     * Get the target number (for end of game reveal)
     * 
     * @return The target number
     */
    public int getTargetNumber() {
        return targetNumber;
    }
    
    /**
     * Get current attempt count
     * 
     * @return Number of attempts made
     */
    public int getAttemptCount() {
        return attemptCount;
    }
    
    /**
     * Get guess history
     * 
     * @return List of all guesses made
     */
    public List<Integer> getGuessHistory() {
        return new ArrayList<>(guessHistory);
    }
    
    /**
     * Check if a number has been guessed before
     * 
     * @param guess The number to check
     * @return true if already guessed
     */
    public boolean hasBeenGuessed(int guess) {
        return guessHistory.contains(guess);
    }
    
    /**
     * Get the range of numbers still possible
     * 
     * @return Array with [min, max] possible values
     */
    public int[] getRemainingRange() {
        int min = currentDifficulty.getMinRange();
        int max = currentDifficulty.getMaxRange();
        
        for (int guess : guessHistory) {
            if (guess < targetNumber && guess >= min) {
                min = guess + 1;
            } else if (guess > targetNumber && guess <= max) {
                max = guess - 1;
            }
        }
        
        return new int[]{min, max};
    }
}

/**
 * Enum representing the result of a guess
 */
enum GuessResult {
    TOO_LOW("Too Low - Guess Higher"),
    TOO_HIGH("Too High - Guess Lower"),
    CORRECT("Correct - You Win!");
    
    private final String message;
    
    GuessResult(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
}