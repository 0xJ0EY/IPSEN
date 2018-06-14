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

/**
 * A class that acts as an intermediary between a rulesview and models
 * Created by Joey de Ruiter
 */
public class RulesController {

    @FXML private Button previous_btn;
    @FXML private Button next_btn;
    @FXML private ImageView ruleImage;
    @FXML private ScrollPane rulesScrollpane;


    private int index = 0;
    private ArrayList<Image> images = new ArrayList<Image>();

    /**
     * This is for loading images to imageViews
     * @author Robin Silverio
     * */
    public void initialize() {

        Runnable loadImages = () -> {
            for (int i = 0; i < 15; i++) {
                Image image = new Image("client/resources/img/rules_map/rules-" + i + ".jpg");
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
     * This is for handling clicks to see next ruleimage on scrollpane
     * @author Joey de Ruiter and Robin Silverio
     */
    @FXML private void next() {
        if (this.index + 1 > this.images.size()) return;

        this.index++;

        this.updateImage();
        this.updateButtons();

    }

    /**
     * This is for handling clicks to see previous ruleimage on scrollpane
     * @author Joey de Ruiter and Robin Silverio
     */
    @FXML private void previous() {
        if (this.index - 1 < 0) return;
        this.index--;

        this.updateImage();
        this.updateButtons();

    }

    /**
     * Updates ruleimages after clicking next or previous button
     * @author Robin Silverio
     */
    private void updateImage() {
        this.ruleImage.setImage(this.images.get(this.index));
    }

    /**
     * To prevent nullpointerexception, disable buttons when scrollpane loads first or last index
     * @author Joey de Ruiter
     */
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
