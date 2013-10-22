package client.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JButton;

import client.ClientMainWindowInterface;
import client.SubjectWindowInterface;
import server.ServerForumInterface;
import server.SubjectInterface;

public class MainWindowController implements MainWindowControllerInterface {
	
	public MainWindowController(ClientMainWindowInterface clientMainWindow, ServerForumInterface remoteServer, String pseudo) {
		this.clientMainWindow = clientMainWindow;
		this.remoteServer = remoteServer;
		this.pseudo = pseudo;
		this.displayedSubjects = new ArrayList<String>();
		
		this.clientMainWindow.getAddSubjectButton().addActionListener(new MainWindowAddSubjectButtonListener(this));
		this.clientMainWindow.getSubscribeButton().addActionListener(new MainWindowSubscribeButtonListener(this));
		this.clientMainWindow.getUpdateSubjectListButton().addActionListener(new MainWindowUpdateSubjectButtonListener(this));
	}
	
	public void addNewSubject() {
		String newSubjectName = this.clientMainWindow.getNewSubjectField().getText();
		try {
			this.remoteServer.addSubject(newSubjectName);
		}
		catch(RemoteException e) {
			this.clientMainWindow.displayError("Connection lost","Connection with the server was lost");
		}
	}
	
	@Override
	public void displaySubjectSelection() {
		try {
			Collection<String> availableSubject = remoteServer.getAvailableSubjects();
			this.clientMainWindow.displaySubjectList(availableSubject);
		}catch(RemoteException e) {
			clientMainWindow.displayError("Connection lost","Connection with the server was lost");
		}
	}
	
	public void displaySelectedSubject() {
		String subjectName = (String)this.clientMainWindow.getsubjectJList().getSelectedValue();
		try {
			SubjectInterface remoteSubject = remoteServer.getSubject(subjectName);
			SubjectWindowInterface subjectWindow = clientMainWindow.displaySubjectWindow(subjectName);
			SubjectWindowControllerInterface subjectWindowController = new SubjectWindowController(subjectWindow, remoteSubject,pseudo);
			displayedSubjects.add(subjectName);
		}catch(RemoteException e) {
			clientMainWindow.displayError("Connection lost", "Connection with the server was lost");
		}
	}
	
	private ClientMainWindowInterface clientMainWindow;
	private ServerForumInterface remoteServer;
	private String pseudo;
	private Collection<String> displayedSubjects;

}