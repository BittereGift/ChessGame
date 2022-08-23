package com.chessgame.gameSystem.caretaker;

import com.chessgame.game.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bittere_Gift
 */
public class SaveCaretaker {

    private final static SaveCaretaker INSTANCE = new SaveCaretaker();
    private List<Game> saveList = new ArrayList<>();

    private SaveCaretaker() {}

    public static SaveCaretaker getInstance() {
        return INSTANCE;
    }

    public void addMemento(Game gamePrototype) {
        saveList.add(gamePrototype);
    }

    public Game getMemento(int index) {
        return saveList.get(index);
    }
}
