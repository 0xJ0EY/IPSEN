package server.sources.models.stories.rewards;

import client.source.components.reward.RewardComponent;
import server.sources.interfaces.BuildingInterface;
import server.sources.models.stories.Reward;

public class BuildingReward extends Reward {

    private BuildingInterface building;

    public BuildingReward(BuildingInterface building){
        this.building = building;
    }

    @Override
    public RewardComponent getRewardComponent() {
        return new BuildingRewardComponent();
    }
}
