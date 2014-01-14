package gui;

import javax.swing.*;

import controler.ParkingLot_controller;
import controler.VcpInfo;
import entity.Parking_Places;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.TitledBorder;

public class NotWorkingPlaces_Panel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JRadioButton rdbtnParkingLot;
	private JRadioButton rdbtnParkingPlace;
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
	/** vcpInfo is a controller that run on start-up of the 
	 * application and download all the info form the DB
	 * its contains all:
	 * order,subscribed,reservation,employees,parking lot,
	 * parking places,clients,default parking lot,cars
	 */
	private VcpInfo vcpInfo;
	private ArrayList<Parking_Places> parkingPlaces;
	private JComboBox <String> comboBoxParkingLot;
	private JComboBox <String> comboBoxParkingPlace;
	private JComboBox <String> comboBoxLine;
	private JComboBox <String> comboBoxFloor;
	private JPanel panelSelectLotOrPlace;
	private JLabel lblParkingLot;
	private JLabel lblFloorNumber;
	private JLabel lblLineNumber;
	private JLabel lblParkingPlaceNumber;
	private JPanel panelNotWorkingPlace;
	
	/**
	 * This panel is for assign not working places or lots.
	 * @param host for make connection with server side
	 * @param port for make connection with server side
	 * @param vcpInfo for get info from DB
	 */
	public NotWorkingPlaces_Panel(String host,int port, VcpInfo vcpInfo){
		super();
		this.vcpInfo = vcpInfo;
		this.defaultParkingLot=vcpInfo.getDefultParkingLot().getIdparkinglot();
		this.parkingPlaces= vcpInfo.getParkingPlaces();
		this.host=host;
		this.port=port;
		initialize();
		setLayout(null);
		listners();
	}
	/**
	 * Initialize the panel of saving parking place
	 */
	public void initialize(){
		this.setSize(785, 575);
		JLabel lblNotWorkingPalces = new JLabel("Not working places\r\n");
		lblNotWorkingPalces.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 36));
		lblNotWorkingPalces.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotWorkingPalces.setBounds(227, 16, 330, 43);
		add(lblNotWorkingPalces);
		
		JLabel lblChooseParkingLot = new JLabel("Choose parking lot/place:");
		lblChooseParkingLot.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblChooseParkingLot.setBounds(222, 131, 189, 25);
		add(lblChooseParkingLot);
		
		panelSelectLotOrPlace = new JPanel();
		panelSelectLotOrPlace.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Select Lot/Place", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSelectLotOrPlace.setBounds(436, 107, 121, 72);
		add(panelSelectLotOrPlace);
		panelSelectLotOrPlace.setLayout(null);
		
		rdbtnParkingPlace = new JRadioButton("parking place");
		rdbtnParkingPlace.setSelected(true);
		rdbtnParkingPlace.setBounds(6, 16, 109, 23);
		panelSelectLotOrPlace.add(rdbtnParkingPlace);
		buttonGroup1.add(rdbtnParkingPlace);
		
		rdbtnParkingLot = new JRadioButton("parking lot");
		rdbtnParkingLot.setBounds(6, 42, 109, 23);
		panelSelectLotOrPlace.add(rdbtnParkingLot);
		buttonGroup1.add(rdbtnParkingLot);
		
		panelNotWorkingPlace = new JPanel();
		panelNotWorkingPlace.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Not working place", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNotWorkingPlace.setBounds(230, 223, 325, 165);
		add(panelNotWorkingPlace);
		panelNotWorkingPlace.setLayout(null);
		
		
		comboBoxParkingLot = new JComboBox <String>();
		comboBoxParkingLot.setBounds(229, 19, 90, 22);
		panelNotWorkingPlace.add(comboBoxParkingLot);
		
		comboBoxFloor = new JComboBox <String>();
		comboBoxFloor.setBounds(229, 56, 90, 22);
		panelNotWorkingPlace.add(comboBoxFloor);
		
		comboBoxLine = new JComboBox <String>();
		comboBoxLine.setBounds(229, 96, 90, 22);
		panelNotWorkingPlace.add(comboBoxLine);
		
		comboBoxParkingPlace = new JComboBox <String>();
		comboBoxParkingPlace.setBounds(229, 136, 90, 20);
		panelNotWorkingPlace.add(comboBoxParkingPlace);
		
		lblParkingLot = new JLabel("Parking lot:");
		lblParkingLot.setBounds(6, 16, 105, 22);
		panelNotWorkingPlace.add(lblParkingLot);
		lblParkingLot.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		lblFloorNumber = new JLabel("Floor number:");
		lblFloorNumber.setBounds(6, 56, 128, 22);
		panelNotWorkingPlace.add(lblFloorNumber);
		lblFloorNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		lblLineNumber = new JLabel("Line number:");
		lblLineNumber.setBounds(6, 96, 121, 22);
		panelNotWorkingPlace.add(lblLineNumber);
		lblLineNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		lblParkingPlaceNumber = new JLabel("Parking place number:");
		lblParkingPlaceNumber.setBounds(6, 136, 206, 22);
		panelNotWorkingPlace.add(lblParkingPlaceNumber);
		lblParkingPlaceNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBoxParkingPlace.addItem(" ");
		comboBoxParkingPlace.setVisible(false);
		comboBoxLine.addItem(" ");
		comboBoxLine.setVisible(false);
		comboBoxFloor.addItem(" ");
		comboBoxFloor.setVisible(false);
		comboBoxParkingLot.addItem(" ");
		comboBoxParkingLot.setVisible(false);
		fillParkinglotcombobox();
		fillFloorcomboBox();
		fillLinecomboBox();
		
		btnSave = new JButton("Save");
		btnSave.setBounds(338, 418, 109, 56);
		add(btnSave);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(6, 508, 109, 56);
		add(btnExit);
		
		setParkingPlaceAsNotWorking();
	}
	
	/**
	 * Fill the combobox of parking lot with 
	 * the default parking lot number
	 */
	public void fillParkinglotcombobox(){
			comboBoxParkingLot.addItem((Integer.toString(defaultParkingLot)));
	}
	/**
	 * Fill the combobox of floor with 
	 * the number of floors
	 */
	public void fillFloorcomboBox(){
		for(Integer i=1;i<4;i++)
			comboBoxFloor.addItem(i.toString());
	}
	/**
	 * Fill the combobox of line with 
	 * the number of lines
	 */
	public void fillLinecomboBox(){
		for(Integer i=1;i<4;i++)
			comboBoxLine.addItem(i.toString());
	}
	/**
	 * Listeners of the GUI components.
	 */
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
		
		btnSave.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(rdbtnParkingPlace.isSelected())
						getParkingLot_controller().updateParkingPlaceAsnotWorking(parkingLotNum,parkingplaceNum,0);
					
					if(rdbtnParkingLot.isSelected())
						getParkingLot_controller().updateParkingLotAsNotWorking(parkingLotNum);
				}
			});
		}
	
	/**
	 * Set the visibility of components to adapt to assign not working parking places
	 */
	private void setParkingPlaceAsNotWorking(){
		comboBoxParkingLot.setVisible(true);
		comboBoxFloor.setVisible(true);
		comboBoxLine.setVisible(true);
		comboBoxParkingPlace.setVisible(true);
		lblFloorNumber.setVisible(true);
		lblLineNumber.setVisible(true);
		lblParkingPlaceNumber.setVisible(true);
	}
	/**
	 * Set the visibility of components to adapt assign not working parking lots.
	 */
	private void setParkingLotsNotWorking(){
		comboBoxParkingLot.setVisible(true);
		comboBoxFloor.setVisible(false);
		comboBoxLine.setVisible(false);
		comboBoxParkingPlace.setVisible(false);
		lblFloorNumber.setVisible(false);
		lblLineNumber.setVisible(false);
		lblParkingPlaceNumber.setVisible(false);
	}
	/**
	 * Fill the combobox of all the available parking places with.
	 */
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
