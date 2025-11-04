# DigitDash 🎮

**The Ultimate Number Guessing Challenge**

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/technologies/javase-downloads.html)
[![Console](https://img.shields.io/badge/Platform-Console-blue.svg)]()
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![UK](https://img.shields.io/badge/Style-UK-red.svg)]()

*By Michael Semera*

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Screenshots](#screenshots)
- [Installation](#installation)
- [How to Play](#how-to-play)
- [Game Mechanics](#game-mechanics)
- [Difficulty Levels](#difficulty-levels)
- [Scoring System](#scoring-system)
- [Statistics & Achievements](#statistics--achievements)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Code Highlights](#code-highlights)
- [Educational Value](#educational-value)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

---

## 🎯 Overview

**DigitDash** is a feature-rich, console-based number guessing game that combines classic gameplay with modern enhancements. Built entirely in Java, it showcases clean code architecture, advanced console UI techniques, and comprehensive game mechanics including difficulty levels, scoring systems, statistics tracking, and achievements.

### Why DigitDash?

Unlike basic number guessing games, DigitDash offers:
- 🎨 **Colourful Console UI**: ANSI colour codes for vibrant displays
- 📊 **Advanced Statistics**: Comprehensive tracking of performance
- 🏆 **Achievement System**: Unlockable milestones
- 🧠 **Intelligent Hints**: Temperature-based proximity feedback
- ⚡ **Strategic Gameplay**: Rewards efficiency and speed
- 🎮 **Multiple Difficulties**: Progressive challenge levels

---

## ✨ Features

### Core Gameplay

**Three Difficulty Levels**
- **Easy**: 1-50 range, 12 attempts
- **Medium**: 1-100 range, 10 attempts
- **Hard**: 1-500 range, 8 attempts

**Intelligent Feedback System**
- Higher/Lower indicators
- Temperature hints (Hot/Warm/Cool/Cold)
- Proximity-based guidance
- Strategic suggestions for hard mode

**Enhanced User Experience**
- ANSI colour-coded output (UK spelling)
- Clear screen functionality
- Formatted displays with box drawing
- Input validation with error handling
- Pause effects for dramatic moments

### Advanced Features

**Comprehensive Scoring**
- Base score with difficulty multipliers
- Attempt efficiency bonus
- Time-based rewards
- Perfect game recognition
- Cumulative score tracking

**Statistics Tracking**
- Total games/wins/losses
- Win rate percentage
- Performance by difficulty
- Best attempts per level
- Average performance metrics

**Achievement System**
- First win milestones
- Perfect game (≤3 attempts)
- Speed demon (quick completion)
- Win count milestones (10, 50, 100)
- Real-time unlock notifications

**Quality of Life**
- Game instructions menu
- Detailed statistics view
- Session persistence
- Cross-platform compatibility
- Graceful error handling

---

## 📸 Screenshots

### Welcome Screen
```
╔════════════════════════════════════════════════════════════╗
║                                                            ║
║                     DIGITDASH                              ║
║                                                            ║
║              The Number Guessing Challenge                 ║
║                                                            ║
║                   By Michael Semera                        ║
║                                                            ║
╚════════════════════════════════════════════════════════════╝
```

### Main Menu
```
┌─────────────── MAIN MENU ───────────────┐
│  1. Play Easy     (1-50, 12 attempts)  │
│  2. Play Medium   (1-100, 10 attempts) │
│  3. Play Hard     (1-500, 8 attempts)  │
│  4. View Statistics                    │
│  5. Instructions                       │
│  6. Exit Game                          │
└─────────────────────────────────────────┘
```

### Gameplay
```
╔════════════════════════════════════════════════════════════╗
║  Difficulty: MEDIUM                                        ║
║  Range: 1 - 100                                           ║
║  Maximum Attempts: 10                                      ║
╚════════════════════════════════════════════════════════════╝

═══════════════════════════════════════════════════
Attempt 3 of 10
═══════════════════════════════════════════════════

Enter your guess (1-100): 75

┌─────────────────────────────────┐
│  📉 Too High! Go Lower!         │
└─────────────────────────────────┘

💡 Hint: 
♨️ Very warm! Getting closer!

⏱ Attempts remaining: 7
```

### Victory Screen
```
╔═════════════════════════════════════════════╗
║                                             ║
║         🎉 CONGRATULATIONS! 🎉              ║
║                                             ║
║         You guessed correctly!              ║
║                                             ║
╚═════════════════════════════════════════════╝

┌─────────────── GAME RESULTS ───────────────┐
│  Attempts Used: 5                          │
│  Time Taken: 23 seconds                    │
│  Score Earned: 1825                        │
└────────────────────────────────────────────┘

🏆 Achievement Unlocked: Speed Demon!
```

---

## 🚀 Installation

### Prerequisites

**Required:**
- Java Development Kit (JDK) 17 or higher
- Terminal/Console with ANSI colour support

**Verify Installation:**
```bash
java -version  # Should show Java 17+
javac -version # Should show Java 17+
```

### Quick Start

**1. Clone Repository**
```bash
git clone https://github.com/yourusername/digitdash.git
cd digitdash
```

**2. Compile**
```bash
# From project root
javac -d bin src/com/michaelsemera/digitdash/*.java
```

**3. Run**
```bash
java -cp bin com.michaelsemera.digitdash.DigitDashGame
```

### Alternative: Create JAR

**Compile and Package**
```bash
# Compile
javac -d bin src/com/michaelsemera/digitdash/*.java

# Create manifest
echo "Main-Class: com.michaelsemera.digitdash.DigitDashGame" > manifest.txt

# Package JAR
jar cfm DigitDash.jar manifest.txt -C bin .

# Run
java -jar DigitDash.jar
```

### IDE Setup

**IntelliJ IDEA:**
1. File → Open → Select project directory
2. Right-click `DigitDashGame.java`
3. Run 'DigitDashGame.main()'

**Eclipse:**
1. File → Import → Existing Projects
2. Select project directory
3. Right-click `DigitDashGame.java` → Run As → Java Application

**VS Code:**
1. Open project folder
2. Install Java Extension Pack
3. Press F5 or use Run button

---

## 🎮 How to Play

### Basic Gameplay

1. **Launch Game**
   - Run the application
   - Main menu appears

2. **Select Difficulty**
   - Choose Easy (1), Medium (2), or Hard (3)
   - Game generates random number within range

3. **Make Guesses**
   - Enter your guess
   - Receive feedback (higher/lower)
   - Use hints to narrow down

4. **Win or Lose**
   - Guess correctly = Victory!
   - Run out of attempts = Game Over

### Controls

- **Number Input**: Type number and press Enter
- **Menu Navigation**: Enter option number (1-6)
- **Any Key Continue**: Press Enter to proceed

### Tips & Strategies

**Binary Search Method (Optimal)**
```
Range 1-100:
1st guess: 50 (middle)
If too low: guess 75 (middle of 51-100)
If too high: guess 25 (middle of 1-49)
Continue halving the range
```

**Temperature Hint Guide**
- 🔥 **Burning Hot**: Within 5% of range
- ♨️ **Very Warm**: Within 10% of range
- 🌡️ **Warm**: Within 20% of range
- ❄️ **Cool**: Within 40% of range
- 🧊 **Cold**: Beyond 40% of range

**Efficiency Tips**
- Start with middle of range
- Use binary search for best results
- Track your previous guesses mentally
- Perfect games (≤3 attempts) earn bonus achievements
- Speed matters - quick wins earn time bonuses

---

## 🎲 Game Mechanics

### Random Number Generation

**Cryptographically Secure**
```java
SecureRandom random = new SecureRandom();
int target = random.nextInt(max - min + 1) + min;
```

Benefits:
- Unpredictable outcomes
- No pattern exploitation
- True randomness
- Security best practices

### Guess Validation

**Multi-Layer Validation**
1. Input type checking (integer)
2. Range validation (within min-max)
3. Duplicate guess detection (optional)
4. Real-time error feedback

**Edge Cases Handled**
- Non-numeric input
- Numbers outside range
- Negative numbers
- Extremely large numbers
- Empty input
- Special characters

### Hint System

**Proximity Calculation**
```java
int difference = Math.abs(target - guess);
int range = maxRange - minRange;
double percentOff = (difference / range) * 100;
```

**Temperature Thresholds**
- ≤5%: Burning Hot 🔥
- ≤10%: Very Warm ♨️
- ≤20%: Warm 🌡️
- ≤40%: Cool ❄️
- >40%: Cold 🧊

**Strategic Hints (Hard Mode)**
- Suggests upper/lower half focus
- Activates when 100+ units away
- Helps navigate large ranges

---

## 📊 Difficulty Levels

### Easy Mode
```
Range: 1 - 50
Attempts: 12
Multiplier: 1.0x
Ideal For: Beginners, practice
```

**Characteristics:**
- Small range (50 numbers)
- Generous attempt limit
- Quick games
- Base scoring

### Medium Mode
```
Range: 1 - 100
Attempts: 10
Multiplier: 1.5x
Ideal For: Intermediate players
```

**Characteristics:**
- Standard range (100 numbers)
- Moderate attempts
- Balanced challenge
- 50% score bonus

### Hard Mode
```
Range: 1 - 500
Attempts: 8
Multiplier: 2.0x
Ideal For: Expert players, high scores
```

**Characteristics:**
- Large range (500 numbers)
- Limited attempts
- Strategic hints included
- Double score multiplier
- Requires efficient strategy

---

## 🏆 Scoring System

### Base Score Calculation

```
Base Score: 1000 points
× Difficulty Multiplier (1.0 - 2.0x)
+ Attempt Bonus (50 pts × remaining attempts)
+ Time Bonus (200 pts if under 60 seconds)
+ Perfect Game Bonus (500 pts if ≤3 attempts)
+ Efficiency Bonus (25% if under 50% attempts)
```

### Example Calculations

**Easy Mode - 5 Attempts, 45 seconds**
```
Base: 1000
Difficulty: 1000 × 1.0 = 1000
Attempts: 50 × (12-5) = 350
Time: 200
Total: 1550 points
```

**Hard Mode - 3 Attempts, 28 seconds**
```
Base: 1000
Difficulty: 1000 × 2.0 = 2000
Attempts: 50 × (8-3) = 250
Time: 200
Perfect: 500
Efficiency: (2000+250+200+500) × 1.25 = 3687
Total: 3687 points
```

### Grade System

| Score | Grade | Performance |
|-------|-------|-------------|
| 3000+ | S | Perfect |
| 2500+ | A+ | Excellent |
| 2000+ | A | Great |
| 1500+ | B | Good |
| 1000+ | C | Average |
| 500+ | D | Below Average |
| <500 | F | Poor |

### Rank System

| Total Score | Rank |
|-------------|------|
| 50,000+ | Grand Master |
| 30,000+ | Master |
| 20,000+ | Expert |
| 10,000+ | Advanced |
| 5,000+ | Intermediate |
| 2,000+ | Novice |
| <2,000 | Beginner |

---

## 📈 Statistics & Achievements

### Tracked Statistics

**Overall Stats:**
- Total games played
- Total wins/losses
- Win rate percentage
- Total cumulative score
- Average score per game

**Per-Difficulty Stats:**
- Wins per difficulty
- Best (minimum) attempts
- Average attempts
- Average completion time

### Achievement List

**First Victory Series**
- 🏆 First Easy Victory
- 🏆 First Medium Victory
- 🏆 First Hard Victory

**Performance Achievements**
- 🏆 Perfect Game (≤3 attempts)
- 🏆 Speed Demon (under 30 seconds)
- 🏆 Efficiency Expert (under 50% attempts)

**Milestone Achievements**
- 🏆 10 Total Wins
- 🏆 50 Total Wins
- 🏆 100 Total Wins
- 🏆 Win Streak (3+ consecutive)

**Difficulty Mastery**
- 🏆 Easy Master (10 Easy wins)
- 🏆 Medium Master (10 Medium wins)
- 🏆 Hard Master (10 Hard wins)

---

## 🏗️ Architecture

### Design Patterns

**1. Single Responsibility Principle**
Each class has one clear purpose:
- `DigitDashGame`: UI and game flow
- `GameEngine`: Core game logic
- `ScoreManager`: Scoring calculations
- `PlayerStats`: Statistics tracking
- `Difficulty`: Configuration enum

**2. Separation of Concerns**
```
Presentation Layer (Console UI)
    ↓
Game Logic Layer (Engine)
    ↓
Data Management Layer (Stats, Score)
```

**3. Enum Pattern**
- `Difficulty`: Game configurations
- `GuessResult`: Guess outcomes
- Type-safe, immutable configurations

### Class Diagram

```
┌─────────────────────┐
│  DigitDashGame      │
│  (Main Controller)  │
├─────────────────────┤
│ - scanner           │
│ - gameEngine        │
│ - scoreManager      │
│ - playerStats       │
├─────────────────────┤
│ + start()           │
│ + playGame()        │
│ + displayMenu()     │
└──────┬──────────────┘
       │
       ├───uses───┐
       │          │
       ↓          ↓
┌──────────┐  ┌──────────────┐
│GameEngine│  │ScoreManager  │
├──────────┤  ├──────────────┤
│+makeGuess│  │+calculateScore│
└──────────┘  └──────────────┘
       │
       ↓
┌────────────────┐
│  PlayerStats   │
├────────────────┤
│ +recordWin()   │
│ +getWinRate()  │
└────────────────┘
```

### Key Components

**DigitDashGame (Main Class)**
- Orchestrates game flow
- Handles user interface
- Manages game state
- Coordinates other components

**GameEngine**
- Random number generation (SecureRandom)
- Guess validation
- Game state management
- Guess history tracking

**ScoreManager**
- Score calculation algorithm
- Bonus application logic
- Grade determination
- Rank calculation

**PlayerStats**
- Statistics aggregation
- Per-difficulty tracking
- Win rate calculation
- Performance metrics

**Difficulty (Enum)**
- Range configuration
- Attempt limits
- Score multipliers
- Immutable settings

---

## 📁 Project Structure

```
digitdash/
│
├── src/
│   └── com/
│       └── michaelsemera/
│           └── digitdash/
│               ├── DigitDashGame.java       # Main game class
│               ├── GameEngine.java          # Core logic
│               ├── Difficulty.java          # Difficulty enum
│               ├── GuessResult.java         # Result enum
│               ├── ScoreManager.java        # Scoring system
│               └── PlayerStats.java         # Statistics tracking
│
├── bin/                                     # Compiled classes
│
├── docs/                                    # Documentation
│   ├── GAMEPLAY.md
│   └── ARCHITECTURE.md
│
├── README.md                                # This file
├── LICENSE                                  # MIT License
└── .gitignore                               # Git ignore rules
```

---

## 💻 Code Highlights

### Clean Code Practices

**1. Meaningful Names**
```java
// Clear, descriptive variable names
private void displayGuessResult(GuessResult result, int guess)
private int getValidGuess(int min, int max)
private void handleVictory(Difficulty difficulty, int attempts, long timeTaken)
```

**2. Single Responsibility Methods**
```java
// Each method does one thing well
private void displayWelcomeBanner()
private void displayMainMenu()
private void displayStatistics()
```

**3. UK English Spelling**
```java
// Consistent UK spelling throughout
private static final String ANSI_COLOUR_RED = "\u001B[31m";
displayColour();
centraliseText();
```

**4. Comprehensive Documentation**
```java
/**
 * Process a player's guess and return result
 * 
 * @param guess The player's guess
 * @return GuessResult indicating outcome
 */
public GuessResult makeGuess(int guess)
```

### Advanced Techniques

**1. ANSI Colour Codes**
```java
private static final String ANSI_GREEN = "\u001B[32m";
System.out.println(ANSI_GREEN + "Success!" + ANSI_RESET);
```

**2. Cross-Platform Screen Clearing**
```java
private void clearScreen() {
    if (System.getProperty("os.name").contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } else {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
```

**3. Secure Random Generation**
```java
private final SecureRandom random = new SecureRandom();
int number = random.nextInt(range) + min;
```

**4. Input Validation Loop**
```java
while (true) {
    if (scanner.hasNextInt()) {
        int input = scanner.nextInt();
        if (input >= min && input <= max) return input;
    }
    scanner.nextLine(); // Clear buffer
}
```

---

## 📚 Educational Value

### Learning Concepts Demonstrated

**1. Control Structures**
- While loops for game flow
- For loops for iterations
- If-else conditionals for logic
- Switch statements for menus

**2. Object-Oriented Programming**
- Classes and objects
- Encapsulation (private fields, public methods)
- Enums for type safety
- Composition (has-a relationships)

**3. Data Structures**
- Arrays for ranges
- Lists for guess history
- Maps for difficulty stats
- Collections management

**4. Error Handling**
- Input validation
- Exception catching
- Graceful error messages
- Buffer clearing

**5. Algorithm Design**
- Random number generation
- Binary search hints
- Score calculation
- Statistics aggregation

**6. User Experience**
- Clear feedback
- Colour coding
- Progressive disclosure
- Consistent layout

---

## 🚀 Future Enhancements

### Planned Features

**Phase 1: Core Improvements**
- [ ] Save/Load game statistics to file
- [ ] Leaderboard system
- [ ] Difficulty customization
- [ ] Timed challenge mode
- [ ] Multiplayer mode (turn-based)

**Phase 2: Enhanced Gameplay**
- [ ] Power-ups (extra attempts, range reduction)
- [ ] Daily challenges
- [ ] Streak tracking
- [ ] Combo bonuses
- [ ] Special number patterns

**Phase 3: Technical Upgrades**
- [ ] GUI version (JavaFX)
- [ ] Network multiplayer
- [ ] Database integration
- [ ] Web version
- [ ] Mobile app (Android)

**Phase 4: Advanced Features**
- [ ] AI opponent
- [ ] Tournament mode
- [ ] Custom themes
- [ ] Sound effects
- [ ] Replay system

---

## 🤝 Contributing

Contributions are welcome! Here's how:

### Reporting Issues
1. Check existing issues first
2. Provide detailed description
3. Include steps to reproduce
4. Specify environment (OS, Java version)

### Feature Requests
1. Describe the feature
2. Explain use case
3. Suggest implementation

### Pull Requests
1. Fork repository
2. Create feature branch: `git checkout -b feature/name`
3. Make changes
4. Test thoroughly
5. Commit: `git commit -m "Add: Feature description"`
6. Push: `git push origin feature/name`
7. Create Pull Request

### Code Style
- Follow Java naming conventions
- Add Javadoc comments
- Use UK English spelling
- Keep methods under 50 lines
- Write self-documenting code

---

## 📄 License

MIT License

Copyright (c) 2024 Michael Semera

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

---

## 📧 Contact

**Michael Semera**

For questions, suggestions, or collaboration opportunities, please reach out!
- 💼 LinkedIn: [Michael Semera](https://www.linkedin.com/in/michael-semera-586737295/)
- 🐙 GitHub: [@MichaelKS123](https://github.com/MichaelKS123)
- 📧 Email: michaelsemera15@gmail.com

---

## 🙏 Acknowledgments

- Java Community for excellent documentation
- ANSI colour code standards
- Game design best practices
- Clean code principles (Robert C. Martin)

---

## 📊 Project Statistics

- **Lines of Code**: ~1,000
- **Classes**: 6
- **Methods**: 40+
- **Difficulty Levels**: 3
- **Achievements**: 12+
- **Development Time**: Portfolio project

---

**Last Updated**: November 2024  
**Version**: 1.0.0  
**Status**: Complete & Production Ready

---

*Built with ❤️ and clean code principles*