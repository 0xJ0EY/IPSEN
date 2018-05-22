package server.sources.requests;

import server.sources.Server;
import server.sources.interfaces.RequestInterface;

import java.rmi.RemoteException;

public class StartGameRequest implements RequestInterface {

    @Override
    public void execute(Server server) throws RemoteException {
        server.game.play();
    }

}
