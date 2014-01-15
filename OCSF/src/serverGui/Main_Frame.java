package serverGui;  

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.Scheduler;
import ocsf.server.ConnectionToClient;
import ocsf.server.EchoServer;

public class Main_Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Main_Panel mainPanel;
	private int port;
	/**
	 * sv is EchoServer entity that listen to server side and handel client requests
	 */
	private EchoServer sv;
	private String dbIp;
	private String dbUser;
	private String dbPassword;
	private Scheduler scheduler;

	/**
	 * @param port to listen to
	 */
	public Main_Frame(int port) {
		super();
		setTitle("VCP Server");
		this.port = port;
		initialize();
		listners();
	}

	/**
	 * ConnectionStatus is setting all the GUI that give info about the server status.
	 * Given info:Connecterd/Disconnected, client IP and port table.
	 * Checking every one second.
	 */
	private void ConnectionStatus() {
		ScheduledExecutorService exec = Executors
				.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {

				getMainPanel().setTextFieldNumberOfConnections(sv.getNumberOfConnections());
				if (sv.isListening()) {
					getMainPanel().setTxtStatus("Connected");
					Thread[] clientList = sv.getClientConnections();
					String clientIp = null;
					if (clientList != null) {
						String[] columnNames = { "Client IP", "Client port" };
						int len = columnNames.length;
						Vector<String> cols = new Vector<String>(len);
						for (int i = 0; i < len; i++)
							cols.add(columnNames[i]);
						Vector<Object> row = new Vector<Object>(len);
						Vector<Vector<Object>> data = new Vector<Vector<Object>>();

						for (int i = 0; i < clientList.length; i++) {
							for (Thread client : clientList) {
								clientIp = ((ConnectionToClient) client)
										.getClientSocket()
										.getRemoteSocketAddress().toString();
								clientIp = clientIp.replace("/", "");
								String[] clientIpAndPort = clientIp.split(":");
								row.add(clientIpAndPort[0]);// ip
								row.add(clientIpAndPort[1]);// port
							}
							data.add(row);
							row = new Vector<Object>(len);
						}
						JTable table = new JTable(data, cols) {
							private static final long serialVersionUID = 1L;

							public boolean isCellEditable(int data,
									int columnNames) {
								return false;
							}
						};
						getMainPanel().setTableClientConnected(table);
					}
				} else
					getMainPanel().setTxtStatus("Disconnected");
			}
		}, 0, 1, TimeUnit.SECONDS);

	}

	/**
	 * setConnection is set the server and DB.
	 * @param port to listen to
	 * @param dbIp is host to connect to DB
	 * @param dbUser is DB user
	 * @param dbPassword is DB password
	 */
	private void setConnection(int port, String dbIp, String dbUser,String dbPassword) {
		sv = new EchoServer(port, dbIp, dbUser, dbPassword);

		try {
			
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
		ConnectionStatus();
	}

	/**
	 * initialize is initialize the frame GUI
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(this,
					"setLookAndFeel error: " + e.getMessage(),
					"setLookAndFeel ERRORE", JOptionPane.ERROR_MESSAGE);
		}
		this.setContentPane(getMainPanel());
		getContentPane().setBackground(SystemColor.activeCaption);
		this.setSize(800, 600);
		this.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
	}

	/**
	 * listners is the listener to all components of the GUI
	 */
	private void listners() {
		getMainPanel().getBtnExit().addActionListener(new ActionListener() {/*
																			 * Exit
																			 * Button
																			 * Listener
																			 */
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame();

				int result = JOptionPane.showConfirmDialog(frame,
						"Are you sure you want to exit the application?",
						"Exit Application", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					if (sv != null) {
						if (sv.isListening())
							getMainPanel().getBtnStopServer().doClick();
					}
					closeMainFrame();
					System.exit(0);
				}

			}
		});

		getMainPanel().getBtnStartServer().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dbIp = getMainPanel().getTextFieldServerIP().getText();
						dbUser = getMainPanel().getTextFieldDBusername().getText();
						dbPassword = new String(getMainPanel().getPasswordField().getPassword());
						setConnection(port, dbIp, dbUser, dbPassword);
						getMainPanel().getBtnStopServer().setEnabled(true);
						getMainPanel().getBtnStartServer().setEnabled(false);
						getMainPanel().getBtnChangeDB().setEnabled(false);
						getMainPanel().getTextFieldServerIP().setEditable(false);
						getMainPanel().getTextFieldDBusername().setEditable(false);
						getMainPanel().getPasswordField().setEditable(false);
						getScheduler();
					}
				});

		getMainPanel().getBtnStopServer().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							sv.close();
							getMainPanel().getBtnStopServer().setEnabled(false);
							getMainPanel().getBtnStartServer().setEnabled(true);
							getMainPanel().getBtnChangeDB().setEnabled(true);
							getMainPanel().getTextFieldServerIP().setEditable(true);
							getMainPanel().getTextFieldDBusername().setEditable(true);
							getMainPanel().getPasswordField().setEditable(true);
							Vector<Vector<Object>> data = new Vector<Vector<Object>>();
							String[] columnNames = { "Client IP", "Client port" };
							int len = columnNames.length;
							Vector<String> cols = new Vector<String>(len);
							for (int i = 0; i < len; i++)
								cols.add(columnNames[i]);
							JTable table = new JTable(data, cols) {
								private static final long serialVersionUID = 1L;

								public boolean isCellEditable(int data, int columnNames) {
									return false;
								}
							};
							getMainPanel().setTableClientConnected(table);
							scheduler = null;
						} catch (IOException e) {
							System.out.println("error while closing connections:"	+ e.getMessage());
							e.printStackTrace();
						}
					}
				});

	}

	public Main_Panel getMainPanel() {
		if (mainPanel == null)
			mainPanel = new Main_Panel();
		return mainPanel;
	}

	private void closeMainFrame() {
		this.setVisible(false);
		this.dispose();
	}

	public Scheduler getScheduler() {
		if(scheduler == null)
			scheduler = new Scheduler(port, dbIp, dbUser, dbPassword);
		return scheduler;
	}

}
