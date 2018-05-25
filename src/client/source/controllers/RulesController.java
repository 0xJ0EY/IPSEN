package client.source.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class RulesController implements Initializable {

    @FXML private ImageView firstSideRules;

    /**
     * This is for loading images to imageViews
     *  @param location
     *  @param resources
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstSideRules.setImage(new Image("client/resources/img/rules.jpg"));
    }
}
