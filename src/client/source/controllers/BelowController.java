package client.source.controllers;

import client.source.Client;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import java.rmi.RemoteException;

public class BelowController implements ControllerInterface, Observable {

    @FXML private Label roundLabel;

    @FXML private Ellipse round2;
    @FXML private Ellipse round3;
    @FXML private Ellipse round4;
    @FXML private Ellipse round5;
    @FXML private Ellipse round6;
    @FXML private Ellipse round7;

    private Client client;

    public void registerClient(Client client) {
        this.client = client;
    }

    public void showRound(int round){
        switch (round){
            case 1:
                this.round2.setFill(Color.RED);
                break;
            case 2:
                this.round3.setFill(Color.RED);
                break;
            case 3:
                this.round4.setFill(Color.RED);
                break;
            case 4:
                this.round5.setFill(Color.RED);
                break;
            case 5:
                this.round6.setFill(Color.RED);
                break;
            case 6:
                this.round7.setFill(Color.RED);
                break;
            default:
                System.out.println("Game ended");
                break;
        }
        roundLabel.setText("ROUND: "+round+1);
        this.updateObserver();
    }

    @Override
    public void updateObserver() {

    }

    @Override
    public Parent show() throws RemoteException {
        return null;
    }
}
