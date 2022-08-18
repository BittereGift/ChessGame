package com.chessgame.chess;

import java.util.List;

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
