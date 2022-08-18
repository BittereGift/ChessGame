package com.chessgame.chess;

import java.util.List;

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
