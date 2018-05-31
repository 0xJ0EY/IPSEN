package server.sources.models.perks;

/**
 * Created by robin on 28-5-2018.
 */
public class villagePointsForThingsPerk implements Perk {

    private final int value;
    private String good;

    public villagePointsForThingsPerk(int value, String good) {
        this.value = value;
        this.good = good;
    }
}
