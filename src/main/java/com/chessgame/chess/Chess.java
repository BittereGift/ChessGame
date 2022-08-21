package com.chessgame.chess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Point enPassantOriginPawnPoint = null;

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

    public void simpleMove(Point endPoint) {
        chessBoard[getRow()][getCol()] = new NullChess(position);
        this.position = endPoint;
        chessBoard[getRow()][getCol()] = this;
    }

    public boolean isCheck() {
        throw new UnsupportedOperationException("Not supported.");
    }

    protected boolean isInBoardPoint(Point p) {
        int row = p.getRow();
        int col = p.getCol();
        return row >= 0 && col >= 0 && row < BOARD_SIZE && col < BOARD_SIZE;
    }

    protected boolean isLegalPosition(Point p) {
        return isInBoardPoint(p) && (isNullChessPosition(p) || isOtherSideChessPosition(p));
    }

    protected boolean isOtherSideChessPosition(Point p) {
        if (isWhite()) {
            return getChessByPoint(p).isBlack();
        }
        return getChessByPoint(p).isWhite();
    }

    protected boolean isSameColorPosition(Point p) {
        return getChessByPoint(p).getColor().equals(this.getColor());
    }

    protected boolean isNullChessPosition(Point p) {
        return getChessByPoint(p) instanceof NullChess;
    }

    protected boolean isEatablePosition(Point p) {
        return isInBoardPoint(p) && isOtherSideChessPosition(p);
    }

    protected Chess getChessByPoint(Point p) {
        return chessBoard[p.getRow()][p.getCol()];
    }

    enum Direction {
        //将正负数封装给8个方向
        UP(-1, 0),
        UP_RIGHT(-1, 1),
        RIGHT(0, 1),
        DOWN_RIGHT(1, 1),
        DOWN(1, 0),
        DOWN_LEFT(1, -1),
        LEFT(0, -1),
        UP_LEFT(-1, -1);

        private final int rowDirection;
        private final int colDirection;

        Direction(int rowDirection, int colDirection) {
            this.rowDirection = rowDirection;
            this.colDirection = colDirection;
        }

        public int getRow() {
            return rowDirection;
        }

        public int getCol() {
            return colDirection;
        }
    }

    protected List<Point> getPossibleMovesOnOneDirection(Direction direction) {
        List<Point> movesOnOneDirection = new ArrayList<>();
        int rowDirection = direction.getRow();
        int colDirection = direction.getCol();
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

    public Set<Point> getAllPossibleMovesByOtherSide() {
        Set<Point> moves = new HashSet<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Chess chess = chessBoard[i][j];
                if (chess.getColor() == this.color) {
                    continue;
                }
                moves.addAll(chess.getPossibleMoves());
            }
        }
        return moves;
    }

    public boolean existEnPassantOriginPawn() {
        return enPassantOriginPawnPoint != null;
    }

    public int getRow() {
        return this.getPosition().getRow();
    }

    public int getCol() {
        return this.getPosition().getCol();
    }

    public boolean isWhite() {
        return this.getColor() == WHITE;
    }

    public boolean isBlack() {
        return this.getColor() == BLACK;
    }

    public boolean isRook() {
        return this instanceof Rook;
    }

    public boolean isPawn() {
        return this instanceof Pawn;
    }

    public boolean isKing() {
        return this instanceof King;
    }

    protected boolean isUnmovedRook() {
        return this.isRook() && !this.isMoved();
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

    public Point getEnPassantOriginPawnPoint() {
        return enPassantOriginPawnPoint;
    }

    public void setEnPassantOriginPawnPoint(Point enPassantOriginPawnPoint) {
        this.enPassantOriginPawnPoint = enPassantOriginPawnPoint;
    }
}
