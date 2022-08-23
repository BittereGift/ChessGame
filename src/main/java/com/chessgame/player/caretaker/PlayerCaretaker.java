package com.chessgame.player.caretaker;

import com.chessgame.player.memento.PlayerMemento;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bittere_Gift
 */
public class PlayerCaretaker {

    private final static PlayerCaretaker INSTANCE = new PlayerCaretaker();
    private List<PlayerMemento> mementoList = new ArrayList<>();
    
    private PlayerCaretaker() {}

    public static PlayerCaretaker getInstance() {
        return INSTANCE;
    }

    public void addMemento(PlayerMemento memento) {
        mementoList.add(memento);
    }

    public PlayerMemento getMemento(int index) {
        return mementoList.get(index);
    }

    public PlayerMemento getFitPlayer(String playerName, String password) {
        for (PlayerMemento p : mementoList) {
            if (p.getName().equals(playerName) && p.getPassword().equals(password)) {
                return p;
            }
        }
        throw new IllegalStateException("No such player");
    }
}
