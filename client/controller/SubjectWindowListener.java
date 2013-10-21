package client.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SubjectWindowListener implements WindowListener {

	private SubjectWindowControllerInterface subjectWindowController;
	
	public SubjectWindowListener(SubjectWindowControllerInterface subjectWindowController) {
		this.subjectWindowController = subjectWindowController;
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		this.subjectWindowController.unsubscribeClient();

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

}
