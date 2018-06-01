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

public class LoadGameRequest implements RequestInterface {

    private byte[] bytes;

    public LoadGameRequest(File saveGame) {
        this.bytes = bytesFromSaveGame(saveGame);
    }

    private byte[] bytesFromSaveGame(File saveGame) {
        try {
            return Files.readAllBytes(saveGame.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

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

    @Override
    public void execute(Server server) throws RemoteException {
        server.load(this.gameControllerFromBytes(this.bytes));
    }

}
