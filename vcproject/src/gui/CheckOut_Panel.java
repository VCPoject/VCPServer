package gui;

import java.awt.Font;
import java.awt.SystemColor;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class CheckOut_Panel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JFormattedTextField textFieldCarNumber;
	private JButton btnReturn;
	private JTextField textField;
	
	public CheckOut_Panel() {
		super();
		initialize();
		listners();
	}

	private void initialize() {
		this.setBounds(10, 11, 464, 340);
		setLayout(null);
		setBackground(SystemColor.activeCaption);
		
		JLabel lblCheckIn = new JLabel("Check - Out");
		lblCheckIn.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblCheckIn.setBounds(162, 11, 139, 29);
		add(lblCheckIn);
		
		JLabel lblCarNumber = new JLabel("Car number:");
		lblCarNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCarNumber.setBounds(126, 102, 114, 22);
		add(lblCarNumber);
		
		try {
			MaskFormatter formatter = new MaskFormatter("##-###-##");
			formatter.setValidCharacters("0123456789");
			textFieldCarNumber = new JFormattedTextField(formatter);
			textFieldCarNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
			textFieldCarNumber.setHorizontalAlignment(SwingConstants.CENTER);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this,"Formatter error: " + e.getMessage() , "Formatter ERRORE", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		textFieldCarNumber.setBounds(272, 102, 73, 24);
		add(textFieldCarNumber);
		
		JButton btnCheckOut = new JButton("Check-Out");
		btnCheckOut.setBounds(187, 195, 89, 23);
		add(btnCheckOut);
		
		btnReturn = new JButton("Return");
		btnReturn.setBounds(10, 294, 95, 35);
		add(btnReturn);
		
		JLabel lblMemberId = new JLabel("Member ID:");
		lblMemberId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMemberId.setBounds(126, 135, 109, 22);
		add(lblMemberId);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(272, 137, 73, 20);
		add(textField);
		textField.setColumns(10);
		
	}
	
	private void listners() {
		// TODO Auto-generated method stub
	}
	
	public JButton getBtnReturn() {
		return btnReturn;
	}

}
