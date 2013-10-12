package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerForumInterface extends Remote {
	public String getServerName() throws RemoteException;
	public SubjectInterface getSubject(String title) throws RemoteException;
	public ArrayList<String> getAvailableSubjects() throws RemoteException;
	
	public boolean checkPseudo(String pseudo) throws RemoteException;
	public void addClient(String pseudo) throws RemoteException;
	public void removeClient(String pseudo) throws RemoteException;
	public boolean addNewSubject(String newSubjectName) throws RemoteException;

}
