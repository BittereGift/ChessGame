package com.chessgame.chess;

/**
 * @author Bittere_Gift
 */
public class King extends Chess {

    public King() {
        super();
        setName("King");
    }

    public King(Color color) {
        super(color);
        setName("King");
    }

    public King(Color color, Point position) {
        super(color, position);
        setName("King");
    }

}
