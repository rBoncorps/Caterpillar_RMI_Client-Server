package client.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Iterator;

import client.SubjectWindowInterface;

public class RemoteSubjectWindow extends UnicastRemoteObject implements RemoteSubjectWindowInterface {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1952501255768296548L;

	public RemoteSubjectWindow(SubjectWindowInterface subjectWindow) throws RemoteException {
		this.subjectWindow = subjectWindow;
	}
	
	@Override
	public void display(String message) throws RemoteException {
		subjectWindow.display(message);
	}

	public void updateConnectedList(Collection<String> newConnectedList) throws RemoteException {
		this.subjectWindow.updateConnected(newConnectedList);
	}
	
	private SubjectWindowInterface subjectWindow;
	
}
