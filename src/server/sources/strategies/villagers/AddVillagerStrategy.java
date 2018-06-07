package server.sources.strategies.villagers;

import server.sources.interfaces.PlayerBoardControllerInterface;

import java.io.Serializable;

public interface AddVillagerStrategy extends Serializable {

    public void execute(PlayerBoardControllerInterface playerboard);

}
