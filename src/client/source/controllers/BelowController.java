package client.source.controllers;

import client.source.Client;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import server.sources.interfaces.GameControllerInterface;
import server.sources.interfaces.PlayerInterface;
import server.sources.interfaces.ReputationBoardInterface;
import server.sources.models.Player;

/**
 * Class that acts as an intermediary between the belowview and the model.
 * Created by Richard Kerkvliet.
 */

import java.rmi.RemoteException;
import java.util.ArrayList;

public class BelowController implements ControllerInterface, Observable {

    @FXML private Label roundLabel;

    @FXML private ImageView ciderView;
    private Image ciderImage = new Image("/client/resources/img/perks/cider.png");
    private Image noCiderImage = new Image("/client/resources/img/misc/sold.png");

    @FXML private Ellipse round1;
    @FXML private Ellipse round2;
    @FXML private Ellipse round3;
    @FXML private Ellipse round4;
    @FXML private Ellipse round5;
    @FXML private Ellipse round6;
    @FXML private Ellipse round7;

    @FXML private AnchorPane reputationBoardCounter;

    private Client client;

    /**
     * This is for registering client when entering the view
     * @param client a player that uses an application to play game
     * @author Joey de Ruiter
     */
    public void registerClient(Client client) {

        this.client = client;

        // For updating the reputation
        this.client.clientObserver.attach(this);

        // For updating the round marker
        this.client.gameObserver.attach(this);

        // For updating the cider
        this.client.reputationBoardObserver.attach(this);
    }

    public void showRound(int round) {
        switch (round) {
            case 1:
                this.round1.setFill(Color.RED);
                break;
            case 2:
                this.round2.setFill(Color.RED);
                this.round1.setFill(Color.DODGERBLUE);
                break;
            case 3:
                this.round3.setFill(Color.RED);
                this.round2.setFill(Color.DODGERBLUE);
                break;
            case 4:
                this.round4.setFill(Color.RED);
                this.round3.setFill(Color.DODGERBLUE);
                break;
            case 5:
                this.round5.setFill(Color.RED);
                this.round4.setFill(Color.DODGERBLUE);
                break;
            case 6:
                this.round6.setFill(Color.RED);
                this.round5.setFill(Color.DODGERBLUE);
                break;
            case 7:
                this.round7.setFill(Color.RED);
                this.round6.setFill(Color.DODGERBLUE);
                break;
            default:
                System.out.println("Game ended");
                break;
        }

        this.roundLabel.setText("ROUND: " + round);
    }

    @Override
    public void updateObserver() {
        GameControllerInterface gameController = this.client.gameObserver.getState();
        ReputationBoardInterface reputationBoard = this.client.reputationBoardObserver.getState();
        ArrayList<PlayerInterface> players = this.client.clientObserver.getState();

        this.updateRoundMarker(gameController);

        this.updateCider(reputationBoard);

        this.updateReputation(players);
    }

    private void updateReputation(ArrayList<PlayerInterface> players) {

        int offset = 0;
        this.reputationBoardCounter.getChildren().clear();

        for (PlayerInterface player : players) {
            try {

                int reputation = player.getPlayerBoard().getReputation();

                Rectangle rectangle = new Rectangle(
                        this.reputationXPos(reputation) + offset,
                        this.reputationYPos(reputation),
                        10,
                        10);
                rectangle.setFill(Color.web(player.getColour().getHex()));

                this.reputationBoardCounter.getChildren().add(rectangle);

            } catch (RemoteException e) {
                e.printStackTrace();
            }

            offset -= 15;
        }


    }

    private int reputationXPos(int reputation) {
        switch (reputation) {
            case -2: return 200;
            case -1: return 150;
            case 0: return 130;
            case 1: return 130;
            case 2: return 140;
            case 3: return 170;
            case 4: return 200;
            case 5: return 220;
            case 6: return 250;
            case 7: return 220;
            case 8: return 170;
            default: return 0;
        }
    }

    private int reputationYPos(int reputation) {
        switch (reputation) {
            case -2: return 10;
            case -1: return 60;
            case 0: return 130;
            case 1: return 220;
            case 2: return 270;
            case 3: return 320;
            case 4: return 380;
            case 5: return 450;
            case 6: return 550;
            case 7: return 650;
            case 8: return 720;
            default: return 0;
        }
    }

    private void updateCider(ReputationBoardInterface reputationBoard) {
        if (reputationBoard == null) return;

        try {
            if(reputationBoard.hasCider()){
                this.ciderView.setImage(this.ciderImage);
            } else {
                this.ciderView.setImage(this.noCiderImage);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    private void updateRoundMarker(GameControllerInterface gameController) {
        if (gameController == null) return;

        try {
            this.showRound(gameController.getCurrentRound());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Parent show() throws RemoteException {
        return null;
    }
}
