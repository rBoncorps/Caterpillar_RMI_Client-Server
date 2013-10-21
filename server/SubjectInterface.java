package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import client.remote.RemoteSubjectWindowInterface;

public interface SubjectInterface extends Remote {

	public String getName() throws RemoteException;
	public void register(RemoteSubjectWindowInterface c, String pseudo) throws RemoteException;
	public void unregister(RemoteSubjectWindowInterface c, String pseudo) throws RemoteException;
	public void broadcast(String message) throws RemoteException;
	public void updateAllConnectedList() throws RemoteException;
}
