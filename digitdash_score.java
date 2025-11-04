package com.michaelsemera.digitdash;

/**
 * Manages scoring system for the game
 * 
 * @author Michael Semera
 */
public class ScoreManager {
    
    private static final int BASE_SCORE = 1000;
    private static final int ATTEMPT_BONUS_MULTIPLIER = 50;
    private static final int TIME_BONUS_THRESHOLD = 60; // seconds
    private static final int TIME_BONUS = 200;
    private static final int PERFECT_GAME_BONUS = 500;
    
    /**
     * Calculate score based on performance
     * 
     * @param difficulty Game difficulty
     * @param attemptsTaken Number of attempts used
     * @param timeTaken Time taken in seconds
     * @return Calculated score
     */
    public int calculateScore(Difficulty difficulty, int attemptsTaken, long timeTaken) {
        double score = BASE_SCORE;
        
        // Difficulty multiplier
        score *= difficulty.getScoreMultiplier();
        
        // Attempt bonus (fewer attempts = higher bonus)
        int attemptsRemaining = difficulty.getMaxAttempts() - attemptsTaken;
        score += attemptsRemaining * ATTEMPT_BONUS_MULTIPLIER;
        
        // Time bonus for quick completion
        if (timeTaken < TIME_BONUS_THRESHOLD) {
            score += TIME_BONUS;
        }
        
        // Perfect game bonus (3 or fewer attempts)
        if (attemptsTaken <= 3) {
            score += PERFECT_GAME_BONUS;
        }
        
        // Efficiency bonus (completed in less than 50% of max attempts)
        if (attemptsTaken <= difficulty.getMaxAttempts() / 2) {
            score *= 1.25; // 25% bonus
        }
        
        return (int) Math.round(score);
    }
    
    /**
     * Get grade based on score
     * 
     * @param score The score achieved
     * @return Grade letter (S, A, B, C, D, F)
     */
    public String getGrade(int score) {
        if (score >= 3000) return "S"; // Perfect
        if (score >= 2500) return "A+";
        if (score >= 2000) return "A";
        if (score >= 1500) return "B";
        if (score >= 1000) return "C";
        if (score >= 500) return "D";
        return "F";
    }
    
    /**
     * Calculate rank based on total score
     * 
     * @param totalScore Cumulative score
     * @return Rank name
     */
    public String calculateRank(int totalScore) {
        if (totalScore >= 50000) return "Grand Master";
        if (totalScore >= 30000) return "Master";
        if (totalScore >= 20000) return "Expert";
        if (totalScore >= 10000) return "Advanced";
        if (totalScore >= 5000) return "Intermediate";
        if (totalScore >= 2000) return "Novice";
        return "Beginner";
    }
}