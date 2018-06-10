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

<<<<<<< Updated upstream
    /**
     * This is only for setting information stats on building card.
     * @return Informatie van statistieken over dat building card
     * @author Robin Silvério
     */
    @Override
    public String toString() {
        return "Income for goods: " + this.good + "(" + this.value + ")";
=======
    @Override
    public String getBackground() {
        return "income_from_goods_perk.png";
>>>>>>> Stashed changes
    }
}
