package server.sources.models.perks;

/**
 * Created by robin on 28-5-2018.
 */
public class Village_points_for_things_perk implements Perk {

    private final int value;
    private String good;

    public Village_points_for_things_perk(int value, String good) {
        this.value = value;
        this.good = good;
    }
}
