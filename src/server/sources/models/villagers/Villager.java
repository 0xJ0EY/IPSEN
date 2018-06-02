package server.sources.models.villagers;

import client.source.components.villager.TypeDefaultComponent;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.Serializable;
import java.util.ArrayList;

public class Villager implements Serializable {

    protected ArrayList<Lantern> lanterns = new ArrayList<Lantern>();
    protected boolean injured;
    protected boolean tired;
    protected String background;

    public Villager(ArrayList<Lantern> lanterns, boolean injured, boolean tired) {
        this.lanterns = lanterns;
        this.injured = injured;
        this.tired = tired;

        this.generateRandomBackground();

    }

    //TODO: moet nog bedden weghalen
    public void rest(){
        if (injured) {
            injured = false;
            tired = true;
        } else if (tired) {
            tired = false;
        }
    }
    
    private void calculateLanters(int thrown){

        // TODO: 02/06/2018 work in progresss 
        
        int amount = 0;
        for (Lantern lantern: lanterns) { 
            amount = lantern.getAmount(thrown);
        }
    }

    private void generateRandomBackground() {
        int villager = (int) Math.floor(Math.random() * 5 + 1);
        this.background = "villagerBackground" + villager + ".png";

    }

    public boolean isUseable() {
        return (!this.injured && !this.tired);
    }

    public void tire() {
        this.tired = true;
    }

    public void injure() {
        this.injured = false;
    }

    public AnchorPane getType() {
        return new TypeDefaultComponent();
    }

    public String getBackground() {
        return background;
    }
}
