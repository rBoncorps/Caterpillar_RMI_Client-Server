package Client.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JList;
import javax.swing.JOptionPane;

import Client.MainWindow;
import Client.ClientSubjectWindow;
import Server.ServerForumInterface;
import Server.SubjectInterface;

public class SubscribeButtonListener implements ActionListener {

	private MainWindow window;
	private ServerForumInterface server;
	private JList subList;
	
	public SubscribeButtonListener(MainWindow window,ServerForumInterface server,JList subList) {
		this.window = window;
		this.server = server;
		this.subList = subList;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		String subject = (String) subList.getSelectedValue();
		SubjectInterface subInt;
		try {
			subInt = server.getSubject(subject);
			new ClientSubjectWindow(server, subInt,window.getPseudo());
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(window, "Error - Impossible to subscribe");
		}
		
	}

}
