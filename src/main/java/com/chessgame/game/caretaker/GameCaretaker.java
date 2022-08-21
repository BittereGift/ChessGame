package com.chessgame.game.caretaker;

import com.chessgame.game.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bittere_Gift
 */
public class GameCaretaker {

    private final static GameCaretaker INSTANCE = new GameCaretaker();
    private List<Game> gamePrototypeList = new ArrayList<>();

    private GameCaretaker() {}

    public static GameCaretaker getInstance() {
        return INSTANCE;
    }

    public void addMemento(Game gamePrototype) {
        gamePrototypeList.add(gamePrototype);
    }

    public Game getMemento() {
        if (gamePrototypeList.isEmpty()) {
            throw new IllegalStateException("No game memento");
        }
        Game memento = gamePrototypeList.get(gamePrototypeList.size() - 1);
        gamePrototypeList.remove(gamePrototypeList.size() - 1);
        return memento;
    }
}
