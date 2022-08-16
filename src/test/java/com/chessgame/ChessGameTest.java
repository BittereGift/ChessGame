package com.chessgame;

import cn.hutool.core.util.ArrayUtil;
import com.chessgame.chess.*;
import com.chessgame.player.AbstractPlayer;
import com.chessgame.player.Player;
import com.chessgame.util.Util;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

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
        Color black = new BlackColor();
        Color white = new WhiteColor();
        Point point = new Point(1, 1);
        Chess king = new King(black, point);
        System.out.println(king);
    }

    @Test
    public void cloneTest() {
        Color black = new BlackColor();
        Color white = new WhiteColor();
        Point point = new Point(1, 1);
        Chess[][] args1 = {{new King(black, point)}, {new King(black, point)}, {new King(black, point)}};
        System.out.println("args1: " + Arrays.toString(args1));
        Chess[][] args2 = Util.cloneChessBoard(args1);
        System.out.println("args2:" + Arrays.toString(args2));
        args1[0][0].setColor(white);
        System.out.println("**************************************************");
        System.out.println("args1: " + Arrays.toString(args1));
        System.out.println(args1[0][0].getColor());
        System.out.println("args2:" + Arrays.toString(args2));
        System.out.println(args2[0][0].getColor());
    }
}
