package gui;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;



public class ComplainFu_Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton btnReturn;
	private JLabel lblCarNumber ;
	private JLabel lblIdNumber;
	private JButton btnSubmit;
	private JTextField textFieldIdNumber;
	private JFormattedTextField textFieldCarNumber;
	private JLabel lblRefound;
	private JTextField textField_1;
	private JTextArea textArea;
	private JComboBox complains;
	
	public ComplainFu_Panel() {
		super();
		initialize();
		listners();
	}
	
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
			
			textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setBounds(578, 350, 197, 93);
			add(textArea);
			
			complains = new JComboBox();
			complains.setBounds(25, 402, 211, 20);
			add(complains);
			
			JLabel lblSelectYourComplain = new JLabel("     Select your Complain");
			lblSelectYourComplain.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblSelectYourComplain.setBounds(51, 369, 200, 22);
			add(lblSelectYourComplain);
			
		
	}
	
	private void listners(){
		
		getBtnSubmit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
	
	public JButton getBtnReturn() {
		return btnReturn;
	}
	
	public JButton getBtnSubmit() {
		return btnSubmit;
	}
}
