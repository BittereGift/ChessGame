package com.chessgame.chess;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * return all possible strategies for this chess
     *
     * @return a List of Point of all possible strategies
     */
    @Override
    protected List<Point> getPossibleMoves() {
        List<Point> moves = new ArrayList<>();

    }

}
