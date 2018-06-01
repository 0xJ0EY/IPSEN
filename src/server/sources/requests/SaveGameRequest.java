package server.sources.requests;

import server.sources.Server;
import server.sources.interfaces.RequestInterface;

import java.rmi.RemoteException;

public class SaveGameRequest implements RequestInterface {

    @Override
    public void execute(Server server) throws RemoteException {
        server.save();

        System.out.println("[System] Saved the game");
    }
}
