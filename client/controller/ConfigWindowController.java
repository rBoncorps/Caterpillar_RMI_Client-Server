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
			//essai connection au serveur avant création
			ServerForumInterface forumServer = (ServerForumInterface) 
					Naming.lookup(adr);
			try {
				//Verification que le pseudo n'est pas deja utilisé
				boolean pseudoOK = forumServer.checkPseudo(pseudo);
				System.out.println("pseudoOK : " + pseudoOK);
				if (pseudoOK == false) {
					JOptionPane.showMessageDialog((ClientConfigWindow)this.configWindow, pseudo + " : this pseudo is already in use");
				}
				else {
					forumServer.addClient(pseudo);
					this.configWindow.setVisible(false);
					ClientMainWindowInterface window = new ClientMainWindow(forumServer.getChatName());
					MainWindowControllerInterface windowController = new MainWindowController(window, forumServer, pseudo);
					windowController.displaySubjectSelection();
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
