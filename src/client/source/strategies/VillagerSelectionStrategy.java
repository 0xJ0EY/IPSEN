package client.source.strategies;

import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.GameClientInterface;

/**
 * Use the strategy pattern to change the way villagerSelectionController handles
 * the requests
 *
 * @author Joey de Ruiter
 */
public interface VillagerSelectionStrategy {

    public void execute(GameClientInterface gameClient, ActionInterface request);

}
