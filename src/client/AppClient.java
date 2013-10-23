package client;

import javax.swing.UIManager;

import client.controller.ConfigWindowController;

public class AppClient {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		}catch(Exception e) {
			System.out.println("App style not found, launching with default style");
		}
		try {
			new ConfigWindowController();
		} catch(Exception e){
			System.out.println ("Forum server is not accessible, please try again later");
		}
	}

}
