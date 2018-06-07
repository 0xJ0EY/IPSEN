package server.sources.strategies.villagers;

import server.sources.interfaces.PlayerBoardControllerInterface;
import server.sources.models.villagers.VillagerFactory;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

public class AddOilGirlVillagerStrategy implements AddVillagerStrategy {

    @Override
    public void execute(PlayerBoardControllerInterface playerboard) {

        try {
            VillagerFactory villagerFactory = new VillagerFactory();
            playerboard.addVillager(villagerFactory.createOilGirlVillager());

        } catch (ParserConfigurationException | RemoteException e) {
            e.printStackTrace();
        }

    }
}
