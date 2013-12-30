package gui;

import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import entity.Car;
import entity.Parking_Lot;
import entity.Parking_Places;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register_Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnReturn;
	private final ButtonGroup buttonGroupSubscribeType = new ButtonGroup();
	private final ButtonGroup buttonGroupCustomerType = new ButtonGroup();
	private JTextField txtIdNumber;
	private JFormattedTextField formattedTextFieldCarNumber;
	private JButton btnSubmit;
	private JButton btnAddCar;
	private JComboBox<String> comboBoxAddCar;
	private JComboBox<Integer> comboBoxParkingLot;
	private JDateChooser startDateChooser;
	private JComboBox<String> comboBoxDepartureHour;
	private JComboBox<String> comboBoxDepartureMin;
	private JRadioButton rdbtnFull;
	private JRadioButton rdbtnPartial;
	private ArrayList<Parking_Lot> parkingLot;
	private ArrayList<Car> cars;

	public Register_Panel(ArrayList<Parking_Lot> parkingLot) {
		super();
		this.parkingLot = parkingLot;
		initialize();
		listners();
	}

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
		panelNewSubscribe.setBounds(181, 225, 423, 251);
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

		JLabel lblStartDate = new JLabel("Start date:");
		lblStartDate.setBounds(8, 96, 114, 22);
		panelNewSubscribe.add(lblStartDate);
		lblStartDate.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblParkingLot = new JLabel("Parking lot:");
		lblParkingLot.setBounds(8, 136, 114, 22);
		panelNewSubscribe.add(lblParkingLot);
		lblParkingLot.setFont(new Font("Tahoma", Font.BOLD, 18));

		txtIdNumber = new JTextField();
		txtIdNumber.setBounds(159, 16, 255, 24);
		panelNewSubscribe.add(txtIdNumber);
		txtIdNumber.setColumns(10);

		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(325, 217, 89, 23);
		panelNewSubscribe.add(btnSubmit);

		comboBoxParkingLot = new JComboBox<Integer>();
		comboBoxParkingLot.setBounds(159, 136, 255, 24);
		for (Parking_Lot pLot : getParkingLot()) {
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

		startDateChooser = new JDateChooser();
		startDateChooser.setBounds(159, 96, 255, 24);
		panelNewSubscribe.add(startDateChooser);

		JLabel lblDepartureTime = new JLabel("Departure time:");
		lblDepartureTime.setBounds(8, 176, 145, 22);
		panelNewSubscribe.add(lblDepartureTime);
		lblDepartureTime.setFont(new Font("Tahoma", Font.BOLD, 18));

		comboBoxDepartureHour = new JComboBox<String>();
		comboBoxDepartureHour.setBounds(227, 176, 90, 24);
		panelNewSubscribe.add(comboBoxDepartureHour);

		comboBoxDepartureMin = new JComboBox<String>();
		comboBoxDepartureMin.setBounds(324, 176, 90, 24);
		panelNewSubscribe.add(comboBoxDepartureMin);

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

		JRadioButton rdbtnPrivate = new JRadioButton("Private");
		buttonGroupCustomerType.add(rdbtnPrivate);
		rdbtnPrivate.setSelected(true);
		rdbtnPrivate.setBackground(SystemColor.activeCaption);
		rdbtnPrivate.setBounds(6, 16, 73, 23);
		panelSelectCustomer.add(rdbtnPrivate);

		JRadioButton rdbtnBusiness = new JRadioButton("Business");
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
	}

	private void listners() {
		btnAddCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtIdNumber.getText().toString().isEmpty()){
				boolean canAdd = true;
				String carNumberStr = formattedTextFieldCarNumber.getText();
				if (carNumberStr != null) {
					for (int i = 0; i < comboBoxAddCar.getItemCount(); i++) {
						if (comboBoxAddCar.getItemAt(i).equals(carNumberStr)) {
							JOptionPane.showMessageDialog(new JFrame(),
									"Add car error: "
											+ "You already insert car number: "
											+ carNumberStr);
							canAdd = false;
						}
					}
					if (canAdd) {
						
						comboBoxAddCar.setEnabled(true);
						comboBoxAddCar.addItem(carNumberStr);
					}
					formattedTextFieldCarNumber.setText(null);
				}

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
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public ArrayList<Parking_Lot> getParkingLot() {
		return parkingLot;
	}

	public ArrayList<Car> getCars() {
		if (cars == null)
			cars = new ArrayList<Car>();
		return cars;
	}
}
