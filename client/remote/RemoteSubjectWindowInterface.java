package client.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface RemoteSubjectWindowInterface extends Remote {

	public void display(String message) throws RemoteException;
	public void updateConnectedList(Collection<String> newConnectedList) throws RemoteException;
}
