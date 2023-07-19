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
import org.gameofdice.model.GameStatus;
import org.gameofdice.model.Player;

public class GameManagerServiceImpl implements GameManagerService {

	private List<Player> players;
	private int pointsToWin;
	private Map<Player, Integer> consecutiveOneRolls = new HashMap<>();
	private ArrayList<Player> completedPlayers = new ArrayList<>();
	private Map<Player, Integer> currentDiceValue = new HashMap<Player, Integer>();
	private Map<Player, Integer> lastDicevalue = new HashMap<Player, Integer>();

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
			lastDicevalue.putAll(currentDiceValue);
			currentDiceValue.clear();
			
		}
		System.out.println("\n" + END_MESSAGE);
	}

	private void playGame(Player player) {
		if (player.getPlayStatus() == GameStatus.WON) return;	// If player already won, then returning from here

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
		currentDiceValue.put(player, diceValue);
		System.out.println(player.getName() + " rolled " + diceValue + "; Total score: " + player.getScore());

		if(lastDicevalue.containsKey(player) && lastDicevalue.get(player) + diceValue == 8) {
			System.out.println(LINE_BREAK_2);
			System.out.println("Your sum is 8, we are adding " + diceValue + " to your total score!");
			System.out.println(LINE_BREAK_2);
			player.setScore(player.getScore() + diceValue);
			System.out.println("Total score: " + player.getScore());
		}
		

		checkAndPrintIfWon(player);

		printRankTable(players);

		if (player.getPlayStatus() == GameStatus.PLAYING) {
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

	private void checkAndPrintIfWon(Player player) {
		if (player.getScore() >= pointsToWin) {
			
			player.setPlayStatus(GameStatus.WON);
			int rank = completedPlayers.size() == 0 ? 1 : completedPlayers.get(completedPlayers.size() - 1).getRank() + 1;
			player.setRank(rank);
			
			completedPlayers.add(player);
			System.out.println("\n" + LINE_BREAK_1);
			System.out.println("Congratulations " + player.getName() + "! You completed the game with Rank " + completedPlayers.size());
			System.out.println(LINE_BREAK_1);
		}
	}

	private int rollDice() {
		return new Random().nextInt(6) + 1;
	}

	private void printRankTable(List<Player> players) {
		System.out.println("\n" + LINE_BREAK_1);
		System.out.println("Rank Table:");

		Set<Player> rankedPlayers = new LinkedHashSet<>();

		for (Player player : completedPlayers) {
			rankedPlayers.add(player);
		}

		List<Player> sortPlayers = new ArrayList<Player>(players);
		Collections.sort(sortPlayers, (p1, p2) -> Long.compare(p2.getScore(), p1.getScore()));
		int rank = completedPlayers.size() == 0 ? 1 : completedPlayers.get(completedPlayers.size() - 1).getRank() + 1;
		for (Player player : sortPlayers) {
			if(player.getPlayStatus() == GameStatus.PLAYING) player.setRank(rank++);
			rankedPlayers.add(player);
		}

		for (Player player : rankedPlayers)
			System.out.println(player);

		System.out.println(LINE_BREAK_1);
	}

	private boolean isGameFinished() {
		return completedPlayers.size() == players.size();
	}
}
