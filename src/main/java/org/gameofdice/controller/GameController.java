/**
 * 
 */
package org.gameofdice.controller;

import org.gameofdice.model.Game;
import org.gameofdice.service.GameManagerService;
import org.gameofdice.service.GameManagerServiceImpl;

/**
 * @author Akash.Gupta
 *
 */
public class GameController {
    GameManagerService gameManager = new GameManagerServiceImpl();

    public Game createGame(int numberOfPlayers, int pointsToWin){
        return Game.getBuilder().setPlayers(numberOfPlayers).setPointsToWin(pointsToWin).build();
    }

    public void startGame(Game game) {
        gameManager.startGame(game);
    }
}
