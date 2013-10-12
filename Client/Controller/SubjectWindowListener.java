package Client.Controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;

import Client.ClientSubject;
import Server.ServerForumInterface;
import Server.SubjectInterface;

public class SubjectWindowListener implements WindowListener {

	private SubjectInterface subject;
	private ClientSubject clientSubject;
	
	public SubjectWindowListener(SubjectInterface subject, ClientSubject cliSub) {
		this.subject = subject;
		this.clientSubject = cliSub;
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		try {
			System.out.println("la");
			subject.unsubscribe(clientSubject);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("lksjslj");
		}

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

}
