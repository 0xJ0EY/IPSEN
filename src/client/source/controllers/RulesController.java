package client.source.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RulesController {

    @FXML private Button previous_btn;
    @FXML private Button next_btn;
    @FXML private ImageView ruleImage;
    @FXML private ScrollPane rulesScrollpane;


    private int index = 0;
    private ArrayList<Image> images = new ArrayList<Image>();

    /**
     * This is for loading images to imageViews
     * */
    public void initialize() {

        Runnable loadImages = () -> {
            for (int i = 0; i < 15; i++) {
                Image image =  new Image("client/resources/img/rules_map/rules-" + i + ".jpg");
                images.add(image);
            }

            this.updateImage();
            this.updateButtons();
        };

        new Thread(loadImages).start();

        // This is for resizing imageview.
        this.ruleImage.fitWidthProperty().bind(rulesScrollpane.widthProperty());

    }

    /**
     * This is for handling clicks
     */
    @FXML private void next() {
        if (this.index + 1 > this.images.size()) return;

        this.index++;

        this.updateImage();
        this.updateButtons();

    }

    @FXML private void previous() {
        if (this.index - 1 < 0) return;
        this.index--;

        this.updateImage();
        this.updateButtons();

    }

    private void updateImage() {
        this.ruleImage.setImage(this.images.get(this.index));
    }

    private void updateButtons() {

        if (this.index == 0) {
            this.previous_btn.setDisable(true);
            this.next_btn.setDisable(false);

        } else if (this.index == this.images.size() - 1) {
            this.previous_btn.setDisable(false);
            this.next_btn.setDisable(true);

        } else {
            this.previous_btn.setDisable(false);
            this.next_btn.setDisable(false);

        }

    }
}
