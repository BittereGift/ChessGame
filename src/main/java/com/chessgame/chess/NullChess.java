package com.chessgame.chess;

/**
 * @author Bittere_Gift
 */
public class NullChess extends Chess {

    public NullChess() {
        super();
        setName("Null");
    }

    public NullChess(Color color) {
        super(color);
        setName("Null");
    }

    public NullChess(Color color, Point position) {
        super(color, position);
        setName("Null");
    }

}
