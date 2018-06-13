package server.sources.requests;

import server.sources.Server;
import server.sources.interfaces.RequestInterface;

import java.rmi.RemoteException;

/**
 * Activates the game.
 *
 * @author Joey de Ruiter
 */
public class StartGameRequest implements RequestInterface {

    /**
     * Starts the game in the server.
     *
     * @param server
     * @throws RemoteException
     */
    @Override
    public void execute(Server server) throws RemoteException {
        server.startGame();
    }

}
