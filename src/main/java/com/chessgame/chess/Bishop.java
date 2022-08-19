package com.chessgame.chess;

import java.util.ArrayList;
import java.util.List;

import static com.chessgame.chess.Chess.Direction.*;

/**
 * @author Bittere_Gift
 */
public class Bishop extends Chess {

    public Bishop() {
        super();
        setName("Bisho");
    }

    public Bishop(Color color) {
        super(color);
        setName("Bisho");
    }

    public Bishop(Color color, Point position) {
        super(color, position);
        setName("Bisho");
    }

    /**
     * return all possible strategies for this chess
     *
     * @return a List of Point of all possible strategies
     */
    @Override
    public List<Point> getPossibleMoves() {
        List<Point> moves = new ArrayList<>();
        Direction[] directions = new Direction[] {UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT};
        for (Direction direction : directions) {
            List<Point> possibleMovesOnOneDirection = getPossibleMovesOnOneDirection(direction);
            moves.addAll(possibleMovesOnOneDirection);
        }
        return moves;
    }

}
