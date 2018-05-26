package client.source.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RulesController implements Initializable, EventHandler<ActionEvent> {


    @FXML private Button first_rule;
    @FXML private Button second_rule;
    @FXML private Button third_rule;
    @FXML private Button fourth_rule;
    @FXML private Button fifth_rule;
    @FXML private Button sixth_rule;
    @FXML private Button seventh_rule;
    @FXML private Button eighth_rule;
    @FXML private Button ninth_rule;
    @FXML private Button tenth_rule;
    @FXML private Button eleventh_rule;
    @FXML private Button twelfth_rule;
    @FXML private Button thirteenth_rule;
    @FXML private Button fourteenth_rule;
    @FXML private Button fifteenth_rule;
    @FXML private Button sixteenth_rule;



    @FXML private ImageView ruleImage;

    /**
     * This is for loading images to imageViews
     *  @param location
     *  @param resources
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ruleImage.setImage(new Image("client/resources/img/rules_map/rules-00.jpg"));
    }

    /**
     * This is for handling clicks
     * @param evt
     */
    @Override
    public void handle(ActionEvent evt) {
        if (evt.getSource().equals(first_rule)) {
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-00.jpg"));
        }
        else if (evt.getSource().equals(second_rule)) {
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-01.jpg"));
        }
        else if (evt.getSource().equals(third_rule)) {
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-02.jpg"));
        }
        else if (evt.getSource().equals(fourth_rule)) {
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-03.jpg"));
        }
        else if (evt.getSource().equals(fifth_rule)) {
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-04.jpg"));
        }
        else if (evt.getSource().equals(sixth_rule)) {
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-05.jpg"));
        }
        else if (evt.getSource().equals(seventh_rule)) {
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-06.jpg"));
        }
        else if (evt.getSource().equals(eighth_rule)) {
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-07.jpg"));
        }
        else if (evt.getSource().equals(ninth_rule)) {
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-08.jpg"));
        }
        else if (evt.getSource().equals(tenth_rule)) {
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-09.jpg"));
        }
        else if (evt.getSource().equals(eleventh_rule)) {
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-10.jpg"));
        }
        else if (evt.getSource().equals(twelfth_rule)) {
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-11.jpg"));
        }
        else if (evt.getSource().equals(thirteenth_rule)) {
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-12.jpg"));
        }
        else if (evt.getSource().equals(fourteenth_rule)) {
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-13.jpg"));
        }
        else if (evt.getSource().equals(fifteenth_rule)) {
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-14.jpg"));
        }
        else if (evt.getSource().equals(sixteenth_rule)){
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-15.jpg"));
        }
    }
}
