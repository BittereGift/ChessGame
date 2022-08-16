package com.chessgame.chess;

/**
 * @author Bittere_Gift
 */
public class Queen extends Chess {

    public Queen() {
        super();
        setName("Queen");
    }

    public Queen(Color color) {
        super(color);
        setName("Queen");
    }

    public Queen(Color color, Point position) {
        super(color, position);
        setName("Queen");
    }

}
