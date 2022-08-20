package com.chessgame.chess;

import java.util.ArrayList;
import java.util.List;

import static com.chessgame.chess.Color.NULL;

/**
 * @author Bittere_Gift
 */
public class NullChess extends Chess {

    public NullChess(Point point) {
        super(NULL, point);
        setName("Null");
    }

    /**
     * return all possible strategies for this chess
     *
     * @return a List of Point of all possible strategies
     */
    @Override
    public List<Point> getPossibleMoves() {
        return new ArrayList<>();
    }

}
