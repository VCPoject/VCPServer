package gui;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controler.VcpInfo;

public class CheckInOut_Frame extends JFrame {
	
	private CheckIn_Panel checkInPanel;
	private CheckOut_Panel checkOutPanel;
	private static final long serialVersionUID = 1L;
	private boolean isCheckIn = false;
	private String host;
	private int port;
	private VcpInfo vcpInfo;
	

	public CheckInOut_Frame(String host,int port, VcpInfo vcpInfo,boolean isCheckIn) {
		super();
		this.isCheckIn = isCheckIn;
		this.host = host;
		this.port = port;
		this.vcpInfo = vcpInfo;
		initialize();
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
		this.setSize(500, 400);
		this.setResizable(false);
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
		if (isCheckIn)
			this.setContentPane(getCheckInPanel());
		else
			this.setContentPane(getCheckOutPanel());
	}

	public CheckIn_Panel getCheckInPanel() {
			if (checkInPanel == null)
				checkInPanel = new CheckIn_Panel(host,port,getVcpInfo());
			return checkInPanel;
	}

	public JButton getBtnReturn() {
		if(isCheckIn)
			return getCheckInPanel().getBtnReturn();
		else
			return getCheckOutPanel().getBtnReturn();
	}

	public CheckOut_Panel getCheckOutPanel() {
		if (checkOutPanel == null)
			checkOutPanel = new CheckOut_Panel();
		return checkOutPanel;
	}

	public VcpInfo getVcpInfo() {
		return vcpInfo;
	}

}
