package gui;

import javax.swing.*;

import controler.ParkingLot_controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;

import entity.Parking_Places;

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
	private int parkinglotId;
	private int floorNum;
	private int lineNum;
	private int parkingplaceNum;
	private String host;
	private int port;
	private ArrayList<Parking_Places> parking_places=new ArrayList<Parking_Places>();
	private static final long serialVersionUID = 1L;
	
	public SavingParkingPlace_Panel(String host, int port) {
		super();
		initialize();
		this.host=host;
		this.port=port;
		setLayout(null);
		listners();
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
		buttonGroup.add(rdbtnParkinLot);
		add(rdbtnParkinLot);
		
		rdbtnFloor = new JRadioButton("floor");
		rdbtnFloor.setBounds(51, 200, 109, 23);
		buttonGroup.add(rdbtnFloor);
		add(rdbtnFloor);
		
		rdbtnLine = new JRadioButton("line");
		rdbtnLine.setBounds(51, 260, 109, 23);
		buttonGroup.add(rdbtnLine);
		add(rdbtnLine);
		
		rdbtnParkingPlaceNo = new JRadioButton("parking place no.");
		rdbtnParkingPlaceNo.setBounds(51, 323, 109, 23);
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
		comboBoxParkinglot.addItem(" ");
		fillParkinglotcombobox();
		
		comboBoxFloor = new JComboBox<String> ();
		comboBoxFloor.setBounds(166, 201, 373, 20);		
		add(comboBoxFloor);
		comboBoxFloor.addItem(" ");
		fillFloorcomboBox();
		
		comboBoxLine = new JComboBox<String> ();
		comboBoxLine.setBounds(166, 261, 373, 20);
		add(comboBoxLine);
		comboBoxLine.addItem(" ");
		fillLinecomboBox();
		
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
			comboBoxLine.addItem(i.toString());
	}
	
	
	public void listners(){
		
		
		
		rdbtnParkinLot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBoxParkinglot.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						parkinglotId =Integer.parseInt(comboBoxParkinglot.getSelectedItem().toString());
						parking_places=getParkingLot_controller().getVaccantParkingPlaces(parkinglotId);
						
					}
				});
			}
		});
		
		rdbtnFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBoxFloor.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						floorNum =Integer.parseInt(comboBoxFloor.getSelectedItem().toString());
					}
				});
			}
		});
		

		rdbtnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				comboBoxLine.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						lineNum =Integer.parseInt(comboBoxLine.getSelectedItem().toString());
							fillParkingPlacecombox();
							
					}
					
					
				});
			}
		});
		
		rdbtnParkingPlaceNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				comboBoxParkingPlace.addActionListener(new ActionListener(){
					
					public void actionPerformed(ActionEvent e) {
						try{
							parkingplaceNum=Integer.parseInt(comboBoxParkingPlace.getSelectedItem().toString());
						}
						
						catch(Exception e1){}
					}
				});
			}
		});
		
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			getParkingLot_controller().saveParkingPlace(parkinglotId,floorNum,lineNum,parkingplaceNum);
			}
		});
	}
	
	public void fillParkingPlacecombox(){
	
		comboBoxParkingPlace.removeAllItems();
		comboBoxParkingPlace.addItem(" ");
			for(Parking_Places parking_place: parking_places)
				if(parking_place.getFloor()==floorNum && parking_place.getRow()==lineNum)
					comboBoxParkingPlace.addItem((Integer.toString(parking_place.getColumn())));
	}
			
	
	public ParkingLot_controller getParkingLot_controller(){
		if(parkinglotcontroller==null)
			parkinglotcontroller=new ParkingLot_controller(this.host,this.port);
		
		return  parkinglotcontroller;
	}
	
	public JButton getBtnExit(){
		return btnExit;
	}


}
