package server.sources.models.perks;

/**
 * Created by robin on 28-5-2018.
 */
public class IncomeForGoodsPerk implements Perk {

    private int value;
    private String good;

    public IncomeForGoodsPerk(int value, String good) {
        this.value = value;
        this.good = good;
    }
}
