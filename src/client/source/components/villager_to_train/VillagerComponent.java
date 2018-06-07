package client.source.components.villager_to_train;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import server.sources.models.villagers.AllroundVillager;
import server.sources.models.villagers.TrainerVillager;
import server.sources.models.villagers.Villager;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Arrays;

public class VillagerComponent extends AnchorPane {

    private static Client client;
    private int price = (int) (Math.random()* 20) + 1;
    private Villager villager;
    @FXML private AnchorPane background;
    @FXML private AnchorPane type;
    @FXML
    private Button trainBtn;
    @FXML private Text price_label;



    public VillagerComponent(Villager villager) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager_to_train/villager.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (villager.getClass().getSimpleName()){
            case "TrainerVillager":
                this.type.setStyle("-fx-background-image: url('/client/resources/img/type/Feather.png')");
                break;
            case "BuilderVillager":
                this.type.setStyle("-fx-background-image: url('/client/resources/img/type/Hammer.png')");
                break;
            case "AllroundVillager":
                this.type.setStyle("-fx-background-image: url('/client/resources/img/type/allround.png')");
            default:
                break;
        }

        this.villager = villager;

        this.price_label.setText("price: " + Integer.toString(this.price));

        this.background.setStyle(
                "-fx-background-image: url('/client/resources/img/villagerBackgrounds/" + this.villager.getBackground() + " ');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-position: center center;" +
                "-fx-background-size: 110 200");

    }

    @FXML
    private void trainBtn(){

        Alert alert = new Alert(Alert.AlertType.WARNING, "You don't have enough coins to buy!!", ButtonType.OK);

        try {
            if (client.getGameClient().getPlayer().getPlayerBoard().getCoins() < price){
                alert.show();
            }
            else{
                client.getGameClient().getPlayer().getPlayerBoard().payCoin(price);
                client.getGameClient().getPlayer().getPlayerBoard().addVillager(this.villager);
                System.out.println("You have trained and recruited a villager.");
                System.out.println(Arrays.toString(client.getGameClient().getPlayer().getPlayerBoard().listVillagers().toArray()));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void setClient(Client c){
        client = c;
    }
}
