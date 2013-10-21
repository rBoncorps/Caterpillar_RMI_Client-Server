package client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;

public class SubjectWindow extends JDialog implements SubjectWindowInterface {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8334889770068904652L;
	
	public SubjectWindow(JFrame parent, String subjectName) {
		super(parent,subjectName);
		this.name = subjectName;
		
		this.setSize(600, 500);
		JPanel subPanel = new JPanel();
		this.setContentPane(subPanel);
		GridBagLayout subLayout = new GridBagLayout();
		subPanel.setLayout(subLayout);
		
		GridBagConstraints constr = new GridBagConstraints();
		
		lblSubject = new JLabel(this.name);
		constr.gridx = 1;
		constr.gridy = 0;
		constr.gridheight = 1;
		constr.gridwidth = 3;
		constr.fill = GridBagConstraints.BOTH;
		subPanel.add(lblSubject,constr);
		
		subjectChat = new JTextArea(25,1);
		subjectChat.setLineWrap(true);
		subjectChat.setWrapStyleWord(true);
		subjectChat.setEditable(false);
		JScrollPane subjectChatScroll = new JScrollPane(subjectChat);
		subjectChatScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		subjectChatScroll.setSize(new Dimension(400,300));
		constr.gridx = 0;
		constr.gridy = 1;
		constr.gridheight = 25;
		constr.gridwidth = 3;
		constr.fill = GridBagConstraints.BOTH;
		subPanel.add(subjectChatScroll,constr);
		
		connectedListModel = new DefaultListModel();
		connectedJList = new JList(connectedListModel);
		connectedJList.setMinimumSize(new Dimension(100,300));
		constr.gridx = 3;
		constr.gridy = 1;
		constr.gridheight = 25;
		constr.gridwidth = 2;
		constr.fill = GridBagConstraints.BOTH;
		subPanel.add(connectedJList,constr);
		
		subjectChatMessage = new JTextField("Votre Message Ici !!!",20);
		subjectChatMessage.setMinimumSize(new Dimension(300, 30));
		constr.gridx = 0;
		constr.gridy = 26;
		constr.gridheight = 1;
		constr.gridwidth = 3;
		constr.fill = GridBagConstraints.BOTH;
		subPanel.add(subjectChatMessage,constr);
		
		subjectChatSendButton = new JButton("Send");
		constr.gridx = 4;
		constr.gridy = 26;
		constr.gridheight = 1;
		constr.gridwidth = 1;
		constr.fill = GridBagConstraints.BOTH;
		subPanel.add(subjectChatSendButton,constr);
		
		this.setVisible(true);
	}

	@Override
	public void display(String message) {
		Calendar calendar = new GregorianCalendar();
		String msg = "[" + calendar.get(Calendar.HOUR_OF_DAY)  + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + "]" + message;
		subjectChat.append(msg + "\n");
	}
	
	public void updateConnected(Collection<String> newConnectedList) {
		connectedListModel.clear();
		Iterator<String> it = newConnectedList.iterator();
		while(it.hasNext()) {
			connectedListModel.addElement(it.next());
		}
		this.updateJList();
	}
	
	@Override
	public JButton getSendButton() {
		return subjectChatSendButton;
	}
	@Override
	public JTextField getInputField() {
		return subjectChatMessage;
	}
	
	@Override
	public String getWindowName() {
		return name;
	}
	
	public JLabel getLblSubject() {
		return lblSubject;
	}
	
	public ListModel getConnectedListModel() {
		return connectedListModel;
	}
	
	public JList getConnectedJList() {
		return connectedJList;
	}
	
	public void displayError(String title, String message) {
		JOptionPane.showMessageDialog(this,
			    message,
			    title,
			    JOptionPane.ERROR_MESSAGE);
	}
	
	private void updateJList() {
		this.connectedJList.setModel(connectedListModel);
	}
	
	public void addWindowListener(WindowListener windowListener) {
		super.addWindowListener(windowListener);
	}
	
	private String name;
	private JTextArea subjectChat;
	private JTextField subjectChatMessage;
	private JButton subjectChatSendButton;
	private DefaultListModel connectedListModel;
	private JList connectedJList;
	private JLabel lblSubject;
	

}
