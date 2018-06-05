package client.source.components.party;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import server.sources.models.stories.Option;
import server.sources.models.stories.Reward;

import java.io.IOException;

public class OptionButtonComponent extends Button {

    private Option option;


    public OptionButtonComponent(Option option){
        this.option = option;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../resources/views/components/party/optionButton.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setText("Explore "+option.getCost());
        this.setDisable(true);

        this.setOnMouseClicked( e-> {
            for (Reward reward:option.getRewards()) {
                reward.execute();
            }
        });
    }

    public void enableOption(int lanterns){
        if(lanterns >= this.option.getCost()) {
            this.setDisable(false);
        }
    }

}
