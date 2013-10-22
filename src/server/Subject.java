package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import client.remote.RemoteSubjectWindowInterface;

public class Subject extends UnicastRemoteObject implements SubjectInterface {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Subject(String name) throws RemoteException {
		this.name = name;
		clientWindows = new ArrayList<RemoteSubjectWindowInterface>();
		connectedList = new ArrayList<String>();
	}
	
	public String getName() throws RemoteException {
		return name;
	}
	
	@Override
	public void register(RemoteSubjectWindowInterface c,String pseudo) throws RemoteException {
		clientWindows.add(c);
		connectedList.add(pseudo);
		this.updateAllConnectedList();
	}

	@Override
	public void unregister(RemoteSubjectWindowInterface c, String pseudo) throws RemoteException {
		// Vérifier qu'il ne faut pas implémenter comparable
		clientWindows.remove(c);
		connectedList.remove(pseudo);
		this.updateAllConnectedList();
	}

	@Override
	public void broadcast(String message) throws RemoteException {
		Iterator<RemoteSubjectWindowInterface> it = clientWindows.iterator();
		while(it.hasNext()) {
			it.next().display(message);
		}
	}
	
	public void updateAllConnectedList() throws RemoteException {
		Iterator<RemoteSubjectWindowInterface> it = clientWindows.iterator();
		while(it.hasNext()) {
			it.next().updateConnectedList(connectedList);
		}
	}
	
	private String name;
	private Collection<RemoteSubjectWindowInterface> clientWindows;
	private Collection<String> connectedList;

}
