package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ServerForum extends UnicastRemoteObject implements
		ServerForumInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7574842304354455215L;
	

	public ServerForum() throws RemoteException { 
		chatName = "Caterpillar Chatroom";
		loggedPseudos = new ArrayList<String>();
		subjectNames = new ArrayList<String>();
		subjectNames.add("Book");
		subjectNames.add("Music");
		subjectNames.add("Manga");
		subjects = new ArrayList<SubjectInterface>();
		Iterator<String> it = subjectNames.iterator();
		while(it.hasNext()) {
			subjects.add(new Subject(it.next()));
		}
		System.out.println("Server created");
	}
	
	@Override
	public Collection<String> getAvailableSubjects() throws RemoteException {
		return subjectNames;
	}
	
	@Override
	public SubjectInterface getSubject(String title) throws RemoteException {
		Iterator<SubjectInterface> it = subjects.iterator();
		while(it.hasNext()) {
			SubjectInterface tmp = it.next();
			if(tmp.getName().equals(title)) {
				return tmp;
			}
		}
		return null;
	}
	
	public boolean checkPseudo(String name) throws RemoteException {
		Iterator<String> it = loggedPseudos.iterator();
		while(it.hasNext()) {
			String tmp = it.next();
			if(tmp.equals(name)) {
				return false;
			}
		}
		return true;
	}
	
	public String getChatName() throws RemoteException {
		return chatName;
	}
	
	public void addClient(String pseudo) throws RemoteException{
		loggedPseudos.add(pseudo);
	}
	
	public void removeClient(String pseudo) throws RemoteException{
		loggedPseudos.remove(pseudo);
	}
	
	public void addSubject(String newSubjectName) throws RemoteException{
		subjectNames.add(newSubjectName);
		subjects.add(new Subject(newSubjectName));
		System.out.println(newSubjectName);
	}
	
	private Collection<String> subjectNames;
	private Collection<SubjectInterface> subjects;
	private Collection<String> loggedPseudos;
	private String chatName;

}