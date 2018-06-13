package server.sources.requests;

import server.sources.Server;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.RequestInterface;
import server.sources.models.GameClient;

import java.rmi.RemoteException;

/**
 * Activates when the host asks to save the game.
 *
 * @author Joey de Ruiter
 */
public class SaveGameRequest implements RequestInterface {

    private GameClientInterface target;

    /**
     * @param target
     */
    public SaveGameRequest(GameClientInterface target) {
        this.target = target;
    }

    /**
     * Activates the safe action for the server.
     *
     * @param server
     * @throws RemoteException
     */
    @Override
    public void execute(Server server) throws RemoteException {
        server.save(this.target);

        System.out.println("[System] Saved the game");
    }
}
