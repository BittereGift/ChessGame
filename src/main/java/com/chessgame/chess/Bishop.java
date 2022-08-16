package com.chessgame.chess;

/**
 * @author Bittere_Gift
 */
public class Bishop extends Chess {

    public Bishop() {
        super();
        setName("Bishop");
    }

    public Bishop(Color color) {
        super(color);
        setName("Bishop");
    }

    public Bishop(Color color, Point position) {
        super(color, position);
        setName("Bishop");
    }

}
