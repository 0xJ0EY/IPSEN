package server.sources.models.stories;

import client.source.Client;
import org.w3c.dom.Element;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Reward implements Serializable {

    // TODO: 05/06/2018 remove
//    private String reward;


    private Element reward;
    private int value;
    private String type;

    // TODO: 05/06/2018 remove
//    public Reward(String reward) {
//        this.reward = reward;
//    }

    public Reward(Element rewardElement){
        this.reward = rewardElement;
        this.value = Integer.parseInt(this.reward.getAttribute("value"));
        this.type = this.reward.getAttribute("type");
        System.out.println("this is the type: "+ type + " and this is the value: " + value);
    }


    public void execute(Client client) throws RemoteException {
        switch(this.reward.getTextContent()){
            case "COIN":
                System.out.println("Coin");
                client.getGameClient().getPlayer().getPlayerBoard().addCoin(this.value);
                break;
            case "VILLAGER":
                System.out.println("Villager");
                break;
            case "GOOD":
                System.out.println("Good");
                for (int i = 0; i<this.value; i++) {
                    client.getGameClient().getPlayer().getPlayerBoard().addGood(this.type);
                    System.out.println("added good: " + type);
                }
                break;
            case "REPUTATION":
                System.out.println("Reputation");
                break;
            case "POTION":
                System.out.println("Potion");
                break;
            case "CIDER":
                System.out.println("Cider");
                break;
        }
    }

    public Element getReward() {
        return reward;
    }
}
