package server.sources.notifications;

import javafx.application.Platform;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class UpdatePlayerListNotification implements NotificationInterface {

    /**
     * Update the lobby list
     * @author Joey de Ruiter
     * @param gameClient
     */
    @Override
    public void execute(GameClientInterface gameClient) {

        // Use a lambda expression to access the JavaFX thread and update the lobby ui
        Platform.runLater(() -> {
            try {
                // Force that every client updates the lobby list
                gameClient.getClient().getLobby().updateLobbyList();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

    }
}
