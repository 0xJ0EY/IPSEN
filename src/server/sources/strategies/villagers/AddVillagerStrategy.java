package server.sources.strategies.villagers;

import server.sources.interfaces.PlayerBoardInterface;

import java.io.Serializable;

public interface AddVillagerStrategy extends Serializable {

    public void execute(PlayerBoardInterface playerboard);

}
