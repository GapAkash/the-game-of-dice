/**
 * 
 */
package org.gameofdice.model;

/**
 * @author Akash.Gupta
 *
 */
public class Player {

    private String name;
    private long score;
    private int rank;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.rank = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Player{" + "name='" + name + '\'' + ", score=" + score + ", rank=" + rank + '}';
    }
    
    
}
