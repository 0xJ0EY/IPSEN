package client.source.controllers;

import client.source.Client;

/**
 * Class that acts as an intermediary between the belowview and the model.
 * Created by Joey de Ruiter.
 */
public class BelowController {

    private Client client;

    /**
     * This is for registering client when entering the view
     * @param client a player that uses an application to play game
     * @author Joey de Ruiter
     */
    public void registerClient(Client client) {
        this.client = client;
    }

}
