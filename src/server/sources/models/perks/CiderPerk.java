package server.sources.models.perks;

/**
 * Created by robin on 28-5-2018.
 */
public class CiderPerk implements Perk {

    private int value;

    public CiderPerk(int value){
        this.value = value;
    }

<<<<<<< Updated upstream
    /**
     * This is only for setting information stats on building card.
     * @return Informatie van statistieken over dat building card
     * @author Robin Silvério
     */
    @Override
    public String toString(){
        return this.getClass().getSimpleName().substring(0, 5) + ": " + this.value;
    }
=======
    @Override
    public String getBackground() {
        return "cider_perk.png";
    }

>>>>>>> Stashed changes
}
