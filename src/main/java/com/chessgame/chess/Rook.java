package com.chessgame.chess;

import java.util.List;

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
