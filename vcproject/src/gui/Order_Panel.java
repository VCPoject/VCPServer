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

import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

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
	private JTextField textFieldIdNumber;
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
	private JTextField textFieldPayment;
	private JLabel lblPayment;
	private VcpInfo vcpInfo;

	public Order_Panel(String host, int port,VcpInfo vcpInfo) {
		super();
		this.host = host;
		this.port = port;
		this.vcpInfo = vcpInfo;
		initialize();/* initilize panel gui */
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
		lblOrderType.setBounds(237, 99, 103, 22);
		add(lblOrderType);

		panelSelction = new JPanel();
		panelSelction.setBackground(SystemColor.activeCaption);
		panelSelction.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Select",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSelction.setBounds(352, 86, 185, 46);
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
		panelDetails.setBounds(211, 154, 364, 376);
		add(panelDetails);
		panelDetails.setLayout(null);

		lblIdNumber = new JLabel("ID number:");
		lblIdNumber.setBounds(6, 16, 197, 22);
		panelDetails.add(lblIdNumber);
		lblIdNumber.setFont(new Font("Tahoma", Font.BOLD, 18));

		textFieldIdNumber = new JTextField();
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
		/////////////////////////////////////////////
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
		btnSubmit.setBounds(247, 327, 103, 38);
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
		comboBoxDepartureMin.setBounds(283, 249, 65, 24);
		panelDetails.add(comboBoxDepartureMin);
		
		lblPayment = new JLabel("Payment:");
		lblPayment.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPayment.setBounds(6, 282, 87, 22);
		panelDetails.add(lblPayment);
		
		textFieldPayment = new JTextField();
		textFieldPayment.setBounds(213, 282, 137, 24);
		panelDetails.add(textFieldPayment);
		textFieldPayment.setColumns(10);

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
		// /////////////////////////////////////////

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
			Integer pLotNum = pLot.getIdparkinglot();
			getComboBoxParkLot().addItem(pLotNum.toString());
		}

	}

	private void oneTime() {/* set panel gui to adapt one time client */
		dateChooserArrival.setVisible(true);
		dateChooserDeparture.setVisible(true);
		
		textFieldPayment.setVisible(true);

		lblArrivalDay.setVisible(true);
		lblDepartureDay.setVisible(true);
		lblPayment.setVisible(true);

		lblTimeOfArrival.setBounds(6, 183, 197, 22);
		lblTimeOfArrival.setVisible(true);

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

	}

	private void TempClient() {/* change panel gui to adapt for temp client */
		dateChooserArrival.setVisible(false);
		dateChooserDeparture.setVisible(false);
		lblArrivalDay.setVisible(false);
		lblTimeOfArrival.setVisible(false);
		lblDepartureDay.setVisible(false);
		lblParkingLot.setVisible(false);
		comboBoxParkLot.setVisible(false);
		lblTimeOfDeparture.setVisible(true);
		comboBoxDepartureHour.setVisible(true);
		comboBoxDepartureMin.setVisible(true);
		comboBoxArrivalMin.setVisible(false);
		comboBoxArrivalHour.setVisible(false);
		comboBoxDepartureHour.setBounds(213, 115, 65, 24);
		comboBoxDepartureMin.setBounds(283, 115, 65, 24);
		lblTimeOfDeparture.setBounds(6, 115, 197, 22);
		textFieldPayment.setVisible(false);
		lblPayment.setVisible(false);
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

		rdbtnOneTimeClient.addActionListener(new ActionListener() {/*
																	 * listener
																	 * to one
																	 * time
																	 * client
																	 * radio
																	 * butten
																	 */
			public void actionPerformed(ActionEvent e) {
				oneTime();
			}
		});

		getBtnSubmit().addActionListener(new ActionListener() {/*
																 * listener to
																 * submit butten
																 */
			public void actionPerformed(ActionEvent e) {
				try {
					ClientEntity client;/* client entity */
					if (rdbtnTempClient.isSelected()) {
						client = new TempClient();
					} else {
						client = new OneTimeClient();
					}

					Car car = new Car();/* car entity */
					Order order = new Order();/* order entity */

					ArrayList<Object> result = null;
					String status = "did not checked in yet";

					client.setIdClient(Integer.parseInt(textFieldIdNumber
							.getText()));
					client.setEmail(textFieldEmail.getText());

					car.setCarNum(Integer.parseInt(textFieldCarNumber.getText()
							.replaceAll("-", "")));
					car.setClient(client);

					int parkId;
					if (comboBoxParkLot.getSelectedItem().toString()
							.equals("Select parking lot"))
						parkId = 1;
					else
						parkId = Integer.parseInt(comboBoxParkLot
								.getSelectedItem().toString());

					DateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");

					order.setCar(car);
					order.setClient(client);
					order.setIdparking(parkId);
					order.setStatus(status);

					if (rdbtnTempClient.isSelected()) {
						// get current date time with Date()
						Date date = new Date();
						String[] dateAndTime = dateFormat.format(date).split(
								"\\s");
						order.setArrivalDate(dateAndTime[0]);
						order.setArrivalTime(dateAndTime[1]);
						order.setType("temp");
						String addOrderQuery = "INSERT INTO `vcp_db`.`order`"
								+ "(`carNum`,`idclient`,`idparking`,`arrivalDate`,`arrivalTime`,`departureTime`,`status`,`type`) "
								+ "VALUES(?,?,?,?,?,?,?,?);";
						order.setQuery(addOrderQuery);
						String timeDeparture = comboBoxDepartureHour
								.getSelectedItem().toString()
								+ ":"
								+ comboBoxDepartureMin.getSelectedItem()
										.toString() + ":00";
						if (timeDeparture.contains("Hours")
								|| timeDeparture.contains("Min"))
							order.setDepartureTime("00:00:00");
						else
							order.setDepartureTime(timeDeparture);
						/* Check if OneTime client is selected */
					} else if (rdbtnOneTimeClient.isSelected()) {

						String timeArrival = null;
						String dateDeparture = null;

						String arrivalHour = comboBoxArrivalHour
								.getSelectedItem().toString();
						String arrivalMin = comboBoxArrivalMin
								.getSelectedItem().toString();
						if (!arrivalHour.equals("Hour")
								|| !arrivalMin.equals("Min")) {
							timeArrival = comboBoxArrivalHour.getSelectedItem()
									.toString()
									+ ":"
									+ comboBoxArrivalMin.getSelectedItem()
											.toString() + ":00";

						} else {
							throw new Exception(
									"You didn`t select arrivel time");
						}
						Date departureDate = dateChooserDeparture.getDate();
						DateFormat formatDate = new SimpleDateFormat(
								"yyyy-MM-dd");
						dateDeparture = formatDate.format(departureDate);

						Date arrivalDate = dateChooserArrival.getDate();

						String dateArrival = formatDate.format(arrivalDate);
						order.setArrivalDate(dateArrival);
						order.setArrivalTime(timeArrival);
						order.setDepartureDate(dateDeparture);

						order.setType("one time");
						String addOrderQuery = "INSERT INTO `vcp_db`.`order`"
								+ "(`carNum`,`idclient`,`idparking`,`arrivalDate`,`arrivalTime`,"
								+ "`departureDate`,`departureTime`,status,`type`)"
								+ "VALUES(?,?,?,?,?,?,?,?,?);";
						order.setQuery(addOrderQuery);
					}
					if (!checkIfClientExists(client)) {
						getMakeOrderController().addNewClient(client);
					}
					result = getMakeOrderController().getResult();
					if (result.get(0) != null)
						if (!checkIfCarExist(car)) {
							result = null;
							getMakeOrderController().addCarToDB(car);
						}
					result = getMakeOrderController().getResult();
					if (result.get(0) != null) {
						getMakeOrderController().addNewOrder(order);
						result = getMakeOrderController().getResult();
					}

					if (result.get(0).equals("done")) {
						getMakeOrderController().showSeccussesMsg("Order done");
						getBtnReturn().doClick();
					} else {
						getMakeOrderController().showWarningMsg(
								result.get(0).toString());

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
				car.getCarNum(), car.getClient().getIdClient() };
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
}
