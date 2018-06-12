package server.sources.strategies.villagers;

import server.sources.interfaces.PlayerBoardInterface;
import server.sources.models.villagers.VillagerFactory;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

/**
 * Implementation of the addVillagerStrategy to add villagers to the local player playerboard.
 */
public class AddCatVillagerStrategy implements AddVillagerStrategy {

    /**
     * Add a local cat villager object to the player's playerboard.
     * @param playerboard
     * @author Joey de Ruiter
     */
    @Override
    public void execute(PlayerBoardInterface playerboard) {

        try {
            VillagerFactory villagerFactory = new VillagerFactory();
            playerboard.addVillager(villagerFactory.createCatVillager());

        } catch (ParserConfigurationException | RemoteException e) {
            e.printStackTrace();
        }

    }
}
