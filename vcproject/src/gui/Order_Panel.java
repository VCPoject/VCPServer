package gui;

import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

import controler.FinancialCardController;
import controler.MakeOrderController;
import controler.VcpInfo;
import entity.*;

public class Order_Panel extends JPanel {

	/**
	 * 
	 */
	private String host;
	private int port = 5555;
	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JFormattedTextField textFieldIdNumber;
	private JFormattedTextField textFieldCarNumber;
	private JTextField textFieldEmail;
	private JPanel panelSelction;
	private JRadioButton rdbtnOneTimeClient;
	private JRadioButton rdbtnTempClient;
	private JPanel panelDetails;
	private JComboBox<String> comboBoxParkLot;
	private JLabel lblTimeOfDeparture;
	private JLabel lblParkingLot;
	private JLabel lblMakeOrder;
	private JLabel lblOrderType;
	private JLabel lblIdNumber;
	private JLabel lblCarNumber;
	private JLabel lblEmail;
	private JButton btnSubmit;
	private JLabel lblTimeOfArrival;
	private JButton btnReturn;
	private JLabel lblArrivalDay;
	private JDateChooser dateChooserArrival;
	private MakeOrderController makeOrderController;
	private JDateChooser dateChooserDeparture;
	private JLabel lblDepartureDay;
	private JComboBox<String> comboBoxArrivalHour;
	private JComboBox<String> comboBoxArrivalMin;
	private JComboBox<String> comboBoxDepartureHour;
	private JComboBox<String> comboBoxDepartureMin;
	private VcpInfo vcpInfo;
	private Payment_Frame paymentFrame;
	private FinancialCard fCard;
	private Float timeToPay;
	private JFormattedTextField frmtdtxtfldCreditCard;
	private JLabel lblCardExpiration;
	private JComboBox<String> comboBoxMonth;
	private JComboBox<String> comboBoxYear;
	private JButton btnPay;
	private JLabel lblAmount;
	private JTextField textFieldAmount;
	private Order order;
	private JLabel lblCreditCard;
	private JPanel PayPan;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JPanel panelCradit;
	private JRadioButton rdbtnCradit;
	private JRadioButton rdbtnCash;
	private JLabel lblPayCash;

	public Order_Panel(String host, int port, VcpInfo vcpInfo) {
		super();
		this.host = host;
		this.port = port;
		this.vcpInfo = vcpInfo;
		initialize();/* initilize panel gui */
		//oneTime();
		TempClient();/* change panel gui to adapt for temp client */
		listners();/* panel listeners */
	}

