package client.source.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RulesController implements Initializable {

    @FXML private Button previous_btn;
    @FXML private Button next_btn;
    int pageNumber = 0;
    @FXML private ImageView ruleImage;

    /**
     * This is for loading images to imageViews
     *  @param location
     *  @param resources
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ruleImage.setImage(new Image("client/resources/img/rules_map/rules-0.jpg"));
    }

    /**
     * This is for handling clicks
     *
     */
    @FXML private void next(){
        pageNumber++;
        if (pageNumber <= 15){
            System.out.println("client/resources/img/rules_map/rules-" + pageNumber + ".jpg");
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-" + pageNumber + ".jpg"));
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Sorry, this page does not exist.", ButtonType.OK);
            alert.show();
        }
    }
    @FXML private void previous(){
        pageNumber--;
        if (pageNumber < 0){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Sorry this page does not exist.", ButtonType.OK);
            alert.show();
        }
        else{
            System.out.println("client/resources/img/rules_map/rules-" + pageNumber + ".jpg");
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-" + pageNumber + ".jpg"));
        }
    }
}
