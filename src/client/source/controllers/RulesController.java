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
//        rules_1.setImage(new Image("client/resources/img/rules_map/rules-01.jpg"));
//        rules_2.setImage(new Image("client/resources/img/rules_map/rules-02.jpg"));
//        rules_3.setImage(new Image("client/resources/img/rules_map/rules-03.jpg"));
//        rules_4.setImage(new Image("client/resources/img/rules_map/rules-04.jpg"));
//        rules_5.setImage(new Image("client/resources/img/rules_map/rules-05.jpg"));
//        rules_6.setImage(new Image("client/resources/img/rules_map/rules-06.jpg"));
//        rules_7.setImage(new Image("client/resources/img/rules_map/rules-07.jpg"));
//        rules_8.setImage(new Image("client/resources/img/rules_map/rules-08.jpg"));
//        rules_9.setImage(new Image("client/resources/img/rules_map/rules-09.jpg"));
    }

    @Override
    public void handle(ActionEvent evt) {
        if (evt.getSource().equals(first)) {
            System.out.println("Hello World");
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-00.jpg"));
        }
        else if (evt.getSource().equals(second)) {
            System.out.println("What is this!!!");
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-01.jpg"));
        }
        else if (evt.getSource().equals(third)) {
            System.out.println("What is this!!!");
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-01.jpg"));
        }
        else if (evt.getSource().equals(fourth)) {
            System.out.println("What is this!!!");
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-01.jpg"));
        }
        else if (evt.getSource().equals(fifth)) {
            System.out.println("What is this!!!");
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-01.jpg"));
        }
        else if (evt.getSource().equals(fifth)) {
            System.out.println("What is this!!!");
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-01.jpg"));
        }
        else if (evt.getSource().equals(sixth)) {
            System.out.println("What is this!!!");
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-01.jpg"));
        }
        else if (evt.getSource().equals(seventh)) {
            System.out.println("What is this!!!");
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-01.jpg"));
        }
        else if (evt.getSource().equals(eighth)) {
            System.out.println("What is this!!!");
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-01.jpg"));
        }
        else if (evt.getSource().equals(ninth)) {
            System.out.println("What is this!!!");
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-01.jpg"));
        }
        else if (evt.getSource().equals(tenth)) {
            System.out.println("What is this!!!");
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-01.jpg"));
        }
        else if (evt.getSource().equals(second)) {
            System.out.println("What is this!!!");
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-01.jpg"));
        }
        else if (evt.getSource().equals(second)) {
            System.out.println("What is this!!!");
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-01.jpg"));
        }
        else if (evt.getSource().equals(second)) {
            System.out.println("What is this!!!");
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-01.jpg"));
        }
        else if (evt.getSource().equals(second)) {
            System.out.println("What is this!!!");
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-01.jpg"));
        }
        else if (evt.getSource().equals(second)) {
            System.out.println("What is this!!!");
            ruleImage.setImage(new Image("client/resources/img/rules_map/rules-01.jpg"));
        }
    }
}
