package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowSubscribeButtonListener implements ActionListener {

	public MainWindowSubscribeButtonListener(MainWindowControllerInterface mainWindowController) {
		this.mainWindowController = mainWindowController;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.mainWindowController.displaySelectedSubject();
	}
	
	private MainWindowControllerInterface mainWindowController; 

}
