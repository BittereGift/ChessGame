import com.chessgame.chess.Point;
import com.chessgame.game.Game;
import com.chessgame.player.AbstractPlayer;
import com.chessgame.player.Player;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        AbstractPlayer player1 = new Player("zhangsan", "");
        AbstractPlayer player2 = new Player("lisi", "");
        Game game = new Game(player1, player2);
        System.out.println(game);
        while (true) {
            if (game.isCheck()) {
                System.out.println("Check!");
                if (game.isMate()) {
                    System.out.println("Mate!");
                    break;
                }
            }
            System.out.println("Select Chess:");
            int originRow = scan.nextInt();
            int originCol = scan.nextInt();
            Point originPoint = new Point(originRow, originCol);
            List<Point> possibleMovesByChess = game.getPossibleMovesByChess(game.getChessByPoint(originPoint));
            System.out.println("Possible moves:" + possibleMovesByChess.toString());

            System.out.println("Select Move:");
            int newRow = scan.nextInt();
            int newCol = scan.nextInt();
            Point newPoint = new Point(newRow, newCol);
            AbstractPlayer currentPlayer = game.getCurrentPlayer();
            currentPlayer.setMove(originPoint, newPoint);
            try {
                game.moveChessAndGoToNextRound();
                System.out.println(game);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private void printMove(Point startPoint, Point endPoint) {
        System.out.println("Move:" +
                startPoint.toString() +
                " -> " + endPoint.toString());
    }
}
