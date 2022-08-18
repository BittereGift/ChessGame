package com.chessgame.player;

import com.chessgame.chess.Chess;

/**
 * @author Bittere_Gift
 */
public abstract class AbstractPlayer {

    protected static int totalPlayerCount = 1;
    private int id;
    private String name;
    private String password;
    private int playedCount;
    private int winCount;

    public void addWinCount(int addWinCount) {
        winCount += addWinCount;
    }

    public void addPlayedCount(int addPlayedCount) {
        playedCount += addPlayedCount;
    }

    @Override
    public String toString() {
        return "AbstractPlayer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", playedCount=" + playedCount +
                ", winCount=" + winCount +
                '}';
    }

    public int getPlayedCount() {
        return playedCount;
    }

    public void setPlayedCount(int playedCount) {
        this.playedCount = playedCount;
    }

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getWinRate() {
        return playedCount == 0 ? 0 : (double) (winCount / totalPlayerCount);
    }
}
