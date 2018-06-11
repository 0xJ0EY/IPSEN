package server.sources.models.perks;

/**
 * Created by robin on 28-5-2018.
 */
public class VillagePointsForVillagersPerk implements Perk {

    private int value;

    public VillagePointsForVillagersPerk(int value) {
        this.value = value;
    }

    @Override
    public String getBackground() {
        return "village_points_for_villager_perk.png";
    }
}
