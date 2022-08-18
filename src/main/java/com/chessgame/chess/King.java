package com.chessgame.chess;

import com.chessgame.util.Util;

import java.util.ArrayList;
import java.util.List;

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
    protected List<Point> getPossibleMoves() {
        List<Point> moves = new ArrayList<>();
        List<Point> simpleMoves = getSimpleMoves();
        List<Point> castlingMoves = getCastlingMoves();
        moves.addAll(simpleMoves);
        moves.addAll(castlingMoves);
        return moves;
    }

    private List<Point> getSimpleMoves() {
        List<Point> moves = new ArrayList<>();
        int row = this.getPosition().getRow();
        int col = this.getPosition().getCol();
        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                if (!isInBoardPoint(this.getPosition())) {
                    continue;
                }
                Point currentPoint = new Point(i, j);
                if (isNullOrDifferentColorPosition(currentPoint)) {
                    moves.add(currentPoint);
                }
            }
        }
        return moves;
    }

    private List<Point> getCastlingMoves() {
        List<Point> moves = new ArrayList<>();
        //TODO getCastlingMoves
        return moves;
    }

}
