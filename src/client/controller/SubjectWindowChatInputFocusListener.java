package client.controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class SubjectWindowChatInputFocusListener implements FocusListener {

	@Override
	public void focusGained(FocusEvent arg0) {
		JTextField chatInput = (JTextField)arg0.getSource();
		chatInput.setText("");
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		JTextField chatInput = (JTextField)arg0.getSource();
		if(chatInput.getText() == "") {
			chatInput.setText("Enter your message here");
		}
	}

	
}
