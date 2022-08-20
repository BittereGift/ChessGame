package com.chessgame;

import com.chessgame.chess.*;
import com.chessgame.game.Game;
import com.chessgame.player.AbstractPlayer;
import com.chessgame.player.Player;
import com.chessgame.util.Util;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.chessgame.chess.Color.BLACK;
import static com.chessgame.chess.Color.WHITE;
import static com.chessgame.game.Game.BOARD_SIZE;

public class ChessGameTest {

    @Test
    public void playerCreateTest() {
        String name = "zhangsan";
        String password = "123";
        AbstractPlayer player = new Player(name, password);
        System.out.println(player);
        String name2 = "lisi";
        AbstractPlayer player2 = new Player(name2, password);
        System.out.println(player2);
    }

    @Test
    public void chessCreateTest() {
        Point point = new Point(1, 1);
        Chess king = new King(BLACK, point);
        System.out.println(king);
    }

    @Test
    public void cloneTest() {
        Point point = new Point(1, 1);
        Chess[][] args1 = {{new King(BLACK, point)}, {new King(BLACK, point)}, {new King(BLACK, point)}};
        System.out.println("args1: " + Arrays.toString(args1));
        Chess[][] args2 = Util.cloneChessBoard(args1);
        System.out.println("args2:" + Arrays.toString(args2));
        args1[0][0].setColor(WHITE);
        System.out.println("**************************************************");
        System.out.println("args1: " + Arrays.toString(args1));
        System.out.println(args1[0][0].getColor());
        System.out.println("args2:" + Arrays.toString(args2));
        System.out.println(args2[0][0].getColor());
    }

    @Test
    public void queenTest() {
        Chess queen = new Queen(BLACK, new Point(1, 1));
        Chess counter = new Queen(WHITE, new Point(2, 2));
        Chess sameColor = new Pawn(BLACK, new Point(0, 0));

        Chess[][] chessBoard = new Chess[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoard[i][j] = new NullChess(new Point(i, j));
            }
        }
        chessBoard[1][1] = queen;
        chessBoard[2][2] = counter;
        chessBoard[0][0] = sameColor;
        queen.setChessBoard(chessBoard);

        List<Point> possibleMoves = queen.getPossibleMoves();
        System.out.println("possibleMoves: " + possibleMoves.toString());
    }

    @Test
    public void printChessBoardTest() {
        Game game = new Game();
        game.printChessBoard();
    }

    @Test
    public void rookTest() {
        Chess rook = new Rook(BLACK, new Point(1, 1));
        Chess counter = new Queen(WHITE, new Point(1, 5));
        Chess sameColor = new Pawn(BLACK, new Point(5, 1));

        Chess[][] chessBoard = new Chess[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoard[i][j] = new NullChess(new Point(i, j));
            }
        }
        chessBoard[1][1] = rook;
        chessBoard[1][5] = counter;
        chessBoard[5][1] = sameColor;
        rook.setChessBoard(chessBoard);

        List<Point> possibleMoves = rook.getPossibleMoves();
        System.out.println("possibleMoves: " + possibleMoves.toString());
    }

    @Test
    public void knightTest() {
        Chess knight = new Knight(BLACK, new Point(1, 1));
        Chess counter = new Queen(WHITE, new Point(2, 3));
        Chess sameColor = new Pawn(BLACK, new Point(3, 2));

        Chess[][] chessBoard = new Chess[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoard[i][j] = new NullChess(new Point(i, j));
            }
        }
        chessBoard[1][1] = knight;
        chessBoard[2][3] = counter;
        chessBoard[3][2] = sameColor;
        knight.setChessBoard(chessBoard);

        List<Point> possibleMoves = knight.getPossibleMoves();
        System.out.println("possibleMoves: " + possibleMoves.toString());
    }

    @Test
    public void setTest() {
        Set<Point> set = new HashSet<>();
        set.add(new Point(0,0));
        set.add(new Point(0,0));
        System.out.println(set);
    }

    @Test
    public void kingTest() {
        Chess king = new King(BLACK, new Point(0, 4));
        Chess rook1 = new Rook(BLACK, new Point(0, 0));
        Chess rook2 = new Rook(BLACK, new Point(0, 7));
        Chess sameColor = new Queen(BLACK, new Point(0, 3));

        Chess[][] chessBoard = new Chess[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoard[i][j] = new NullChess(new Point(i, j));
            }
        }
        chessBoard[0][4] = king;
        chessBoard[0][0] = rook1;
        chessBoard[0][7] = rook2;
        chessBoard[0][6] = sameColor;
        king.setChessBoard(chessBoard);

        List<Point> possibleMoves = king.getPossibleMoves();
        System.out.println("possibleMoves: " + possibleMoves.toString());
    }

    @Test
    public void pawnGetForwardEatMovesTest() {
        Chess pawn = new Pawn(BLACK, new Point(1, 4));
        Chess counter = new Rook(WHITE, new Point(2, 3));
        Chess sameColor = new Queen(BLACK, new Point(0, 3));

        Chess[][] chessBoard = new Chess[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoard[i][j] = new NullChess(new Point(i, j));
            }
        }
        chessBoard[1][4] = pawn;
        chessBoard[2][3] = counter;
        chessBoard[0][3] = sameColor;
        pawn.setChessBoard(chessBoard);

        List<Point> possibleMoves = pawn.getPossibleMoves();
        System.out.println("possibleMoves: " + possibleMoves.toString());
    }
}
