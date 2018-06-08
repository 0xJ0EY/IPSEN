package server.sources.models.stories;

import client.source.Client;
import org.w3c.dom.Element;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.strategies.villagers.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.Serializable;
import java.rmi.RemoteException;

public class Reward implements Serializable {

    private Element reward;
    private int value;
    private String type;

    public Reward(Element rewardElement){
        this.reward = rewardElement;
        this.value = Integer.parseInt(this.reward.getAttribute("value"));
        this.type = this.reward.getAttribute("type");
    }
    
    public void execute(Client client) throws RemoteException, ParserConfigurationException {


        PlayerBoardInterface playerBoard = client.getGameClient().getPlayer().getPlayerBoard();

        switch(this.reward.getTextContent()){
            case "COIN":
                client.getGameClient().getPlayer().getPlayerBoard().addCoins(this.value);
                break;
            case "VILLAGER":

                AddVillagerStrategy reward = this.rewardVillager(this.type);
                playerBoard.executeVillagerStrategy(reward);

                break;
            case "GOOD":
                for (int i = 0; i < this.value; i++) {
                    playerBoard.addGood(this.type);
                }
                break;
            case "REPUTATION":
                // TODO: 06/06/2018 this 
                break;
            case "POTION":
                playerBoard.addPotion();
                break;
            case "CIDER":
                playerBoard.addCider();
                break;
        }
    }

    private AddVillagerStrategy rewardVillager(String type) throws ParserConfigurationException {

        switch (type){
            case "TIN":
                return new AddTinVillagerStrategy();

            case "OIL":
                return new AddOilGirlVillagerStrategy();

            case "CAT":
                return new AddCatVillagerStrategy();

            case "FISH":
                return new AddFishVillagerStrategy();

            default:
                System.out.println("fout in stories.xml");
                return null;
        }

    }

    public Element getReward() {
        return reward;
    }
}
