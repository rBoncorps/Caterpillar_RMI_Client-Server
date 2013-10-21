package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigWindowConnectListener implements ActionListener {

	public ConfigWindowConnectListener(ConfigWindowControllerInterface configController) {
		this.configWinController = configController;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.configWinController.connectToServer();
	}
	
	private ConfigWindowControllerInterface configWinController;

}
