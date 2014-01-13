package gui;

import java.awt.SystemColor;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import entity.Order;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JButton;

public class InvoicePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldOrderNum;
	private JTextField textFieldPaymentDate;
	private JTextField textFieldIdNumber;
	private JFormattedTextField textFieldCarNumber;
	private JTextField textFieldAmount;
	private Order order;
	private String amount;
	private JTextField textFieldPaymentTime;
	private JButton btnReturn;

	public InvoicePanel(Order order, String amount) {
		super();
		this.amount = amount;
		this.order = order;
		initialize();
		listners();
	}

	private void initialize() {
		this.setBounds(10, 11, 464, 340);
		setLayout(null);
		setBackground(SystemColor.activeCaption);
		
		JLabel lblInvoice = new JLabel("Invoice");
		lblInvoice.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblInvoice.setBounds(187, 0, 90, 29);
		add(lblInvoice);
		
		JPanel panelInvoice = new JPanel();
		panelInvoice.setBackground(SystemColor.activeCaption);
		panelInvoice.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Invoice", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInvoice.setBounds(88, 66, 288, 202);
		add(panelInvoice);
		panelInvoice.setLayout(null);
		
		JLabel lblOrderNumber = new JLabel("Order number:");
		lblOrderNumber.setBounds(6, 15, 135, 22);
		panelInvoice.add(lblOrderNumber);
		lblOrderNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		textFieldOrderNum = new JTextField(order.getIdorder().toString());
		textFieldOrderNum.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOrderNum.setBounds(196, 15, 86, 22);
		panelInvoice.add(textFieldOrderNum);
		textFieldOrderNum.setEditable(false);
		textFieldOrderNum.setColumns(10);
		
		JLabel lblCarNumber = new JLabel("Car number:");
		lblCarNumber.setBounds(6, 45, 114, 22);
		panelInvoice.add(lblCarNumber);
		lblCarNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblPaymentDate = new JLabel("Payment date:");
		lblPaymentDate.setBounds(6, 105, 132, 22);
		panelInvoice.add(lblPaymentDate);
		lblPaymentDate.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] stringDate = format.format(today).split("\\s");
		
		textFieldPaymentDate = new JTextField(stringDate[0]);
		textFieldPaymentDate.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPaymentDate.setBounds(196, 105, 86, 22);
		panelInvoice.add(textFieldPaymentDate);
		textFieldPaymentDate.setEditable(false);
		textFieldPaymentDate.setColumns(10);
		
		textFieldPaymentTime = new JTextField(stringDate[1]);
		textFieldPaymentTime.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPaymentTime.setEditable(false);
		textFieldPaymentTime.setBounds(196, 135, 86, 22);
		panelInvoice.add(textFieldPaymentTime);
		textFieldPaymentTime.setColumns(10);
		
		JLabel lblIdNumber = new JLabel("Id number:");
		lblIdNumber.setBounds(6, 75, 103, 22);
		panelInvoice.add(lblIdNumber);
		lblIdNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		textFieldIdNumber = new JTextField(order.getClient().toString());
		textFieldIdNumber.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldIdNumber.setBounds(196, 75, 86, 20);
		panelInvoice.add(textFieldIdNumber);
		textFieldIdNumber.setEditable(false);
		textFieldIdNumber.setColumns(10);
		
		try {
			MaskFormatter formatter = new MaskFormatter("##-###-##");
			formatter.setValidCharacters("0123456789");
			textFieldCarNumber = new JFormattedTextField(formatter);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this,"Formatter error: " + e.getMessage() , "Formatter ERRORE", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		textFieldCarNumber.setText(order.getCar().toString());
		textFieldCarNumber.setBounds(196, 45, 86, 22);
		panelInvoice.add(textFieldCarNumber);
		textFieldCarNumber.setEditable(false);
		textFieldCarNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldCarNumber.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(6, 165, 78, 22);
		panelInvoice.add(lblAmount);
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		textFieldAmount = new JTextField(amount);
		textFieldAmount.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAmount.setBounds(196, 165, 86, 22);
		panelInvoice.add(textFieldAmount);
		textFieldAmount.setEditable(false);
		textFieldAmount.setColumns(10);
		
		JLabel lblPaymentTime = new JLabel("Payment time:");
		lblPaymentTime.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPaymentTime.setBounds(6, 135, 132, 22);
		panelInvoice.add(lblPaymentTime);
		
		btnReturn = new JButton("Return");
		btnReturn.setBounds(187, 279, 89, 29);
		add(btnReturn);
		

		
	}
	
	private void listners() {
		
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}
}
