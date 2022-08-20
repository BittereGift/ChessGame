package com.chessgame.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.chessgame.chess.Chess.Direction.LEFT;
import static com.chessgame.chess.Chess.Direction.RIGHT;

/**
 * @author Bittere_Gift
 */
public class King extends Chess {

    public King() {
        super();
        setName("King");
    }

    public King(Color color) {
        super(color);
        setName("King");
    }

    public King(Color color, Point position) {
        super(color, position);
        setName("King");
    }

    /**
     * return all possible strategies for this chess
     *
     * @return a List of Point of all possible strategies
     */
    @Override
    public List<Point> getPossibleMoves() {
        List<Point> moves = new ArrayList<>();
        List<Point> simpleMoves = getSimpleMoves();
        List<Point> castlingMoves = getCastlingMoves();
        moves.addAll(simpleMoves);
        moves.addAll(castlingMoves);
        return moves;
    }

    private List<Point> getSimpleMoves() {
        List<Point> moves = new ArrayList<>();
        int row = this.getRow();
        int col = this.getCol();
        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                Point currentPoint = new Point(i, j);
                if (isLegalPosition(currentPoint)) {
                    moves.add(currentPoint);
                }
            }
        }
        return moves;
    }

    private List<Point> getCastlingMoves() {
        List<Point> moves = new ArrayList<>();
        if (isMoved() || isCheck()) {
            return moves;
        }

        int row = getRow();
        int col = getCol();
        if (canCastling(LEFT)) {
            moves.add(new Point(row, 2));
        }
        if (canCastling(RIGHT)) {
            moves.add(new Point(row, 6));
        }
        return moves;
    }

    @Override
    public boolean isCheck() {
        Set<Point> possibleMovesByOtherSide = getAllPossibleMovesByOtherSide();
        return possibleMovesByOtherSide.contains(this.getPosition());
    }

    private boolean canCastling(Direction direction) {
        int row = getRow();
        int col = getCol();
        int counter = 1;
        while (true) {
            int checkCol = direction == LEFT ? col - counter : col + counter;
            Point point = new Point(row, checkCol);
            if (!isInBoardPoint(point)) {
                break;
            }
            if (existUnmovedRook(point)) {
                return true;
            }
            if (!isSafeAndNullPosition(point)) {
                break;
            }
            counter++;
        }
        return false;
    }

    private boolean isSafeAndNullPosition(Point point) {
        return !isCastlingAttackedPoint(point) && isNullChessPosition(point);
    }

    private boolean isCastlingAttackedPoint(Point point) {
        int col = point.getCol();
        Set<Point> possibleMovesByOtherSide = getAllPossibleMovesByOtherSide();
        return (col >= 2 && col <= 6) && possibleMovesByOtherSide.contains(point);
    }

    private boolean existUnmovedRook(Point point) {
        Chess chess = getChessByPoint(point);
        return chess.isUnmovedRook();
    }

}
