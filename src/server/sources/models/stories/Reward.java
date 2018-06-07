package server.sources.models.stories;

import client.source.Client;
import org.w3c.dom.Element;
import server.sources.models.ReputationBoard;
import server.sources.models.villagers.*;

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
        switch(this.reward.getTextContent()){
            case "COIN":
                client.getGameClient().getPlayer().getPlayerBoard().addCoins(this.value);
                break;
            case "VILLAGER":
                client.getGameClient().getPlayer().getPlayerBoard().addVillager(rewardVillager(this.type));
                break;
            case "GOOD":
                for (int i = 0; i<this.value; i++) {
                    client.getGameClient().getPlayer().getPlayerBoard().addGood(this.type);
                }
                break;
            case "REPUTATION":
                if(value == 1){
                    client.getGameClient().getPlayer().addReputatoin();
                }else{
                    client.getGameClient().getPlayer().removeReputation();
                }
                System.out.println(client.getGameClient().getPlayer().getReputation());
                System.out.println();
                break;
            case "POTION":
                client.getGameClient().getPlayer().getPlayerBoard().addPotion();
                break;
            case "CIDER":
                client.getGameClient().getPlayer().getPlayerBoard().addCider();
                break;
        }
    }

    private Villager rewardVillager(String type) throws ParserConfigurationException {
        VillagerFactory vf = new VillagerFactory();
        Villager villager;

        switch (type){
            case "TIN":
                villager = vf.createTinVillager();
                break;
            case "OIL":
                villager = vf.createOilGirlVillager();
                break;
            case "CAT":
                villager = vf.createCatVillager();
                break;
            case "FISH":
                villager = vf.createFishVillager();
                break;
            default:
                villager = null;
                System.out.println("fout in stories.xml");
        }

        return villager;
    }

    public Element getReward() {
        return reward;
    }
}
