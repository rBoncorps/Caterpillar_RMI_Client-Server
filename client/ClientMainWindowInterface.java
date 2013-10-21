package client;

import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

public interface ClientMainWindowInterface {
	public void displaySubjectList(Collection<String> subjectList);
	public SubjectWindowInterface displaySubjectWindow(String subjectName);
	public JList getsubjectJList();
	public JTextField getNewSubjectField();
	public JButton getSubscribeButton();
	public JButton getAddSubjectButton();
	public JButton getUpdateSubjectListButton();
	public void displayError(String title, String message);
}
