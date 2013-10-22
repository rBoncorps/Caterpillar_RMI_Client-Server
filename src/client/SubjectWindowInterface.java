package client;

import java.awt.event.WindowListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListModel;

public interface SubjectWindowInterface{
	public void display(String message);
	public void displayError(String title, String message);
	public void updateConnected(Collection<String> newConnectedList);
	public JButton getSendButton();
	public JTextField getInputField();
	public String getWindowName();
	public JLabel getLblSubject();
	public ListModel getConnectedListModel();
	public JList getConnectedJList();
	public void addWindowListener(WindowListener windowListener);
}
