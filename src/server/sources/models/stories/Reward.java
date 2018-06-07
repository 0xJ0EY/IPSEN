package server.sources.models.stories;

import client.source.Client;
import client.source.components.reward.RewardComponent;
import server.sources.interfaces.PlayerBoardControllerInterface;
import server.sources.interfaces.RewardInterface;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

public class Reward implements RewardInterface {

    protected PlayerBoardControllerInterface playerBoard;

    public Reward(){
    }
    
    public void execute(Client client) throws RemoteException, ParserConfigurationException {
        this.playerBoard = client.getGameClient().getPlayer().getPlayerBoard();
    }

    @Override
    public RewardComponent getRewardComponent() {
        return new RewardComponent();
    }


}
