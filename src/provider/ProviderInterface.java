package provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.SubjectInterface;

public interface ProviderInterface extends Remote{
	
	public String getSubjectName() throws RemoteException;
	public SubjectInterface getSubject() throws RemoteException;
	
}
