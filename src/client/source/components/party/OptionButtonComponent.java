package client.source.components.party;

import client.source.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import server.sources.interfaces.ServerInterface;
import server.sources.models.stories.Option;
import server.sources.models.stories.Reward;
import server.sources.notifications.RewardScreenNotification;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.rmi.RemoteException;

public class OptionButtonComponent extends Button {

    private Option option;
    private ServerInterface server;

    public OptionButtonComponent(Option option, Client client){
        this.option = option;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/party/optionButton.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setText("Explore "+option.getCost());
        this.setDisable(true);

        this.setOnMouseClicked( e -> {
            for (Reward reward:option.getRewards()) {
                try {
                    reward.execute(client);
                } catch (RemoteException | ParserConfigurationException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                this.server.notifyClients(new RewardScreenNotification(option));
                client.getGameClient().getPlayer().getPlayerBoard().addCaveCard();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        });
    }

    public void enableOption(int lanterns){
        if(lanterns >= this.option.getCost()) {
            this.setDisable(false);
        }
    }

    public void setServer(ServerInterface server) {
        this.server = server;
    }
}
