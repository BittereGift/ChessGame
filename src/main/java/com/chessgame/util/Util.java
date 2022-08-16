package com.chessgame.util;

import cn.hutool.core.util.ObjectUtil;
import com.chessgame.chess.Chess;

/**
 * @author Bittere_Gift
 */
public class Util {

    private Util() {}

    public static Chess[][] cloneChessBoard(Chess[][] chessBoard) {
        Chess[][] chess = new Chess[chessBoard.length][chessBoard[0].length];
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[0].length; j++) {
                Chess clone = ObjectUtil.clone(chessBoard[i][j]);
                chess[i][j] = clone;
            }
        }
        return chess;
    }
}
