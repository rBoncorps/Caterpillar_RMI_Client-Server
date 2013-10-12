package Client.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Client.MainWindow;
import Server.ServerForumInterface;

public class AddSubjectButtonListener implements ActionListener {

	private MainWindow window;
	private ServerForumInterface server;
	private JTextField newSubjectEdit;
	
	public AddSubjectButtonListener(MainWindow window,ServerForumInterface server, JTextField newSubjectEdit) {
		this.window = window;
		this.server = server;
		this.newSubjectEdit = newSubjectEdit;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String newSubject = newSubjectEdit.getText();
		newSubjectEdit.setText("");
		try {
			if(server.addNewSubject(newSubject)) {
				
				window.updateListModel(server.getAvailableSubjects());
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(window, "Error - Impossible to add New Subject");
		}
	}

}
