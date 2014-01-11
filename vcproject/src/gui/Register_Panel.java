package gui;

import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.DateUtil;

import controler.RegisterController;
import controler.VcpInfo;
import entity.Car;
import entity.Parking_Lot;
import entity.Subscribe;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register_Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private String host;
	private int port;
	private RegisterController registerController;
	private JButton btnReturn;
	private final ButtonGroup buttonGroupSubscribeType = new ButtonGroup();
	private final ButtonGroup buttonGroupCustomerType = new ButtonGroup();
	private JFormattedTextField txtIdNumber;
	private JFormattedTextField formattedTextFieldCarNumber;
	private JButton btnSubmit;
	private JButton btnAddCar;
	private JComboBox<String> comboBoxAddCar;
	private JComboBox<Integer> comboBoxParkingLot;
	private JComboBox<String> comboBoxDepartureHour;
	private JComboBox<String> comboBoxDepartureMin;
	private JRadioButton rdbtnFull;
	private JRadioButton rdbtnPartial;
	/** vcpInfo is a controller that run on start-up of the 
	 * application and download all the info form the DB
	 * its contains all:
	 * order,subscribed,reservation,employees,parking lot,
	 * parking places,clients,default parking lot,cars
	 */
	private VcpInfo vcpInfo;
	
	/**
	 * cars contains all cars info
	 */
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

	/**
	 * This panel is for make register.
	 * @param host for make connection with server side
	 * @param port for make connection with server side
	 * @param vcpInfo for get info from DB
	 */
	public Register_Panel(String host, int port, VcpInfo vcpInfo) {
		super();
		this.host = host;
		this.port = port;
		this.vcpInfo = vcpInfo;
		initialize();
		listners();
	}

	/**
	 * Initialize the panel of register.
	 */
	private void initialize() {
		this.setSize(785, 575);
		setBackground(SystemColor.activeCaption);
		setLayout(null);

		btnReturn = new JButton("Return");
		btnReturn.setBounds(10, 519, 93, 35);
		add(btnReturn);

		JLabel lblRegister = new JLabel("Register");
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblRegister.setBounds(343, 27, 99, 29);
		add(lblRegister);

		JLabel lblSubscribeType = new JLabel("Subscribe Type:");
		lblSubscribeType.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSubscribeType.setBounds(254, 100, 143, 22);
		add(lblSubscribeType);

		JPanel panelSelectSubscribe = new JPanel();
		panelSelectSubscribe.setBackground(SystemColor.activeCaption);
		panelSelectSubscribe.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Select",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSelectSubscribe.setBounds(397, 87, 140, 46);
		add(panelSelectSubscribe);
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

		JPanel panelNewSubscribe = new JPanel();
		panelNewSubscribe.setBackground(SystemColor.activeCaption);
		panelNewSubscribe.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "New subscribe",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNewSubscribe.setBounds(180, 190, 423, 320);
		add(panelNewSubscribe);
		panelNewSubscribe.setLayout(null);

		JLabel lblIdNumber = new JLabel("ID number:");
		lblIdNumber.setBounds(8, 16, 143, 29);
		panelNewSubscribe.add(lblIdNumber);
		lblIdNumber.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblCarNumber = new JLabel("Car number:");
		lblCarNumber.setBounds(8, 56, 114, 22);
		panelNewSubscribe.add(lblCarNumber);
		lblCarNumber.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblParkingLot = new JLabel("Parking lot:");
		lblParkingLot.setBounds(8, 91, 114, 22);
		panelNewSubscribe.add(lblParkingLot);
		lblParkingLot.setFont(new Font("Tahoma", Font.BOLD, 18));

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

		txtIdNumber.setBounds(159, 16, 255, 24);
		panelNewSubscribe.add(txtIdNumber);
		txtIdNumber.setColumns(10);

		btnSubmit = new JButton("Submit");
		btnSubmit.setEnabled(false);
		btnSubmit.setBounds(325, 280, 89, 30);
		panelNewSubscribe.add(btnSubmit);

		comboBoxParkingLot = new JComboBox<Integer>();
		comboBoxParkingLot.setBounds(159, 91, 255, 24);
		for (Parking_Lot pLot : getVcpInfo().getParkingLot()) {
			comboBoxParkingLot.addItem(pLot.getIdparkinglot());
		}
		panelNewSubscribe.add(comboBoxParkingLot);

		comboBoxAddCar = new JComboBox<String>();
		comboBoxAddCar.setBounds(308, 56, 106, 24);
		panelNewSubscribe.add(comboBoxAddCar);
		comboBoxAddCar.setEnabled(false);

		btnAddCar = new JButton("Add");
		btnAddCar.setBounds(233, 56, 70, 24);
		panelNewSubscribe.add(btnAddCar);

		JLabel lblDepartureTime = new JLabel("Departure time:");
		lblDepartureTime.setBounds(8, 131, 145, 22);
		panelNewSubscribe.add(lblDepartureTime);
		lblDepartureTime.setFont(new Font("Tahoma", Font.BOLD, 18));

		comboBoxDepartureMin = new JComboBox<String>();
		comboBoxDepartureMin.setBounds(324, 131, 90, 24);
		panelNewSubscribe.add(comboBoxDepartureMin);

		comboBoxDepartureHour = new JComboBox<String>();
		comboBoxDepartureHour.setBounds(227, 131, 90, 24);
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

		JLabel lblCustomerType = new JLabel("Customer type:");
		lblCustomerType.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCustomerType.setBounds(254, 144, 143, 22);
		add(lblCustomerType);

		JPanel panelSelectCustomer = new JPanel();
		panelSelectCustomer.setBackground(SystemColor.activeCaption);
		panelSelectCustomer.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Select",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSelectCustomer.setBounds(397, 133, 168, 46);
		add(panelSelectCustomer);
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

		try {
			formattedTextFieldCarNumber = new JFormattedTextField(
					new MaskFormatter("##-###-##"));
			formattedTextFieldCarNumber.setBounds(159, 56, 70, 24);
			panelNewSubscribe.add(formattedTextFieldCarNumber);
			formattedTextFieldCarNumber
					.setHorizontalAlignment(SwingConstants.CENTER);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this,
					"Formatter error: " + e.getMessage(), "Formatter ERRORE",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		PayPan = new JPanel();
		PayPan.setBackground(SystemColor.activeCaption);
		PayPan.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Payment",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PayPan.setBounds(35, 164, 356, 106);
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
	
	/**
	 * Listeners of the GUI components.
	 */
	private void listners() {
		btnAddCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean canAdd = false;
					String idclient = txtIdNumber.getText().toString();
					if (idclient.equals("         "))
						throw new Exception("Enter ID first");
					String carNumberStr = formattedTextFieldCarNumber.getText()
							.toString();
					if (!carNumberStr.equals("  -   -  ")
							&& carNumberStr != null) {
						carNumberStr = carNumberStr.replace("-", "");
						for (int i = 0; i < comboBoxAddCar.getItemCount(); i++) {
							if (comboBoxAddCar.getItemAt(i)
									.equals(carNumberStr)) {
								throw new Exception(
										"Add car error: You already insert car number: "
												+ carNumberStr);
							}
						}
						Car registerCar = new Car();
						registerCar.setCarNum(Integer.parseInt(carNumberStr));
						registerCar.setClient(Integer.parseInt(idclient));
						for (Car car : getVcpInfo().getAllCars()) {
							if (car.getCarNum().equals(Integer.parseInt(carNumberStr))
									&& car.getClient().equals(Integer.parseInt(idclient))) {
								canAdd = true;
								break;
							}
						}
						if (!canAdd) {
							throw new Exception("Add car error: id " + idclient
									+ "is not associated with car number: "
									+ carNumberStr);
						}
						Set<Entry<Integer, Subscribe>> subscribeEntry=getVcpInfo().getAllSubscribed().entrySet();
						Iterator<Entry<Integer, Subscribe>> subscribeIterator=subscribeEntry.iterator();
						Subscribe findSubscribe;
						
						while(subscribeIterator.hasNext()){
							findSubscribe=subscribeIterator.next().getValue();
							if (findSubscribe.getCarNum().equals(Integer.parseInt(carNumberStr)) && findSubscribe.getIdClient().equals(Integer.parseInt(idclient))) {
								if (getRegisterController().isExpired(findSubscribe)) {
									throw new Exception(carNumberStr + " is already registerd.\nYou can to make resubscribe to member id: " + findSubscribe.getSubscribeNum());
								}
								else{
									throw new Exception(carNumberStr + " is already registerd and valid, no need to make register\n you can check-in with member id: " + findSubscribe.getSubscribeNum());
								}
							}else if(findSubscribe.getCarNum().equals(Integer.parseInt(carNumberStr))){
								throw new Exception("Car number " + carNumberStr + " is allready registerd to other client");
							}
						}

						comboBoxAddCar.setEnabled(true);
						comboBoxAddCar.addItem(carNumberStr);
						changePayment();
						formattedTextFieldCarNumber.setText(null);
					} else {
						throw new Exception("You didn`t insert car number");
					}
				} catch (Exception e2) {
					getRegisterController().showWarningMsg(e2.getMessage());
				}

			}
		});

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for (int i = 0; i < comboBoxAddCar.getItemCount(); i++) {
						Subscribe newSubscribe = new Subscribe();
						String carnumber = comboBoxAddCar.getItemAt(i).replace(
								"-", "");
						newSubscribe.setCarNum(Integer.parseInt(carnumber));
						newSubscribe.setIdClient(Integer.parseInt(txtIdNumber
								.getText().toString()));
						Date date = new Date();
						DateFormat dateFormat = new SimpleDateFormat(
								"yyyy-MM-dd");
						String todayDate = dateFormat.format(date);
						newSubscribe.setStartDate(todayDate);
						date = getRegisterController().addDays(date, 28);
						newSubscribe.setEndDate(dateFormat.format(date));
						String subscribeQuery;
						if (rdbtnPartial.isSelected()) {
							subscribeQuery = "INSERT INTO `vcp_db`.`subscribe`(`idclient`,`carNum`,`idparking`,`startDate`,`subscribType`,`customerType`,`leavingTime`) VALUES(?,?,?,?,?,?,?);";
							newSubscribe.setSubscribeType(rdbtnPartial.getText());
							newSubscribe.setIdparking(Integer
									.parseInt(comboBoxParkingLot
											.getSelectedItem().toString()));
							newSubscribe.setDepartureTime(comboBoxDepartureHour
									.getSelectedItem()
									+ ":"
									+ comboBoxDepartureMin.getSelectedItem()
									+ ":00");
						} else {
							newSubscribe.setSubscribeType(rdbtnFull.getText());
							subscribeQuery = "INSERT INTO `vcp_db`.`subscribe`(`idclient`,`carNum`,`startDate`,`endDate`,`subscribType`,`customerType`) VALUES(?,?,?,?,?,?);";
						}
						if (rdbtnPrivate.isSelected())
							newSubscribe.setCustomerType(rdbtnPrivate.getText());
						else
							newSubscribe.setCustomerType(rdbtnBusiness
									.getText());
						newSubscribe.setSubscribeNum(getVcpInfo()
								.getAllSubscribed().size() + 1);
						newSubscribe.setQuery(subscribeQuery);
						getVcpInfo().getAllSubscribed().put(newSubscribe.getSubscribeNum(),newSubscribe);
						getRegisterController().addNewSubscribe(newSubscribe);
						if (!getRegisterController().getResult().get(0)
								.equals("done"))
							throw new Exception(
									"Error while try to add subscribe to database");
						getRegisterController()
								.showSeccussesMsg(
										"Your subscribe number for car "
												+ carnumber
												+ " is: "
												+ newSubscribe
														.getSubscribeNum()
												+ "\n"
												+ "you will need to enter him when you make check-in");
					}
					getRegisterController().showSeccussesMsg(
							"Wellcome!\nYou are now Subscribe.");
				} catch (Exception e2) {
					getRegisterController().showWarningMsg(e2.getMessage());
				}

			}
		});

		rdbtnFull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxParkingLot.setEnabled(false);
				comboBoxDepartureHour.setEnabled(false);
				comboBoxDepartureMin.setEnabled(false);
			}
		});

		rdbtnPartial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxParkingLot.setEnabled(true);
				comboBoxDepartureHour.setEnabled(true);
				comboBoxDepartureMin.setEnabled(true);
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
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (rdbtnCradit.isSelected()) {
						if (frmtdtxtfldCreditCard.getText().equals(
								"                ")) {
							throw new Exception("You didnt insert cradit card");
						}
					}

					if (textFieldAmount.getText().equals("0.0"))
						throw new Exception("You didn't insert any car");
					getRegisterController().showSeccussesMsg(
							"Payment recived, you can press now submit");
					btnSubmit.setEnabled(true);
					btnPay.setEnabled(false);
				} catch (Exception e) {
					getRegisterController().showWarningMsg(
							"Error in Payment: " + e.getMessage());
				}

			}
		});
	}


	/**
	 * Check if the client was registered by the client id. 
	 * @param clientID is the ID of the client
	 * @return true if client is not subscribe but in the system and his car is
	 * @throws Exception
	 */
	public boolean checkForClientValidity(Integer clientID) throws Exception {
		Set<Entry<Integer, Subscribe>> subscribeEntry = getVcpInfo().getAllSubscribed().entrySet();
		Iterator<Entry<Integer, Subscribe>> subscribeIterator = subscribeEntry.iterator();
		Subscribe subscribe;
		while(subscribeIterator.hasNext()) {
			subscribe=subscribeIterator.next().getValue();
			if (subscribe.getIdClient().equals(clientID)) {
				return true;
			}
		}

		return false;
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	
	/**
	 * Getting all cars info
	 * @return ArrayList with all the cars that in the system.
	 */
	public ArrayList<Car> getCars() {
		if (cars == null)
			cars = new ArrayList<Car>();
		return cars;
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

	/**
	 * Change the amount of payment by type of register and the number of cars
	 * The amount is insert to textFieldAmount.
	 */
	private void changePayment() {
		String payment;
		if (rdbtnFull.isSelected())
			if (comboBoxAddCar.getItemCount() > 1)
				payment = getVcpInfo().getParkingPricingInfo().getFull(comboBoxAddCar.getItemCount()).toString();
			else
				payment = getVcpInfo().getParkingPricingInfo().getFullOneCar().toString();
		else if (comboBoxAddCar.getItemCount() > 1)
			payment = getVcpInfo().getParkingPricingInfo().getPartially(comboBoxAddCar.getItemCount()).toString();
		else
			payment = getVcpInfo().getParkingPricingInfo().getPartiallyOneCar().toString();
		textFieldAmount.setText(payment);
	}
	
}
