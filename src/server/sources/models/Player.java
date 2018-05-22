package server.sources.models;

import server.sources.interfaces.GameClientInterface;
import java.io.Serializable;

public class Player implements Serializable {

    public PlayerBoard board = new PlayerBoard();
    private GameClientInterface gameClient;

    public GameClientInterface getGameClient() {
        return gameClient;
    }

    public void setGameClient(GameClientInterface gameClient) {
        this.gameClient = gameClient;
    }
}