	private void initialize() {
		this.setSize(785, 575);
		setBackground(SystemColor.activeCaption);
		setLayout(null);
		lblMakeOrder = new JLabel("Make Order");
		lblMakeOrder.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblMakeOrder.setBounds(324, 27, 137, 23);
		add(lblMakeOrder);

		lblOrderType = new JLabel("Order type:");
		lblOrderType.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOrderType.setBounds(237, 74, 103, 22);
		add(lblOrderType);

		panelSelction = new JPanel();
		panelSelction.setBackground(SystemColor.activeCaption);
		panelSelction.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Select",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSelction.setBounds(352, 61, 185, 46);
		add(panelSelction);
		panelSelction.setLayout(null);

		rdbtnOneTimeClient = new JRadioButton("In advance");
		buttonGroup.add(rdbtnOneTimeClient);
		rdbtnOneTimeClient.setBounds(89, 16, 90, 23);
		panelSelction.add(rdbtnOneTimeClient);
		rdbtnOneTimeClient.setBackground(SystemColor.activeCaption);

		rdbtnTempClient = new JRadioButton("Park now");
		rdbtnTempClient.setSelected(true);
		buttonGroup.add(rdbtnTempClient);
		rdbtnTempClient.setBounds(6, 16, 81, 23);
		panelSelction.add(rdbtnTempClient);
		rdbtnTempClient.setBackground(SystemColor.activeCaption);

		panelDetails = new JPanel();
		panelDetails.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Insert details",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDetails.setBounds(211, 118, 364, 436);
		add(panelDetails);
		panelDetails.setLayout(null);

		lblIdNumber = new JLabel("ID number:");
		lblIdNumber.setBounds(6, 16, 197, 22);
		panelDetails.add(lblIdNumber);
		lblIdNumber.setFont(new Font("Tahoma", Font.BOLD, 18));

		try {
			MaskFormatter formatterID = new MaskFormatter("#########");
			formatterID.setValidCharacters("0123456789");
			textFieldIdNumber = new JFormattedTextField(formatterID);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this,
					"Formatter error: " + e.getMessage(), "Formatter ERRORE",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		textFieldIdNumber.setBounds(213, 16, 137, 24);
		panelDetails.add(textFieldIdNumber);
		textFieldIdNumber.setColumns(10);

		lblCarNumber = new JLabel("Car number:");
		lblCarNumber.setBounds(6, 49, 197, 22);
		panelDetails.add(lblCarNumber);
		lblCarNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		/* set format for insert car number */
		try {
			MaskFormatter formatter = new MaskFormatter("##-###-##");
			formatter.setValidCharacters("0123456789");
			textFieldCarNumber = new JFormattedTextField(formatter);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this,
					"Formatter error: " + e.getMessage(), "Formatter ERRORE",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		// ///////////////////////////////////////////
		textFieldCarNumber.setBounds(213, 49, 137, 24);
		panelDetails.add(textFieldCarNumber);
		textFieldCarNumber.setColumns(10);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(213, 82, 137, 24);
		panelDetails.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(6, 82, 197, 22);
		panelDetails.add(lblEmail);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 18));

		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(251, 387, 103, 38);
		panelDetails.add(btnSubmit);

		lblTimeOfDeparture = new JLabel("Time of departure:");
		panelDetails.add(lblTimeOfDeparture);
		lblTimeOfDeparture.setFont(new Font("Tahoma", Font.BOLD, 18));

		lblParkingLot = new JLabel("Parking lot:");
		lblParkingLot.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblParkingLot.setBounds(6, 117, 197, 22);
		panelDetails.add(lblParkingLot);
		lblParkingLot.setVisible(false);

		comboBoxParkLot = new JComboBox<String>();
		comboBoxParkLot.setBounds(213, 117, 137, 24);
		panelDetails.add(comboBoxParkLot);
		getComboBoxParkLot().addItem("Select parking lot");
		fillComboBoxParkLot();/* set into comboBox all the parking lot */

		lblTimeOfArrival = new JLabel("Time of arrival:");
		lblTimeOfArrival.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelDetails.add(lblTimeOfArrival);
		lblTimeOfArrival.setVisible(false);

		lblArrivalDay = new JLabel("Arrival day");
		lblArrivalDay.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblArrivalDay.setBounds(6, 150, 96, 22);
		lblArrivalDay.setVisible(false);
		panelDetails.add(lblArrivalDay);

		dateChooserArrival = new JDateChooser();
		dateChooserArrival.setBounds(213, 150, 137, 24);
		panelDetails.add(dateChooserArrival);

		lblDepartureDay = new JLabel("Departure day:");
		lblDepartureDay.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDepartureDay.setBounds(6, 216, 137, 22);
		panelDetails.add(lblDepartureDay);

		dateChooserDeparture = new JDateChooser();
		dateChooserDeparture.setBounds(213, 216, 137, 24);
		panelDetails.add(dateChooserDeparture);

		comboBoxArrivalHour = new JComboBox<String>();
		comboBoxArrivalHour.setBounds(213, 183, 65, 24);
		panelDetails.add(comboBoxArrivalHour);

		comboBoxArrivalMin = new JComboBox<String>();
		comboBoxArrivalMin.setBounds(283, 183, 65, 24);
		panelDetails.add(comboBoxArrivalMin);

		comboBoxDepartureHour = new JComboBox<String>();
		comboBoxDepartureHour.setBounds(213, 249, 65, 24);
		panelDetails.add(comboBoxDepartureHour);

		comboBoxDepartureMin = new JComboBox<String>();
		comboBoxDepartureMin.setBounds(283, 249, 67, 24);
		panelDetails.add(comboBoxDepartureMin);
		
		PayPan = new JPanel();
		PayPan.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PayPan.setBounds(6, 277, 356, 106);
		panelDetails.add(PayPan);
		PayPan.setLayout(null);
		
		panelCradit = new JPanel();
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
		buttonGroup_1.add(rdbtnCash);
		rdbtnCash.setBounds(298, 10, 49, 23);
		PayPan.add(rdbtnCash);
		
		rdbtnCradit = new JRadioButton("Cradit");
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
		
		for (Integer i = 1; i <= 12; i++) {
			if (i <= 9)
				comboBoxMonth.addItem("0" + i.toString());
			else
				comboBoxMonth.addItem(i.toString());
		}
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
		Date date = new Date();
		String[] dateStr = dateFormat.format(date).split("-");
		Integer dateToStr = Integer.parseInt(dateStr[0]);
		for (Integer i = 0; i <= 10; i++) {
			Integer finalDate = dateToStr + i;
			comboBoxYear.addItem(finalDate.toString());
		}
		
		btnPay = new JButton("Pay cradit");
		btnPay.setBounds(158, 387, 89, 38);
		panelDetails.add(btnPay);
		
		lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAmount.setBounds(6, 387, 78, 22);
		panelDetails.add(lblAmount);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setEditable(false);
		textFieldAmount.setBounds(86, 387, 66, 24);
		panelDetails.add(textFieldAmount);
		textFieldAmount.setColumns(10);

		/* setting valid hours for user to chose */
		comboBoxArrivalHour.addItem("Hour");
		comboBoxDepartureHour.addItem("Hour");
		for (Integer i = 0; i < 25; i++) {
			if (i <= 9) {
				comboBoxArrivalHour.addItem("0" + i.toString());
				comboBoxDepartureHour.addItem("0" + i.toString());
			} else {
				comboBoxArrivalHour.addItem(i.toString());
				comboBoxDepartureHour.addItem(i.toString());
			}
		}
		////////////////////////////////////////////

		/* setting valid minutes for user to chose */
		comboBoxArrivalMin.addItem("Min");
		comboBoxDepartureMin.addItem("Min");
		for (Integer i = 0; i < 60; i++) {
			if (i <= 9) {
				comboBoxArrivalMin.addItem("0" + i.toString());
				comboBoxDepartureMin.addItem("0" + i.toString());
			} else {
				comboBoxArrivalMin.addItem(i.toString());
				comboBoxDepartureMin.addItem(i.toString());
			}
		}
		// //////////////////////////////////////////////
		btnReturn = new JButton("Return");
		btnReturn.setBounds(10, 519, 93, 35);
		add(btnReturn);
	}

