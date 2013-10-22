package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowAddSubjectButtonListener implements ActionListener{

	public MainWindowAddSubjectButtonListener(MainWindowControllerInterface mainWindowController) {
		this.mainWindowController = mainWindowController;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		this.mainWindowController.addNewSubject();
	}
	
	private MainWindowControllerInterface mainWindowController;

}
