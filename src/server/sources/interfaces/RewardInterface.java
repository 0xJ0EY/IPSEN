package server.sources.interfaces;

import client.source.components.reward.RewardComponent;

import java.io.Serializable;
import java.rmi.Remote;

public interface RewardInterface extends Serializable, Remote {
    public RewardComponent getRewardComponent();
}
