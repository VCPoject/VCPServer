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
	private JFormattedTextField textFieldDeparturTime;
	private JFormattedTextField textFieldArrivalTime;
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
	private JDateChooser dateChooser;
	private MakeOrderController makeOrderController;

	public Order_Panel(String host, int port) {
		super();
		this.host = host;
		this.port = port;
		initialize();
		TempClient();
		listners();
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
		panelDetails.setBounds(211, 178, 364, 313);
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
		btnSubmit.setBounds(247, 264, 103, 38);
		panelDetails.add(btnSubmit);

		lblTimeOfDeparture = new JLabel("Time of departure:");
		panelDetails.add(lblTimeOfDeparture);
		lblTimeOfDeparture.setFont(new Font("Tahoma", Font.BOLD, 18));

		try {
			MaskFormatter formatterDeparturTime = new MaskFormatter("##:##");
			formatterDeparturTime.setValidCharacters("0123456789");
			textFieldDeparturTime = new JFormattedTextField(
					formatterDeparturTime);
			textFieldDeparturTime.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldDeparturTime.setText("Hh:Mm");
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this,
					"Formatter error: " + e.getMessage(), "Formatter ERRORE",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		panelDetails.add(textFieldDeparturTime);
		textFieldDeparturTime.setColumns(10);

		lblParkingLot = new JLabel("Parking lot:");
		lblParkingLot.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblParkingLot.setBounds(6, 148, 197, 22);
		panelDetails.add(lblParkingLot);
		lblParkingLot.setVisible(false);

		comboBoxParkLot = new JComboBox<String>();
		comboBoxParkLot.setBounds(213, 148, 137, 24);
		panelDetails.add(comboBoxParkLot);
		getComboBoxParkLot().addItem("Select parking lot");
		fillComboBoxParkLot();

		lblTimeOfArrival = new JLabel("Time of arrival:");
		lblTimeOfArrival.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelDetails.add(lblTimeOfArrival);
		lblTimeOfArrival.setVisible(false);

		try {
			MaskFormatter formatterDeparturTime = new MaskFormatter("##:##");
			formatterDeparturTime.setValidCharacters("0123456789");
			textFieldArrivalTime = new JFormattedTextField(
					formatterDeparturTime);
			textFieldArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldArrivalTime.setText("Hh:Mm");
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this,
					"Formatter error: " + e.getMessage(), "Formatter ERRORE",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		panelDetails.add(textFieldArrivalTime);
		textFieldArrivalTime.setColumns(10);

		lblArrivalDay = new JLabel("Arrival day");
		lblArrivalDay.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblArrivalDay.setBounds(6, 115, 96, 22);
		panelDetails.add(lblArrivalDay);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(213, 115, 137, 24);
		panelDetails.add(dateChooser);
		dateChooser.setVisible(false);

		btnReturn = new JButton("Return");
		btnReturn.setBounds(10, 519, 93, 35);
		add(btnReturn);
	}

	public JComboBox<String> getComboBoxParkLot() {
		return comboBoxParkLot;
	}

	private void fillComboBoxParkLot() {
		Object[] sqlMsg = { "SELECT idparking FROM vcp_db.parking_lot" };
		getMakeOrderController().sendQueryToServer(sqlMsg);
		ArrayList<Object> result = null;
		result = getMakeOrderController().getResult();
		for (Object obj : result) {
			getComboBoxParkLot().addItem(obj.toString());
		}

	}

	private void oneTime() {
		dateChooser.setVisible(true);

		lblArrivalDay.setVisible(true);

		lblTimeOfArrival.setBounds(6, 214, 197, 22);
		lblTimeOfArrival.setVisible(true);

		textFieldArrivalTime.setBounds(213, 214, 52, 20);
		textFieldArrivalTime.setVisible(true);

		lblParkingLot.setVisible(true);
		comboBoxParkLot.setVisible(true);

		lblTimeOfDeparture.setVisible(false);
		lblTimeOfDeparture.setVisible(true);
		lblTimeOfDeparture.setBounds(6, 181, 197, 22);

		textFieldDeparturTime.setBounds(213, 181, 52, 24);
		lblTimeOfDeparture.setVisible(true);
		textFieldDeparturTime.setVisible(true);

	}

	private void TempClient() {
		dateChooser.setVisible(false);
		lblArrivalDay.setVisible(false);
		lblTimeOfArrival.setVisible(false);
		textFieldArrivalTime.setVisible(false);
		lblParkingLot.setVisible(false);
		comboBoxParkLot.setVisible(false);
		textFieldDeparturTime.setVisible(false);
		lblTimeOfDeparture.setVisible(false);
		lblTimeOfArrival.setBounds(6, 153, 197, 22);
		textFieldArrivalTime.setBounds(213, 152, 52, 24);
		lblTimeOfDeparture.setBounds(6, 119, 197, 22);
		textFieldDeparturTime.setBounds(213, 115, 52, 20);
	}

	private void listners() {

		rdbtnTempClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TempClient();
			}
		});

		rdbtnOneTimeClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oneTime();
			}
		});

		getBtnSubmit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Object> result = null;
				int idnum = Integer.parseInt(textFieldIdNumber.getText());
				int carnum = Integer.parseInt(textFieldCarNumber.getText().replaceAll("-", ""));
				int parkId;
				if (comboBoxParkLot.getSelectedItem().toString().equals("Select parking lot"))
					parkId = 1;
				else
					parkId = Integer.parseInt(comboBoxParkLot.getSelectedItem().toString());
				String email = textFieldEmail.getText();
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				Date date = new Date();
				String[] dateAndTime = dateFormat.format(date).split("\\s");
				Object[] addNewClient = {"INSERT INTO `vcp_db`.`client` (`idclient`, `email`) VALUES(?,?);",
																							idnum, email };
				getMakeOrderController().sendQueryToServer(addNewClient);
				Object[] addNewCar = {"INSERT INTO `vcp_db`.`car` (`carNum`, `idclient`) VALUES (?,?);",
						carnum, idnum };
				getMakeOrderController().sendQueryToServer(addNewCar);
				
				if (rdbtnTempClient.isSelected()) {
					Object[] addOrderMsg = {
							"INSERT INTO `vcp_db`.`order`(`carNum`,`idparking`,`startDate`,`startTime`,`status`) VALUES (?,?,?,?,?);",
							carnum, parkId, dateAndTime[0], dateAndTime[1],
							rdbtnTempClient.getText() };
					getMakeOrderController().sendQueryToServer(addOrderMsg);
					result = getMakeOrderController().getResult();
				}
				
				if (result.get(0).equals("done")) {
					getMakeOrderController().showSeccussesMsg("Order done");
				}
				getMakeOrderController().closeConnection();
			}
		});

	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	public void threadSleep() {
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private MakeOrderController getMakeOrderController() {
		if(makeOrderController == null || !makeOrderController.isConnected()){
			makeOrderController = new MakeOrderController(host,port);
		}
			
		return makeOrderController;
	}
}
