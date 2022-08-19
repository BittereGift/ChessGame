package com.chessgame.chess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.chessgame.chess.Color.BLACK;
import static com.chessgame.chess.Color.WHITE;
import static com.chessgame.game.Game.BOARD_SIZE;

/**
 * @author Bittere_Gift
 */
public abstract class Chess implements Serializable {

    private Color color;
    private Point position;
    private String name;
    private Chess[][] chessBoard;
    private boolean isMoved = false;

    public Chess() {}

    public Chess(Color color) {
        this.color = color;
    }

    public Chess(Color color, Point position) {
        this.color = color;
        this.position = position;
    }

    /**
     * return all possible strategies for this chess
     * @return a List of Point of all possible strategies
     */
    public abstract List<Point> getPossibleMoves();

    protected boolean isInBoardPoint(Point p) {
        int row = p.getRow();
        int col = p.getCol();
        return row >= 0 && col >= 0 && row < BOARD_SIZE && col < BOARD_SIZE;
    }

    protected boolean isLegalPosition(Point p) {
        return isInBoardPoint(p) && (isNullChessPosition(p) || isOtherSideChessPosition(p));
    }

    protected boolean isOtherSideChessPosition(Point p) {
        if (isWhiteColor()) {
            return getChessByPoint(p).isBlackColor();
        }
        return getChessByPoint(p).isWhiteColor();
    }

    protected boolean isSameColorPosition(Point p) {
        return getChessByPoint(p).getColor().equals(this.getColor());
    }

    protected boolean isNullChessPosition(Point p) {
        return getChessByPoint(p) instanceof NullChess;
    }

    protected Chess getChessByPoint(Point p) {
        return chessBoard[p.getRow()][p.getCol()];
    }

    enum Direction {
        //将正负数封装给8个方向
        UP(0, -1),
        UP_RIGHT(1, -1),
        RIGHT(1, 0),
        DOWN_RIGHT(1, 1),
        DOWN(0, 1),
        DOWN_LEFT(-1, 1),
        LEFT(-1, 0),
        UP_LEFT(-1, -1);

        private final int rowDirection;
        private final int colDirection;

        Direction(int rowDirection, int colDirection) {
            this.rowDirection = rowDirection;
            this.colDirection = colDirection;
        }

        public int getRowDirection() {
            return rowDirection;
        }

        public int getColDirection() {
            return colDirection;
        }
    }

    protected List<Point> getPossibleMovesOnOneDirection(Direction direction) {
        List<Point> movesOnOneDirection = new ArrayList<>();
        int rowDirection = direction.getRowDirection();
        int colDirection = direction.getColDirection();
        int row = position.getRow();
        int col = position.getCol();

        int counter = 1;
        while (true) {
            int newRow = row + counter * rowDirection;
            int newCol = col + counter * colDirection;
            Point point = new Point(newRow, newCol);
            if (!isInBoardPoint(point)) {
                break;
            }
            if (isNullChessPosition(point)) {
                movesOnOneDirection.add(point);
            } else if (isOtherSideChessPosition(point)) {
                movesOnOneDirection.add(point);
                break;
            } else if (isSameColorPosition(point)) {
                break;
            }
            counter++;
        }
        return movesOnOneDirection;
    }

    public int getRow() {
        return this.getPosition().getRow();
    }

    public int getCol() {
        return this.getPosition().getCol();
    }

    public boolean isWhiteColor() {
        return this.getColor() == WHITE;
    }

    public boolean isBlackColor() {
        return this.getColor() == BLACK;
    }

    @Override
    public String toString() {
        return color + "." + name;
    }

    public void print() {
        System.out.println("Chess{" +
                "color=" + color +
                ", position=" + position +
                ", name='" + name + '\'' +
                '}');
    }

    public Chess[][] getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(Chess[][] chessBoard) {
        this.chessBoard = chessBoard;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
        setMoved(true);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }
}
