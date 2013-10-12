package Client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;

public class ClientSubject extends UnicastRemoteObject implements ClientDisplayInterface {
	
	private JTextArea subjectText;
	private String pseudo;
	private JList connectedList;
	
	public ClientSubject(JTextArea subjectText,JList connectedList,String pseudo) throws RemoteException{
		this.subjectText = subjectText;
		this.pseudo = pseudo;
		this.connectedList = connectedList;
	}

	public void display(String message) throws RemoteException {
		subjectText.append(message + "\n");
	}
	
	
	public String getPseudo() throws RemoteException {
		return pseudo;
	}

	public void displayConnected(ArrayList<String> connected) throws RemoteException {
		DefaultListModel listModel = new DefaultListModel();
		Iterator<String> it = connected.iterator();
		while(it.hasNext()) {
			listModel.addElement(it.next());
		}
		this.connectedList.setModel(listModel);
	}

}
