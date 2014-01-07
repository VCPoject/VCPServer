package serverGui;

import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

import ocsf.server.TextAreaOutputStream;

import javax.swing.border.TitledBorder;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;

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
	private JTextField txtStatus;
	private JPanel panelConsole;
	private JTable tableClientConnected;
	private JScrollPane scrollPaneClientConnected;
	private JPanel panelClientConnected;
	private JScrollPane scrollPaneConsol;
	private JLabel lblDbServerIp;
	private JTextField textFieldServerIP;
	private JLabel lblDbUsername;
	private JTextField textFieldDBusername;
	private JLabel lblDbPassword;
	private JButton btnChangeDB;
	private JPasswordField passwordField;
	private JPanel panelDB;
	private JTextField textFieldLocalIP;

	public Main_Panel() {
		super();
		initialize();
		listners();
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
		btnStartServer.setBounds(216, 51, 110, 50);
		add(btnStartServer);

		btnStopServer = new JButton("Stop Server");
		btnStopServer.setEnabled(false);
		btnStopServer.setBounds(441, 51, 110, 50);
		add(btnStopServer);

		lblNumberOfConnections = new JLabel("Number of Connections:");
		lblNumberOfConnections.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNumberOfConnections.setBounds(200, 112, 220, 22);
		add(lblNumberOfConnections);

		textFieldNumberOfConnections = new JTextField();
		textFieldNumberOfConnections.setEditable(false);
		textFieldNumberOfConnections
				.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNumberOfConnections.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldNumberOfConnections.setText("-1");
		textFieldNumberOfConnections.setBounds(503, 112, 86, 22);
		add(textFieldNumberOfConnections);
		textFieldNumberOfConnections.setColumns(10);

		JLabel lblServerStatus = new JLabel("Server status:");
		lblServerStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblServerStatus.setBounds(200, 145, 126, 22);
		add(lblServerStatus);

		txtStatus = new JTextField();
		txtStatus.setHorizontalAlignment(SwingConstants.CENTER);
		txtStatus.setEditable(false);
		txtStatus.setText("Disconnected");
		txtStatus.setBounds(503, 145, 86, 20);
		add(txtStatus);
		txtStatus.setColumns(10);

		panelConsole = new JPanel();
		panelConsole.setBackground(SystemColor.activeCaption);
		panelConsole.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Console",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelConsole.setBounds(10, 202, 527, 190);
		add(panelConsole);
		panelConsole.setLayout(null);

		scrollPaneConsol = new JScrollPane();
		scrollPaneConsol.setBounds(10, 16, 507, 163);
		panelConsole.add(scrollPaneConsol);

		JTextArea textAreaConsole = new JTextArea();
		scrollPaneConsol.setViewportView(textAreaConsole);
		textAreaConsole.setEditable(false);
		TextAreaOutputStream taos = null;

		try {
			taos = new TextAreaOutputStream(textAreaConsole, 60);
			panelClientConnected = new JPanel();
			panelClientConnected.setBackground(SystemColor.activeCaption);
			panelClientConnected.setBorder(new TitledBorder(UIManager
					.getBorder("TitledBorder.border"), "Connected Client",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelClientConnected.setBounds(547, 202, 228, 190);
			add(panelClientConnected);
			panelClientConnected.setLayout(null);

			scrollPaneClientConnected = new JScrollPane();
			scrollPaneClientConnected.setBounds(10, 16, 208, 163);
			panelClientConnected.add(scrollPaneClientConnected);

			panelDB = new JPanel();
			panelDB.setBackground(SystemColor.activeCaption);
			panelDB.setBorder(new TitledBorder(UIManager
					.getBorder("TitledBorder.border"), "Database Managment",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDB.setBounds(257, 393, 270, 162);
			add(panelDB);
			panelDB.setLayout(null);

			lblDbServerIp = new JLabel("DB Server IP:");
			lblDbServerIp.setBounds(6, 16, 123, 22);
			panelDB.add(lblDbServerIp);
			lblDbServerIp.setFont(new Font("Tahoma", Font.BOLD, 18));

			textFieldServerIP = new JTextField();
			textFieldServerIP.setBounds(146, 16, 118, 20);
			panelDB.add(textFieldServerIP);
			textFieldServerIP.setColumns(10);

			lblDbUsername = new JLabel("DB Username:");
			lblDbUsername.setBounds(6, 49, 130, 22);
			panelDB.add(lblDbUsername);
			lblDbUsername.setFont(new Font("Tahoma", Font.BOLD, 18));

			textFieldDBusername = new JTextField();
			textFieldDBusername.setBounds(146, 49, 118, 20);
			panelDB.add(textFieldDBusername);
			textFieldDBusername.setColumns(10);

			lblDbPassword = new JLabel("DB Password:");
			lblDbPassword.setBounds(6, 82, 125, 22);
			panelDB.add(lblDbPassword);
			lblDbPassword.setFont(new Font("Tahoma", Font.BOLD, 18));

			btnChangeDB = new JButton("Change DB");
			btnChangeDB.setBounds(85, 126, 93, 29);
			panelDB.add(btnChangeDB);

			passwordField = new JPasswordField();
			passwordField.setBounds(146, 82, 118, 20);
			panelDB.add(passwordField);
			
			JLabel lblServerIp = new JLabel("Server IP:");
			lblServerIp.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblServerIp.setBounds(200, 177, 92, 22);
			add(lblServerIp);
			
			textFieldLocalIP = new JTextField();
			textFieldLocalIP.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldLocalIP.setEditable(false);
			textFieldLocalIP.setBounds(503, 176, 86, 20);
			add(textFieldLocalIP);
			textFieldLocalIP.setColumns(10);
			
			try {
				InetAddress addr = InetAddress.getLocalHost();
				textFieldLocalIP.setText(addr.getHostAddress());
			} catch (UnknownHostException e) {
				System.out.println("Error, cant find local ip: " + e.getMessage());
			}

			getDbInfo();

			tableClientConnected = new JTable();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintStream ps = new PrintStream(taos);
		System.setOut(ps);
		System.setErr(ps);

	}

	private void listners() {
		btnChangeDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDbInfo();
				JFrame frame = new JFrame();
				String msg = "Database changed";
				JOptionPane.showMessageDialog(frame, msg, "", JOptionPane.INFORMATION_MESSAGE);
			}
		});

	}

	public void setTxtStatus(String txtStatus) {
		this.txtStatus.setText(txtStatus);
	}

	public JButton getBtnExit() {
		return btnExit;
	}

	public JButton getBtnChangeDB() {
		return btnChangeDB;
	}

	public JButton getBtnStartServer() {
		return btnStartServer;
	}

	public void setTextFieldNumberOfConnections(Integer numOfConnections) {
		this.textFieldNumberOfConnections.setText(numOfConnections.toString());
	}

	public JButton getBtnStopServer() {
		return btnStopServer;
	}

	public JTable getTableClientConnected() {
		return tableClientConnected;
	}

	public void setTableClientConnected(JTable tableClientConnected) {
		this.tableClientConnected = tableClientConnected;
		this.tableClientConnected.setSurrendersFocusOnKeystroke(true);
		scrollPaneClientConnected.setViewportView(this.tableClientConnected);
	}

	public JTextField getTextFieldServerIP() {
		return textFieldServerIP;
	}

	public JTextField getTextFieldDBusername() {
		return textFieldDBusername;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	private void getDbInfo() {
		// The name of the file to open.
		String fileName = "dbInfo.txt";
		String line = null;// read line

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			int i = 0;
			while ((line = bufferedReader.readLine()) != null) {
				if (i == 0)
					textFieldServerIP.setText(line);
				else if (i == 1)
					textFieldDBusername.setText(line);
				else if (i == 2)
					passwordField.setText(line);
				i++;
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}

	}

	private void setDbInfo() {

		// The name of the file to open.
		String fileName = "dbInfo.txt";

		try {
			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(fileName);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			String ip = getTextFieldServerIP().getText();
			String user = getTextFieldDBusername().getText();
			String password = null;
			if (getPasswordField().getPassword() != null)
				password = new String(getPasswordField().getPassword());

			// Note that write() does not automatically
			// append a newline character.
			if (ip != null)
				if (user != null)
					if (password != null) {
						bufferedWriter.write(ip);
						bufferedWriter.newLine();
						bufferedWriter.write(user);
						bufferedWriter.newLine();
						bufferedWriter.write(password);
					} else
						System.out.println("No password was entered");
				else
					System.out.println("No user name was entered");
			else
				System.out.println("No database ip was entered");

			// Always close files.
			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

	}
}
