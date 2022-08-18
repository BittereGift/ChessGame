package com.chessgame.chess;

import java.util.List;

/**
 * @author Bittere_Gift
 */
public class NullChess extends Chess {

    public NullChess() {
        super();
        setName("Null");
    }

    public NullChess(Color color) {
        super(color);
        setName("Null");
    }

    public NullChess(Color color, Point position) {
        super(color, position);
        setName("Null");
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
