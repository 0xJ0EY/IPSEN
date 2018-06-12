package server.sources.models.stories;

import client.source.Client;
import server.sources.interfaces.PlayerBoardInterface;

import client.source.components.reward.RewardComponent;
import server.sources.interfaces.RewardInterface;


import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

/**
 * This class creates a Reward.
 * @author Richard Kerkvliet
 */
public abstract class Reward implements RewardInterface {

    protected PlayerBoardInterface playerBoard;

    /**
     * creates a Reward.
     * @author Richard Kerkvliet
     */
    public Reward(){
    }

    /**
     * Executes the Reward.
     * @param client
     * @throws RemoteException
     * @throws ParserConfigurationException
     * @author Richard Kerklviet
     */
    public void execute(Client client) throws RemoteException, ParserConfigurationException {
        this.playerBoard = client.getGameClient().getPlayer().getPlayerBoard();
    }

    /**
     * Returns the RewardComponent
     * @return RewardComponent
     * @author Richard Kerkvliet
     */
    @Override
    public abstract RewardComponent getRewardComponent();


}
