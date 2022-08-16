package com.chessgame.player;

/**
 * @author Bittere_Gift
 */
public abstract class Computer extends AbstractPlayer {

    public Computer() {
        this.setId(0);
        this.setPassword(null);
        this.setPlayedCount(0);
        this.setWinCount(0);
    }
}
