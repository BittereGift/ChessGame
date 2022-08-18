package com.chessgame.chess;

import java.util.List;

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

    /**
     * return all possible strategies for this chess
     *
     * @return a List of Point of all possible strategies
     */
    @Override
    protected List<Point> getPossibleMoves() {
        return null;
    }

}
