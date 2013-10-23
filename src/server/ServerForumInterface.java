package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface ServerForumInterface extends Remote {

	public Collection<String> getAvailableSubjects() throws RemoteException;
	public SubjectInterface getSubject(String title) throws RemoteException;
	
	public String getChatName() throws RemoteException;
	
	public boolean addClient(String pseudo) throws RemoteException;
	public void removeClient(String pseudo) throws RemoteException;
	
	public boolean addSubject(String newSubjectName) throws RemoteException;
	
	
	
}
