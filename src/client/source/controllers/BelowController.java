package client.source.controllers;

import client.source.Client;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import server.sources.interfaces.GameControllerInterface;
import server.sources.interfaces.ReputationBoardInterface;

/**
 * Class that acts as an intermediary between the belowview and the model.
 * Created by Richard Kerkvliet.
 */

import java.rmi.RemoteException;

public class BelowController implements ControllerInterface, Observable {

    @FXML private Label roundLabel;

    @FXML private ImageView ciderView;
    private Image ciderImage = new Image("/client/resources/img/rewards/cider.png");
    private Image noCiderImage = new Image("/client/resources/img/misc/sold.png");

    @FXML private Ellipse round1;
    @FXML private Ellipse round2;
    @FXML private Ellipse round3;
    @FXML private Ellipse round4;
    @FXML private Ellipse round5;
    @FXML private Ellipse round6;
    @FXML private Ellipse round7;

    private Client client;

    /**
     * This is for registering client when entering the view
     * @param client a player that uses an application to play game
     * @author Joey de Ruiter
     */
    public void registerClient(Client client) {

        this.client = client;

        // For updating the reputation
        this.client.playerBoardObserver.attach(this);

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

        this.updateRoundMarker(gameController);

        this.updateCider(reputationBoard);
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
