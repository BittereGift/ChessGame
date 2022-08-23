package com.chessgame.gameSystem.caretaker;

import com.chessgame.game.Game;
import com.chessgame.player.AbstractPlayer;
import com.chessgame.player.Player;
import com.chessgame.player.caretaker.PlayerCaretaker;
import com.chessgame.player.memento.PlayerMemento;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bittere_Gift
 */
public class LoginPlayerCaretaker {

    private final static LoginPlayerCaretaker INSTANCE = new LoginPlayerCaretaker();
    private List<PlayerMemento> mementoList = new ArrayList<>();

    private LoginPlayerCaretaker() {}

    public static LoginPlayerCaretaker getInstance() {
        return INSTANCE;
    }

    public void addMemento(PlayerMemento memento) {
        mementoList.add(memento);
    }

    public PlayerMemento getMemento(int index) {
        return mementoList.get(index);
    }
}
