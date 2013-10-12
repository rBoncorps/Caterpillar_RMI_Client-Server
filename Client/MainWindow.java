package Client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

import Client.Controller.AddSubjectButtonListener;
import Client.Controller.MainWindowListener;
import Client.Controller.SubscribeButtonListener;
import Client.Controller.UpdateSubjectButtonListener;
import Server.ServerForumInterface;

public class MainWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -222755496583496451L;
	private ArrayList<String> availableSubject;
	private String pseudo;
	private ServerForumInterface server;
	private DefaultListModel subjectListModel;
	private JList subjectList;
	
	public MainWindow(ServerForumInterface server, String pseudo) {
		
		this.server = server;
		this.pseudo = pseudo;
		
		//Retrieve server Name
		String title = "Welcome to the chat";
		try {
			title = this.server.getServerName();
		}
		catch(Exception e) {
			System.out.println("Error retrieving server name");
		}
		this.setTitle(title);
		
		//Retrieve available subjects on server
		try {
			availableSubject = server.getAvailableSubjects();
		}
		catch(Exception e) {
			System.out.println("Impossible to retrieve server subjects");
		}
		
		JPanel mainPanel = new JPanel();
		this.setContentPane(mainPanel);
		GridBagLayout mainLayout = new GridBagLayout();
		mainPanel.setLayout(mainLayout);
		
		GridBagConstraints constr = new GridBagConstraints();
		
		JLabel lblSubject = new JLabel("Subject List");
		constr.gridx = 0;
		constr.gridy = 0;
		constr.gridheight = 1;
		constr.gridwidth = 1;
		mainPanel.add(lblSubject,constr);
		
		//Creating JList containing all available subjects
		subjectListModel = new DefaultListModel();
		Iterator<String> itSubject = availableSubject.iterator();
		while(itSubject.hasNext()) {
			subjectListModel.addElement(itSubject.next());
		}
		subjectList = new JList(subjectListModel);
		subjectList.setSize(50, 200);
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridx = 0;
		constr.gridy = 1;
		constr.gridheight = 3;
		constr.gridwidth = 1;
		mainPanel.add(subjectList,constr);
		
		JButton majSubjects = new JButton("Update Subject List");
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridx = 0;
		constr.gridy = 4;
		constr.gridheight = 1;
		constr.gridwidth = 1;
		mainPanel.add(majSubjects,constr);
		
		JButton subscribeButton = new JButton("Subscribe");
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridx = 1;
		constr.gridy = 1;
		constr.gridheight = 2;
		constr.gridwidth = 1;
		mainPanel.add(subscribeButton,constr);
		
		JTextField newSubject = new JTextField(3);
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridx = 0;
		constr.gridy = 6;
		constr.gridheight = 1;
		constr.gridwidth = 1;
		mainPanel.add(newSubject,constr);
		
		JButton addNewSubject = new JButton("Add new Subject");
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridx = 1;
		constr.gridy = 6;
		constr.gridheight = 1;
		constr.gridwidth = 1;
		mainPanel.add(addNewSubject,constr);
		
		this.setSize(300, 400);
		this.setVisible(true);
		
		
		MainWindowListener mainWinListener = new MainWindowListener(server,pseudo);
		this.addWindowListener(mainWinListener);
		
		UpdateSubjectButtonListener updateListener = new UpdateSubjectButtonListener(this, server);
		majSubjects.addActionListener(updateListener);
		
		AddSubjectButtonListener addSubjectListener = new AddSubjectButtonListener(this, server, newSubject);
		addNewSubject.addActionListener(addSubjectListener);
		
		SubscribeButtonListener subscribeSubject = new SubscribeButtonListener(this,server,subjectList);
		subscribeButton.addActionListener(subscribeSubject);
	}
	
	public void updateListModel(ArrayList<String> subjectStringList) {
		subjectListModel.clear();
		availableSubject = subjectStringList;
		Iterator<String> itSubject = availableSubject.iterator();
		while(itSubject.hasNext()) {
			subjectListModel.addElement(itSubject.next());
		}
		subjectList.setModel(subjectListModel);
	}
	
	public String getPseudo() {
		return pseudo;
	}
	
	
}