	public JComboBox<String> getComboBoxParkLot() {
		return comboBoxParkLot;
	}

	private void fillComboBoxParkLot() {/* set into comboBox all the parking lot */
		ArrayList<Parking_Lot> result = vcpInfo.getParkingLot();
		for (Parking_Lot pLot : result) {
			getComboBoxParkLot().addItem((Integer.toString(pLot.getIdparkinglot())));
		}

	}

	private void oneTime() {/* set panel gui to adapt one time client */
		dateChooserArrival.setVisible(true);
		dateChooserDeparture.setVisible(true);

		lblArrivalDay.setVisible(true);
		lblDepartureDay.setVisible(true);

		lblTimeOfArrival.setBounds(6, 183, 197, 22);
		lblTimeOfArrival.setVisible(true);
		
		lblAmount.setVisible(true);
		
		textFieldAmount.setVisible(true);
		
		comboBoxDepartureHour.setVisible(true);
		comboBoxArrivalHour.setVisible(true);

		comboBoxArrivalMin.setVisible(true);
		comboBoxDepartureMin.setVisible(true);
		
		lblParkingLot.setVisible(true);
		comboBoxParkLot.setVisible(true);

		lblTimeOfDeparture.setVisible(false);
		lblTimeOfDeparture.setVisible(true);
		lblTimeOfDeparture.setBounds(6, 249, 197, 22);
		lblTimeOfDeparture.setVisible(true);

		comboBoxDepartureHour.setBounds(213, 249, 65, 24);
		comboBoxDepartureMin.setBounds(283, 249, 65, 24);
		
		PayPan.setVisible(true);
		
		btnSubmit.setEnabled(false);
		btnPay.setVisible(true);
		
		

	}

