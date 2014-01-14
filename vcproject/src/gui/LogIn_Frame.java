package gui;
 

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import controler.LogIn_controller;
import controler.VcpInfo;
import entity.Employee;

public class LogIn_Frame extends JFrame{

	private static final long serialVersionUID = 1L;
	/**
	 * loginpanel is the login panel to set in this frame
	 */
	private LogIn_Panel loginpanel;
	private String host;
	/** vcpInfo is a controller that run on start-up of the 
	 * application and download all the info form the DB
	 * its contains all:
	 * order,subscribed,reservation,employees,parking lot,
	 * parking places,clients,default parking lot,cars
	 */
	private VcpInfo vcpInfo;
	
	/**
	 * This panel is for the login screen of the employees
	 * @param host for connecting to server side
	 * @param employeeMap contains all the employees from DB
	 */
	public LogIn_Frame(String host){
		super();
		this.host = host;
		initialize();
	}
	/**
	 * Initialize the frame of saving parking place
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
		setTitle("Log in");
		setDefaultCloseOperation(LogIn_Frame.DO_NOTHING_ON_CLOSE);
		this.setSize(500, 400);
		this.setResizable(false);
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
		this.setContentPane(getLogIn_Panel());
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				getLogIn_Panel().getBtnExit().doClick();
			}
		});
	}
	 
	public LogIn_Panel getLogIn_Panel() { 
		
		if(loginpanel==null)
			loginpanel=new LogIn_Panel(host,getVcpInfo().getEmployeeInfo());
		
		return loginpanel; 
	}
	
	/**
	 * Close the frame and stop the reminder
	 */
	public void closeLoginFrame() {
		this.setVisible(false);
		this.dispose();
	}
	
	public LogIn_controller getLogincontroller(){
		return getLogIn_Panel().getLogincontroller();
		
	}
	
	public VcpInfo getVcpInfo() {
		if(vcpInfo == null)
			vcpInfo = new VcpInfo(host);
		return vcpInfo;
	}
	
	public Employee getConnectedEmployee(){
		return getLogincontroller().getConnectedEmployee();
	}
}
