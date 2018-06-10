package server.sources.models.perks;

/**
 * Created by robin on 28-5-2018.
 */
public class PotionPerk implements Perk {
    private int value = 1;

    public PotionPerk(){
        this.value = value;
    }

    @Override
    public String getBackground() {
        return "potion_perk.png";
    }
}
