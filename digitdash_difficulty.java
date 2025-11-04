package com.michaelsemera.digitdash;

/**
 * Difficulty levels for the game
 * 
 * @author Michael Semera
 */
public enum Difficulty {
    EASY(1, 50, 12, 1.0),
    MEDIUM(1, 100, 10, 1.5),
    HARD(1, 500, 8, 2.0);
    
    private final int minRange;
    private final int maxRange;
    private final int maxAttempts;
    private final double scoreMultiplier;
    
    /**
     * Constructor for difficulty levels
     * 
     * @param minRange Minimum number in range
     * @param maxRange Maximum number in range
     * @param maxAttempts Maximum allowed attempts
     * @param scoreMultiplier Score multiplier for this difficulty
     */
    Difficulty(int minRange, int maxRange, int maxAttempts, double scoreMultiplier) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.maxAttempts = maxAttempts;
        this.scoreMultiplier = scoreMultiplier;
    }
    
    public int getMinRange() {
        return minRange;
    }
    
    public int getMaxRange() {
        return maxRange;
    }
    
    public int getMaxAttempts() {
        return maxAttempts;
    }
    
    public double getScoreMultiplier() {
        return scoreMultiplier;
    }
    
    /**
     * Get the range size
     * 
     * @return Size of the number range
     */
    public int getRangeSize() {
        return maxRange - minRange + 1;
    }
    
    /**
     * Get description of the difficulty
     * 
     * @return Human-readable description
     */
    public String getDescription() {
        return String.format("%s (%d-%d, %d attempts)", 
                           name(), minRange, maxRange, maxAttempts);
    }
}