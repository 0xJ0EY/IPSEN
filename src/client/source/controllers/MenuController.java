package client.source.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class MenuController {
    private final int TAB_ABOVE     = 0;
    private final int TAB_BELOW     = 1;
    private final int TAB_MARKET    = 2;

    private TabPane tabContainer;

    @FXML
    public void clickAbove() {
        tabContainer.getSelectionModel().select(this.TAB_ABOVE);
    }

    @FXML
    public void clickBelow() {
        tabContainer.getSelectionModel().select(this.TAB_BELOW);

    }

    public void assignTabContainer(TabPane tabContainer) {
        this.tabContainer = tabContainer;
    }

}
