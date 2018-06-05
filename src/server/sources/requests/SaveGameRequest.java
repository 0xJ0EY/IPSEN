package server.sources.requests;

import server.sources.Server;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.RequestInterface;
import server.sources.models.GameClient;

import java.rmi.RemoteException;

public class SaveGameRequest implements RequestInterface {

    private GameClientInterface target;

    public SaveGameRequest(GameClientInterface target) {
        this.target = target;
    }

    @Override
    public void execute(Server server) throws RemoteException {
        server.save(this.target);

        System.out.println("[System] Saved the game");
    }
}
