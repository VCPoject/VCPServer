package gui;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import controler.ComplainFuController;



public class ComplainFu_Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton btnReturn;
	private JLabel lblCarNumber;
	private JLabel lblIdNumber;
	private JButton btnSubmit;
	private JButton btnSubmit1;
	private JTextField textFieldIdNumber;
	private JFormattedTextField textFieldCarNumber;
	private JLabel lblRefound;
	private JTextField textField_1;
	private JTextArea textArea;
	private JComboBox<String> complains;
	private String host;
	private int port = 5555;
	private ComplainFuController complainController;
	private JScrollPane scrollPane;
	
	/**
	 * This panel is for client to follow theirs complains
	 * @param host for connect to server side
	 * @param port for connect to server side
	 */
	public ComplainFu_Panel(String host, int port) {
		super();
		this.host = host;
		this.port = port;
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
		 
		 JLabel lblCancelOrder = new JLabel("Complain Follow Up");
		 lblCancelOrder.setFont(new Font("Tahoma", Font.BOLD, 24));
		 lblCancelOrder.setBounds(261, 11, 262, 29);
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
			btnSubmit.setBounds(353, 235, 103, 38);
			add(btnSubmit);
			
			JLabel lblAmountOfCredit = new JLabel("Complain Answer: ");
			lblAmountOfCredit.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblAmountOfCredit.setBounds(373, 339, 190, 29);
			add(lblAmountOfCredit);
			
			JLabel lblThisAmountOf = new JLabel("The cradit will be add to the user money card");
			lblThisAmountOf.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblThisAmountOf.setBounds(483, 507, 292, 15);
			add(lblThisAmountOf);
			
			lblRefound = new JLabel("Refound: ");
			lblRefound.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblRefound.setBounds(483, 467, 93, 29);
			add(lblRefound);
			
			textField_1 = new JTextField();
			textField_1.setEditable(false);
			textField_1.setBounds(601, 473, 138, 22);
			add(textField_1);
			textField_1.setColumns(10);
			
			scrollPane = new JScrollPane();
			scrollPane.setBounds(578, 350, 197, 93);
			add(scrollPane);
			
			textArea = new JTextArea();
			textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
			scrollPane.setViewportView(textArea);
			textArea.setEditable(false);
			
			complains = new JComboBox<String>();
			complains.setBounds(25, 402, 211, 20);
			add(complains);
			
			JLabel lblSelectYourComplain = new JLabel("     Select your Complain");
			lblSelectYourComplain.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblSelectYourComplain.setBounds(51, 369, 200, 22);
			add(lblSelectYourComplain);
			
			btnSubmit1 = new JButton("Submit");
			btnSubmit1.setBounds(78, 446, 89, 23);
			add(btnSubmit1);
			
		
	}
	/**
	 * Listeners of the GUI components.
	 */
	private void listners(){
		getBtnSubmit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idnum = Integer.parseInt(textFieldIdNumber.getText());
				int carnum = Integer.parseInt(textFieldCarNumber.getText().replaceAll("-", ""));
				if(getComplainController().checkValidity(idnum,carnum))
				{
					String[] comp = complainController.getComplains(idnum);
					complains.setEnabled(true);
					for(int i=0;i<comp.length;i++)
					complains.addItem(comp[i]);
				}
			}
		});
		getBtnSubmit1().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String s=complainController.getSelectedItemComplain((String)complains.getSelectedItem());
			if(s.equals("No Result")){
				textArea.setText("The Complain is still in\norder Please"
						+ " Check again \nsoon");
			}
			else{
				textArea.setText(s);
				s=complainController.getSelectedItemAmount((String)complains.getSelectedItem());
				textField_1.setText(s);
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
	public JButton getBtnSubmit1() {
		return btnSubmit1;
	}
	
	private ComplainFuController getComplainController() {
		if(complainController == null || !complainController.isConnected()){
			complainController = new ComplainFuController(host,port);
		}
			
		return complainController;
	}
}
