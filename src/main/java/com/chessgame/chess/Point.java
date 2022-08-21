package com.chessgame.chess;

import java.io.Serializable;
import java.util.Objects;

import static com.chessgame.game.Game.BOARD_SIZE;

/**
 * @author Bittere_Gift
 */
public class Point implements Serializable {

    private int row;
    private int col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isInBoard() {
        int row = getRow();
        int col = getCol();
        return row >= 0 && col >= 0 && row < BOARD_SIZE && col < BOARD_SIZE;
    }

    @Override
    public String toString() {
        return "Point{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return row == point.row && col == point.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
