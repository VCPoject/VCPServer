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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;

public class Payment_Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private JLabel lblTotalPaymentCash;
	private JTextField textFieldTotalPayment;
	private JComboBox<String> comboBoxMonth;
	private JComboBox<String> comboBoxYear;
	private JTextField textFieldTotalPaymentCash;
	private Float needToPay;

	public Payment_Panel() {
		super();
		initialize();
	}

	public Payment_Panel(Float needToPay) {
		super();
		this.needToPay = needToPay;
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

		lblTotalPayment = new JLabel("Total payment:");
		lblTotalPayment.setFont(new Font("Tahoma", Font.BOLD, 18));

		textFieldCardNumber = new JTextField();
		textFieldCardNumber.setBounds(171, 182, 213, 20);

		textFieldIdNumber = new JTextField();
		textFieldIdNumber.setBounds(171, 153, 213, 20);

		textFieldFullName = new JTextField();
		textFieldFullName.setBounds(171, 124, 213, 20);

		lblTotalPaymentCash = new JLabel("Total payment:");
		lblTotalPaymentCash.setFont(new Font("Tahoma", Font.BOLD, 18));

		textFieldTotalPaymentCash = new JTextField();
		textFieldTotalPaymentCash.setColumns(10);
		
		//cash();
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
		comboBoxMonth.setVisible(false);
		comboBoxYear.setVisible(false);
		comboBoxMonth.setVisible(false);
		textFieldCardNumber.setVisible(false);
		textFieldIdNumber.setVisible(false);
		textFieldFullName.setVisible(false);
		textFieldTotalPayment.setVisible(false);
		lblTotalPayment.setVisible(false);

		textFieldTotalPaymentCash.setBounds(239, 160, 118, 20);
		add(textFieldTotalPaymentCash);
		textFieldTotalPaymentCash.setVisible(true);

		lblTotalPaymentCash.setBounds(89, 156, 136, 22);
		add(lblTotalPaymentCash);
		lblTotalPaymentCash.setVisible(true);

	}

	public void credit() {

		if (textFieldTotalPaymentCash.isVisible()
				&& textFieldTotalPaymentCash != null)
			textFieldTotalPaymentCash.setVisible(false);
		if (lblTotalPaymentCash.isVisible() && lblTotalPaymentCash != null)
			lblTotalPaymentCash.setVisible(false);
		lblFullName.setBounds(15, 124, 95, 22);
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

		lblCardExpiration.setBounds(15, 215, 146, 22);
		add(lblCardExpiration);
		lblCardExpiration.repaint();
		lblCardExpiration.setVisible(true);

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

		lblTotalPayment.setBounds(15, 244, 136, 22);
		add(lblTotalPayment);

		comboBoxMonth = new JComboBox<String>();
		comboBoxMonth.setBounds(171, 215, 60, 20);
		comboBoxMonth.addItem("Month");
		for (Integer i = 1; i <= 12; i++) {
			if (i <= 9)
				comboBoxMonth.addItem("0" + i.toString());
			else
				comboBoxMonth.addItem(i.toString());
		}
		add(comboBoxMonth);

		comboBoxYear = new JComboBox<String>();
		comboBoxYear.setBounds(240, 215, 60, 20);
		comboBoxYear.addItem("Year");
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
		Date date = new Date();
		String[] dateStr = dateFormat.format(date).split("-");
		Integer dateToStr = Integer.parseInt(dateStr[0]);
		for (Integer i = 0; i <= 10; i++) {
			Integer finalDate = dateToStr + i;
			comboBoxYear.addItem(finalDate.toString());
		}
		add(comboBoxYear);

		textFieldTotalPayment = new JTextField();
		textFieldTotalPayment.setEditable(false);
		textFieldTotalPayment.setBounds(174, 244, 210, 20);
		add(textFieldTotalPayment);
		textFieldTotalPayment.setColumns(10);
		lblTotalPayment.repaint();
		lblTotalPayment.setVisible(true);

	}

	public boolean checkValidity() {
		if (rdbtnCredit.isSelected()) {
			String name = textFieldFullName.getText();
			String idNumber = textFieldIdNumber.getText();
			String cardNumber = textFieldCardNumber.getText();
			String month = comboBoxMonth.getSelectedItem().toString();
			String year = comboBoxYear.getSelectedItem().toString();

			if (!name.isEmpty() && !idNumber.isEmpty() && !cardNumber.isEmpty()) {
				if (!month.equals("Month") && !year.equals("Year")) {
					synchronized (this) {
						notifyAll();
						return true;
					}
				} else
					return false;
			}
			return false;
		} else {
			return true;
		}

	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	public JButton getBtnPay() {
		return btnPay;
	}

	public Float getNeedToPay() {
		return needToPay;
	}

	public void setNeedToPay(Float needToPay) {
		this.needToPay = needToPay;
		textFieldTotalPayment.setText(needToPay.toString());
		textFieldTotalPaymentCash.setText(needToPay.toString());
	}
}
