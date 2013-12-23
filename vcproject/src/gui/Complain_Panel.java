package gui;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextArea;

public class Complain_Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnReturn;
	private JLabel lblCarNumber ;
	private JLabel lblIdNumber;
	private JButton btnSubmit;
	private JTextField textFieldIdNumber;
	private JFormattedTextField textFieldCarNumber;
	private JTextField textField;
	
	
	public Complain_Panel() {
		super();
		initialize();
		listners();
	}
	private void initialize(){
		setLayout(null);
		this.setSize(785, 575);
		 setBackground(SystemColor.activeCaption);
		 
		 JLabel lblCancelOrder = new JLabel("Complain");
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
			btnSubmit.setBounds(366, 386, 103, 38);
			add(btnSubmit);
			
			JTextArea textArea = new JTextArea();
			textArea.setBounds(352, 233, 228, 130);
			add(textArea);
			
			JLabel lblComplainDetails = new JLabel("Complain Details:");
			lblComplainDetails.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblComplainDetails.setBounds(145, 233, 197, 29);
			add(lblComplainDetails);
		
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
