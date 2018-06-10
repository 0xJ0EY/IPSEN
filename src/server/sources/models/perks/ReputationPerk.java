package server.sources.models.perks;

/**
 * Created by robin on 28-5-2018.
 */
public class ReputationPerk implements Perk {
    private final int REPUTATION = 1;

    @Override
    public String getBackground() {
        return "reputation_perk.png";
    }
}
