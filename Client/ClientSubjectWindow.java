package Client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.event.CaretListener;

import Client.Controller.SendButtonListener;
import Client.Controller.SubjectWindowChatListener;
import Client.Controller.SubjectWindowListener;
import Server.ServerForumInterface;
import Server.SubjectInterface;

public class ClientSubjectWindow extends JDialog implements Serializable{
	
	private JTextArea subjectChat;
	private JTextField textChat;
	private ServerForumInterface server;
	private SubjectInterface subject;
	private ClientSubject cliSub;
	private String pseudo;
	private DefaultListModel connectedListModel;
	private JList connectedList;
	
	public ClientSubjectWindow(ServerForumInterface server, SubjectInterface subInt, String pseudo) {
		
		this.server = server;
		this.pseudo = pseudo;
		this.subject = subInt;

		//Affichage
		this.setSize(350, 500);
		JPanel subPanel = new JPanel();
		this.setContentPane(subPanel);
		GridBagLayout subLayout = new GridBagLayout();
		subPanel.setLayout(subLayout);
		
		GridBagConstraints constr = new GridBagConstraints();
		
		JLabel lblSubject = new JLabel("Subject");
		try {
			lblSubject.setText(subInt.getName());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		subjectChatScroll.setSize(new Dimension(200,100));
		constr.gridx = 0;
		constr.gridy = 1;
		constr.gridheight = 25;
		constr.gridwidth = 3;
		constr.fill = GridBagConstraints.BOTH;
		subPanel.add(subjectChatScroll,constr);
		
		connectedListModel = new DefaultListModel();
		connectedList = new JList();
		constr.gridx = 3;
		constr.gridy = 1;
		constr.gridheight = 25;
		constr.gridwidth = 2;
		constr.fill = GridBagConstraints.BOTH;
		subPanel.add(connectedList,constr);
		
		textChat = new JTextField("Votre Message Ici !!!",20);
		textChat.setSize(200, 30);
		constr.gridx = 0;
		constr.gridy = 26;
		constr.gridheight = 1;
		constr.gridwidth = 3;
		constr.fill = GridBagConstraints.BOTH;
		subPanel.add(textChat,constr);
		
		JButton sendButton = new JButton("Send");
		constr.gridx = 1;
		constr.gridy = 27;
		constr.gridheight = 1;
		constr.gridwidth = 1;
		constr.fill = GridBagConstraints.BOTH;
		subPanel.add(sendButton,constr);
		
		SubjectWindowChatListener chatListener = new SubjectWindowChatListener(this);
		textChat.addKeyListener(chatListener);
		
		SendButtonListener subButtonListener = new SendButtonListener(textChat,this);
		sendButton.addActionListener(subButtonListener);
		
		this.setVisible(true);
		
		try {
			cliSub = new ClientSubject(subjectChat,connectedList,pseudo);
			SubjectWindowListener subWinListener = new SubjectWindowListener(subject,cliSub);
			this.addWindowListener(subWinListener);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			System.out.println("fail to create clientSubject");
		}
		try {
			subject.subscribe(cliSub);
		}
		catch(Exception e) {
			System.out.println("Fail to Subscribe");
			e.printStackTrace();
		}
	}
	
	public void sendServer(String message) throws RemoteException {
		Calendar calendar = new GregorianCalendar();
		String msg = "[" + calendar.get(Calendar.HOUR_OF_DAY)  + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + "]" + pseudo + " : " + message;
		subject.broadcast(msg);
	}
}
