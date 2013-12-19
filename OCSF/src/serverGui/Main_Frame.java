package serverGui;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ocsf.server.ConnectionToClient;
import ocsf.server.EchoServer;
import ocsf.server.TextAreaOutputStream;

public class Main_Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Main_Panel mainPanel;
	private int port;
	private EchoServer sv;

	public Main_Frame(int port) {
		super();
		this.port = port;
		initialize();
		listners();
	}

	private void ConnectionStatus() {
		ScheduledExecutorService exec = Executors
				.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {

				getMainPanel().setTextFieldNumberOfConnections(
						sv.getNumberOfConnections());
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
								row.add(clientIpAndPort[0]);//ip
								row.add(clientIpAndPort[1]);//port
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

	private void setConnection(int port) {
		sv = new EchoServer(port);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
		ConnectionStatus();

	}

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
	}

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
						setConnection(port);
					}
				});

		getMainPanel().getBtnStopServer().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							sv.close();
							Vector<Vector<Object>> data = new Vector<Vector<Object>>();
							String[] columnNames = { "Client IP", "Client port" };
							int len = columnNames.length;
							Vector<String> cols = new Vector<String>(len);
							for (int i = 0; i < len; i++)
								cols.add(columnNames[i]);
							JTable table = new JTable(data, cols) {
								private static final long serialVersionUID = 1L;
								public boolean isCellEditable(int data,
										int columnNames) {
									return false;
								}
							};
							getMainPanel().setTableClientConnected(table);
						} catch (IOException e) {
							System.out
									.println("error while closing connections:"
											+ e.getMessage());
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

}
