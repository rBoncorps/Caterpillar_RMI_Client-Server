package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import provider.ProviderInterface;

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
		subjectProviderMap = new HashMap<String,ProviderInterface>();
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
		// Check provided subjects
		ProviderInterface prov = subjectProviderMap.get(title);
		if (prov == null) {
			System.out.println("Error : Subject can't be found on provider");
		}
		else {
			return prov.getSubject();
		}
		return null;
	}
	
	@Override
	public String getChatName() throws RemoteException {
		return chatName;
	}
	
	@Override
	public boolean addClient(String pseudo) throws RemoteException {
		boolean correctPseudo = checkPseudo(pseudo);
		if(correctPseudo) {
			loggedPseudos.add(pseudo);
		}
		return correctPseudo;
	}
	
	@Override
	public void removeClient(String pseudo) throws RemoteException{
		loggedPseudos.remove(pseudo);
	}
	
	@Override
	public boolean addSubject(String newSubjectName) throws RemoteException {
		if(subjectNames.contains(newSubjectName)) {
			return false;
		}
		else {
			subjectNames.add(newSubjectName);
			subjects.add(new Subject(newSubjectName));
			System.out.println("Subject " + newSubjectName + " created");
			return true;
		}
	}
	
	private boolean checkPseudo(String name) {
		Iterator<String> it = loggedPseudos.iterator();
		while(it.hasNext()) {
			String tmp = it.next();
			if(tmp.equals(name)) {
				return false;
			}
		}
		return true;
	}
	
	public void registerProvider(ProviderInterface provider) throws RemoteException {
		String subject = provider.getSubjectName();
		this.subjectNames.add(subject);
		this.subjectProviderMap.put(subject, provider);
	}
	
	private Collection<String> subjectNames;
	private Collection<SubjectInterface> subjects;
	private Collection<String> loggedPseudos;
	private HashMap<String, ProviderInterface> subjectProviderMap;
	private String chatName;

}