	private void TempClient() {/* change panel gui to adapt for temp client */
		dateChooserArrival.setVisible(false);
		dateChooserDeparture.setVisible(false);
		lblArrivalDay.setVisible(false);
		lblTimeOfArrival.setVisible(false);
		lblDepartureDay.setVisible(false);
		lblParkingLot.setVisible(false);
		lblAmount.setVisible(false);
		comboBoxParkLot.setVisible(false);
		lblTimeOfDeparture.setVisible(true);
		comboBoxDepartureHour.setVisible(true);
		comboBoxDepartureMin.setVisible(true);
		comboBoxArrivalMin.setVisible(false);
		comboBoxArrivalHour.setVisible(false);
		textFieldAmount.setVisible(false);
		PayPan.setVisible(false);
		comboBoxDepartureHour.setBounds(213, 115, 65, 24);
		comboBoxDepartureMin.setBounds(283, 115, 65, 24);
		lblTimeOfDeparture.setBounds(6, 181, 197, 22);
		btnPay.setVisible(false);
		btnSubmit.setEnabled(true);
		
		this.repaint();
	}

	private void listners() {

		rdbtnTempClient.addActionListener(new ActionListener() {/*
																 * listener to
																 * temp client
																 * radio butten
																 */
			public void actionPerformed(ActionEvent e) {
				TempClient();
			}
		});
		/*	Listener to one time/temp client radio button	*/
		rdbtnOneTimeClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oneTime();
			}
		});
		
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(rdbtnCradit.isSelected()){
						if(frmtdtxtfldCreditCard.getText().equals("                ")){
							throw new Exception("You didnt insert cradit card");
						}
						getMakeOrderController().showSeccussesMsg("Payment recived, you can press now submit");
					}
					btnSubmit.setEnabled(true);
				} catch (Exception e) {
					getMakeOrderController().showWarningMsg(
							"Error in Payment: " + e.getMessage());
				}
				
				
					
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
		
		comboBoxDepartureMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String timeArrival = null;
					String dateDeparture = null;
					order = new Order();
					
					/* Checking for departure time selection */
					String timeDeparture = comboBoxDepartureHour.getSelectedItem().toString() + ":"	+ comboBoxDepartureMin.getSelectedItem().toString() + ":00";
					if (timeDeparture.contains("Hours") && timeDeparture.contains("Min"))/* if not selected hours and minutes */
						order.setDepartureTime("00:00:00");
					else if(!timeDeparture.contains("Hours") && timeDeparture.contains("Min")){/* if not selected min */
						timeDeparture = comboBoxDepartureHour.getSelectedItem().toString() + ":" + "00:00";
						order.setDepartureTime(timeDeparture);
					}
					else/* if all selected */
						order.setDepartureTime(timeDeparture);
					
					if (rdbtnOneTimeClient.isSelected()){
						Float onetime = getVcpInfo().getParkingPricingInfo().getOneTime();
						String arrivalHour = comboBoxArrivalHour.getSelectedItem().toString();
						String arrivalMin = comboBoxArrivalMin.getSelectedItem().toString();
						if (!arrivalHour.equals("Hour")	&& !arrivalMin.equals("Min")) {
							timeArrival = comboBoxArrivalHour.getSelectedItem().toString()
									+ ":" + comboBoxArrivalMin.getSelectedItem().toString() + ":00";
						} else {
							throw new Exception("You didn`t select arrivel time");
						}
						Date departureDate = dateChooserDeparture.getDate();
						if(departureDate == null)
							throw new Exception("You didnt select departure date");
						DateFormat formatDate = new SimpleDateFormat(
								"yyyy-MM-dd");
						dateDeparture = formatDate.format(departureDate);
						order.setDepartureDate(dateDeparture);
						Date arrivalDate = dateChooserArrival.getDate();
						if(arrivalDate == null)
							throw new Exception("You didnt select arrival date");
						String dateArrival = formatDate.format(arrivalDate);
						order.setArrivalDate(dateArrival);
						order.setArrivalTime(timeArrival);
						timeToPay = (Float) (findHoursToPay(order) * onetime);
						if(timeToPay < 0)
							throw new Exception("You didnt select valid time");
						textFieldAmount.setText(timeToPay.toString());
					}
				} catch (Exception e2) {
					getMakeOrderController().showWarningMsg(
							"Error in submit: " + e2.getMessage());
				}

			}
		});

		getBtnSubmit().addActionListener(new ActionListener() {/*
																 * listener to
																 * submit butten
																 */
			public void actionPerformed(ActionEvent e) {
				try {
					ClientEntity client = new ClientEntity();

					Car car = new Car();/* car entity */

					ArrayList<Object> result = null;
					String status = "did not checked in yet";/* status that will be applied to new order that didnt checked in */
					
					/*	Check and set client id	*/
					String cIDStr = textFieldIdNumber.getText();
					if(cIDStr.equals("         "))
						throw new Exception("You didnt enter any ID");
					Integer cID = Integer.parseInt(cIDStr);
					client.setIdClient(cID);/* setting client id */
					
					/*	Check and set client email	*/
					String cEmail = textFieldEmail.getText();
					if(!cEmail.isEmpty() && cEmail.contains("@"))
						client.setEmail(cEmail);/* setting client email */
					else
						throw new Exception("You didnt enter any email");

					/*	Check and set car number and his owner	*/
					Integer carNumber = Integer.parseInt(textFieldCarNumber.getText().replaceAll("-", ""));
					if(!carNumber.toString().isEmpty()){
					car.setCarNum(Integer.parseInt(textFieldCarNumber.getText().replaceAll("-", "")));/* setting car number */
					car.setClient(client.getIdClient());/* setting client entity to car entity */
					}
					else
						throw new Exception("You didnt enter car number");

					/*	Check for selected parking lot	*/
					String parkId = comboBoxParkLot.getSelectedItem().toString();
					if (parkId.equals("Select parking lot") && !rdbtnTempClient.isSelected())/* check if parking lot selected */
						throw new Exception("You didnt select parking lot");
					else if(parkId.equals("Select parking lot") && rdbtnTempClient.isSelected())
						parkId = (Integer.toString(vcpInfo.getDefultParkingLot().getIdparkinglot()));

					order.setCar(car.getCarNum());
					order.setClient(client.getIdClient());
					order.setIdparking(Integer.parseInt(parkId));
					order.setStatus(status);

					if (rdbtnTempClient.isSelected()) {/* temp client selected */
						// get current date time with Date()
						Date date = new Date();
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String[] dateAndTime = dateFormat.format(date).split(
								"\\s");
						order.setArrivalDate(dateAndTime[0]);
						order.setArrivalTime(dateAndTime[1]);
						order.setDepartureDate(dateAndTime[0]);
						order.setType("temp");
						String addOrderQuery = "INSERT INTO `vcp_db`.`order`"
								+ "(`carNum`,`idclient`,`idparking`,`arrivalDate`,`arrivalTime`,departureDate,`departureTime`,`status`,`type`) "
								+ "VALUES(?,?,?,?,?,?,?,?,?);";
						order.setQuery(addOrderQuery);
						
						/* Check if OneTime client is selected */
					} else if (rdbtnOneTimeClient.isSelected()) {
						order.setType("one time");
						
						String addOrderQuery = "INSERT INTO `vcp_db`.`order`"
								+ "(`carNum`,`idclient`,`idparking`,`arrivalDate`,`arrivalTime`,"
								+ "`departureDate`,`departureTime`,status,`type`)"
								+ "VALUES(?,?,?,?,?,?,?,?,?);";
						order.setQuery(addOrderQuery);
					}
					if (!checkIfClientExists(client)) {/* add new client in the db */
						getMakeOrderController().addNewClient(client);
					}
					result = getMakeOrderController().getResult();
					if (result.get(0) != null)
						if (!checkIfCarExist(car)) {
							result = null;
							getMakeOrderController().addCarToDB(car);
							getVcpInfo().getAllCars().add(car);
						}
					result = getMakeOrderController().getResult();
					if (result.get(0) != null) {
						getMakeOrderController().addNewOrder(order);
						result = getMakeOrderController().getResult();
						getVcpInfo().getAllOrders().add(order);
					}

					if (result.get(0).equals("done")) {
						if (rdbtnOneTimeClient.isSelected()) {
							getBtnReturn().doClick();
							FinancialCardController fController = new FinancialCardController(
									host, port);
							fCard = fController.getFinancialCard(order.getClient());
							String updateFCard;
							if (fCard == null) {
								fCard = new FinancialCard();
								fCard.setIdClient(order.getClient());
								fCard.setAmount(timeToPay);
								updateFCard = "INSERT INTO `vcp_employ`.`financial_card`(`idclient`,`amount`) VALUES(?,?);";
							} else {
								fCard.setAmount(fCard.getAmount() + timeToPay);
								updateFCard = "UPDATE `vcp_employ`.`financial_card` SET `amount` = ? WHERE `idclient` = ?;";
							}
							fCard.setQuery(updateFCard);
							fController.sendQueryToServer(fCard);
							if (fController.getResult().get(0).equals("done") && rdbtnOneTimeClient.isSelected())
								getMakeOrderController().showSeccussesMsg("Order done. "
										+ "Your financial card update with amount of: " + fCard.getAmount());
							else
								throw new Exception("Cant update financial card");
						}else if(rdbtnTempClient.isSelected()){
							getMakeOrderController().showSeccussesMsg("Order done. "
									+ "Payment will be taken at the exit from the parking lot");
							getBtnReturn().doClick();
						}else throw new Exception("error while try to add new order");
					} else {
						String ErrMsg = result.get(0).toString();
						throw new Exception(ErrMsg);
					}
				} catch (Exception e2) {
					getMakeOrderController().showWarningMsg(
							"Error in submit: " + e2.getMessage());
				} finally {
					getMakeOrderController().closeConnection();
				}

			}
		});

	}

	protected boolean checkIfCarExist(Car car) {
		Object[] isCarExists = {
				"SELECT count(`car`.`carNum`) FROM `vcp_db`.`car` WHERE carNum = ? AND idclient = ?;",
				car.getCarNum(), car.getClient() };
		getMakeOrderController().searchCar(isCarExists);
		ArrayList<Object> result = getMakeOrderController().getResult();
		if (result.get(0).toString().equals("0")) {
			String addCarQuery = "INSERT INTO `vcp_db`.`car`(`carNum`,`idclient`)VALUES(?,?);";
			car.setQuery(addCarQuery);
			return false;
		}

		return true;
	}

	protected boolean checkIfClientExists(ClientEntity client) {
		Object[] isClientExists = {
				"SELECT count(`client`.`idclient`) FROM `vcp_db`.`client` WHERE idclient = ?;",
				client.getIdClient() };
		getMakeOrderController().searchClient(isClientExists);
		ArrayList<Object> result = getMakeOrderController().getResult();
		if (result.get(0).toString().equals("0")) {
			String addClientQuery = "INSERT INTO `vcp_db`.`client`(`idclient`,`email`) VALUES(?,?);";
			client.setQuery(addClientQuery);
			return false;
		}

		return true;
	}

	public Payment_Frame getPaymentFrame(Float payment) {
		if (paymentFrame == null) {
			paymentFrame = new Payment_Frame(payment);
		}
		return paymentFrame;
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	private MakeOrderController getMakeOrderController() {
		if (makeOrderController == null || !makeOrderController.isConnected()) {
			makeOrderController = new MakeOrderController(host, port);
		}

		return makeOrderController;
	}

	public VcpInfo getVcpInfo() {
		return vcpInfo;
	}

	public Long findHoursToPay(Order order) {
		String dateStart = order.getArrivalDate() + " "
				+ order.getArrivalTime();
		String dateStop = order.getDepartureDate() + " "
				+ order.getDepartureTime();
		
		String[] arrTime = order.getArrivalTime().split(":");
		String[] depTime = order.getDepartureTime().split(":");
		Long toAdd = (long) 0;
		if(Integer.parseInt(depTime[1]) > Integer.parseInt(arrTime[1]))
			toAdd = (long) 1;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Get msec from each, and subtract.
		long diff = d2.getTime() - d1.getTime();
		long diffSeconds = diff / 1000;
		long diffMinutes = diff / (60 * 1000);
		long diffHours = diff / (60 * 60 * 1000);
		if (diffHours < 1) {
			return (long) 1;
		} else {
			return (long) diffHours + toAdd;
		}
	}
}
