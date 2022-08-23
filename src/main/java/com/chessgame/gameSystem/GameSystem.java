package com.chessgame.gameSystem;


import cn.hutool.core.util.ObjectUtil;
import com.chessgame.game.Game;
import com.chessgame.gameSystem.caretaker.LoginPlayerCaretaker;
import com.chessgame.gameSystem.caretaker.SaveCaretaker;
import com.chessgame.player.caretaker.PlayerCaretaker;
import com.chessgame.player.memento.PlayerMemento;

import java.io.*;

/**
 * @author Bittere_Gift
 */
public class GameSystem {

    private final static GameSystem INSTANCE = new GameSystem();

    private SaveCaretaker saveCaretaker = SaveCaretaker.getInstance();
    private PlayerCaretaker playerCaretaker = PlayerCaretaker.getInstance();
    private LoginPlayerCaretaker loginPlayerCaretaker = LoginPlayerCaretaker.getInstance();

    private GameSystem() {}

    public static GameSystem getInstance() {
        return INSTANCE;
    }

    public void save() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("game.save"));
        oos.writeObject(saveCaretaker);
        oos.writeObject(playerCaretaker);
        oos.close();
    }

    public void load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("game.save"));
        saveCaretaker = (SaveCaretaker) ois.readObject();
        playerCaretaker = (PlayerCaretaker) ois.readObject();
        ois.close();
    }

    public void saveGame(Game game) {
        saveCaretaker.addMemento(ObjectUtil.cloneByStream(game));
    }

    public void savePlayer(PlayerMemento memento) {
        playerCaretaker.addMemento(memento);
    }

    /**
     * this method should throw an exception if there is no such player
     * @param name
     * @param password
     */
    public void login(String name, String password) {
        PlayerMemento fitPlayer = playerCaretaker.getFitPlayer(name, password);
        loginPlayerCaretaker.addMemento(fitPlayer);
    }
}
