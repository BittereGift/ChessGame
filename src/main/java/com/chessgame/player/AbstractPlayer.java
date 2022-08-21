package com.chessgame.player;

import com.chessgame.chess.Point;

import java.io.Serializable;

/**
 * @author Bittere_Gift
 */
public abstract class AbstractPlayer implements Serializable {

    protected static int totalPlayerCount = 1;
    private int id;
    private String name;
    private String password;
    private int playedCount;
    private int winCount;
    private ChessStrategy strategy = new ChessStrategy();

    public void addWinCount(int addWinCount) {
        winCount += addWinCount;
    }

    public void addPlayedCount(int addPlayedCount) {
        playedCount += addPlayedCount;
    }

    public void setMove(Point start, Point end) {
        strategy.setStartPoint(start);
        strategy.setEndPoint(end);
    }

    public void setMove() {
        throw new UnsupportedOperationException("Not supported.");
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

    public ChessStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(ChessStrategy strategy) {
        this.strategy = strategy;
    }
}
