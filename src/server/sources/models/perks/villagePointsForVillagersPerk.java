package server.sources.models.perks;

/**
 * Created by robin on 28-5-2018.
 */
public class VillagePointsForVillagersPerk implements Perk {

    private int value;

    public VillagePointsForVillagersPerk(int value) {
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
        return "VP for villagers: " + this.value;
=======
    @Override
    public String getBackground() {
        return "village_points_for_villager_perk.png";
>>>>>>> Stashed changes
    }
}
