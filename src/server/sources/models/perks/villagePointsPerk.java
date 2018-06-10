package server.sources.models.perks;

/**
 * Created by robin on 28-5-2018.
 */
public class VillagePointsPerk implements Perk {

    private int value;

    public VillagePointsPerk(int value) {
        this.value = value;
    }

<<<<<<< Updated upstream
    /**
     * This is only for setting information stats on building card.
     * @return Informatie van statistieken over dat building card
     * @author Robin Silvério
     */
    @Override
    public String toString() {
        return "Village Points: " + this.value;
    }
=======
    @Override
    public String getBackground() {
        return "village_points.png";
    }

>>>>>>> Stashed changes
}
