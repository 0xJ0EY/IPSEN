package server.sources.models.perks;

/**
 * Created by robin on 28-5-2018.
 */
public class VillagePointsForEmptyCavePerk implements Perk {

    private int value;

    public VillagePointsForEmptyCavePerk(int value) {
        this.value = value;
    }

    @Override
    public String getBackground() {
        return "village_points_for_empty_cave_perk.png";
    }
}
