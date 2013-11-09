package provider;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import server.Subject;
import server.SubjectInterface;

public class Provider extends UnicastRemoteObject implements ProviderInterface{

	private String subjectName;
	private String adresse;
	private SubjectInterface subject;
	
	public Provider(String subjectName, String adresse) throws RemoteException {
		this.subjectName = subjectName;
		this.adresse = adresse;
		this.subject = new Subject(subjectName); 		
	}
	
	public String getSubjectName() throws RemoteException {
		return this.subjectName;
	}
	
	public SubjectInterface getSubject() throws RemoteException {
		return subject;
	}
}
