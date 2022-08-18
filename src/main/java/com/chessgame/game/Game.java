package com.chessgame.game;

import com.chessgame.chess.Chess;
import com.chessgame.chess.Color;
import com.chessgame.player.AbstractPlayer;
import com.chessgame.util.Util;

import java.util.Arrays;

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

    public Game(AbstractPlayer whitePlayer, AbstractPlayer blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }

    public Game(AbstractPlayer whitePlayer, AbstractPlayer blackPlayer, Chess[][] chessBoard, Color currentPlayerColor) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.chessBoard = Util.cloneChessBoard(chessBoard);
        //TODO
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
