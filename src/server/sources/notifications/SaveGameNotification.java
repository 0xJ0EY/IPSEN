package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.io.File;
import java.io.IOException;

import java.io.FileOutputStream;
import java.rmi.RemoteException;

public class SaveGameNotification implements NotificationInterface {


    private final String DS = File.separator;
    private byte[] bytes;

    public SaveGameNotification(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {

        this.writeBytesToFile();
        gameClient.getClient().getMain().showMessage("Save file written to desktop");

    }

    private void writeBytesToFile() {

        String path = System.getProperty("user.home") + DS + "Desktop";
        String file = "save_game.uml";

        String filepath = path + DS + file;

        try (FileOutputStream fos = new FileOutputStream(new File(filepath))) {
            fos.write(this.bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
