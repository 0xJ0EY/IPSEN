package server.sources.models.perks;

/**
 * Created by robin on 28-5-2018.
 */
public class PotionPerk implements Perk {
    private int value = 1;

    public PotionPerk(){
        this.value = value;
    }

    /**
     * This is only for setting information stats on building card.
     * @return Informatie van statistieken over dat building card
     * @author Robin Silvério
     */
    @Override
    public String toString(){
        return this.getClass().getSimpleName().substring(0, 6) + ": " + this.value;
    }
}
