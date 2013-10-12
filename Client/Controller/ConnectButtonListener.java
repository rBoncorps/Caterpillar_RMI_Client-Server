package Client.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Client.MainWindow;
import Client.IntroWindow;
import Server.ServerForumInterface;

public class ConnectButtonListener implements ActionListener{
	
	JTextField adrF;
	JTextField pseudoF;
	IntroWindow window;
	
	public ConnectButtonListener(IntroWindow window,JTextField adresseF, JTextField pseudoF) {
		this.adrF = adresseF;
		this.pseudoF = pseudoF;
		this.window = window;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String adr = adrF.getText();
		String pseudo = pseudoF.getText();
		
		
		try {
			//essai connection au serveur avant création
			ServerForumInterface forumServer = (ServerForumInterface) 
					Naming.lookup(adr);
			try {
				//Verification que le pseudo n'est pas deja utilisé
				boolean pseudoOK = forumServer.checkPseudo(pseudo);
				System.out.println("pseudoOK : " + pseudoOK);
				if (pseudoOK == false) {
					JOptionPane.showMessageDialog(window, pseudo + " : this pseudo is already in use");
				}
				else {
					forumServer.addClient(pseudo);
					new MainWindow(forumServer,pseudo);
					window.setVisible(false);
				}
			} 
			catch (RemoteException e) {
				System.out.println("Error pseudo");
			}
		} 
		catch(Exception e){
			JOptionPane.showMessageDialog(window, "Server is not reachable");
		}	
	}
}
