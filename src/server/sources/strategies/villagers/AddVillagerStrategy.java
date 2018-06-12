package server.sources.strategies.villagers;

import server.sources.interfaces.PlayerBoardInterface;

import java.io.Serializable;

/**
 * Implementation of the stategy pattern for creating villagers.
 * @author Joey de Ruiter
 */
public interface AddVillagerStrategy extends Serializable {

    /**
     * Add the villager to the playerboard.
     * The reason for this strategy pattern is that system requires a local object to be set in the playerboard.
     * @param playerboard
     * @author Joey de Ruiter
     */
    public void execute(PlayerBoardInterface playerboard);

}
