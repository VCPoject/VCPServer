package serverGui;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ocsf.server.EchoServer;

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

	private void numOfCon() {
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
		  @Override
		  public void run() {
			  getMainPanel().setTextFieldNumberOfConnections(sv.getNumberOfConnections());
			  System.out.println("num of conn: " + sv.getNumberOfConnections());
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
		numOfCon();
		
	}

	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        	JOptionPane.showMessageDialog(this,"setLookAndFeel error: " + e.getMessage() , "setLookAndFeel ERRORE", JOptionPane.ERROR_MESSAGE);
        }
		this.setContentPane(getMainPanel());
		getContentPane().setBackground(SystemColor.activeCaption);
		this.setSize(800, 600);
		this.setResizable(false);
		
	}
	
	private void listners() {
		getMainPanel().getBtnExit().addActionListener(new ActionListener() {/*Exit Button Listener*/
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame();
				
				int result = JOptionPane.showConfirmDialog(frame,
						"Are you sure you want to exit the application?",
						"Exit Application", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					closeMainFrame();
				}

			}
		});
		
		getMainPanel().getBtnStartServer().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setConnection(port);
			}
		});
		
		
		
	}

	public Main_Panel getMainPanel() {
		if(mainPanel == null)
			mainPanel = new Main_Panel();
		return mainPanel;
	}
	
	private void closeMainFrame() {
		this.setVisible(false);
		this.dispose();
	}

}
