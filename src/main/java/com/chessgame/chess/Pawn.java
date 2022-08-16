package com.chessgame.chess;

/**
 * @author Bittere_Gift
 */
public class Pawn extends Chess {

    public Pawn() {
        super();
        setName("Pawn");
    }

    public Pawn(Color color) {
        super(color);
        setName("Pawn");
    }

    public Pawn(Color color, Point position) {
        super(color, position);
        setName("Pawn");
    }

}
