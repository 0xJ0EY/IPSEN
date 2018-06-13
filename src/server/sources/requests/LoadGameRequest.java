package server.sources.requests;

import server.sources.Server;
import server.sources.controllers.GameController;
import server.sources.interfaces.RequestInterface;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.rmi.RemoteException;

/**
 * Loads the game from an uml type file.
 *
 * @author Joey de Ruiter
 */
public class LoadGameRequest implements RequestInterface {

    private byte[] bytes;

    /**
     * @param saveGame
     */
    public LoadGameRequest(File saveGame) {
        this.bytes = bytesFromSaveGame(saveGame);
    }

    /**
     * Reads all the buytes and returns them in an array.
     *
     * @param saveGame
     * @return
     */
    private byte[] bytesFromSaveGame(File saveGame) {
        try {
            return Files.readAllBytes(saveGame.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    /**
     * Gets game controller.
     *
     * @param bytes
     * @return
     */
    private GameController gameControllerFromBytes(byte[] bytes) {

        try {
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream is = new ObjectInputStream(in);

            return (GameController) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Activates when activated.
     *
     * @param server
     * @throws RemoteException
     */
    @Override
    public void execute(Server server) throws RemoteException {
        System.out.println("[System] Loading save game");
        server.load(this.gameControllerFromBytes(this.bytes));
        System.out.println("[System] Save game loaded");
    }

}
