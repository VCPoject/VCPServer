package gui;

import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;

public class Payment_Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldTotalPayment;
	private JTextField textFieldCardExpiration;
	private JTextField textFieldCardNumber;
	private JTextField textFieldIdNumber;
	private JTextField textFieldFullName;
	private JButton btnReturn;
	private JButton btnPay;
	private JRadioButton rdbtnCredit;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblFullName;
	private JLabel lblIdNumber;
	private JLabel lblCardNumber;
	private JLabel lblCardExpiration;
	private JLabel lblTotalPayment;
	private JRadioButton rdbtnCash;
	private JTextField textFieldTotalPaymentCash;
	private JLabel lblTotalPaymentCash;

	public Payment_Panel() {
		super();
		initialize();
		listners();
	}

	private void initialize() {
		this.setBounds(10, 11, 464, 340);
		setLayout(null);
		setBackground(SystemColor.activeCaption);

		JLabel lblPayment = new JLabel("Payment:");
		lblPayment.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblPayment.setBounds(174, 11, 115, 29);
		add(lblPayment);

		JLabel lblPaymentType = new JLabel("Payment Type:");
		lblPaymentType.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPaymentType.setBounds(90, 64, 135, 22);
		add(lblPaymentType);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Select",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(239, 51, 118, 46);
		add(panel);
		panel.setLayout(null);

		rdbtnCredit = new JRadioButton("Credit");
		rdbtnCredit.setSelected(true);
		buttonGroup.add(rdbtnCredit);
		rdbtnCredit.setBounds(6, 16, 55, 23);
		panel.add(rdbtnCredit);
		rdbtnCredit.setBackground(SystemColor.activeCaption);

		rdbtnCash = new JRadioButton("Cash");
		buttonGroup.add(rdbtnCash);
		rdbtnCash.setBounds(63, 16, 49, 23);
		panel.add(rdbtnCash);
		rdbtnCash.setBackground(SystemColor.activeCaption);

		btnPay = new JButton("Pay");
		btnPay.setBounds(365, 306, 89, 23);
		add(btnPay);

		btnReturn = new JButton("Return");
		btnReturn.setBounds(10, 306, 89, 23);
		add(btnReturn);

		lblFullName = new JLabel("Full name:");
		lblFullName.setFont(new Font("Tahoma", Font.BOLD, 18));

		lblIdNumber = new JLabel("ID number:");
		lblIdNumber.setFont(new Font("Tahoma", Font.BOLD, 18));

		lblCardNumber = new JLabel("Card number:");
		lblCardNumber.setFont(new Font("Tahoma", Font.BOLD, 18));

		lblCardExpiration = new JLabel("Card Expiration:");
		lblCardExpiration.setFont(new Font("Tahoma", Font.BOLD, 18));

		textFieldTotalPayment = new JTextField();
		textFieldTotalPayment.setBounds(171, 256, 55, 20);

		lblTotalPayment = new JLabel("Total payment:");
		lblTotalPayment.setFont(new Font("Tahoma", Font.BOLD, 18));

		textFieldCardExpiration = new JTextField();
		textFieldCardExpiration.setBounds(171, 223, 54, 20);

		textFieldCardNumber = new JTextField();
		textFieldCardNumber.setBounds(171, 186, 213, 20);

		textFieldIdNumber = new JTextField();
		textFieldIdNumber.setBounds(171, 153, 213, 20);

		textFieldFullName = new JTextField();
		textFieldFullName.setBounds(171, 124, 213, 20);

		textFieldTotalPayment = new JTextField();
		textFieldTotalPayment.setBounds(239, 160, 55, 20);

		lblTotalPaymentCash = new JLabel("Total payment:");
		lblTotalPaymentCash.setFont(new Font("Tahoma", Font.BOLD, 18));

		textFieldTotalPaymentCash = new JTextField();

		credit();

	}

	private void listners() {
		rdbtnCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				credit();
			}
		});

		rdbtnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cash();
			}
		});
	}

	public void cash() {
		lblFullName.setVisible(false);
		lblIdNumber.setVisible(false);
		lblCardNumber.setVisible(false);
		lblCardExpiration.setVisible(false);
		textFieldCardExpiration.setVisible(false);
		textFieldCardExpiration.setVisible(false);
		textFieldCardNumber.setVisible(false);
		textFieldIdNumber.setVisible(false);
		textFieldFullName.setVisible(false);
		textFieldTotalPayment.setVisible(false);
		lblTotalPayment.setVisible(false);

		textFieldTotalPaymentCash.setBounds(239, 160, 55, 20);
		add(textFieldTotalPaymentCash);
		textFieldTotalPaymentCash.setColumns(10);
		textFieldTotalPaymentCash.setVisible(true);

		lblTotalPaymentCash.setBounds(89, 156, 136, 22);
		add(lblTotalPaymentCash);
		lblTotalPaymentCash.setVisible(true);

	}

	public void credit() {

		if (textFieldTotalPaymentCash.isVisible() && textFieldTotalPaymentCash != null)
			textFieldTotalPaymentCash.setVisible(false);
		if (lblTotalPaymentCash.isVisible() && lblTotalPaymentCash != null)
			lblTotalPaymentCash.setVisible(false);
		lblFullName.setBounds(15, 120, 95, 22);
		add(lblFullName);
		lblFullName.repaint();
		lblFullName.setVisible(true);

		lblIdNumber.setBounds(15, 153, 106, 22);
		add(lblIdNumber);
		lblIdNumber.repaint();
		lblIdNumber.setVisible(true);

		lblCardNumber.setBounds(15, 186, 125, 22);
		add(lblCardNumber);
		lblCardNumber.repaint();
		lblCardNumber.setVisible(true);

		lblCardExpiration.setBounds(15, 219, 146, 22);
		add(lblCardExpiration);
		lblCardExpiration.repaint();
		lblCardExpiration.setVisible(true);

		textFieldTotalPayment.setBounds(171, 256, 55, 20);
		add(textFieldTotalPayment);
		textFieldTotalPayment.setColumns(10);
		textFieldTotalPayment.repaint();
		textFieldTotalPayment.setVisible(true);

		textFieldCardExpiration.setBounds(171, 223, 54, 20);
		add(textFieldCardExpiration);
		textFieldCardExpiration.setColumns(10);
		textFieldCardExpiration.repaint();
		textFieldCardExpiration.setVisible(true);

		textFieldCardNumber.setBounds(171, 186, 213, 20);
		add(textFieldCardNumber);
		textFieldCardNumber.setColumns(10);
		textFieldCardNumber.repaint();
		textFieldCardNumber.setVisible(true);

		textFieldIdNumber.setBounds(171, 153, 213, 20);
		add(textFieldIdNumber);
		textFieldIdNumber.setColumns(10);
		textFieldIdNumber.repaint();
		textFieldIdNumber.setVisible(true);

		textFieldFullName.setBounds(171, 124, 213, 20);
		add(textFieldFullName);
		textFieldFullName.setColumns(10);
		textFieldFullName.repaint();
		textFieldFullName.setVisible(true);

		add(textFieldTotalPayment);
		textFieldTotalPayment.setColumns(10);
		textFieldTotalPayment.repaint();
		textFieldTotalPayment.setVisible(true);
		
		lblTotalPayment.setBounds(15, 252, 136, 22);
		add(lblTotalPayment);
		lblTotalPayment.repaint();
		lblTotalPayment.setVisible(true);
		

	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	public JButton getBtnPay() {
		return btnPay;
	}
}
