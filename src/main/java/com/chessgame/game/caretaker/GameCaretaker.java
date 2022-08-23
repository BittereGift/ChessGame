package com.chessgame.game.caretaker;

import com.chessgame.game.Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bittere_Gift
 */
public class GameCaretaker implements Serializable {

    private List<Game> gamePrototypeList = new ArrayList<>();

    public void addMemento(Game gamePrototype) {
        gamePrototypeList.add(gamePrototype);
    }

    public Game undoMemento() {
        if (gamePrototypeList.isEmpty()) {
            throw new IllegalStateException("No game memento");
        }
        Game memento = gamePrototypeList.get(gamePrototypeList.size() - 1);
        gamePrototypeList.remove(gamePrototypeList.size() - 1);
        return memento;
    }
}
