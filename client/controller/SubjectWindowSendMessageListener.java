package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class SubjectWindowSendMessageListener implements ActionListener {

	public SubjectWindowSendMessageListener(SubjectWindowController subjectController) {
		this.subjectController = subjectController;
	}
	
	@Override
	public void actionPerformed(ActionEvent a) {
		subjectController.broadcast();
	}
	
	private SubjectWindowController subjectController;
}
