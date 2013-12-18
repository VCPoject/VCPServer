package serverGui;

import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main_Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnExit;
	private JButton btnStartServer;
	private JButton btnStopServer;
	private JLabel lblNumberOfConnections;
	private JTextField textFieldNumberOfConnections;

	public Main_Panel() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(785, 575);
		setBackground(SystemColor.activeCaption);
		setLayout(null);
		
		JLabel lblServer = new JLabel("VCP Server");
		lblServer.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblServer.setBounds(326, 11, 132, 29);
		add(lblServer);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(10, 519, 93, 35);
		add(btnExit);
		
		btnStartServer = new JButton("Start Server");
		btnStartServer.setBounds(318, 99, 148, 102);
		add(btnStartServer);
		
		btnStopServer = new JButton("Stop Server");
		btnStopServer.setBounds(310, 384, 148, 102);
		add(btnStopServer);
		
		lblNumberOfConnections = new JLabel("Number of Connections:");
		lblNumberOfConnections.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNumberOfConnections.setBounds(156, 257, 220, 22);
		add(lblNumberOfConnections);
		
		textFieldNumberOfConnections = new JTextField();
		textFieldNumberOfConnections.setEditable(false);
		textFieldNumberOfConnections.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNumberOfConnections.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldNumberOfConnections.setText("-1");
		textFieldNumberOfConnections.setBounds(386, 261, 86, 20);
		add(textFieldNumberOfConnections);
		textFieldNumberOfConnections.setColumns(10);

	}

	public JButton getBtnExit() {
		return btnExit;
	}


	public JButton getBtnStartServer() {
		return btnStartServer;
	}

	public void setTextFieldNumberOfConnections(
			Integer numOfConnections) {
		this.textFieldNumberOfConnections.setText(numOfConnections.toString());;
	}
}
