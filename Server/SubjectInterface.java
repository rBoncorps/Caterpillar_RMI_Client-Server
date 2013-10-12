package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Client.ClientDisplayInterface;

public interface SubjectInterface extends Remote {

	public void subscribe(ClientDisplayInterface cliDisplay) throws RemoteException;
	public void unsubscribe(ClientDisplayInterface cliDisplay) throws RemoteException;
	public void broadcast(String message) throws RemoteException;
	public String getName() throws RemoteException;
}
