package com.chessgame.chess;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bittere_Gift
 */
public class Knight extends Chess {

    public Knight() {
        super();
        setName("Knigh");
    }

    public Knight(Color color) {
        super(color);
        setName("Knigh");
    }

    public Knight(Color color, Point position) {
        super(color, position);
        setName("Knigh");
    }

    /**
     * return all possible strategies for this chess
     *
     * @return a List of Point of all possible strategies
     */
    @Override
    public List<Point> getPossibleMoves() {
        List<Point> moves = new ArrayList<>();
        int row = this.getRow();
        int col = this.getCol();
        Point[] points = new Point[] {
                new Point(row + 1, col - 2),
                new Point(row + 2, col - 1),
                new Point(row - 1, col - 2),
                new Point(row - 2, col - 1),
                new Point(row + 1, col + 2),
                new Point(row + 2, col + 1),
                new Point(row - 1, col + 2),
                new Point(row - 2, col + 1),
        };
        for (Point p : points) {
            if (isLegalPosition(p)) {
                moves.add(p);
            }
        }
        return moves;
    }

}
