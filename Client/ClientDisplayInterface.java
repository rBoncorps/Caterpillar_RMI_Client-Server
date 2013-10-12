package Client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientDisplayInterface extends Remote{
	public void display(String message) throws RemoteException;
	public void displayConnected(ArrayList<String> connected) throws RemoteException;
	public String getPseudo() throws RemoteException;
}
