package client;

import java.rmi.Naming;

import javax.swing.UIManager;

import client.controller.ConfigWindowController;
import client.controller.MainWindowController;
import client.controller.MainWindowControllerInterface;
import server.ServerForumInterface;

public class AppClient {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		}catch(Exception e) {
			System.out.println("Style not found");
		}
		try {
			ClientConfigWindowInterface configWin = new ClientConfigWindow("Tabernac Forum Client");
			ConfigWindowController configController = new ConfigWindowController(configWin);
			configWin.setVisible(true);
			/*ServerForumInterface forumServer = (ServerForumInterface)
			Naming.lookup("//localhost:8090/server");
			ClientMainWindowInterface window = new ClientMainWindow("Tabarnac Forum Client");
			MainWindowControllerInterface windowController = new MainWindowController(window, forumServer);
			windowController.displaySubjectSelection();*/
		} catch(Exception e){
			System.out.println ("Forum server is not accessible, please try again later");
		}
	}

}
