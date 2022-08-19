package com.chessgame.chess;

import java.util.ArrayList;
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
    public List<Point> getPossibleMoves() {
        List<Point> moves = new ArrayList<>();
        List<Point> forwardMove = getForwardMoves();
        List<Point> forwardEatMove = getForwardEatMoves();

        moves.addAll(forwardMove);
        moves.addAll(forwardEatMove);
        return moves;
    }

    private List<Point> getForwardMoves() {
        List<Point> moves = new ArrayList<>();
        int row = this.getRow();
        int col = this.getCol();

        //one step
        row = isWhiteColor() ? row - 1 : row + 1;
        Point point = new Point(row, col);
        if (!isLegalPosition(point)) {
            return new ArrayList<>();
        }
        moves.add(point);

        //two steps
        row = isWhiteColor() ? row - 2 : row + 2;
        point = new Point(row, col);
        if (isLegalPosition(point) && !isMoved()) {
            moves.add(point);
        }
        return moves;
    }

    private List<Point> getForwardEatMoves() {
        //TODO getForwardEatMove
        return new ArrayList<>();
    }

    private List<Point> getEnPassantMoves() {
        //TODO getEnPassantMoves
        return new ArrayList<>();
    }

}
