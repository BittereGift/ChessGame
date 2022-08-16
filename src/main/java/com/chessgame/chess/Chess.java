package com.chessgame.chess;

import java.io.Serializable;

/**
 * @author Bittere_Gift
 */
public abstract class Chess implements Serializable {

    private Color color;
    private Point position;
    private String name;

    public Chess() {}

    public Chess(Color color) {
        this.color = color;
    }

    public Chess(Color color, Point position) {
        this.color = color;
        this.position = position;
    }

    @Override
    public String toString() {
        return color + "." + name;
    }

    public void printChess() {
        System.out.println("Chess{" +
                "color=" + color +
                ", position=" + position +
                ", name='" + name + '\'' +
                '}');
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
