package Client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Client.Controller.ConnectButtonListener;
import Client.Controller.ExitButtonIntroListener;

public class IntroWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5044564067320876255L;

	public IntroWindow() {
		
		super("Caterpillar_RMI_Chat");
		
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
		
		JTextField adresseField = new JTextField("//localhost:8090/server");
		constr.fill = GridBagConstraints.BOTH;
		constr.weightx = 0.7;
		constr.gridx = 1;
		constr.gridy = 0;
		introPane.add(adresseField,constr);
		
		// TODO add random generated pseudo 
		// to avoid having the same pseudo that another person
		JLabel pseudoLabel = new JLabel("Enter your pseudo : ");
		constr.fill = GridBagConstraints.NONE;
		constr.weightx = 0.3;
		constr.gridx = 0;
		constr.gridy = 1;
		introPane.add(pseudoLabel,constr);
		
		JTextField pseudoField = new JTextField("anonymous" + ((int)(Math.random()*(9999 - 1111) + 1111)));
		constr.fill = GridBagConstraints.BOTH;
		constr.gridx = 1;
		constr.gridy = 1;
		constr.weightx = 0.7;
		introPane.add(pseudoField,constr);
		
		JButton connectButton = new JButton("Connect");
		constr.fill = GridBagConstraints.NONE;
		constr.gridx = 0;
		constr.gridy = 3;
		constr.weightx = 0.5;
		constr.anchor = GridBagConstraints.PAGE_END;
		introPane.add(connectButton,constr);
		
		JButton exitButton = new JButton("Exit");
		constr.fill = GridBagConstraints.NONE;
		constr.gridx = 1;
		constr.gridy = 3;
		constr.weightx = 0.5;
		constr.anchor = GridBagConstraints.PAGE_END;
		introPane.add(exitButton,constr);
		
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		// Onclick -> System.exit(0)
		ExitButtonIntroListener exitButListener = new ExitButtonIntroListener();
		exitButton.addActionListener(exitButListener);
		
		ConnectButtonListener connectListener = new ConnectButtonListener(this,adresseField,pseudoField);
		connectButton.addActionListener(connectListener);
		
	}
}
