package com.michaelsemera.digitdash;

import java.util.HashMap;
import java.util.Map;

/**
 * Tracks player statistics across games
 * 
 * @author Michael Semera
 */
public class PlayerStats {
    
    private int totalGames;
    private int totalWins;
    private int totalLosses;
    private int totalScore;
    
    private final Map<Difficulty, DifficultyStats> statsByDifficulty;
    
    public PlayerStats() {
        this.totalGames = 0;
        this.totalWins = 0;
        this.totalLosses = 0;
        this.totalScore = 0;
        this.statsByDifficulty = new HashMap<>();
        
        // Initialize stats for each difficulty
        for (Difficulty diff : Difficulty.values()) {
            statsByDifficulty.put(diff, new DifficultyStats());
        }
    }
    
    /**
     * Record a win
     * 
     * @param difficulty Difficulty level
     * @param attempts Attempts taken
     * @param timeTaken Time taken in seconds
     * @param score Score achieved
     */
    public void recordWin(Difficulty difficulty, int attempts, long timeTaken, int score) {
        totalGames++;
        totalWins++;
        totalScore += score;
        
        DifficultyStats stats = statsByDifficulty.get(difficulty);
        stats.recordWin(attempts, timeTaken);
    }
    
    /**
     * Record a loss
     */
    public void recordLoss() {
        totalGames++;
        totalLosses++;
    }
    
    /**
     * Get total games played
     * 
     * @return Total games
     */
    public int getTotalGames() {
        return totalGames;
    }
    
    /**
     * Get total wins
     * 
     * @return Total wins
     */
    public int getTotalWins() {
        return totalWins;
    }
    
    /**
     * Get total losses
     * 
     * @return Total losses
     */
    public int getTotalLosses() {
        return totalLosses;
    }
    
    /**
     * Get win rate percentage
     * 
     * @return Win rate (0-100)
     */
    public double getWinRate() {
        if (totalGames == 0) return 0.0;
        return (double) totalWins / totalGames * 100;
    }
    
    /**
     * Get total cumulative score
     * 
     * @return Total score
     */
    public int getTotalScore() {
        return totalScore;
    }
    
    /**
     * Get average score per game
     * 
     * @return Average score
     */
    public double getAverageScore() {
        if (totalWins == 0) return 0.0;
        return (double) totalScore / totalWins;
    }
    
    /**
     * Get wins for specific difficulty
     * 
     * @param difficulty Difficulty level
     * @return Number of wins
     */
    public int getWins(Difficulty difficulty) {
        return statsByDifficulty.get(difficulty).getWins();
    }
    
    /**
     * Get best (minimum) attempts for difficulty
     * 
     * @param difficulty Difficulty level
     * @return Best attempts, or max value if no wins
     */
    public int getBestAttempts(Difficulty difficulty) {
        return statsByDifficulty.get(difficulty).getBestAttempts();
    }
    
    /**
     * Get average attempts for difficulty
     * 
     * @param difficulty Difficulty level
     * @return Average attempts
     */
    public double getAverageAttempts(Difficulty difficulty) {
        return statsByDifficulty.get(difficulty).getAverageAttempts();
    }
    
    /**
     * Inner class for tracking stats per difficulty
     */
    private static class DifficultyStats {
        private int wins;
        private int totalAttempts;
        private int bestAttempts;
        private long totalTime;
        
        public DifficultyStats() {
            this.wins = 0;
            this.totalAttempts = 0;
            this.bestAttempts = Integer.MAX_VALUE;
            this.totalTime = 0;
        }
        
        public void recordWin(int attempts, long timeTaken) {
            wins++;
            totalAttempts += attempts;
            totalTime += timeTaken;
            
            if (attempts < bestAttempts) {
                bestAttempts = attempts;
            }
        }
        
        public int getWins() {
            return wins;
        }
        
        public int getBestAttempts() {
            return bestAttempts == Integer.MAX_VALUE ? 0 : bestAttempts;
        }
        
        public double getAverageAttempts() {
            if (wins == 0) return 0.0;
            return (double) totalAttempts / wins;
        }
        
        public double getAverageTime() {
            if (wins == 0) return 0.0;
            return (double) totalTime / wins;
        }
    }
}