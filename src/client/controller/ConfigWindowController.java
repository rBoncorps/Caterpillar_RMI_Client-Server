package client.controller;

import java.rmi.Naming;
import java.rmi.RemoteException;

import server.ServerForumInterface;
import client.ClientConfigWindow;
import client.ClientConfigWindowInterface;
import client.ClientMainWindow;
import client.ClientMainWindowInterface;

public class ConfigWindowController implements ConfigWindowControllerInterface {
	
	public ConfigWindowController() {
		this.configWindow = new ClientConfigWindow("Tabernac Forum Client");
		this.configWindow.setVisible(true);
		
		this.configWindow.getConnectButton().addActionListener(
					new ConfigWindowConnectListener(this));
		this.configWindow.getExitButton().addActionListener(new ConfigWindowExitListener());
	}
	
	@Override
	public void connectToServer() {
		String adr = this.configWindow.getServerAdresse();
		String pseudo = this.configWindow.getPseudo();
		
		try {
			/*
			 * Try to connect to the server.
			 */
			ServerForumInterface serverForum = (ServerForumInterface) 
					Naming.lookup(adr);
			try {
				/*
				 * Try to add the client to the server.
				 */
				boolean clientAdded = serverForum.addClient(pseudo);
				if(clientAdded) {
					this.configWindow.setVisible(false);
					MainWindowControllerInterface mainWindowController = new MainWindowController(serverForum.getChatName(), serverForum, pseudo);
					mainWindowController.displaySubjectSelection();
				}
				else {
					configWindow.displayError("Invalid pseudo", "Pseudo " + pseudo + " is already in use");
				}
			} 
			catch (RemoteException e) {
				System.out.println("Error pseudo");
			}
		} 
		catch(Exception e){
			configWindow.displayError("Server not reachable", "Server is not reachable");
		}	
	}

	private ClientConfigWindowInterface configWindow;
}
