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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    private Image imageLeft = null;
    private Image imageMiddle = new Image("/client/resources/img/rules_map/rules-0.jpg");
    private Image imageRight = new Image("/client/resources/img/rules_map/rules-1.jpg");

    /**
     * Set up the first image & set the scroll pane to full width
     * @author Joey de Ruiter
     * */
    public void initialize() {
        this.updateImage();

        this.ruleImage.fitWidthProperty().bind(rulesScrollpane.widthProperty());

        this.next_btn.setDisable(false);
    }


    /**
     * Load the image to the right
     * @author Joey de Ruiter
     */
    @FXML private void next() {
        // Move to the right

        this.imageLeft = this.imageMiddle;
        this.imageMiddle = this.imageRight;
        this.imageRight = null;

        this.updateImage();

        this.loadImageRight();

    }

    /**
     * Load the image to the left
     * @author Joey de Ruiter
     */
    @FXML private void previous() {
        // Move to the left
        this.imageRight = this.imageMiddle;
        this.imageMiddle = this.imageLeft;
        this.imageLeft = null;

        this.updateImage();

        this.loadImageLeft();
    }

    /**
     * Set the main image
     */
    private void updateImage() {
        this.ruleImage.setImage(this.imageMiddle);
    }

    /**
     * Load most right image in a thread, so it wont be run on the main thread.
     * @author Joey de Ruiter
     */
    private void loadImageRight() {
        Thread thread = new Thread(() -> {

            this.index++;

            String rightImage = "client/resources/img/rules_map/rules-" + (this.index + 1) + ".jpg";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(rightImage);

            this.disableRightButton();

            if (inputStream == null) {
                this.imageRight = null;
                return;
            }

            this.imageRight = new Image(inputStream);
            this.enableBothButtons();
        });

        thread.start();
    }

    /**
     * Load most left image in a thread, so it wont be run on the main thread.
     * @author Joey de Ruiter
     */
    private void loadImageLeft() {
        Thread thread = new Thread(() -> {

            this.index--;

            String rightImage = "client/resources/img/rules_map/rules-" + (this.index - 1) + ".jpg";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(rightImage);

            this.disableLeftButton();

            if (inputStream == null) {
                this.imageLeft = null;
                return;
            }

            this.imageLeft = new Image(inputStream);
            this.enableBothButtons();
        });

        thread.start();
    }

    private void disableLeftButton() {
        this.previous_btn.setDisable(true);

    }

    private void disableRightButton() {
        this.next_btn.setDisable(true);

    }

    private void enableBothButtons() {
        this.previous_btn.setDisable(false);
        this.next_btn.setDisable(false);

    }
}
