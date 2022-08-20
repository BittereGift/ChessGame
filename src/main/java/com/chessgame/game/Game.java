package com.chessgame.game;

import com.chessgame.chess.*;
import com.chessgame.player.AbstractPlayer;
import com.chessgame.util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.chessgame.chess.Color.BLACK;
import static com.chessgame.chess.Color.WHITE;

/**
 * @author Bittere_Gift
 */
public class Game {

    public final static int BOARD_SIZE = 8;
    private AbstractPlayer whitePlayer;
    private AbstractPlayer blackPlayer;
    private int round;
    private Chess selectedChess;
    private AbstractPlayer currentPlayer;
    private Color currentPlayerColor;
    private Chess[][] chessBoard;

    public Game() {
        initNewChessBoard();
    }

    public Game(AbstractPlayer whitePlayer, AbstractPlayer blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        initNewChessBoard();
    }

    public Game(AbstractPlayer whitePlayer, AbstractPlayer blackPlayer, Chess[][] chessBoard, Color currentPlayerColor) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.chessBoard = Util.cloneChessBoard(chessBoard);
        //TODO
    }

    private void initNewChessBoard() {
        this.chessBoard = new Chess[BOARD_SIZE][BOARD_SIZE];
        initNullChess();
        initPawn();
        initChessExceptPawn();
        setChessBoardToChess();
    }

    private void initPawn() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            chessBoard[1][i] = new Pawn(BLACK, new Point(1, i));
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            chessBoard[6][i] = new Pawn(WHITE, new Point(6, i));
        }
    }

    private void initChessExceptPawn() {
        for (int i = 0; i < BOARD_SIZE; i += BOARD_SIZE - 1) {
            Color color = i == 0 ? BLACK : WHITE;
            chessBoard[i][0] = new Rook(color, new Point(i, 0));
            chessBoard[i][1] = new Knight(color, new Point(i, 1));
            chessBoard[i][2] = new Bishop(color, new Point(i, 2));
            chessBoard[i][3] = new Queen(color, new Point(i, 3));
            chessBoard[i][4] = new King(color, new Point(i, 4));
            chessBoard[i][5] = new Bishop(color, new Point(i, 5));
            chessBoard[i][6] = new Knight(color, new Point(i, 6));
            chessBoard[i][7] = new Rook(color, new Point(i, 7));
        }
    }

    private void initNullChess() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                chessBoard[i][j] = new NullChess(new Point(i, j));
            }
        }
    }

    private void setChessBoardToChess() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                chessBoard[i][j].setChessBoard(chessBoard);
            }
        }
    }

    public Set<Point> getCurrentPlayerAllPossibleMoves() {
        return getAllPossibleMoves(currentPlayerColor);
    }

    public Set<Point> getAllPossibleMoves(Color playerColor) {
        Set<Point> moves = new HashSet<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Chess chess = chessBoard[i][j];
                if (chess.getColor() != playerColor) {
                    continue;
                }
                List<Point> possibleMovesByChess =  getPossibleMovesByChess(chess);
                moves.addAll(possibleMovesByChess);
            }
        }
        return moves;
    }

    public List<Point> getPossibleMovesByChess(Chess chess) {
        return chess.getPossibleMoves();
    }

    public void printChessBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.printf(chessBoard[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public AbstractPlayer getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(AbstractPlayer whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public AbstractPlayer getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(AbstractPlayer blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Chess getSelectedChess() {
        return selectedChess;
    }

    public void setSelectedChess(Chess selectedChess) {
        this.selectedChess = selectedChess;
    }

    public AbstractPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(AbstractPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Color getCurrentPlayerColor() {
        return currentPlayerColor;
    }

    public void setCurrentPlayerColor(Color currentPlayerColor) {
        this.currentPlayerColor = currentPlayerColor;
    }

    public Chess[][] getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(Chess[][] chessBoard) {
        this.chessBoard = chessBoard;
    }
}
