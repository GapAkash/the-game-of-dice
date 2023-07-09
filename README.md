# The Game Of Dice

The "Game of Dice" is a multiplayer game where N players roll a 6-faced dice in a round-robin fashion. Each time a player rolls the dice, their points increase by the number (1 to 6) achieved by the roll.

This program allows you to simulate and play "The Game Of Dice" on the command line. Players take turns rolling the dice, accumulate points, and aim to reach a specific score to complete the game.

## Requirements

- Java Development Kit (JDK) 8 or later installed on your machine.

## Getting Started

### Option 1: Download the JAR file

1. Go to the [Releases](https://github.com/GapAkash/the-game-of-dice/releases) page of this repository.

2. Download the JAR file (`TheGameOfDice-1.0.0.jar`) from the latest release.

3. Open a terminal or command prompt and navigate to the directory where you downloaded the JAR file.

4. Run the program by executing the following command:

   ```bash
   java -jar TheGameOfDice-1.0.0.jar
   ```

   The program will start running and prompt you for the number of players and the points required to win the game.

5. Follow the on-screen instructions to play "The Game Of Dice." When it's your turn, press 'r' and hit enter to roll the dice. The program will display the outcome of your roll, your current score, and the rank table after each roll.

6. Continue playing until a player reaches or exceeds the specified points required to win. The program will display a congratulations message to the winning player and terminate the game.

### Option 2: Build and run from source

1. Clone or download the project to your local machine.

2. Open a terminal or command prompt and navigate to the project's root directory.

3. Navigate to the `bin` directory:

   ```bash
   cd bin
   ```

4. Run the program by executing the following command:

   ```bash
   java org.gameofdice.Main
   ```

   The program will start running and prompt you for the number of players and the points required to win the game.

6. Follow the on-screen instructions to play "The Game Of Dice." When it's your turn, press 'r' and hit enter to roll the dice. The program will display the outcome of your roll, your current score, and the rank table after each roll.

7. Continue playing until a player reaches or exceeds the specified points required to win. The program will display a congratulations message to the winning player and terminate the game.

## Customization

You can customize the number of player and points to win at the start of the game.

## Rule

- The order in which the users roll the dice is decided randomly at the start of the game.
- If a player rolls the value "6" then they immediately get another chance to roll again and move ahead in the game.
- If a player rolls the value "1" two consecutive times then they are forced to skip their next turn as a penalty.

---
