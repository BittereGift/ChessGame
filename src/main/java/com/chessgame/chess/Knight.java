package com.chessgame.chess;

/**
 * @author Bittere_Gift
 */
public class Knight extends Chess {

    public Knight() {
        super();
        setName("Knight");
    }

    public Knight(Color color) {
        super(color);
        setName("Knight");
    }

    public Knight(Color color, Point position) {
        super(color, position);
        setName("Knight");
    }

}
