package gui;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextArea;

import controler.ComplainController;
import controler.VcpInfo;

import javax.swing.SwingConstants;

public class Complain_Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnReturn;
	private JLabel lblCarNumber ;
	private JLabel lblIdNumber;
	private JButton btnSubmit;
	private JTextField textFieldIdNumber;
	private JFormattedTextField textFieldCarNumber;
	private JTextArea textAreaComplainDetails;
	private String host;
	private int port = 5555;
	private ComplainController complainController;
	private VcpInfo vcpInfo;
	
	/**
	 * This panel is for add complain from the clients
	 * @param host for connecting to server side
	 * @param port for connecting to server side
	 */
	public Complain_Panel(String host, int port,VcpInfo vcpInfo) {
		super();
		this.host = host;
		this.port = port;
		this.vcpInfo=vcpInfo;
		initialize();
		listners();
	}
	/**
	 * Initialize the panel of saving parking place
	 */
	private void initialize(){
		setLayout(null);
		this.setSize(785, 575);
		 setBackground(SystemColor.activeCaption);
		 
		 JLabel lblCancelOrder = new JLabel("Complain");
		 lblCancelOrder.setFont(new Font("Tahoma", Font.BOLD, 24));
		 lblCancelOrder.setBounds(336, 11, 112, 29);
		 add(lblCancelOrder);
		 
		 btnReturn = new JButton("Return");
		 btnReturn.setBounds(10, 519, 93, 35);
		 add(btnReturn);
		 
		    lblIdNumber = new JLabel("ID number:");
			lblIdNumber.setBounds(234, 131, 197, 22);
			lblIdNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
			add(lblIdNumber);
			
			textFieldIdNumber = new JTextField();
			textFieldIdNumber.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldIdNumber.setBounds(441, 131, 137, 24);
			add(textFieldIdNumber);
			textFieldIdNumber.setColumns(10);
			
			lblCarNumber = new JLabel("Car number:");
			lblCarNumber.setBounds(234, 164, 197, 22);
			add(lblCarNumber);
			lblCarNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
			
			try {
				MaskFormatter formatter = new MaskFormatter("##-###-##");
				formatter.setValidCharacters("0123456789");
				textFieldCarNumber = new JFormattedTextField(formatter);
				textFieldCarNumber.setHorizontalAlignment(SwingConstants.CENTER);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(this,"Formatter error: " + e.getMessage() , "Formatter ERRORE", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

			textFieldCarNumber.setBounds(441, 164, 137, 24);
			add(textFieldCarNumber);
			textFieldCarNumber.setColumns(10);
			
			btnSubmit = new JButton("Submit");
			btnSubmit.setBounds(366, 386, 103, 38);
			add(btnSubmit);
			
			textAreaComplainDetails = new JTextArea();
			textAreaComplainDetails.setBounds(352, 233, 228, 130);
			add(textAreaComplainDetails);
			
			JLabel lblComplainDetails = new JLabel("Complain Details:");
			lblComplainDetails.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblComplainDetails.setBounds(145, 233, 197, 29);
			add(lblComplainDetails);
		
	}
	/**
	 * Listeners of the GUI components.
	 */
	private void listners(){
		getBtnSubmit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idnum = Integer.parseInt(textFieldIdNumber.getText());
				int carnum = Integer.parseInt(textFieldCarNumber.getText().replaceAll("-", ""));
				String complain = textAreaComplainDetails.getText();
				if(getComplainController().checkValidity(idnum,carnum,complain))
				{
				complainController.sendAck(idnum,complain);
				}
			}
		});	
			
	  }
	
	public JButton getBtnReturn() {
		return btnReturn;
	}
	
	public JButton getBtnSubmit() {
		return btnSubmit;
	}
	
	public ComplainController getComplainController() {
		if(complainController == null || !complainController.isConnected()){
			complainController = new ComplainController(host,port,vcpInfo);
		}
			
		return complainController;
	}
}
