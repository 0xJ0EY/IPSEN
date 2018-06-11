package server.sources.models.perks;

/**
 * Created by robin on 28-5-2018.
 */
public class VillagePointsPerk implements Perk {

    private int value;

    public VillagePointsPerk(int value) {
        this.value = value;
    }

    @Override
    public String getBackground() {
        return "village_points.png";
    }

    public int getValue() {
        return this.value;
    }

}
