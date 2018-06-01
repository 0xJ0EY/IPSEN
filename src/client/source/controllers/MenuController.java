package client.source.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;

public class MenuController {

    private enum Tabs { ABOVE, BELOW, MARKET, TURN, SETTINGS, RULES }

    private TabPane tabContainer;

    @FXML private Parent root;

    @FXML private Button turnButton;
    @FXML private Button settingsButton;

    /**
     * These methods are used for opening a selected tab.
     */
    @FXML private void onClickAbove() {
        tabContainer.getSelectionModel().select(Tabs.ABOVE.ordinal());
    }

    @FXML private void onClickBelow() {
        tabContainer.getSelectionModel().select(Tabs.BELOW.ordinal());
    }

    @FXML private void onMarketClick() {
        tabContainer.getSelectionModel().select(Tabs.MARKET.ordinal());
    }

    @FXML private void onTurnClick() {
        tabContainer.getSelectionModel().select(Tabs.TURN.ordinal());
    }

    @FXML private void onClickSettings() {
        tabContainer.getSelectionModel().select(Tabs.SETTINGS.ordinal());
    }

    @FXML public void onRulesClick() {
        tabContainer.getSelectionModel().select(Tabs.RULES.ordinal());
    }

    /**
     * This is for enabling and disabling a startbutton
     */
    public void enableTurnButton() {
        this.turnButton.setDisable(false);
    }

    public void disableTurnButton() {
        this.turnButton.setDisable(true);
    }

    public void enableSettingsButton() {
        this.settingsButton.setDisable(false);
    }

    public void disableSettingsButton() {
        this.settingsButton.setDisable(true);
    }

    /**
     * This is for assigning a tab container.
     * @param tabContainer
     */
    public void assignTabContainer(TabPane tabContainer) {
        this.tabContainer = tabContainer;
    }

}
