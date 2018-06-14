package server.sources.models.perks;

/**
 * Created by robin on 28-5-2018.
 */
public class BedPerk implements Perk {

    private int value = 1;

    @Override
    public String getBackground() {
        return "bed_perk.png";
    }

    public int getValue(){
        return this.value;
    }

}
