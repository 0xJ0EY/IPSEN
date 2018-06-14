package server.sources.notifications;

import client.source.controllers.TurnController;
import server.sources.controllers.GameController;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.io.*;

import java.rmi.RemoteException;
import java.time.LocalDate;

public class SaveGameNotification implements NotificationInterface {

    private final String DS = File.separator;
    private byte[] bytes;
    private boolean isSaved = false; // This is for determining if a game has been saved

    public SaveGameNotification(GameController gameController) {
        this.bytes = this.gameControllerToBytes(gameController);
    }

    private byte[] gameControllerToBytes(GameController gameController) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;

        try {
            // Create byte array from the GameController object
            out = new ObjectOutputStream(bos);

            // by changing the controller such as 'out.writeObject(new TurnController)', it will trigger an error.
            out.writeObject(gameController);
            out.flush();

            isSaved = true;

            return bos.toByteArray();

        } catch (IOException e) {
            isSaved = false;
        }

        return new byte[0];
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {

        this.writeBytesToFile();

        if (isSavedGame())
            gameClient.getClient().showMessage("Save file written to documents");
        else
            gameClient.getClient().showMessage("Something went wrong with saving a file to documents.");

    }

    private void writeBytesToFile() {

        LocalDate date = LocalDate.now();

        String path = System.getProperty("user.home") + DS + "Documents";
        String file =   date.getYear() +  "_" +
                        date.getMonthValue() + "_" +
                        date.getDayOfMonth() + "_save_game.uml";

        String filepath = path + DS + file;

        try (FileOutputStream fos = new FileOutputStream(new File(filepath))) {
            fos.write(this.bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // This is for checking if the game has been saved.
    private boolean isSavedGame(){
        return this.isSaved;
    }

}
