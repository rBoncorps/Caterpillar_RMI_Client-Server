package Client.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import Client.MainWindow;
import Server.ServerForumInterface;

public class UpdateSubjectButtonListener implements ActionListener {

	private MainWindow window;
	private ServerForumInterface server;
	
	public UpdateSubjectButtonListener(MainWindow window,ServerForumInterface server) {
		this.window = window;
		this.server = server;
	}
	
	public void actionPerformed(ActionEvent arg0){
		//Retrieve updated subjectList
		try {
			ArrayList<String> subList = server.getAvailableSubjects();
			window.updateListModel(subList);
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(window, "Impossible to update subject list");
		}
		
	}

}
