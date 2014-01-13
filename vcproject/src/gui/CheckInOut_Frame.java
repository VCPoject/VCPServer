package gui;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private InvoicePanel invoicePanelPanel;
	
	/**
	 * isCheckIn is a flag that decide if open check in panel or check out 
	 */
	private boolean isCheckIn = false;
	private String host;
	private int port;
	/** vcpInfo is a controller that run on start-up of the 
	 * application and download all the info form the DB
	 * its contains all:
	 * order,subscribed,reservation,employees,parking lot,
	 * parking places,clients,default parking lot,cars
	 */
	private VcpInfo vcpInfo;
	

	/**
	 * @param host for make connection with server side
	 * @param port for make connection with server side
	 * @param vcpInfo for make connection with server side
	 * @param isCheckIn flag that decide if open check in panel or check out 
	 */
	public CheckInOut_Frame(String host,int port, VcpInfo vcpInfo,boolean isCheckIn) {
		super();
		this.isCheckIn = isCheckIn;
		this.host = host;
		this.port = port;
		this.vcpInfo = vcpInfo;
		initialize();
		listners();
	}
	private void listners() {
		getCheckOutPanel().getBtnPay().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (getCheckOutPanel().getRdbtnCradit().isSelected()) {
						if (getCheckOutPanel().getFrmtdtxtfldCreditCard().getText().equals(
								"                ")) {
							throw new Exception("You didnt insert cradit card");
						}
					}

					if (getCheckOutPanel().getTextFieldAmount().getText().equals("0.0"))
						throw new Exception("You didn't insert any car");
					if(!(getCheckOutPanel().isTempClient())){
					getCheckOutPanel().getRegisterController().showSeccussesMsg("Payment recived, you can press now submit");
					}else{
						setContentPane(getInvoicePanelPanel());
						getInvoicePanelPanel().getBtnReturn().addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								setContentPane(getCheckOutPanel());
								repaint();
								getCheckOutPanel().getRegisterController().showSeccussesMsg("you can press now submit");
							}
						});
					}
					getCheckOutPanel().getBtnCheckOut().setEnabled(true);
					getCheckOutPanel().getBtnPay().setEnabled(false);
				} catch (Exception e) {
					getCheckOutPanel().getRegisterController().showWarningMsg(
							"Error in Payment: " + e.getMessage());
				}

			}
		});
		
	}
	/**
	 * Initialize the panel of saving parking place
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
			checkOutPanel = new CheckOut_Panel(host,port,getVcpInfo());
		return checkOutPanel;
	}

	public VcpInfo getVcpInfo() {
		return vcpInfo;
	}
	public InvoicePanel getInvoicePanelPanel() {
		if(invoicePanelPanel == null)
			invoicePanelPanel = new InvoicePanel(getCheckOutPanel().getTempOrder(),getCheckOutPanel().getTextFieldAmount().getText());
		return invoicePanelPanel;
	}

}
