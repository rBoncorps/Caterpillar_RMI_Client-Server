package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

import Client.ClientDisplayInterface;

public class Subject extends UnicastRemoteObject implements SubjectInterface{

	private String subName = "";
	private ArrayList<ClientDisplayInterface> subscribers;
	private ArrayList<String> subscribersPseudo;
	
	public Subject(String name) throws RemoteException{
		subName = name;
		subscribers = new ArrayList<ClientDisplayInterface>();
		subscribersPseudo = new ArrayList<String>();
	}
	
	public String getName() {
		return subName;
	}
	
	public void subscribe(ClientDisplayInterface cliDisplay) throws RemoteException {
		subscribers.add(cliDisplay);
		subscribersPseudo.add(cliDisplay.getPseudo());
		
		Iterator<ClientDisplayInterface> it = subscribers.iterator();
		while(it.hasNext()) {
			it.next().displayConnected(subscribersPseudo);
		}
	}

	public void unsubscribe(ClientDisplayInterface cliDisplay) throws RemoteException {
		subscribers.remove(cliDisplay);
		
		subscribersPseudo.remove(cliDisplay.getPseudo());
		System.out.println("ClientSubject Removed");
		
		Iterator<ClientDisplayInterface> it = subscribers.iterator();
		while(it.hasNext()) {
			it.next().displayConnected(subscribersPseudo);
		}
	}

	public void broadcast(String message) throws RemoteException {
		Iterator<ClientDisplayInterface> it = subscribers.iterator();
		while(it.hasNext()) {
			it.next().display(message);
		}
	}

}
