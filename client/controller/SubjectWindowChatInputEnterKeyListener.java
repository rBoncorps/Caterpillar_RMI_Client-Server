package client.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class SubjectWindowChatInputEnterKeyListener implements KeyListener {

	public SubjectWindowChatInputEnterKeyListener(SubjectWindowControllerInterface subjectController) {
		this.subjectController = subjectController;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			subjectController.broadcast();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	private SubjectWindowControllerInterface subjectController;
}
