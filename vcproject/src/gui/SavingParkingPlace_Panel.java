package gui;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controler.ParkingLot_controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class SavingParkingPlace_Panel extends JPanel {
	
	private JRadioButton rdbtnParkinLot;
	private JRadioButton rdbtnFloor;
	private JRadioButton rdbtnLine;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnParkingPlaceNo;
	private JButton btnSave;
	private JButton btnExit;
	private JComboBox <String> comboBoxParkinglot;
	private JComboBox <String> comboBoxFloor;  
	private JComboBox <String> comboBoxLine;
	private JComboBox <String> comboBoxParkingPlace;
	private ParkingLot_controller parkinglotcontroller;
	
	
	private static final long serialVersionUID = 1L;
	
	public SavingParkingPlace_Panel() {
		super();
		initialize();
		setLayout(null);
	}
	
	private void initialize() {
		this.setSize(785, 575);
		JLabel lblReserv = new JLabel("Reservation");
		lblReserv.setBounds(176, 34, 231, 52);
		lblReserv.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblReserv.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblReserv);
		
		rdbtnParkinLot = new JRadioButton("parking lot");
		rdbtnParkinLot.setBounds(51, 150, 109, 23);
		rdbtnParkinLot.setSelected(true);
		buttonGroup.add(rdbtnParkinLot);
		add(rdbtnParkinLot);
		
		rdbtnFloor = new JRadioButton("floor");
		rdbtnFloor.setBounds(51, 200, 109, 23);
		rdbtnFloor.setSelected(true);
		buttonGroup.add(rdbtnFloor);
		add(rdbtnFloor);
		
		rdbtnLine = new JRadioButton("line");
		rdbtnLine.setBounds(51, 260, 109, 23);
		rdbtnLine.setSelected(true);
		buttonGroup.add(rdbtnLine);
		add(rdbtnLine);
		
		rdbtnParkingPlaceNo = new JRadioButton("parking place no.");
		rdbtnParkingPlaceNo.setBounds(51, 323, 109, 23);
		rdbtnParkingPlaceNo.setSelected(true);
		buttonGroup.add(rdbtnParkingPlaceNo);
		add(rdbtnParkingPlaceNo);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(462, 413, 89, 43);
		add(btnSave);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(51, 413, 89, 43);
		add(btnExit);
		
		comboBoxParkinglot = new JComboBox<String> ();
		comboBoxParkinglot.setBounds(166, 151, 373, 20);
		add(comboBoxParkinglot);
		fillParkinglotcombobox();
		
		comboBoxFloor = new JComboBox<String> ();
		comboBoxFloor.setBounds(166, 201, 373, 20);
		add(comboBoxFloor);
		fillFloorcomboBox();
		
		comboBoxLine = new JComboBox<String> ();
		comboBoxLine.setBounds(166, 261, 373, 20);
		add(comboBoxLine);
		
		comboBoxParkingPlace = new JComboBox<String>();
		comboBoxParkingPlace.setBounds(166, 324, 373, 20);
		add(comboBoxParkingPlace);
		
	}

	public void fillParkinglotcombobox(){
		for(Integer i=1;i<7;i++)
			comboBoxParkinglot.addItem(i.toString());
		
	}
	
	public void fillFloorcomboBox(){
		for(Integer i=1;i<4;i++)
			comboBoxFloor.addItem(i.toString());
	}
	
	public void fillLinecomboBox(){
		for(Integer i=1;i<4;i++)
			comboBoxFloor.addItem(i.toString());
	}
	
	
	public void listners(){
		
		rdbtnParkingPlaceNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int parkinglotId = Integer.parseInt(comboBoxParkinglot.getSelectedItem().toString());
				int num=getParkingLot_controller().getNumOfParkingPlaces(parkinglotId);
				fillParkingPlacecombox(num);
			}


			public void fillParkingPlacecombox(int num){
				for(Integer i=1;i<(num*3)+1;i++)
						comboBoxParkingPlace.addItem(i.toString());
			}
		});
		
		rdbtnParkinLot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//getNumOfParkingPlaces();
			}

			
		});

	}
	
	
	
	public ParkingLot_controller getParkingLot_controller(){
		if(parkinglotcontroller==null)
			parkinglotcontroller=new ParkingLot_controller("localhost",5555);
		
		return  parkinglotcontroller;
	}
}
