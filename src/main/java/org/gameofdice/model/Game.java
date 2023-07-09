/**
 * 
 */
package org.gameofdice.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Akash.Gupta
 *
 */
public class Game {

    private List<Player> players;
    private int pointsToWin;

    public Game(List<Player> players, int pointsToWin) {
        this.players = players;
        this.pointsToWin = pointsToWin;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getPointsToWin() {
        return pointsToWin;
    }

    public void setPointsToWin(int pointsToWin) {
        this.pointsToWin = pointsToWin;
    }

    @Override
    public String toString() {
        return "Game{" +
                "players=" + players +
                ", pointsToWin=" + pointsToWin +
                '}';
    }

    public static class Builder {
        private List<Player> players;
        private int pointsToWin;

        public Builder setPlayers(int numberOfPlayers) {
            this.players = new ArrayList<>();
            for (int i = 0; i < numberOfPlayers; i++) {
                this.players.add(new Player("Player-" + (i + 1)));
            }
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setPointsToWin(int pointsToWin) {
            this.pointsToWin = pointsToWin;
            return this;
        }

        public Game build() {
            return new Game(players, pointsToWin);
        }
    }
}