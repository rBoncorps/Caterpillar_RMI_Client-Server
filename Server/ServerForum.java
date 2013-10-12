package Server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class ServerForum extends UnicastRemoteObject implements ServerForumInterface, Serializable{
	
	private static final long serialVersionUID = 1L;
	private String serverName = "Caterpillar_RMI_Chat";
	
	private ArrayList<String> subjectNameList;
	private ArrayList<SubjectInterface> subjectList;
	private ArrayList<String> pseudoLogged;
	
	public ServerForum() throws RemoteException {
		subjectList = new ArrayList<SubjectInterface>();
		subjectNameList = new ArrayList<String>();
		pseudoLogged = new ArrayList<String>();
		
		subjectNameList.add("Sport");
		subjectNameList.add("Music");
		subjectNameList.add("Cinema");
		Iterator<String> it = subjectNameList.iterator();
		while (it.hasNext()) {
			String sub = it.next();
			Subject subject = new Subject(sub);
			subjectList.add(subject);
		}
		System.out.println("Serveur Créé !");
	}
	
	public String getServerName() throws RemoteException {
		return serverName;
	}
	
	public SubjectInterface getSubject(String title) throws RemoteException {
		Iterator<SubjectInterface> ite = subjectList.iterator();
		while (ite.hasNext()) {
			SubjectInterface tmp = ite.next();
			if (tmp.getName().equals(title)) {
				return tmp;
			}
		}
		System.out.println("Server - Sujet inconnu");
		return null;
	}
	
	public ArrayList<String> getAvailableSubjects() {
		return subjectNameList;
	}
	
	public boolean checkPseudo(String pseudo) throws RemoteException{
		Iterator<String> itPseudo = pseudoLogged.iterator();
		while(itPseudo.hasNext()) {
			if(itPseudo.next().equals(pseudo)) {
				return false;
			}
		}
		return true;
	}
	
	public void addClient(String pseudo) throws RemoteException {
		pseudoLogged.add(pseudo);
		System.out.println(" Added : " + pseudo + " ; pseudoLogged : " + pseudoLogged);
	}
	
	public void removeClient(String pseudo) throws RemoteException {
		pseudoLogged.remove(pseudo);
		System.out.println(" Removed : " + pseudo + " ; pseudoLogged : " + pseudoLogged);
	}
	
	public boolean addNewSubject(String newSubjectName) throws RemoteException{
		try {
			Subject newSubject = new Subject(newSubjectName);
			subjectList.add(newSubject);
			subjectNameList.add(newSubjectName);
		} catch (RemoteException e) {
			System.out.println("Error creating new subject");
			return false;
		}
		return true;
	}

}
