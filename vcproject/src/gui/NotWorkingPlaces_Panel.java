package gui;

import javax.swing.*;

import controler.ParkingLot_controller;
import controler.VcpInfo;
import entity.Parking_Places;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NotWorkingPlaces_Panel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JRadioButton rdbtnParkingLot;
	private JRadioButton rdbtnParkingPlace;
	private JRadioButton rdbtnParkingLot1;
	private JRadioButton rdbtnFloorNo;
	private JRadioButton rdbtnLineNo;
	private JRadioButton rdbtnParkingPlaceNo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup1 = new ButtonGroup();
	private JButton btnSave;
	private JButton btnExit;
	private int port;
	private String host;
	private ParkingLot_controller parkinglotcontroller;
	private int parkingLotNum;
	private int floorNum;
	private int lineNum;
	private int parkingplaceNum;
	private int defaultParkingLot;
	private VcpInfo vcpInfo;
	private ArrayList<Parking_Places> parkingPlaces;
	private JComboBox <String> comboBoxParkingLot;
	private JComboBox <String> comboBoxParkingPlace;
	private JComboBox <String> comboBoxLine;
	private JComboBox <String> comboBoxFloor;
	
	public NotWorkingPlaces_Panel(String host,int port, VcpInfo vcpInfo){
		super();
		this.defaultParkingLot=vcpInfo.getDefultParkingLot().getIdparkinglot();
		this.parkingPlaces= vcpInfo.getParkingPlaces();
		this.host=host;
		this.port=port;
		initialize();
		setLayout(null);
		listners();
	}
	
	public void initialize(){
		this.setSize(785, 575);
		JLabel lblNotWorkingPalces = new JLabel("Not working places\r\n");
		lblNotWorkingPalces.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 36));
		lblNotWorkingPalces.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotWorkingPalces.setBounds(140, 25, 354, 54);
		add(lblNotWorkingPalces);
		
		JLabel lblChooseParkingLot = new JLabel("Choose parking lot/place:");
		lblChooseParkingLot.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblChooseParkingLot.setBounds(26, 90, 189, 25);
		add(lblChooseParkingLot);
		
		rdbtnParkingPlace = new JRadioButton("parking place");
		rdbtnParkingPlace.setBounds(139, 139, 109, 23);
		buttonGroup1.add(rdbtnParkingPlace);
		add(rdbtnParkingPlace);
		
		rdbtnParkingLot = new JRadioButton("parking lot");
		rdbtnParkingLot.setBounds(345, 139, 109, 23);
		buttonGroup1.add(rdbtnParkingLot);
		add(rdbtnParkingLot);
		
		rdbtnParkingLot1 = new JRadioButton("parking lot");
		rdbtnParkingLot1.setBounds(6, 200, 109, 23);
		buttonGroup.add(rdbtnParkingLot1);
		add(rdbtnParkingLot1);
		rdbtnParkingLot1.setVisible(false);
		
		
		comboBoxParkingLot = new JComboBox <String>();
		comboBoxParkingLot.setBounds(118, 201, 364, 20);
		add(comboBoxParkingLot);
		comboBoxParkingLot.addItem(" ");
		fillParkinglotcombobox();
		comboBoxParkingLot.setVisible(false);
		
		rdbtnFloorNo = new JRadioButton("floor no.");
		rdbtnFloorNo.setBounds(6, 262, 109, 23);
		buttonGroup.add(rdbtnFloorNo);
		add(rdbtnFloorNo);
		rdbtnFloorNo.setVisible(false);
		
		comboBoxFloor = new JComboBox <String>();
		comboBoxFloor.setBounds(118, 263, 364, 20);
		add(comboBoxFloor);
		comboBoxFloor.addItem(" ");
		fillFloorcomboBox();
		comboBoxFloor.setVisible(false);
		
		rdbtnLineNo = new JRadioButton("Line no.");
		rdbtnLineNo.setBounds(6, 324, 109, 23);
		buttonGroup.add(rdbtnLineNo );
		add(rdbtnLineNo);
		rdbtnLineNo.setVisible(false);
		
		comboBoxLine = new JComboBox <String>();
		comboBoxLine.setBounds(118, 325, 364, 20);
		add(comboBoxLine);
		comboBoxLine.addItem(" ");
		fillLinecomboBox();
		comboBoxLine.setVisible(false);
		
		rdbtnParkingPlaceNo = new JRadioButton("parking place no.");
		rdbtnParkingPlaceNo.setBounds(6, 380, 109, 23);
		buttonGroup.add(rdbtnParkingPlaceNo );
		add(rdbtnParkingPlaceNo);
		rdbtnParkingPlaceNo.setVisible(false);
		
		comboBoxParkingPlace = new JComboBox <String>();
		comboBoxParkingPlace.setBounds(118, 381, 364, 20);
		add(comboBoxParkingPlace);
		comboBoxParkingPlace.addItem(" ");
		comboBoxParkingPlace.setVisible(false);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(473, 443, 109, 56);
		add(btnSave);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(51, 443, 109, 56);
		add(btnExit);
	}
	

	public void fillParkinglotcombobox(){
			comboBoxParkingLot.addItem((Integer.toString(defaultParkingLot)));
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
		rdbtnParkingPlace.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				setParkingPlaceAsNotWorking();
			}
		});
		
		rdbtnParkingLot.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				setParkingLotsNotWorking();
			}
		});
		
		rdbtnParkingLot1.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				comboBoxParkingLot.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						try{
						parkingLotNum=Integer.parseInt(comboBoxParkingLot.getSelectedItem().toString());
						parkingPlaces=getParkingLot_controller().getAllparkingLotplaces(parkingLotNum);
						}
						
						catch(Exception e1){
							parkingLotNum=0;
						}
					}
				});
			}
		});
		
		rdbtnFloorNo.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				comboBoxFloor.addActionListener(new ActionListener() {
				
					public void actionPerformed(ActionEvent e) {
						try{
						floorNum=Integer.parseInt(comboBoxFloor.getSelectedItem().toString());
						}
						
						catch(Exception e1){
							floorNum=0;
						}
					}
				});
				
			}
		});
		
		rdbtnLineNo.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				comboBoxLine.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						try{
						lineNum=Integer.parseInt(comboBoxLine.getSelectedItem().toString());
						fillParkingPlacecombox();
						}
						catch(Exception e1){
							lineNum=0;
						}
					}
				});
			}
		});
		
		rdbtnParkingPlaceNo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				comboBoxParkingPlace.addActionListener(new ActionListener() {
				
					public void actionPerformed(ActionEvent arg0) {
						try{
							parkingplaceNum=Integer.parseInt(comboBoxParkingPlace.getSelectedItem().toString());
						}
					
						catch(Exception e1){parkingplaceNum=0;}
					}
				});
			}
		});
		
		btnSave.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(rdbtnParkingPlace.isSelected())
						getParkingLot_controller().updateParkingPlaceAsnotWorking(parkingLotNum,parkingplaceNum,0);
					
					if(rdbtnParkingLot.isSelected())
						getParkingLot_controller().updateParkingLotAsNotWorking(parkingLotNum);
				}
			});
		}
	
	private void setParkingPlaceAsNotWorking(){
		rdbtnParkingLot1.setVisible(true);
		comboBoxParkingLot.setVisible(true);
		rdbtnFloorNo.setVisible(true);
		rdbtnLineNo.setVisible(true);
		rdbtnParkingPlaceNo.setVisible(true);
		comboBoxFloor.setVisible(true);
		comboBoxLine.setVisible(true);
		comboBoxParkingPlace.setVisible(true);
	}
	
	private void setParkingLotsNotWorking(){
		rdbtnParkingLot1.setVisible(true);
		comboBoxParkingLot.setVisible(true);
		rdbtnFloorNo.setVisible(false);
		rdbtnLineNo.setVisible(false);
		rdbtnParkingPlaceNo.setVisible(false);
		comboBoxFloor.setVisible(false);
		comboBoxLine.setVisible(false);
		comboBoxParkingPlace.setVisible(false);
	}
	
	public void fillParkingPlacecombox(){
		
		comboBoxParkingPlace.removeAllItems();
		comboBoxParkingPlace.addItem(" ");
			for(Parking_Places parking_place: parkingPlaces)
				if(parking_place.getFloor()==floorNum && parking_place.getRow()==lineNum)
					comboBoxParkingPlace.addItem((Integer.toString(parking_place.getColumn())));
	}
			
	
	public JButton getbtnExit(){
		return btnExit;
	}
	
	public JButton getbtnSave(){
		return btnSave;
	}
	
	public ParkingLot_controller getParkingLot_controller(){
		if(parkinglotcontroller==null)
			parkinglotcontroller=new ParkingLot_controller(this.host,this.port,vcpInfo);
		
		return  parkinglotcontroller;
	}
}
