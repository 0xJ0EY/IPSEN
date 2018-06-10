package server.sources.models.perks;

/**
 * Created by robin on 28-5-2018.
 */
public class VillagePointsForEmptyCavePerk implements Perk {

    private int value;

    public VillagePointsForEmptyCavePerk(int value) {
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
        return "VP for empty cave: " + this.value;
=======
    @Override
    public String getBackground() {
        return "village_points_for_empty_cave_perk.png";
>>>>>>> Stashed changes
    }
}
