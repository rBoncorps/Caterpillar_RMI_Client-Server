package Client.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;

import javax.swing.JTextField;

import Client.ClientSubjectWindow;

public class SubjectWindowChatListener implements KeyListener {

	private ClientSubjectWindow cliSubWin;
	
	public SubjectWindowChatListener(ClientSubjectWindow cliSubWin) {
		this.cliSubWin = cliSubWin;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			JTextField chatInput = (JTextField)arg0.getSource();
			try {
				cliSubWin.sendServer(chatInput.getText());
				chatInput.setText("");;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
