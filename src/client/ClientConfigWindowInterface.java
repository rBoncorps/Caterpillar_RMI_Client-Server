package client;

import javax.swing.JButton;
import javax.swing.JTextField;

public interface ClientConfigWindowInterface {
	public String getServerAdresse();
	public String getPseudo();
	public JButton getConnectButton();
	public JButton getExitButton();
	
	public void setVisible(boolean isVisible);

}
