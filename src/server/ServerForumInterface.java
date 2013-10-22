package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface ServerForumInterface extends Remote {

	public Collection<String> getAvailableSubjects() throws RemoteException;
	public SubjectInterface getSubject(String title) throws RemoteException;
	
	public boolean checkPseudo(String pseudo) throws RemoteException;
	public String getChatName() throws RemoteException;
	
	public void addClient(String pseudo) throws RemoteException;
	public void removeClient(String pseudo) throws RemoteException;
	
	public void addSubject(String newSubjectName) throws RemoteException;
	
	
	
}
