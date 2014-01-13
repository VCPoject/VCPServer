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
import javax.swing.JComboBox;

public class Complain_Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnReturn;
	private JLabel lblCarNumber ;
	private JLabel lblIdNumber;
	private JButton btnSubmit;
	private JTextField textFieldIdNumber;
	private JFormattedTextField textFieldCarNumber;
	private JTextArea textArea;
	private String host;
	private int port = 5555;
	private ComplainController complainController;
	private JLabel lblParkingLotNumber;
	private JComboBox<Integer> comboBox;
	private int selected;
	
	
	public Complain_Panel(String host, int port) {
		super();
		this.host = host;
		this.port = port;
		initialize();
		listners();
	}
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
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(this,"Formatter error: " + e.getMessage() , "Formatter ERRORE", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

			textFieldCarNumber.setBounds(441, 164, 137, 24);
			add(textFieldCarNumber);
			textFieldCarNumber.setColumns(10);
			
			btnSubmit = new JButton("Submit");
			btnSubmit.setEnabled(false);
			btnSubmit.setBounds(352, 481, 103, 38);
			add(btnSubmit);
			
			textArea = new JTextArea();
			textArea.setEnabled(false);
			textArea.setBounds(352, 340, 228, 130);
			add(textArea);
			
			JLabel lblComplainDetails = new JLabel("Complain Details:");
			lblComplainDetails.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblComplainDetails.setBounds(145, 329, 197, 29);
			add(lblComplainDetails);
			
			lblParkingLotNumber = new JLabel("Parking Lot Number:");
			lblParkingLotNumber.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblParkingLotNumber.setBounds(145, 280, 176, 29);
			add(lblParkingLotNumber);
			
			comboBox = new JComboBox();
			comboBox.setBounds(352, 286, 226, 20);
			for(int i=1;i<7;i++)
			comboBox.addItem(i);
			add(comboBox);
		
	}
	
	private void listners(){
		

		getBtnSubmit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idnum = Integer.parseInt(textFieldIdNumber.getText());
				int carnum = Integer.parseInt(textFieldCarNumber.getText().replaceAll("-", ""));
				String complain = textArea.getText();
				if(getComplainController().checkValidity(idnum,carnum,complain,selected))
				{
				complainController.sendAck(idnum,complain);
				}
			}
		});	
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setEnabled(true);
				btnSubmit.setEnabled(true);
				selected=Integer.parseInt(comboBox.getSelectedItem().toString());
			}
		});
	  }
	
	public JButton getBtnReturn() {
		return btnReturn;
	}
	
	public JButton getBtnSubmit() {
		return btnSubmit;
	}
	
	private ComplainController getComplainController() {
		if(complainController == null || !complainController.isConnected()){
			complainController = new ComplainController(host,port);
		}
			
		return complainController;
	}
}
