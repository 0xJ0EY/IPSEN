package client.source.components.villager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import server.sources.interfaces.VillagerInterface;

import java.io.IOException;
import java.rmi.RemoteException;

public class SelectableVillagerComponent extends VillagerComponent {

    protected boolean selected;

    public SelectableVillagerComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager/selectable_villager.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClickSelect() {
        this.selected = !this.selected;

        if(this.selected) {
            showIndicator();
        } else {
            hideIndicator();
        }
    }

    public boolean isSelected() {
        return this.selected;
    }

    protected void showIndicator(){
        try {
            this.background.setStyle(
                "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);" +
                "-fx-background-image: url('/client/resources/img/villagerBackgrounds/" + this.villager.getBackground() + " ');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-position: center center;" +
                "-fx-background-size: 115 205"
            );
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    protected void hideIndicator(){
        try {
            this.background.setStyle(
                "-fx-effect: dropshadow(three-pass-box, white, 00, 0, 0, 0);" +
                "-fx-background-image: url('/client/resources/img/villagerBackgrounds/" + this.villager.getBackground() + " ');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-position: center center;" +
                "-fx-background-size: 110 200"
            );
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
