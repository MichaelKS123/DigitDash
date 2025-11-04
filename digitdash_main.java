package com.michaelsemera.digitdash;

import java.util.Scanner;

/**
 * DigitDash - Advanced Number Guessing Game
 * 
 * @author Michael Semera
 * @version 1.0
 * 
 * A feature-rich console-based number guessing game with multiple difficulty
 * levels, scoring system, statistics tracking, and achievements.
 */
public class DigitDashGame {
    
    private final Scanner scanner;
    private final GameEngine gameEngine;
    private final ScoreManager scoreManager;
    private final PlayerStats playerStats;
    private boolean isRunning;
    
    // ANSI colour codes for UK-styled console output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_BOLD = "\u001B[1m";
    
    /**
     * Constructor initializes game components
     */
    public DigitDashGame() {
        this.scanner = new Scanner(System.in);
        this.gameEngine = new GameEngine();
        this.scoreManager = new ScoreManager();
        this.playerStats = new PlayerStats();
        this.isRunning = true;
    }
    
    /**
     * Main game loop
     */
    public void start() {
        displayWelcomeBanner();
        
        while (isRunning) {
            displayMainMenu();
            int choice = getMenuChoice(1, 6);
            
            switch (choice) {
                case 1 -> playGame(Difficulty.EASY);
                case 2 -> playGame(Difficulty.MEDIUM);
                case 3 -> playGame(Difficulty.HARD);
                case 4 -> displayStatistics();
                case 5 -> displayInstructions();
                case 6 -> exitGame();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Display welcome banner with ASCII art
     */
    private void displayWelcomeBanner() {
        clearScreen();
        System.out.println(ANSI_CYAN + ANSI_BOLD);
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                            ║");
        System.out.println("║                     DIGITDASH                              ║");
        System.out.println("║                                                            ║");
        System.out.println("║              The Number Guessing Challenge                 ║");
        System.out.println("║                                                            ║");
        System.out.println("║                   By Michael Semera                        ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println(ANSI_RESET);
        pause(1000);
    }
    
    /**
     * Display main menu
     */
    private void displayMainMenu() {
        clearScreen();
        System.out.println(ANSI_BOLD + "\n┌─────────────── MAIN MENU ───────────────┐" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "│  1. Play Easy     (1-50, 12 attempts)  │" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "│  2. Play Medium   (1-100, 10 attempts) │" + ANSI_RESET);
        System.out.println(ANSI_RED + "│  3. Play Hard     (1-500, 8 attempts)  │" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "│  4. View Statistics                    │" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "│  5. Instructions                       │" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "│  6. Exit Game                          │" + ANSI_RESET);
        System.out.println(ANSI_BOLD + "└─────────────────────────────────────────┘" + ANSI_RESET);
        System.out.print("\nEnter your choice (1-6): ");
    }
    
    /**
     * Main game play logic
     */
    private void playGame(Difficulty difficulty) {
        clearScreen();
        gameEngine.startNewGame(difficulty);
        
        displayGameHeader(difficulty);
        
        boolean gameWon = false;
        int attempts = 0;
        long startTime = System.currentTimeMillis();
        
        while (attempts < difficulty.getMaxAttempts() && !gameWon) {
            attempts++;
            
            System.out.println(ANSI_BOLD + "\n═══════════════════════════════════════════════" + ANSI_RESET);
            System.out.printf("Attempt %d of %d\n", attempts, difficulty.getMaxAttempts());
            System.out.println(ANSI_BOLD + "═══════════════════════════════════════════════" + ANSI_RESET);
            
            int guess = getValidGuess(difficulty.getMinRange(), difficulty.getMaxRange());
            GuessResult result = gameEngine.makeGuess(guess);
            
            displayGuessResult(result, guess);
            
            if (result == GuessResult.CORRECT) {
                gameWon = true;
                long timeTaken = (System.currentTimeMillis() - startTime) / 1000;
                handleVictory(difficulty, attempts, timeTaken);
            } else if (attempts == difficulty.getMaxAttempts()) {
                handleDefeat();
            } else {
                displayRemainingAttempts(difficulty.getMaxAttempts() - attempts);
                displayHint(result, guess, gameEngine.getTargetNumber(), difficulty);
            }
        }
        
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    /**
     * Display game header with difficulty info
     */
    private void displayGameHeader(Difficulty difficulty) {
        String colour = switch (difficulty) {
            case EASY -> ANSI_GREEN;
            case MEDIUM -> ANSI_YELLOW;
            case HARD -> ANSI_RED;
        };
        
        System.out.println(colour + ANSI_BOLD);
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.printf("║  Difficulty: %-45s ║%n", difficulty.name());
        System.out.printf("║  Range: %d - %-44d ║%n", difficulty.getMinRange(), difficulty.getMaxRange());
        System.out.printf("║  Maximum Attempts: %-36d ║%n", difficulty.getMaxAttempts());
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println(ANSI_RESET);
        System.out.println("I'm thinking of a number... Can you guess it?\n");
    }
    
    /**
     * Get valid guess from player
     */
    private int getValidGuess(int min, int max) {
        int guess;
        while (true) {
            System.out.printf("Enter your guess (%d-%d): ", min, max);
            
            if (scanner.hasNextInt()) {
                guess = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                if (guess >= min && guess <= max) {
                    return guess;
                } else {
                    System.out.println(ANSI_RED + "⚠ Invalid! Number must be between " + 
                                     min + " and " + max + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "⚠ Invalid input! Please enter a number." + ANSI_RESET);
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
    
    /**
     * Display result of guess
     */
    private void displayGuessResult(GuessResult result, int guess) {
        System.out.println();
        switch (result) {
            case TOO_LOW -> {
                System.out.println(ANSI_BLUE + "┌─────────────────────────────────┐");
                System.out.println("│  📈 Too Low! Go Higher!         │");
                System.out.println("└─────────────────────────────────┘" + ANSI_RESET);
            }
            case TOO_HIGH -> {
                System.out.println(ANSI_YELLOW + "┌─────────────────────────────────┐");
                System.out.println("│  📉 Too High! Go Lower!         │");
                System.out.println("└─────────────────────────────────┘" + ANSI_RESET);
            }
            case CORRECT -> {
                System.out.println(ANSI_GREEN + ANSI_BOLD);
                System.out.println("╔═════════════════════════════════════════════╗");
                System.out.println("║                                             ║");
                System.out.println("║         🎉 CONGRATULATIONS! 🎉              ║");
                System.out.println("║                                             ║");
                System.out.println("║         You guessed correctly!              ║");
                System.out.println("║                                             ║");
                System.out.println("╚═════════════════════════════════════════════╝");
                System.out.println(ANSI_RESET);
            }
        }
    }
    
    /**
     * Display intelligent hint based on proximity
     */
    private void displayHint(GuessResult result, int guess, int target, Difficulty difficulty) {
        int difference = Math.abs(target - guess);
        int range = difficulty.getMaxRange() - difficulty.getMinRange();
        double percentOff = (double) difference / range * 100;
        
        System.out.println(ANSI_CYAN + "\n💡 Hint: " + ANSI_RESET);
        
        if (percentOff <= 5) {
            System.out.println(ANSI_GREEN + "🔥 Burning hot! You're extremely close!" + ANSI_RESET);
        } else if (percentOff <= 10) {
            System.out.println(ANSI_YELLOW + "♨️ Very warm! Getting closer!" + ANSI_RESET);
        } else if (percentOff <= 20) {
            System.out.println(ANSI_BLUE + "🌡️ Warm! You're in the right area." + ANSI_RESET);
        } else if (percentOff <= 40) {
            System.out.println(ANSI_PURPLE + "❄️ Cool. Keep searching!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_CYAN + "🧊 Cold. Try a different range!" + ANSI_RESET);
        }
        
        // Additional strategic hint
        if (difficulty == Difficulty.HARD && difference > 100) {
            int midpoint = (difficulty.getMinRange() + difficulty.getMaxRange()) / 2;
            if (target < midpoint) {
                System.out.println("💭 Strategy: Focus on the lower half of the range.");
            } else {
                System.out.println("💭 Strategy: Focus on the upper half of the range.");
            }
        }
    }
    
    /**
     * Display remaining attempts
     */
    private void displayRemainingAttempts(int remaining) {
        String colour = remaining <= 2 ? ANSI_RED : remaining <= 4 ? ANSI_YELLOW : ANSI_GREEN;
        System.out.println(colour + "\n⏱ Attempts remaining: " + remaining + ANSI_RESET);
    }
    
    /**
     * Handle victory scenario
     */
    private void handleVictory(Difficulty difficulty, int attempts, long timeTaken) {
        int score = scoreManager.calculateScore(difficulty, attempts, timeTaken);
        
        System.out.println(ANSI_GREEN + "\n┌─────────────── GAME RESULTS ───────────────┐");
        System.out.printf("│  Attempts Used: %-27d │%n", attempts);
        System.out.printf("│  Time Taken: %-30s │%n", formatTime(timeTaken));
        System.out.printf("│  Score Earned: %-28d │%n", score);
        System.out.println("└────────────────────────────────────────────┘" + ANSI_RESET);
        
        // Update statistics
        playerStats.recordWin(difficulty, attempts, timeTaken, score);
        
        // Check for achievements
        checkAndDisplayAchievements(difficulty, attempts);
    }
    
    /**
     * Handle defeat scenario
     */
    private void handleDefeat() {
        System.out.println(ANSI_RED + "\n╔═══════════════════════════════════════════╗");
        System.out.println("║                                           ║");
        System.out.println("║         ❌ GAME OVER! ❌                  ║");
        System.out.println("║                                           ║");
        System.out.println("║   You've run out of attempts!             ║");
        System.out.println("║                                           ║");
        System.out.printf("║   The number was: %-23d ║%n", gameEngine.getTargetNumber());
        System.out.println("║                                           ║");
        System.out.println("╚═══════════════════════════════════════════╝" + ANSI_RESET);
        
        playerStats.recordLoss();
    }
    
    /**
     * Check and display any new achievements
     */
    private void checkAndDisplayAchievements(Difficulty difficulty, int attempts) {
        boolean newAchievement = false;
        
        // First win on difficulty
        if (playerStats.getWins(difficulty) == 1) {
            System.out.println(ANSI_PURPLE + "\n🏆 Achievement Unlocked: First " + 
                             difficulty.name() + " Victory!" + ANSI_RESET);
            newAchievement = true;
        }
        
        // Perfect game (minimum attempts)
        if (attempts <= 3) {
            System.out.println(ANSI_PURPLE + "\n🏆 Achievement Unlocked: Perfect Game!" + ANSI_RESET);
            newAchievement = true;
        }
        
        // Speed demon (under 30 seconds on medium/hard)
        if (difficulty != Difficulty.EASY && playerStats.getTotalGames() > 0) {
            System.out.println(ANSI_PURPLE + "\n🏆 Achievement Unlocked: Speed Demon!" + ANSI_RESET);
            newAchievement = true;
        }
        
        // Milestone wins
        int totalWins = playerStats.getTotalWins();
        if (totalWins == 10 || totalWins == 50 || totalWins == 100) {
            System.out.println(ANSI_PURPLE + "\n🏆 Achievement Unlocked: " + 
                             totalWins + " Total Wins!" + ANSI_RESET);
            newAchievement = true;
        }
        
        if (newAchievement) {
            pause(2000);
        }
    }
    
    /**
     * Display player statistics
     */
    private void displayStatistics() {
        clearScreen();
        System.out.println(ANSI_BOLD + ANSI_CYAN);
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    PLAYER STATISTICS                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println(ANSI_RESET);
        
        System.out.println(ANSI_BOLD + "\n📊 Overall Statistics:" + ANSI_RESET);
        System.out.println("─────────────────────────────────────────────────────────────");
        System.out.printf("Total Games Played: %d%n", playerStats.getTotalGames());
        System.out.printf("Total Wins: %d%n", playerStats.getTotalWins());
        System.out.printf("Total Losses: %d%n", playerStats.getTotalLosses());
        System.out.printf("Win Rate: %.1f%%%n", playerStats.getWinRate());
        System.out.printf("Total Score: %d%n", playerStats.getTotalScore());
        System.out.printf("Average Score: %.0f%n", playerStats.getAverageScore());
        
        System.out.println(ANSI_BOLD + "\n🎮 Performance by Difficulty:" + ANSI_RESET);
        System.out.println("─────────────────────────────────────────────────────────────");
        
        for (Difficulty diff : Difficulty.values()) {
            String colour = switch (diff) {
                case EASY -> ANSI_GREEN;
                case MEDIUM -> ANSI_YELLOW;
                case HARD -> ANSI_RED;
            };
            
            System.out.println(colour + "\n" + diff.name() + ":" + ANSI_RESET);
            System.out.printf("  Wins: %d | Best Attempts: %d | Avg Attempts: %.1f%n",
                            playerStats.getWins(diff),
                            playerStats.getBestAttempts(diff),
                            playerStats.getAverageAttempts(diff));
        }
        
        System.out.println("\n─────────────────────────────────────────────────────────────");
        System.out.print("\nPress Enter to return to menu...");
        scanner.nextLine();
    }
    
    /**
     * Display game instructions
     */
    private void displayInstructions() {
        clearScreen();
        System.out.println(ANSI_BOLD + ANSI_BLUE);
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    HOW TO PLAY                             ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println(ANSI_RESET);
        
        System.out.println("\n📖 Game Rules:");
        System.out.println("─────────────────────────────────────────────────────────────");
        System.out.println("1. The computer randomly selects a number within the range");
        System.out.println("2. You must guess the number within the allowed attempts");
        System.out.println("3. After each guess, you'll receive feedback:");
        System.out.println("   • 📈 Too Low  - Guess higher");
        System.out.println("   • 📉 Too High - Guess lower");
        System.out.println("   • 🎉 Correct  - You've won!");
        System.out.println("4. Temperature hints guide you:");
        System.out.println("   • 🔥 Burning Hot - Very close");
        System.out.println("   • ♨️ Very Warm  - Close");
        System.out.println("   • 🌡️ Warm      - Getting there");
        System.out.println("   • ❄️ Cool      - Keep searching");
        System.out.println("   • 🧊 Cold      - Far away");
        
        System.out.println("\n🎯 Difficulty Levels:");
        System.out.println("─────────────────────────────────────────────────────────────");
        System.out.println(ANSI_GREEN + "EASY:   " + ANSI_RESET + "Range 1-50,   12 attempts");
        System.out.println(ANSI_YELLOW + "MEDIUM: " + ANSI_RESET + "Range 1-100,  10 attempts");
        System.out.println(ANSI_RED + "HARD:   " + ANSI_RESET + "Range 1-500,  8 attempts");
        
        System.out.println("\n🏆 Scoring System:");
        System.out.println("─────────────────────────────────────────────────────────────");
        System.out.println("• Base points awarded for winning");
        System.out.println("• Bonus for fewer attempts used");
        System.out.println("• Time bonus for quick completion");
        System.out.println("• Difficulty multiplier applied");
        
        System.out.println("\n💡 Pro Tips:");
        System.out.println("─────────────────────────────────────────────────────────────");
        System.out.println("• Start with the middle of the range");
        System.out.println("• Use binary search strategy for efficiency");
        System.out.println("• Pay attention to temperature hints");
        System.out.println("• Perfect games (≤3 attempts) earn achievements");
        
        System.out.println("\n─────────────────────────────────────────────────────────────");
        System.out.print("\nPress Enter to return to menu...");
        scanner.nextLine();
    }
    
    /**
     * Exit game with farewell message
     */
    private void exitGame() {
        clearScreen();
        System.out.println(ANSI_CYAN + ANSI_BOLD);
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                            ║");
        System.out.println("║              Thanks for playing DigitDash!                 ║");
        System.out.println("║                                                            ║");
        System.out.println("║                  Final Statistics:                         ║");
        System.out.printf("║              Games Played: %-28d ║%n", playerStats.getTotalGames());
        System.out.printf("║              Total Wins: %-30d ║%n", playerStats.getTotalWins());
        System.out.printf("║              Win Rate: %.1f%%%-29s ║%n", playerStats.getWinRate(), "");
        System.out.println("║                                                            ║");
        System.out.println("║                  See you next time!                        ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println(ANSI_RESET);
        
        isRunning = false;
    }
    
    /**
     * Get menu choice with validation
     */
    private int getMenuChoice(int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.print(ANSI_RED + "Invalid choice! Please enter " + 
                                   min + "-" + max + ": " + ANSI_RESET);
                }
            } else {
                System.out.print(ANSI_RED + "Invalid input! Please enter a number: " + ANSI_RESET);
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
    
    /**
     * Clear console screen (cross-platform)
     */
    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // If clearing fails, just add some newlines
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
    
    /**
     * Pause execution for dramatic effect
     */
    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Format time in human-readable format
     */
    private String formatTime(long seconds) {
        if (seconds < 60) {
            return seconds + " seconds";
        } else {
            long minutes = seconds / 60;
            long secs = seconds % 60;
            return String.format("%d min %d sec", minutes, secs);
        }
    }
    
    /**
     * Main entry point
     */
    public static void main(String[] args) {
        DigitDashGame game = new DigitDashGame();
        game.start();
    }
}