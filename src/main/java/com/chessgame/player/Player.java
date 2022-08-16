package com.chessgame.player;

/**
 * @author Bittere_Gift
 */
public class Player extends AbstractPlayer {

    public Player(String name, String password) {
        this.setId(totalPlayerCount);
        totalPlayerCount++;
        this.setName(name);
        this.setPassword(password);
        this.setPlayedCount(0);
        this.setWinCount(0);
    }
}
