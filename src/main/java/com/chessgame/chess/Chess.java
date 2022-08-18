package com.chessgame.chess;

import com.chessgame.game.Game;

import java.io.Serializable;
import java.util.List;

/**
 * @author Bittere_Gift
 */
public abstract class Chess implements Serializable {

    private Color color;
    private Point position;
    private String name;
    private Chess[][] chessBoard;

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
    protected abstract List<Point> getPossibleMoves();

    protected boolean isInBoardPoint(Point p) {
        int row = p.getRow();
        int col = p.getCol();
        return row >= 0 && col >= 0 && row < Game.BOARD_SIZE && col < Game.BOARD_SIZE;
    }

    protected boolean isNullOrDifferentColorPosition(Point p) {
        return isNullChessPosition(p) || isOtherSideChessPosition(p);
    }

    protected boolean isOtherSideChessPosition(Point p) {
        return getChessByPoint(p).getColor().equals(this.color);
    }

    protected boolean isNullChessPosition(Point p) {
        return getChessByPoint(p) instanceof NullChess;
    }

    protected Chess getChessByPoint(Point p) {
        return chessBoard[p.getRow()][p.getCol()];
    }

    enum Direction {
        //将正负数封装给8个方向
        UP(0, 1),
        UP_RIGHT(1, 1),
        RIGHT(1, 0),
        DOWN_RIGHT(1, -1),
        DOWN(0, -1),
        DOWN_LEFT(-1, -1),
        LEFT(-1, 0),
        UP_LEFT(-1, 1);

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
        //TODO getPossibleMovesOnOneDirection
        return null;
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
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
