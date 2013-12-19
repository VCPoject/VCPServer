package serverGui;

import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JTextArea;
import ocsf.server.TextAreaOutputStream;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;

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
		btnStartServer.setBounds(200, 65, 148, 102);
		add(btnStartServer);

		btnStopServer = new JButton("Stop Server");
		btnStopServer.setEnabled(false);
		btnStopServer.setBounds(441, 65, 148, 102);
		add(btnStopServer);

		lblNumberOfConnections = new JLabel("Number of Connections:");
		lblNumberOfConnections.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNumberOfConnections.setBounds(200, 178, 220, 22);
		add(lblNumberOfConnections);

		textFieldNumberOfConnections = new JTextField();
		textFieldNumberOfConnections.setEditable(false);
		textFieldNumberOfConnections
				.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNumberOfConnections.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldNumberOfConnections.setText("-1");
		textFieldNumberOfConnections.setBounds(503, 178, 86, 22);
		add(textFieldNumberOfConnections);
		textFieldNumberOfConnections.setColumns(10);

		JLabel lblServerStatus = new JLabel("Server status:");
		lblServerStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblServerStatus.setBounds(200, 228, 126, 22);
		add(lblServerStatus);

		txtStatus = new JTextField();
		txtStatus.setHorizontalAlignment(SwingConstants.CENTER);
		txtStatus.setEditable(false);
		txtStatus.setText("Disconnected");
		txtStatus.setBounds(503, 228, 86, 20);
		add(txtStatus);
		txtStatus.setColumns(10);

		panelConsole = new JPanel();
		panelConsole.setBackground(SystemColor.activeCaption);
		panelConsole.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Console",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelConsole.setBounds(10, 295, 527, 190);
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
			panelClientConnected.setBounds(547, 295, 228, 190);
			add(panelClientConnected);
			panelClientConnected.setLayout(null);

			scrollPaneClientConnected = new JScrollPane();
			scrollPaneClientConnected.setBounds(10, 16, 208, 163);
			panelClientConnected.add(scrollPaneClientConnected);

			tableClientConnected = new JTable();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintStream ps = new PrintStream(taos);
		System.setOut(ps);
		System.setErr(ps);

	}

	public void setTxtStatus(String txtStatus) {
		this.txtStatus.setText(txtStatus);
	}

	public JButton getBtnExit() {
		return btnExit;
	}

	public JButton getBtnStartServer() {
		return btnStartServer;
	}

	public void setTextFieldNumberOfConnections(Integer numOfConnections) {
		this.textFieldNumberOfConnections.setText(numOfConnections.toString());
		;
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
}
