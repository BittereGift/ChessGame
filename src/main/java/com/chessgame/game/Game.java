package com.chessgame.game;

import cn.hutool.core.util.ObjectUtil;
import com.chessgame.chess.*;
import com.chessgame.game.caretaker.GameCaretaker;
import com.chessgame.player.AbstractPlayer;
import com.chessgame.player.ChessStrategy;
import com.chessgame.util.Util;

import java.io.Serializable;
import java.util.*;

import static com.chessgame.chess.Color.BLACK;
import static com.chessgame.chess.Color.WHITE;
import static com.chessgame.game.Game.PromotionChessType.*;

/**
 * @author Bittere_Gift
 */
public class Game implements Serializable {

    public final static int BOARD_SIZE = 8;

    private GameCaretaker caretaker = new GameCaretaker();
    private AbstractPlayer whitePlayer;
    private AbstractPlayer blackPlayer;
    private int round;
    private AbstractPlayer currentPlayer;
    private Color currentPlayerColor;
    private Chess[][] chessBoard;

    public Game() {
        initNewChessBoard();
    }

    public Game(AbstractPlayer whitePlayer, AbstractPlayer blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        reset();
    }

    public Game(AbstractPlayer whitePlayer, AbstractPlayer blackPlayer, Chess[][] chessBoard, Color currentPlayerColor, int round) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.chessBoard = ObjectUtil.cloneByStream(chessBoard);
        this.currentPlayerColor = currentPlayerColor;
        currentPlayer = isWhitePlayer() ? whitePlayer : blackPlayer;
        this.round = round;
    }

    private void initNewChessBoard() {
        this.chessBoard = new Chess[BOARD_SIZE][BOARD_SIZE];
        initNullChess();
        initPawn();
        initChessExceptPawn();
        setChessBoardToChess();
        createMemento();
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
                List<Point> possibleMovesByChess = getPossibleMovesByChess(chess);
                moves.addAll(possibleMovesByChess);
            }
        }
        return moves;
    }

    public List<Point> getPossibleMovesByChess(Chess chess) {
        return chess.getPossibleMoves();
    }

    /**
     * Catch Exception when move is not possible
     */
    public void moveChessAndGoToNextRound() {
        ChessStrategy strategy = currentPlayer.getStrategy();
        moveChess(strategy);
        clearEnPassant();
        setToNextRound();
        strategy.reset();
        createMemento();
    }

    private void clearEnPassant() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Chess chess = chessBoard[i][j];
                if (isCurrentPlayersChess(chess)) {
                    chess.setEnPassantOriginPawnPoint(null);
                }
            }
        }
    }

    private void moveChess(ChessStrategy strategy) {
        Point startPoint = strategy.getStartPoint();
        Point endPoint = strategy.getEndPoint();
        Chess chessMoved = getChessByPoint(startPoint);
        List<Point> possibleMoves = chessMoved.getPossibleMoves();

        if (!possibleMoves.contains(endPoint)) {
            throw new IllegalArgumentException("Not a supported move");
        }

        chessMoved.simpleMove(endPoint);
        moveIfPawn(chessMoved, startPoint);
        moveIfKing(chessMoved, startPoint);
    }

    private void moveIfPawn(Chess chessMoved, Point originPoint) {
        int originRow = originPoint.getRow();
        int originCol = originPoint.getCol();
        int currentCol = chessMoved.getCol();
        int currentRow = chessMoved.getRow();
        if (!chessMoved.isPawn()) {
            return;
        }
        //if pawn goes 2 Steps, let pawn beside en passant
        if (Math.abs(originRow - currentRow) == 2) {
            Point leftPoint = new Point(currentRow, currentCol - 1);
            Point rightPoint = new Point(currentRow, currentCol + 1);
            Point newPoint = new Point(currentRow, currentCol);
            if (leftPoint.isInBoard()) {
                getChessByPoint(leftPoint).setEnPassantOriginPawnPoint(newPoint);
            }
            if (rightPoint.isInBoard()) {
                getChessByPoint(rightPoint).setEnPassantOriginPawnPoint(newPoint);
            }
        } else if (currentCol != originCol && chessMoved.existEnPassantOriginPawn()) {
            //enPassant
            Point enPassantPoint = chessMoved.getEnPassantOriginPawnPoint();
            eatChessByPoint(enPassantPoint);
        }
    }

    private void moveIfKing(Chess chessMoved, Point originPoint) {
        int originRow = originPoint.getRow();
        int originCol = originPoint.getCol();
        int currentCol = chessMoved.getCol();
        int currentRow = chessMoved.getRow();
        //Castling
        if (!chessMoved.isKing()) {
            return;
        }
        if (Math.abs(originCol - currentCol) > 1) {
            int rookCol = originCol - currentCol > 0 ? 0 : 7;
            int moveColDiffer = originCol - currentCol > 0 ? 1 : -1;
            Point rookPoint = new Point(currentRow, rookCol);
            Chess rook = getChessByPoint(rookPoint);
            rook.simpleMove(new Point(currentRow, currentCol + moveColDiffer));
            rook.setMoved(true);
        }
    }

    private void setToNextRound() {
        currentPlayer = isWhitePlayer() ? blackPlayer : whitePlayer;
        currentPlayerColor = isWhitePlayer() ? BLACK : WHITE;
        round++;
    }

    private void eatChessByPoint(Point point) {
        chessBoard[point.getRow()][point.getCol()] = new NullChess(point);
    }

    enum PromotionChessType {
        //升变类型
        QUEEN,
        ROOK,
        KNIGHT,
        BISHOP
    }

    public boolean canPromotion() {
        for (int i = 0; i < BOARD_SIZE; i += 7) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (chessBoard[i][j].isPawn()) {
                    return true;
                }
            }
        }
        return false;
    }


    public void promotion(PromotionChessType chessType) {
        Map<PromotionChessType, Chess> chessMap = getChessMap();
        Point promotionPawnPoint = getPromotionPawnPoint();
        Chess chess = chessMap.get(chessType);
        chess.setPosition(promotionPawnPoint);
        chessBoard[promotionPawnPoint.getRow()][promotionPawnPoint.getCol()] = chess;
    }

    private Point getPromotionPawnPoint() {
        for (int i = 0; i < BOARD_SIZE; i += 7) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (chessBoard[i][j].isPawn()) {
                    return new Point(i, j);
                }
            }
        }
        throw new IllegalStateException("No promotion points");
    }

    private Map<PromotionChessType, Chess> getChessMap() {
        Map<PromotionChessType, Chess> chessMap = new HashMap<>(4);
        chessMap.put(QUEEN, new Queen());
        chessMap.put(ROOK, new Rook());
        chessMap.put(KNIGHT, new Knight());
        chessMap.put(BISHOP, new Bishop());
        return chessMap;
    }

    /**
     * <code>isCheck()</code> should be checked before use this method.
     * @return
     */
    public boolean isMate() {
        Chess king = getCurrentPlayersKing();

        //if chess attacking eatable
        List<Chess> attackingKingsChessList = getAttackingKingsChess(king);
        if (attackingKingsChessList.size() == 1) {
            Chess attackingChess = attackingKingsChessList.get(0);
            Set<Point> currentPlayerMoves = getCurrentPlayerAllPossibleMoves();
            if (currentPlayerMoves.contains(attackingChess.getPosition())) {
                return false;
            }
        }

        //if king can move to a safe place
        List<Point> kingPossibleMoves = king.getPossibleMoves();
        Set<Point> allPossibleMovesByOtherSide = king.getAllPossibleMovesByOtherSide();
        if (!allPossibleMovesByOtherSide.containsAll(kingPossibleMoves)) {
            return false;
        }

        //TODO if there is a chess that can save the king
        return true;
    }

    private List<Chess> getAttackingKingsChess(Chess king) {
        Point kingPosition = king.getPosition();
        List<Chess> chessList = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Chess chess = chessBoard[i][j];
                if (containsKingPosition(chess, kingPosition)) {
                    chessList.add(chess);
                }
            }
        }
        return chessList;
    }

    private boolean containsKingPosition(Chess chess, Point kingPosition) {
        return !isCurrentPlayersChess(chess) && chess.getPossibleMoves().contains(kingPosition);
    }

    public boolean isCheck() {
        return getCurrentPlayersKing().isCheck();
    }

    private Chess getCurrentPlayersKing() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Chess chess = chessBoard[i][j];
                if (isCurrentPlayersKing(chess)) {
                    return chess;
                }
            }
        }
        throw new IllegalStateException("No current players king found");
    }

    private boolean isCurrentPlayersKing(Chess chess) {
        return chess.isKing() && isCurrentPlayersChess(chess);
    }

    private boolean isCurrentPlayersChess(Chess chess) {
        return currentPlayerColor == chess.getColor();
    }

    /**
     * This method creates a memento to the undo list
     */
    private void createMemento() {
        caretaker.addMemento(ObjectUtil.cloneByStream(this));
    }

    /**
     * This method should catch an exception when there is no mementos.
     */
    public void undo() {
        Game memento = caretaker.undoMemento();
        setWhitePlayer(memento.getWhitePlayer());
        setBlackPlayer(memento.getWhitePlayer());
        setRound(memento.getRound());
        setCurrentPlayer(memento.getCurrentPlayer());
        setCurrentPlayerColor(memento.getCurrentPlayerColor());
        setChessBoard(memento.getChessBoard());
    }

    public void reset() {
        initNewChessBoard();
        round = 1;
        currentPlayer = whitePlayer;
        currentPlayerColor = WHITE;
    }

    public Chess getChessByPoint(Point p) {
        return chessBoard[p.getRow()][p.getCol()];
    }

    private boolean isWhitePlayer() {
        return currentPlayerColor == WHITE;
    }

    private boolean isBlackPlayer() {
        return currentPlayerColor == BLACK;
    }

    @Override
    public String toString() {
        return Util.getLine() +
                "\nwhitePlayer=" + whitePlayer +
                "\nblackPlayer=" + blackPlayer +
                "\nround=" + round +
                "\ncurrentPlayer=" + currentPlayer +
                "\ncurrentPlayerColor=" + currentPlayerColor +
                "\n" + Util.getLine() +
                "\n" + getChessBoardString() +
                "\n" + Util.getLine();
    }

    private String getChessBoardString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                sb.append(chessBoard[i][j]).append("\t");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public void printChessBoard() {
        System.out.println(getChessBoardString());
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
