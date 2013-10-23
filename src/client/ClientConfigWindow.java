package client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientConfigWindow extends JFrame implements ClientConfigWindowInterface {
	
	public ClientConfigWindow(String title) {
		
		super(title);
		
		JPanel introPane = new JPanel();
		this.setContentPane(introPane);
		this.setSize(400,150);
		
		GridBagLayout introLayout = new GridBagLayout();
		introPane.setLayout(introLayout);
		
		GridBagConstraints constr = new GridBagConstraints();
		
		JLabel adrLabel = new JLabel("Enter serveur adress : ");
		constr.fill = GridBagConstraints.NONE;
		constr.weightx = 0.3;
		constr.gridx = 0;
		constr.gridy = 0;
		introPane.add(adrLabel,constr);
		
		adresseField = new JTextField("//localhost:8090/server");
		constr.fill = GridBagConstraints.BOTH;
		constr.weightx = 0.7;
		constr.gridx = 1;
		constr.gridy = 0;
		introPane.add(adresseField,constr);
		
		JLabel pseudoLabel = new JLabel("Enter your pseudo : ");
		constr.fill = GridBagConstraints.NONE;
		constr.weightx = 0.3;
		constr.gridx = 0;
		constr.gridy = 1;
		introPane.add(pseudoLabel,constr);
		
		pseudoField = new JTextField("anonymous" + ((int)(Math.random()*(9999 - 1111) + 1111)));
		constr.fill = GridBagConstraints.BOTH;
		constr.gridx = 1;
		constr.gridy = 1;
		constr.weightx = 0.7;
		introPane.add(pseudoField,constr);
		
		connectButton = new JButton("Connect");
		constr.fill = GridBagConstraints.NONE;
		constr.gridx = 0;
		constr.gridy = 3;
		constr.weightx = 0.5;
		constr.anchor = GridBagConstraints.PAGE_END;
		introPane.add(connectButton,constr);
		
		exitButton = new JButton("Exit");
		constr.fill = GridBagConstraints.NONE;
		constr.gridx = 1;
		constr.gridy = 3;
		constr.weightx = 0.5;
		constr.anchor = GridBagConstraints.PAGE_END;
		introPane.add(exitButton,constr);
		
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	@Override
	public String getServerAdresse() {
		return adresseField.getText();
	}

	@Override
	public String getPseudo() {
		return pseudoField.getText();
	}
	
	@Override
	public JButton getConnectButton() {
		return connectButton;
	}
	
	@Override
	public JButton getExitButton() {
		return exitButton;
	}
	
	@Override
	public void setVisible(boolean isVisible) {
		super.setVisible(isVisible);
	}
	
	@Override
	public void displayError(String title, String message) {
		JOptionPane.showMessageDialog(this,
			    message,
			    title,
			    JOptionPane.ERROR_MESSAGE);
	}
	
	private JTextField adresseField;
	private JTextField pseudoField;
	private JButton connectButton;
	private JButton exitButton;
}
