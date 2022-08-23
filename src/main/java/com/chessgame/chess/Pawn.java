package com.chessgame.chess;

import java.util.ArrayList;
import java.util.List;

import static com.chessgame.chess.Chess.Direction.*;

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
        List<Point> forwardMoves = getForwardMoves();
        List<Point> forwardEatMoves = getForwardEatMoves();
        List<Point> enPassantMoves = getEnPassantMoves();
        moves.addAll(forwardMoves);
        moves.addAll(forwardEatMoves);
        moves.addAll(enPassantMoves);
        return moves;
    }

    private List<Point> getForwardMoves() {
        List<Point> moves = new ArrayList<>();
        int row = this.getRow();
        int col = this.getCol();

        //one step
        row = isWhite() ? row - 1 : row + 1;
        Point point = new Point(row, col);
        if (!isLegalPosition(point)) {
            return new ArrayList<>();
        }
        moves.add(point);

        //two steps
        row = isWhite() ? row - 1 : row + 1;
        point = new Point(row, col);
        if (isLegalPosition(point) && !isMoved()) {
            moves.add(point);
        }
        return moves;
    }

    private List<Point> getForwardEatMoves() {
        List<Point> moves = new ArrayList<>();
        int row = this.getRow();
        int col = this.getCol();
        row = isWhite() ? row + UP.getRow() : row + DOWN.getRow();
        Point leftPoint = new Point(row, col + LEFT.getCol());
        Point rightPoint = new Point(row, col + RIGHT.getCol());
        if (isEatablePosition(leftPoint)) {
            moves.add(leftPoint);
        }
        if (isEatablePosition(rightPoint)) {
            moves.add(rightPoint);
        }
        return moves;
    }

    private List<Point> getEnPassantMoves() {
        List<Point> moves = new ArrayList<>();
        if (!existEnPassantOriginPawn()) {
            return moves;
        }
        int row = this.getEnPassantOriginPawnPoint().getRow();
        int col = this.getEnPassantOriginPawnPoint().getCol();
        row = isWhite() ? row + UP.getRow() : row + DOWN.getRow();
        moves.add(new Point(row, col));
        return moves;
    }

    public boolean existEnPassantOriginPawn() {
        return this.getEnPassantOriginPawnPoint() != null;
    }
}
