package com.chessgame.chess;

import java.util.ArrayList;
import java.util.List;

import static com.chessgame.chess.Chess.Direction.*;

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
    public List<Point> getPossibleMoves() {
        List<Point> moves = new ArrayList<>();
        Direction[] directions = new Direction[] {UP, DOWN, LEFT, RIGHT};
        for (Direction direction : directions) {
            List<Point> possibleMovesOnOneDirection = getPossibleMovesOnOneDirection(direction);
            moves.addAll(possibleMovesOnOneDirection);
        }
        return moves;
    }

}
