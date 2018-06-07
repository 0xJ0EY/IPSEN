package server.sources.models.perks;

/**
 * Created by robin on 28-5-2018.
 */
public class TrainToReadyPerk implements Perk {
    private String perk = "Train to ready";

    /**
     * This is only for setting information stats on building card.
     * @return Informatie van statistieken over dat building card
     * @author Robin Silvério
     */
    @Override
    public String toString() {
        return perk;
    }
}
