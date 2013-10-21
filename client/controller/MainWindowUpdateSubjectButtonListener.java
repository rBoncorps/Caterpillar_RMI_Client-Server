package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowUpdateSubjectButtonListener implements ActionListener{

	public MainWindowUpdateSubjectButtonListener(MainWindowController mainWindowController) {
		this.mainWindowController = mainWindowController;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.mainWindowController.displaySubjectSelection();
		
	}
	
	private MainWindowController mainWindowController;

}
