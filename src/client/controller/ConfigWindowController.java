package client.controller;

import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import client.ClientConfigWindow;
import client.ClientConfigWindowInterface;
import client.ClientMainWindow;
import client.ClientMainWindowInterface;
import server.ServerForumInterface;

public class ConfigWindowController implements ConfigWindowControllerInterface{
	
	public ConfigWindowController(ClientConfigWindowInterface configWindow) {
		this.configWindow = configWindow;
		this.configWindow.getConnectButton().addActionListener(
					new ConfigWindowConnectListener(this));
		this.configWindow.getExitButton().addActionListener(new ConfigWindowExitListener());
	}
	
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
				 * Check if the given pseudo is free on the server.
				 */
				boolean pseudoOk = serverForum.checkPseudo(pseudo);
				if(pseudoOk) {
					serverForum.addClient(pseudo);
					this.configWindow.setVisible(false);
					ClientMainWindowInterface window = new ClientMainWindow(serverForum.getChatName());
					MainWindowControllerInterface windowController = new MainWindowController(window, serverForum, pseudo);
					windowController.displaySubjectSelection();
				}
				else {
					JOptionPane.showMessageDialog((ClientConfigWindow)this.configWindow, pseudo + " : this pseudo is already in use");
				}
			} 
			catch (RemoteException e) {
				System.out.println("Error pseudo");
			}
		} 
		catch(Exception e){
			JOptionPane.showMessageDialog((ClientConfigWindow)this.configWindow, "Server is not reachable");
		}	
	}

	private ClientConfigWindowInterface configWindow;
}
