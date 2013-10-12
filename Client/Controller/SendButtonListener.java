package Client.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JTextField;

import Client.ClientSubjectWindow;

public class SendButtonListener implements ActionListener{

	private JTextField txtToSend;
	private ClientSubjectWindow cliSubWin;
	
	public SendButtonListener(JTextField txtField,ClientSubjectWindow cliSubWin) {
		txtToSend = txtField;
		this.cliSubWin = cliSubWin;
	}
	public void actionPerformed(ActionEvent arg0) {
		String txt = txtToSend.getText();
		System.out.println("txt " + txt);
		try {
			cliSubWin.sendServer(txt);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
