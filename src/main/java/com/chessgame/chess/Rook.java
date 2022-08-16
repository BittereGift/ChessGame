package com.chessgame.chess;

/**
 * @author Bittere_Gift
 */
public class Rook extends Chess {

    public Rook() {
        super();
        setName("Rook");
    }

    public Rook(Color color) {
        super(color);
        setName("Rook");
    }

    public Rook(Color color, Point position) {
        super(color, position);
        setName("Rook");
    }

}
