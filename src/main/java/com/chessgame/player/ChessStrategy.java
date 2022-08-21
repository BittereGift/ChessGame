package com.chessgame.player;

import com.chessgame.chess.Point;

import java.io.Serializable;

/**
 * @author Bittere_Gift
 */
public class ChessStrategy implements Serializable {

    private Point startPoint;
    private Point endPoint;

    public void reset() {
        startPoint = null;
        endPoint = null;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
}
