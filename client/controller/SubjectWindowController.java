package client.controller;

import java.rmi.RemoteException;

import client.SubjectWindowInterface;
import client.remote.RemoteSubjectWindow;
import client.remote.RemoteSubjectWindowInterface;
import server.SubjectInterface;

public class SubjectWindowController implements SubjectWindowControllerInterface {

	public SubjectWindowController(SubjectWindowInterface subjectWindow, SubjectInterface remoteSubject,String pseudo) {
		this.subjectWindow = subjectWindow;
		this.remoteSubject = remoteSubject;
		this.pseudo = pseudo;
		
		try {
			this.remoteWindow = new RemoteSubjectWindow(this.subjectWindow);
			this.remoteSubject.register(remoteWindow,pseudo);
		}catch(RemoteException e) {
			e.printStackTrace();
			System.out.println("SubjectWindowController :: constructor : RemoteException");
		}
		
		subjectWindow.getSendButton().addActionListener(new SubjectWindowSendMessageListener(this));
		subjectWindow.getInputField().addFocusListener(new SubjectWindowChatInputFocusListener());
		subjectWindow.getInputField().addKeyListener(new SubjectWindowChatInputEnterKeyListener(this));
		subjectWindow.addWindowListener(new SubjectWindowListener(this));
	}
	
	@Override
	public void broadcast() {
		String message = this.subjectWindow.getInputField().getText();
		if(!message.equals("") && !message.equals("Enter your message here")) {
			try {
				remoteSubject.broadcast(this.pseudo + " : " + message);
				if(subjectWindow.getInputField().hasFocus()) {
					subjectWindow.getInputField().setText("");
				}
				else {
					subjectWindow.getInputField().setText("Enter your message here");
				}
			}catch(RemoteException e) {
				System.out.println("SubjectWindowController :: broadcast : RemoteException");
			}
		}
	}
	
	public void unsubscribeClient() {
		System.out.println("passe par la");
		try {
			this.remoteSubject.unregister(this.remoteWindow, this.pseudo);
		}
		catch(RemoteException e) {
			System.out.println("SubjectWindowController :: unsubscribe : RemoteException");
		}
	}
	
	private SubjectWindowInterface subjectWindow;
	private SubjectInterface remoteSubject;
	private RemoteSubjectWindowInterface remoteWindow;
	private String pseudo;

}
