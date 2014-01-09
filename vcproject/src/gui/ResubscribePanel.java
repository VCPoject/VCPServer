package gui;

import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import controler.RegisterController;
import controler.VcpInfo;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import entity.Car;
import entity.Parking_Lot;
import entity.Subscribe;

import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResubscribePanel extends JPanel {
	private String host;
	private int port;
	private VcpInfo vcpInfo;
	private RegisterController registerController;
	private JButton btnReturn;
	private final ButtonGroup buttonGroupSubscribeType = new ButtonGroup();
	private final ButtonGroup buttonGroupCustomerType = new ButtonGroup();
	private JFormattedTextField txtIdNumber;
	private JFormattedTextField formattedTextFieldCarNumber;
	private JButton btnSubmit;
	private JButton btnSearch;
	private JComboBox<Integer> comboBoxParkingLot;
	private JComboBox<String> comboBoxDepartureHour;
	private JComboBox<String> comboBoxDepartureMin;
	private JRadioButton rdbtnFull;
	private JRadioButton rdbtnPartial;
	private ArrayList<Car> cars;
	private JLabel lblCreditCard;
	private JPanel PayPan;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JPanel panelCradit;
	private JRadioButton rdbtnCradit;
	private JRadioButton rdbtnCash;
	private JLabel lblPayCash;
	private JFormattedTextField frmtdtxtfldCreditCard;
	private JComboBox<String> comboBoxMonth;
	private JLabel lblCardExpiration;
	private JComboBox<String> comboBoxYear;
	private JButton btnPay;
	private JLabel lblAmount;
	private JTextField textFieldAmount;
	private JRadioButton rdbtnPrivate;
	private JRadioButton rdbtnBusiness;
	private JLabel lblSearchBy;
	private JRadioButton rdbtnIdCar;
	private JRadioButton rdbtnMemberId;
	private JPanel panelSearchType;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panelSearchBy;
	private JPanel panelSearchIdAndCar;
	private JLabel lblMemberId;
	private JTextField textFieldMemberID;
	private JPanel panelNewSubscribe;
	private Subscribe subscribe = null;

	public ResubscribePanel(String host, int port, VcpInfo vcpInfo) {
		this.host = host;
		this.port = port;
		this.vcpInfo = vcpInfo;
		initialize();
		listners();
	}

	private void initialize() {
		this.setSize(785, 575);
		setBackground(SystemColor.activeCaption);
		setLayout(null);

		JLabel lblResubscribe = new JLabel("Resubscribe");
		lblResubscribe.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblResubscribe.setBounds(320, 0, 144, 29);
		add(lblResubscribe);

		// ////////////////////////////////////////////
		btnReturn = new JButton("Return");
		btnReturn.setBounds(10, 519, 93, 35);
		add(btnReturn);

		panelNewSubscribe = new JPanel();
		panelNewSubscribe.setBackground(SystemColor.activeCaption);
		panelNewSubscribe.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Resubscribe",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNewSubscribe.setBounds(180, 190, 435, 320);
		add(panelNewSubscribe);
		panelNewSubscribe.setLayout(null);
		panelNewSubscribe.setVisible(false);

		JLabel lblParkingLot = new JLabel("Parking lot:");
		lblParkingLot.setBounds(8, 110, 114, 22);
		panelNewSubscribe.add(lblParkingLot);
		lblParkingLot.setFont(new Font("Tahoma", Font.BOLD, 18));

		btnSubmit = new JButton("Submit");
		btnSubmit.setEnabled(false);
		btnSubmit.setBounds(325, 280, 89, 30);
		panelNewSubscribe.add(btnSubmit);

		comboBoxParkingLot = new JComboBox<Integer>();
		comboBoxParkingLot.setBounds(159, 110, 255, 24);
		for (Parking_Lot pLot : getVcpInfo().getParkingLot()) {
			comboBoxParkingLot.addItem(pLot.getIdparkinglot());
		}
		panelNewSubscribe.add(comboBoxParkingLot);

		JLabel lblDepartureTime = new JLabel("Departure time:");
		lblDepartureTime.setBounds(8, 140, 145, 22);
		panelNewSubscribe.add(lblDepartureTime);
		lblDepartureTime.setFont(new Font("Tahoma", Font.BOLD, 18));

		comboBoxDepartureMin = new JComboBox<String>();
		comboBoxDepartureMin.setBounds(324, 140, 90, 24);
		panelNewSubscribe.add(comboBoxDepartureMin);

		comboBoxDepartureHour = new JComboBox<String>();
		comboBoxDepartureHour.setBounds(227, 140, 90, 24);
		Integer i = 0;
		for (i = 0; i < 25; i++) {
			if (i <= 9) {
				comboBoxDepartureHour.addItem("0" + i.toString());
				comboBoxDepartureMin.addItem("0" + i.toString());
			} else {
				comboBoxDepartureHour.addItem(i.toString());
				comboBoxDepartureMin.addItem(i.toString());
			}
		}
		panelNewSubscribe.add(comboBoxDepartureHour);

		for (i = 25; i < 60; i++) {
			comboBoxDepartureMin.addItem(i.toString());
		}

		PayPan = new JPanel();
		PayPan.setBackground(SystemColor.activeCaption);
		PayPan.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Payment",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PayPan.setBounds(35, 170, 356, 106);
		panelNewSubscribe.add(PayPan);
		PayPan.setLayout(null);

		panelCradit = new JPanel();
		panelCradit.setBackground(SystemColor.activeCaption);
		panelCradit.setBounds(10, 38, 337, 57);
		PayPan.add(panelCradit);
		panelCradit.setLayout(null);

		lblCreditCard = new JLabel("Cradit card:");
		lblCreditCard.setBounds(0, 0, 106, 22);
		panelCradit.add(lblCreditCard);
		lblCreditCard.setFont(new Font("Tahoma", Font.BOLD, 18));

		MaskFormatter formatterCreditCard;
		try {
			formatterCreditCard = new MaskFormatter("################");
			formatterCreditCard.setValidCharacters("0123456789");
			frmtdtxtfldCreditCard = new JFormattedTextField(formatterCreditCard);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this,
					"Formatter error: " + e.getMessage(), "Formatter ERRORE",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		frmtdtxtfldCreditCard.setBounds(200, 2, 137, 24);
		panelCradit.add(frmtdtxtfldCreditCard);

		lblCardExpiration = new JLabel("Card expiration:");
		lblCardExpiration.setBounds(0, 33, 146, 22);
		panelCradit.add(lblCardExpiration);
		lblCardExpiration.setFont(new Font("Tahoma", Font.BOLD, 18));

		comboBoxMonth = new JComboBox<String>();
		comboBoxMonth.setBounds(200, 33, 65, 24);
		panelCradit.add(comboBoxMonth);

		comboBoxYear = new JComboBox<String>();
		comboBoxYear.setBounds(269, 33, 67, 24);
		panelCradit.add(comboBoxYear);

		rdbtnCash = new JRadioButton("Cash");
		rdbtnCash.setBackground(SystemColor.activeCaption);
		buttonGroup_1.add(rdbtnCash);
		rdbtnCash.setBounds(298, 10, 49, 23);
		PayPan.add(rdbtnCash);

		rdbtnCradit = new JRadioButton("Cradit");
		rdbtnCradit.setBackground(SystemColor.activeCaption);
		rdbtnCradit.setSelected(true);
		buttonGroup_1.add(rdbtnCradit);
		rdbtnCradit.setBounds(220, 10, 55, 23);
		PayPan.add(rdbtnCradit);

		JLabel lblPaymentType = new JLabel("Payment Type:");
		lblPaymentType.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPaymentType.setBounds(75, 11, 135, 22);
		PayPan.add(lblPaymentType);

		lblPayCash = new JLabel("Press button \"Pay Cash\"");
		lblPayCash.setFont(new Font("Arial", Font.BOLD, 18));
		lblPayCash.setBounds(70, 53, 216, 22);
		PayPan.add(lblPayCash);

		btnPay = new JButton("Pay cradit");
		btnPay.setBounds(226, 280, 89, 30);
		panelNewSubscribe.add(btnPay);

		lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAmount.setBounds(8, 280, 78, 22);
		panelNewSubscribe.add(lblAmount);

		textFieldAmount = new JTextField();
		textFieldAmount.setText("0.0");
		textFieldAmount.setEditable(false);
		textFieldAmount.setBounds(96, 280, 86, 24);
		panelNewSubscribe.add(textFieldAmount);
		textFieldAmount.setColumns(10);

		JLabel lblSubscribeType = new JLabel("Subscribe Type:");
		lblSubscribeType.setBounds(8, 20, 143, 22);
		panelNewSubscribe.add(lblSubscribeType);
		lblSubscribeType.setFont(new Font("Tahoma", Font.BOLD, 18));

		JPanel panelSelectSubscribe = new JPanel();
		panelSelectSubscribe.setBounds(151, 11, 140, 46);
		panelNewSubscribe.add(panelSelectSubscribe);
		panelSelectSubscribe.setBackground(SystemColor.activeCaption);
		panelSelectSubscribe.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Select",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSelectSubscribe.setLayout(null);

		rdbtnPartial = new JRadioButton("Partial");
		buttonGroupSubscribeType.add(rdbtnPartial);
		rdbtnPartial.setBounds(6, 16, 71, 23);
		panelSelectSubscribe.add(rdbtnPartial);
		rdbtnPartial.setBackground(SystemColor.activeCaption);
		rdbtnPartial.setSelected(true);

		rdbtnFull = new JRadioButton("Full");
		buttonGroupSubscribeType.add(rdbtnFull);
		rdbtnFull.setBounds(79, 16, 55, 23);
		panelSelectSubscribe.add(rdbtnFull);
		rdbtnFull.setBackground(SystemColor.activeCaption);

		JLabel lblCustomerType = new JLabel("Customer type:");
		lblCustomerType.setBounds(6, 68, 143, 22);
		panelNewSubscribe.add(lblCustomerType);
		lblCustomerType.setFont(new Font("Tahoma", Font.BOLD, 18));

		JPanel panelSelectCustomer = new JPanel();
		panelSelectCustomer.setBounds(149, 57, 168, 46);
		panelNewSubscribe.add(panelSelectCustomer);
		panelSelectCustomer.setBackground(SystemColor.activeCaption);
		panelSelectCustomer.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Select",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSelectCustomer.setLayout(null);

		rdbtnPrivate = new JRadioButton("Private");
		buttonGroupCustomerType.add(rdbtnPrivate);
		rdbtnPrivate.setSelected(true);
		rdbtnPrivate.setBackground(SystemColor.activeCaption);
		rdbtnPrivate.setBounds(6, 16, 73, 23);
		panelSelectCustomer.add(rdbtnPrivate);

		rdbtnBusiness = new JRadioButton("Business");
		buttonGroupCustomerType.add(rdbtnBusiness);
		rdbtnBusiness.setBackground(SystemColor.activeCaption);
		rdbtnBusiness.setBounds(81, 16, 82, 23);
		panelSelectCustomer.add(rdbtnBusiness);

		panelSearchType = new JPanel();
		panelSearchType.setBackground(SystemColor.activeCaption);
		panelSearchType.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Search",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSearchType.setBounds(180, 65, 221, 82);
		add(panelSearchType);
		panelSearchType.setLayout(null);

		lblSearchBy = new JLabel("Search By:");
		lblSearchBy.setBounds(6, 16, 97, 22);
		panelSearchType.add(lblSearchBy);
		lblSearchBy.setFont(new Font("Tahoma", Font.BOLD, 18));

		rdbtnIdCar = new JRadioButton("ID & Car numbers");
		rdbtnIdCar.setSelected(true);
		buttonGroup.add(rdbtnIdCar);
		rdbtnIdCar.setBounds(105, 16, 111, 23);
		panelSearchType.add(rdbtnIdCar);
		rdbtnIdCar.setBackground(SystemColor.activeCaption);
		rdbtnIdCar.setHorizontalAlignment(SwingConstants.LEFT);

		rdbtnMemberId = new JRadioButton("Member ID");
		buttonGroup.add(rdbtnMemberId);
		rdbtnMemberId.setBounds(105, 43, 111, 23);
		panelSearchType.add(rdbtnMemberId);
		rdbtnMemberId.setBackground(SystemColor.activeCaption);
		rdbtnMemberId.setHorizontalAlignment(SwingConstants.LEFT);

		panelSearchBy = new JPanel();
		panelSearchBy.setBackground(SystemColor.activeCaption);
		panelSearchBy.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Search By",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSearchBy.setBounds(402, 65, 213, 82);
		add(panelSearchBy);
		panelSearchBy.setLayout(null);

		panelSearchIdAndCar = new JPanel();
		panelSearchIdAndCar.setBackground(SystemColor.activeCaption);
		panelSearchIdAndCar.setBounds(6, 16, 201, 59);
		panelSearchBy.add(panelSearchIdAndCar);
		panelSearchIdAndCar.setLayout(null);

		lblMemberId = new JLabel("Member ID:");
		lblMemberId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMemberId.setBounds(10, 32, 109, 22);
		panelSearchBy.add(lblMemberId);

		JLabel lblIdNumber = new JLabel("ID number:");
		lblIdNumber.setBounds(0, 0, 106, 22);
		panelSearchIdAndCar.add(lblIdNumber);
		lblIdNumber.setFont(new Font("Tahoma", Font.BOLD, 18));

		textFieldMemberID = new JTextField();
		textFieldMemberID.setBounds(133, 32, 70, 22);
		panelSearchBy.add(textFieldMemberID);
		textFieldMemberID.setColumns(10);
		textFieldMemberID.setVisible(false);

		try {
			MaskFormatter formatterID = new MaskFormatter("#########");
			formatterID.setValidCharacters("0123456789");
			txtIdNumber = new JFormattedTextField(formatterID);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this,
					"Formatter error: " + e.getMessage(), "Formatter ERRORE",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		txtIdNumber.setBounds(115, 0, 86, 24);
		panelSearchIdAndCar.add(txtIdNumber);
		txtIdNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdNumber.setText("");
		txtIdNumber.setColumns(10);

		JLabel lblCarNumber = new JLabel("Car number:");
		lblCarNumber.setBounds(0, 35, 114, 22);
		panelSearchIdAndCar.add(lblCarNumber);
		lblCarNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		try {
			formattedTextFieldCarNumber = new JFormattedTextField(
					new MaskFormatter("##-###-##"));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this,
					"Formatter error: " + e.getMessage(), "Formatter ERRORE",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		formattedTextFieldCarNumber.setBounds(115, 35, 86, 24);
		panelSearchIdAndCar.add(formattedTextFieldCarNumber);
		formattedTextFieldCarNumber
				.setHorizontalAlignment(SwingConstants.CENTER);

		btnSearch = new JButton("Search");
		btnSearch.setBounds(362, 152, 86, 35);
		add(btnSearch);

		for (i = 1; i <= 12; i++) {
			if (i <= 9)
				comboBoxMonth.addItem("0" + i.toString());
			else
				comboBoxMonth.addItem(i.toString());
		}
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
		Date date = new Date();
		String[] dateStr = dateFormat.format(date).split("-");
		Integer dateToStr = Integer.parseInt(dateStr[0]);
		for (i = 0; i <= 10; i++) {
			Integer finalDate = dateToStr + i;
			comboBoxYear.addItem(finalDate.toString());
		}
	}

	private void listners() {
		rdbtnIdCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldMemberID.setVisible(false);
				panelSearchIdAndCar.setVisible(true);
			}
		});

		rdbtnMemberId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldMemberID.setVisible(true);
				panelSearchIdAndCar.setVisible(false);
			}
		});

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean canResubscribe = false;
					if (rdbtnIdCar.isSelected()) {
						String clientIdStr = txtIdNumber.getText();
						if (clientIdStr.equals("         "))
							throw new Exception("You didnt enter ID number");
						Integer clientId = Integer.parseInt(clientIdStr);
						String carNumberStr = formattedTextFieldCarNumber
								.getText().replace("-", "");
						if (carNumberStr.equals("       "))
							throw new Exception("You didnt enter car number");
						Integer carNumber = Integer.parseInt(carNumberStr);

						Set<Entry<Integer, Subscribe>> subscribeEntry = getVcpInfo().getAllSubscribed().entrySet();
						Iterator<Entry<Integer, Subscribe>> subscribeIterator = subscribeEntry.iterator();
						while (subscribeIterator.hasNext()) {
							Subscribe findSubscribe = subscribeIterator.next().getValue();
							if (findSubscribe.getCarNum().equals(carNumber)
									&& findSubscribe.getIdClient().equals(
											clientId)) {
								if (!getRegisterController().isExpired(
										findSubscribe))
									throw new Exception(
											"No need to resubscribe. Subscribe is not expird yet");
								canResubscribe = true;
								subscribe = findSubscribe;
								break;
							}
						}
					} else {
						Integer memberID;
						try {
							memberID = Integer.parseInt(textFieldMemberID
									.getText());
						} catch (Exception e2) {
							throw new Exception(
									"You didnt enter valid member number");
						}
						Subscribe findSubscribe = getVcpInfo().getAllSubscribed().get(memberID);
						if (findSubscribe != null) {
							if (!getRegisterController().isExpired(findSubscribe)) {
								throw new Exception("No need to resubscribe. Subscribe is not expird yet");
							}
							canResubscribe = true;
							subscribe = findSubscribe;
						}
						
					}

					if (!canResubscribe) {
						throw new Exception(
								"There is no member like you enter, contact administrator");
					}

					if (subscribe.getSubscribType().equals("Partial")) {
						rdbtnFull.setSelected(false);
						rdbtnPartial.setSelected(true);
						comboBoxParkingLot.setSelectedIndex(subscribe
								.getIdparking() - 1);
						String[] departureTime = subscribe.getDepartureTime()
								.split(":");
						comboBoxDepartureHour.setSelectedItem(departureTime[0]);
						comboBoxDepartureMin.setSelectedItem(departureTime[1]);
						comboBoxDepartureHour.setEnabled(true);
						comboBoxDepartureMin.setEnabled(true);
						comboBoxParkingLot.setEnabled(true);
					} else {
						rdbtnPartial.setSelected(false);
						rdbtnFull.setSelected(true);
						comboBoxDepartureHour.setEnabled(false);
						comboBoxDepartureMin.setEnabled(false);
						comboBoxParkingLot.setEnabled(false);
					}

					if (subscribe.getCustomerType().equals("Private")) {
						rdbtnBusiness.setSelected(false);
						rdbtnPrivate.setSelected(true);
					} else {
						rdbtnPrivate.setSelected(false);
						rdbtnBusiness.setSelected(true);
					}
					changePayment();
					panelNewSubscribe.setVisible(true);
				} catch (Exception e2) {
					panelNewSubscribe.setVisible(false);
					getRegisterController().showWarningMsg(e2.getMessage());
				}

			}
		});

		rdbtnFull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxDepartureHour.setEnabled(false);
				comboBoxDepartureMin.setEnabled(false);
				comboBoxParkingLot.setEnabled(false);
				changePayment();
			}
		});

		rdbtnPartial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxDepartureHour.setEnabled(true);
				comboBoxDepartureMin.setEnabled(true);
				comboBoxParkingLot.setEnabled(true);
				changePayment();
			}
		});
		
		rdbtnCradit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCradit.setVisible(true);
				btnPay.setText("Pay cradit");
			}
		});

		rdbtnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCradit.setVisible(false);
				btnPay.setText("Pay cash");
			}
		});

		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rdbtnCradit.isSelected()) {
						if (frmtdtxtfldCreditCard.getText().equals(
								"                ")) {
							throw new Exception("You didnt insert cradit card");
						}
					}
					
					if (rdbtnPartial.isSelected()) {
						subscribe.setSubscribType(rdbtnPartial.getText());
						String departureTime = comboBoxDepartureHour
								.getSelectedItem()
								+ ":"
								+ comboBoxDepartureMin.getSelectedItem()
								+ ":00";
						subscribe.setDepartureTime(departureTime);
						subscribe.setIdparking(Integer
								.parseInt(comboBoxParkingLot.getSelectedItem()
										.toString()));
					}else{
						subscribe.setSubscribType(rdbtnFull.getText());
						subscribe.setDepartureTime(null);
						subscribe.setIdparking(null);
					}
					
					Date date = new Date();
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String startDate = dateFormat.format(date);
					subscribe.setStartDate(startDate);
					
					getRegisterController().showSeccussesMsg("Payment received");
					btnSubmit.setEnabled(true);
					btnPay.setEnabled(false);
				} catch (Exception e2) {
					getRegisterController().showWarningMsg(e2.getMessage());
				}

			}
		});
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRegisterController().updateResubscribe(subscribe);
				try {
					if(!getRegisterController().getResult().get(0).toString().equals("done"))
						throw new Exception("Error: Cant update subscribe.");
					getRegisterController().showSeccussesMsg("Resubscribe done.\nYour member number is: " + subscribe.getSubscribeNum() + " you will need him for check-in");
					getBtnReturn().doClick();
				} catch (Exception e2) {
					getRegisterController().showWarningMsg(e2.getMessage());
				}
				
			}
		});

	}

	private void changePayment() {
		String payment;
		if (rdbtnFull.isSelected())
			payment = getVcpInfo().getParkingPricingInfo().getFullOneCar()
					.toString();
		else
			payment = getVcpInfo().getParkingPricingInfo().getPartiallyOneCar()
					.toString();
		textFieldAmount.setText(payment);
	}

	public VcpInfo getVcpInfo() {
		return vcpInfo;
	}

	private RegisterController getRegisterController() {
		if (registerController == null || !registerController.isConnected()) {
			registerController = new RegisterController(host, port);
		}

		return registerController;
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

}
