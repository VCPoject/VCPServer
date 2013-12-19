package gui;

import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class CancelOrder_Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton btnReturn;
	private JLabel lblCarNumber ;
	private JLabel lblIdNumber;
	private JButton btnSubmit;
	private JTextField textFieldIdNumber;
	private JFormattedTextField textFieldCarNumber;
	private JTextField textField;

	public CancelOrder_Panel() {
		super();
		initialize();
		listners();
	}
	
	private void initialize() {
		setLayout(null);
		this.setSize(785, 575);
		 setBackground(SystemColor.activeCaption);
		 
		 JLabel lblCancelOrder = new JLabel("Cancel Order");
		 lblCancelOrder.setFont(new Font("Tahoma", Font.BOLD, 24));
		 lblCancelOrder.setBounds(316, 11, 153, 29);
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
			
			textField = new JTextField();
			textField.setBounds(441, 339, 137, 29);
			add(textField);
			textField.setColumns(10);
			
			JLabel lblAmountOfCredit = new JLabel("Amount of Credit");
			lblAmountOfCredit.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblAmountOfCredit.setBounds(205, 339, 190, 29);
			add(lblAmountOfCredit);
			
			JLabel lblThisAmountOf = new JLabel("The cradit will be add to the user money card");
			lblThisAmountOf.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblThisAmountOf.setBounds(238, 379, 292, 15);
			add(lblThisAmountOf);
		 
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
