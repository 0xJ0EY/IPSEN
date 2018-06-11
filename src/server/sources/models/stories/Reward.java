package server.sources.models.stories;

import client.source.Client;
import server.sources.interfaces.PlayerBoardInterface;

import client.source.components.reward.RewardComponent;
import server.sources.interfaces.RewardInterface;


import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

public abstract class Reward implements RewardInterface {

    protected PlayerBoardInterface playerBoard;

    public Reward(){
    }

    public void execute(Client client) throws RemoteException, ParserConfigurationException {
        this.playerBoard = client.getGameClient().getPlayer().getPlayerBoard();
    }

    @Override
    public abstract RewardComponent getRewardComponent();


}
