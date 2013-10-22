package client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;

public class ClientMainWindow extends JFrame implements ClientMainWindowInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6066690644949989577L;

	public ClientMainWindow(String title) {
		super(title);
		
		mainPanel = new JPanel();
		this.setContentPane(mainPanel);
		GridBagLayout mainLayout = new GridBagLayout();
		mainPanel.setLayout(mainLayout);
		
		GridBagConstraints constr = new GridBagConstraints();
		
		JLabel lblSubject = new JLabel("Subject List");
		constr.gridx = 0;
		constr.gridy = 0;
		constr.gridheight = 1;
		constr.gridwidth = 1;
		mainPanel.add(lblSubject,constr);
		
		//Creating JList containing all available subjects
		subjectListModel = new DefaultListModel();
		subjectJList = new JList(subjectListModel);
		subjectJList.setMinimumSize(new Dimension(100,350));
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridx = 0;
		constr.gridy = 1;
		constr.gridheight = 3;
		constr.gridwidth = 1;
		mainPanel.add(subjectJList,constr);
		
		majSubjectsButton = new JButton("Update Subject List");
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridx = 0;
		constr.gridy = 4;
		constr.gridheight = 1;
		constr.gridwidth = 1;
		mainPanel.add(majSubjectsButton,constr);
		
		subscribeButton = new JButton("Subscribe");
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridx = 1;
		constr.gridy = 1;
		constr.gridheight = 2;
		constr.gridwidth = 1;
		mainPanel.add(subscribeButton,constr);
		
		newSubjectField = new JTextField(3);
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridx = 0;
		constr.gridy = 6;
		constr.gridheight = 1;
		constr.gridwidth = 1;
		mainPanel.add(newSubjectField,constr);
		
		addNewSubjectButton = new JButton("Add new Subject");
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridx = 1;
		constr.gridy = 6;
		constr.gridheight = 1;
		constr.gridwidth = 1;
		mainPanel.add(addNewSubjectButton,constr);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(300,450));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	@Override
	public void displaySubjectList(Collection<String> subjectList) {
		subjectListModel.clear();
		Iterator<String> itSub = subjectList.iterator();
		while(itSub.hasNext()) {
			subjectListModel.addElement(itSub.next());
			//System.out.println(itSub.next());
		}
		this.subjectJList.setModel(subjectListModel);
	}
	
	@Override
	public SubjectWindowInterface displaySubjectWindow(String subjectName) {
		SubjectWindowInterface subjectWindow = new SubjectWindow(this, subjectName);
		return subjectWindow;
	}
	
	public JList getsubjectJList() {
		return subjectJList;
	}
	
	public JTextField getNewSubjectField() {
		return newSubjectField;
	}
	
	public JButton getSubscribeButton() {
		return subscribeButton;
	}
	
	public JButton getAddSubjectButton() {
		return addNewSubjectButton;
	}
	
	public JButton getUpdateSubjectListButton() {
		return majSubjectsButton;
	}
	
	@Override
	public void displayError(String title, String message) {
		JOptionPane.showMessageDialog(this,
			    message,
			    title,
			    JOptionPane.ERROR_MESSAGE);
	}
	
	private JPanel mainPanel;
	private DefaultListModel subjectListModel;
	private JList subjectJList;
	private JTextField newSubjectField;
	private JButton majSubjectsButton;
	private JButton subscribeButton;
	private JButton addNewSubjectButton;
}
