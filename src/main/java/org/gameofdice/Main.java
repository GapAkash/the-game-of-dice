/**
 * 
 */
package org.gameofdice;

import static org.gameofdice.util.GameUtil.LINE_BREAK_2;
import static org.gameofdice.util.GameUtil.MAIN_LINE_BREAK;
import static org.gameofdice.util.GameUtil.SCANNER;
import static org.gameofdice.util.GameUtil.WELCOME_MESSAGE;
import static org.gameofdice.util.GameUtil.inputValidation;

import org.gameofdice.controller.GameController;
import org.gameofdice.model.Game;

/**
 * @author Akash.Gupta
 *
 */
public class Main {
	public static void main(String[] args) {

		System.out.println(MAIN_LINE_BREAK);
		System.out.println(WELCOME_MESSAGE + "\n");

		int numberOfPlayers = inputValidation("Please enter number of players: ", Integer.class, SCANNER);
		int pointsToWin = inputValidation("Please enter points to win the game: ", Integer.class, SCANNER);

		System.out.println("\n" + LINE_BREAK_2);
		System.out.println("Total number of players: " + numberOfPlayers);
		System.out.println("points to win: " + pointsToWin);
		System.out.println(LINE_BREAK_2);

		GameController gameController = new GameController();
		Game game = gameController.createGame(numberOfPlayers, pointsToWin);

		gameController.startGame(game);

		SCANNER.close();
		System.out.println(MAIN_LINE_BREAK);
	}
}
