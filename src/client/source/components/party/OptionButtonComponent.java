package client.source.components.party;

import client.source.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import server.sources.interfaces.ServerInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.stories.Option;
import server.sources.models.stories.Reward;
import server.sources.notifications.RewardScreenNotification;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class OptionButtonComponent extends Button {

    private Option option;
    private ServerInterface server;

    public OptionButtonComponent(Option option, Client client, ArrayList<VillagerInterface> villagers){
        this.option = option;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/party/optionButton.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setText("Explore "+this.option.getCost());
        this.setDisable(true);

        this.setOnMouseClicked( e -> {
            for (Reward reward:this.option.getRewards()) {
                try {
                    reward.execute(client);
                } catch (RemoteException | ParserConfigurationException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                client.getGameClient().getPlayer().getPlayerBoard().addCaveCard();
                this.server.notifyClients(new RewardScreenNotification(this.option, villagers));
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        });
    }

    /**
     * This is for enabling option button
     * @param lanterns int
     * @author Richard Kerkvliet
     */
    public void enableOption(int lanterns){
        if(lanterns >= this.option.getCost()) {
            this.setDisable(false);
        }
    }

    /**
     * For setting a server
     * @param server ServerInterface
     * @author Richard Kerkvliet
     */
    public void setServer(ServerInterface server) {
        this.server = server;
    }
}
