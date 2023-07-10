/**
 * 
 */
package org.gameofdice.service;

import static org.gameofdice.util.GameUtil.END_MESSAGE;
import static org.gameofdice.util.GameUtil.LINE_BREAK_1;
import static org.gameofdice.util.GameUtil.LINE_BREAK_2;
import static org.gameofdice.util.GameUtil.SCANNER;
import static org.gameofdice.util.GameUtil.inputValidation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.gameofdice.model.Game;
import org.gameofdice.model.Player;

public class GameManagerServiceImpl implements GameManagerService {

	private List<Player> players;
	private int pointsToWin;
	private Map<Player, Integer> consecutiveOneRolls = new HashMap<>();
	private ArrayList<Player> completedPlayers = new ArrayList<>();

	@Override
	public void startGame(Game game) {
		players = game.getPlayers();
		pointsToWin = game.getPointsToWin();
		for (Player player : players) consecutiveOneRolls.put(player, 0);

		// Used for shuffling the list so that order of users decided randomly
		Collections.shuffle(players);

		printRankTable(players);

		int round = 1;
		while (!isGameFinished()) {
			System.out.println("\n***** Round " + round++ + " *****");
			for (Player player : players) {
				playGame(player);
			}
		}
		System.out.println("\n" + END_MESSAGE);
	}

	private void playGame(Player player) {
		if (player.getScore() >= pointsToWin) return;	// If player already won, then returning from here

		// condition to check for consecutive Ones 2 times
		if (consecutiveOneRolls.get(player) == 2) {
			System.out.println("\n" + LINE_BREAK_2);
			System.out.println(player.getName() + " skipped because of two consecutive 1's");
			System.out.println(LINE_BREAK_2);
			consecutiveOneRolls.put(player, 0);
			return;
		}

		inputValidation("\n" + player.getName() + " it's your turn (press 'r' to roll the dice) : ", String.class, SCANNER);

		int diceValue = rollDice();
		player.setScore(player.getScore() + diceValue);
		System.out.println(player.getName() + " rolled " + diceValue + "; Total score: " + player.getScore());

		boolean isWon = checkIfWon(player);

		printRankTable(players);

		if (!isWon) {
			if (diceValue == 1) {
				consecutiveOneRolls.put(player, consecutiveOneRolls.get(player) + 1);
			} else if (diceValue == 6) {
				System.out.println("\n" + LINE_BREAK_2);
				System.out.println("You got another chance!");
				System.out.println(LINE_BREAK_2);
				playGame(player);
			}

			if (diceValue != 1)
				consecutiveOneRolls.put(player, 0);
		}
	}

	private boolean checkIfWon(Player player) {
		if (player.getScore() >= pointsToWin) {
			completedPlayers.add(player);
			System.out.println("\n" + LINE_BREAK_1);
			System.out.println("Congratulations, " + player.getName() + "! You completed the game with Rank " + completedPlayers.size());
			System.out.println(LINE_BREAK_1);
			return true;
		}
		return false;
	}

	private int rollDice() {
		return new Random().nextInt(6) + 1;
	}

	private void printRankTable(List<Player> players) {
		System.out.println("\n" + LINE_BREAK_1);
		System.out.println("Rank Table:");

		int rank = 1;
		Set<Player> rankedPlayers = new LinkedHashSet<>();

		for (Player player : completedPlayers)
			rankedPlayers.add(player);

		List<Player> sortPlayers = new ArrayList<Player>(players);
		Collections.sort(sortPlayers, (p1, p2) -> Long.compare(p2.getScore(), p1.getScore()));
		for (Player player : sortPlayers)
			rankedPlayers.add(player);

		for (Player player : rankedPlayers)
			if (player.getScore() >= pointsToWin)
				System.out.println(player.getName() + " : " + player.getScore() + "\tRank: " + rank++ + " \tWON");
			else
				System.out.println(player.getName() + " : " + player.getScore() + "\tRank: " + rank++ + " \tPLAYING");

		System.out.println(LINE_BREAK_1);
	}

	private boolean isGameFinished() {
		return completedPlayers.size() == players.size();
	}
}
