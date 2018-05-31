package server.sources.interfaces;

import server.sources.models.stories.Story;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StoryControllerInterface extends Remote {

    public Story randomStory() throws RemoteException;

    public Story storyAt(int index) throws RemoteException;

}
