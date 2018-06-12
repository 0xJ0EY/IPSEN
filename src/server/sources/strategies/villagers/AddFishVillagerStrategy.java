package server.sources.strategies.villagers;

import server.sources.interfaces.PlayerBoardInterface;
import server.sources.models.villagers.VillagerFactory;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

/**
 * Implementation of the addVillagerStrategy to add villagers to the local player playerboard.
 */
public class AddFishVillagerStrategy implements AddVillagerStrategy {

    /**
     * Add a local fish villager object to the player's playerboard.
     * @param playerboard
     * @author Joey de Ruiter
     */
    @Override
    public void execute(PlayerBoardInterface playerboard) {

        try {
            VillagerFactory villagerFactory = new VillagerFactory();
            playerboard.addVillager(villagerFactory.createFishVillager());

        } catch (ParserConfigurationException | RemoteException e) {
            e.printStackTrace();
        }

    }
}
