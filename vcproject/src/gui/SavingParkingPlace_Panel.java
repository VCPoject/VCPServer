package gui;

import javax.swing.*;

import controler.ParkingLot_controller;
import controler.VcpInfo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;

import entity.Parking_Places;

import com.toedter.calendar.JDateChooser;

import javax.swing.border.TitledBorder;

public class SavingParkingPlace_Panel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton btnSave;
	private JButton btnExit;
	private JComboBox <String> comboBoxParkinglot;
	private JComboBox <String> comboBoxFloor;  
	private JComboBox <String> comboBoxLine;
	private JComboBox <String> comboBoxParkingPlace;
	private JComboBox<String> comboBoxArrivalMin;
	private JComboBox<String> comboBoxArrivalHour;
	private JComboBox<String> comboBoxDepartureMin;
	private JComboBox<String> comboBoxDepartureHour;
	private ParkingLot_controller parkinglotcontroller;
	private ArrayList<Parking_Places> parkingPlaces;
	private ArrayList<Parking_Places>vaccantParkingPlaces;
	private int parkinglotId;
	private int floorNum;
	private int lineNum;
	private int parkingplaceNum;
	private String host;
	/** vcpInfo is a controller that run on start-up of the 
	 * application and download all the info form the DB
	 * its contains all:
	 * order,subscribed,reservation,employees,parking lot,
	 * parking places,clients,default parking lot,cars
	 */
	private VcpInfo vcpInfo;
	private int port;
	private JDateChooser arrivalDate;
	private JDateChooser departureDate;
	private String Datearrival;
	private String arrivalTime;
	private String Datedeparture; 
	private String arrivalTimeMin; 
	private  String arrivalTimeHour; 
	private String departureTimeMin;
	private String departureTimeHour;
	private Date ArrivalDate;
	private Date DepartureDate;
	private JLabel lblArrivalDate;
	private JLabel lblDepartureDate;
	private JLabel lblArrivalTime;
	private JLabel lblDepartureTime;
	private JLabel lblParkingLot;
	private JLabel lblFloor;
	private JLabel lblLine;
	private JLabel lblParkingPlaceNumber;
	private JPanel panel;
	
	/**
	 * This panel is for saving parking place.
	 * @param host for make connection with server side
	 * @param port for make connection with server side
	 * @param vcpInfo for get info from DB
	 */
	public SavingParkingPlace_Panel(String host, int port, VcpInfo vcpInfo){
		super();
		this.vcpInfo=vcpInfo;
		this.parkingPlaces=vcpInfo.getParkingPlaces();
		this.host=host;
		this.port=port;
		initialize();
		listners();
	}
	/**
	 * Initialize the panel of saving parking place
	 */
	private void initialize() {
		this.setSize(785, 575);
		setLayout(null);
		JLabel lblReserv = new JLabel("Reservation");
		lblReserv.setBounds(280, 11, 225, 44);
		lblReserv.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblReserv.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblReserv);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(348, 329, 89, 43);
		add(btnSave);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(6, 521, 89, 43);
		add(btnExit);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Reservation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(120, 134, 544, 165);
		add(panel);
		panel.setLayout(null);
		
		comboBoxParkinglot = new JComboBox<String> ();
		comboBoxParkinglot.setBounds(456, 16, 82, 20);
		panel.add(comboBoxParkinglot);
		
		comboBoxFloor = new JComboBox<String> ();
		comboBoxFloor.setBounds(456, 56, 82, 20);
		panel.add(comboBoxFloor);
		
		comboBoxLine = new JComboBox<String> ();
		comboBoxLine.setBounds(456, 96, 82, 20);
		panel.add(comboBoxLine);
		
		comboBoxParkingPlace = new JComboBox<String>();
		comboBoxParkingPlace.setBounds(456, 136, 82, 20);
		panel.add(comboBoxParkingPlace);
		
		arrivalDate= new JDateChooser();
		arrivalDate.setBounds(176, 19, 87, 20);
		panel.add(arrivalDate);
		
		departureDate = new JDateChooser();
		departureDate.setBounds(176, 56, 87, 20);
		panel.add(departureDate);
		
		
		comboBoxArrivalHour = new JComboBox<String>();
		comboBoxArrivalHour.setBounds(163, 96, 48, 20);
		panel.add(comboBoxArrivalHour);
		
		comboBoxArrivalMin = new JComboBox<String>();
		comboBoxArrivalMin.setBounds(215, 96, 48, 20);
		panel.add(comboBoxArrivalMin);
		
		comboBoxDepartureHour = new JComboBox<String>();
		comboBoxDepartureHour.setBounds(163, 136, 48, 20);
		panel.add(comboBoxDepartureHour);
		
		comboBoxDepartureMin = new JComboBox<String>();
		comboBoxDepartureMin.setBounds(215, 136, 48, 20);
		panel.add(comboBoxDepartureMin);

		
		lblArrivalDate = new JLabel("Arrival date:");
		lblArrivalDate.setBounds(6, 16, 111, 22);
		panel.add(lblArrivalDate);
		lblArrivalDate.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		lblDepartureDate = new JLabel("Departure date:");
		lblDepartureDate.setBounds(6, 56, 145, 22);
		panel.add(lblDepartureDate);
		lblDepartureDate.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		lblArrivalTime = new JLabel("Arrival time:");
		lblArrivalTime.setBounds(6, 96, 111, 22);
		panel.add(lblArrivalTime);
		lblArrivalTime.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		lblDepartureTime = new JLabel("Departure time:");
		lblDepartureTime.setBounds(7, 136, 145, 22);
		panel.add(lblDepartureTime);
		lblDepartureTime.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		lblParkingLot = new JLabel("Parking lot:");
		lblParkingLot.setBounds(294, 16, 105, 22);
		panel.add(lblParkingLot);
		lblParkingLot.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		lblFloor = new JLabel("Floor:");
		lblFloor.setBounds(294, 56, 52, 22);
		panel.add(lblFloor);
		lblFloor.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		lblLine = new JLabel("Line:");
		lblLine.setBounds(294, 96, 45, 22);
		panel.add(lblLine);
		lblLine.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		lblParkingPlaceNumber = new JLabel("Parking place no:");
		lblParkingPlaceNumber.setBounds(293, 136, 158, 22);
		panel.add(lblParkingPlaceNumber);
		lblParkingPlaceNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBoxLine.addItem(" ");
		comboBoxFloor.addItem(" ");
		comboBoxParkinglot.addItem(" ");
		fillParkinglotcombobox();
		fillFloorcomboBox();
		fillLinecomboBox();
		fillcomboBoxHour();
		fillcomboBoxMin();
	}

	
	/**
	 * Initialize the combobox of arrival and departure with minutes
	 */
	public void fillcomboBoxMin() {
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
		
	}
	
	/**
	 * Initialize the combobox of arrival and departure with hours
	 */
	public void fillcomboBoxHour() {
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
		
	}
	
	
	/**
	 * Fill the combobox of parking lot with 
	 * the default parking lot number
	 */
	public void fillParkinglotcombobox(){
		for(Integer i=1;i<7;i++)
			comboBoxParkinglot.addItem(i.toString());
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
		
		comboBoxParkinglot.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				try{
				
				arrivalTime=arrivalTimeHour+":"+arrivalTimeMin+":"+"00";
				ArrivalDate=arrivalDate.getDate();
				
				if(comboBoxParkinglot.getSelectedItem().toString().equals(" "))
					throw new Exception("you didn't insert parking lot id");
				
				parkinglotId=Integer.parseInt(comboBoxParkinglot.getSelectedItem().toString());
				
				if(arrivalDate==null)
					throw new Exception("you didnt select arrival date");
				
				else if(comboBoxArrivalMin.getSelectedItem().equals("Min")||comboBoxArrivalHour.getSelectedItem().equals("Hour"))
					throw new Exception("You didn't insert arrival time");
				
				SimpleDateFormat formatOnlyDate = new SimpleDateFormat("yyyy-MM-dd");
				Date arrival=StringToDate(formatOnlyDate.format(ArrivalDate),arrivalTime);
				vaccantParkingPlaces=getParkingLot_controller().getVaccantParkingPlaces(parkinglotId,arrival);
				}
				
				catch(Exception e1){
					getParkingLot_controller().showWarningMsg(e1.getMessage());
				}
			}
		});
		
		
		comboBoxFloor.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				try{
				floorNum=Integer.parseInt(comboBoxFloor.getSelectedItem().toString());
				}
				
				catch(Exception e1){}
			}
		});
		
		comboBoxLine.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				try{
				lineNum=Integer.parseInt(comboBoxLine.getSelectedItem().toString());
				fillParkingPlacecombox();
				}
				
				catch(Exception e1){}
			}
		});
		
		comboBoxParkingPlace.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				try{
				parkingplaceNum=Integer.parseInt(comboBoxParkingPlace.getSelectedItem().toString());
				}
				
				catch(Exception e1){}
			}
		});
		
		comboBoxArrivalMin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				arrivalTimeMin=comboBoxArrivalMin.getSelectedItem().toString();
			}
		});
		
		comboBoxArrivalMin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				arrivalTimeHour=comboBoxArrivalHour.getSelectedItem().toString();
			}
		});
		
		comboBoxDepartureMin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				departureTimeMin=comboBoxDepartureMin.getSelectedItem().toString();
			}
		});
		
		comboBoxDepartureHour.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				departureTimeHour=comboBoxDepartureHour.getSelectedItem().toString();
			}
		});
		
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{
					
					if(comboBoxParkinglot.getSelectedItem().equals(" "))
						throw new Exception("You did'nt insert parking lot no.");
					
					else if(comboBoxFloor.getSelectedItem().equals(" "))
						throw new Exception("You did'nt insert floor no.");
					
					else if(comboBoxLine.getSelectedItem().equals(" "))
						throw new Exception("You did'nt insert line no.");
					
					else if(comboBoxParkingPlace.getSelectedItem().equals(" "))
						throw new Exception("You did'nt insert parking place no.");
					
					ArrivalDate=arrivalDate.getDate();
					DepartureDate=departureDate.getDate();
					
					if(ArrivalDate == null)
						throw new Exception("You didnt select departure date");
					
					SimpleDateFormat formatArrivalDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat formatOnlyDate = new SimpleDateFormat("yyyy-MM-dd");
					Datearrival=formatOnlyDate.format(ArrivalDate);
					String arrivalTime=arrivalTimeHour+":"+arrivalTimeMin+":"+"00";
					Date arrival=formatArrivalDate.parse(Datearrival +" "+arrivalTime);
					
					 if(DepartureDate == null)
						throw new Exception("You didnt select departure date");
					 
					SimpleDateFormat formatDepartureDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					formatOnlyDate=new SimpleDateFormat("yyyy-MM-dd");
					Datedeparture=formatOnlyDate.format(DepartureDate);
					String departureTime=departureTimeHour+":"+departureTimeMin+":"+"00";
					Date departure=formatDepartureDate.parse(Datedeparture+" "+departureTime);
					
					if(arrivalTime.contains("Min") || arrivalTime.contains("Hour"))
						throw new Exception("You did'nt insert arrival time");
					
					else if(departureTime.contains("Min") || departureTime.contains("Hour"))
						throw new Exception("You did'nt insert departure time");
					
					 if(arrival.after(departure))
						throw new Exception("You're arrival date is further then your departure date");
					
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date currentDate = new Date();
					
					
					if(arrival.before(dateFormat.parse(dateFormat.format(currentDate))))
						throw new Exception("You're arrival date has been expired");
					
					else if(departure.before(dateFormat.parse(dateFormat.format(currentDate))))
						throw new Exception("You're arrival date has been expired");
					
					getParkingLot_controller().saveParkingPlace(parkinglotId,Datearrival,Datedeparture,arrivalTime,
					departureTime,parkingplaceNum,lineNum,floorNum);
				}
				catch(Exception e1){
					getParkingLot_controller().showWarningMsg(e1.getMessage());
				}
			}
		});
			
	

	}

	/**
	 * Fill the combobox of all the available parking places with.
	 */
	public void fillParkingPlacecombox(){
	
		comboBoxParkingPlace.removeAllItems();
		comboBoxParkingPlace.addItem(" ");
			for(Parking_Places parking_place: vaccantParkingPlaces)
				if(parking_place.getFloor()==floorNum && parking_place.getRow()==lineNum)
					comboBoxParkingPlace.addItem((Integer.toString(parking_place.getColumn())));
	}
			
	
	public ParkingLot_controller getParkingLot_controller(){
		if(parkinglotcontroller==null)
			parkinglotcontroller=new ParkingLot_controller(this.host,this.port,vcpInfo);
		
		return  parkinglotcontroller;
	}
	
	public JButton getBtnExit(){
		return btnExit;
	}
	
	public Date StringToDate(String strDate,String strTime) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date departDate = format.parse(strDate+" "+strTime);
		return departDate;
	}
	
	
	
	public VcpInfo getVcpInfo() {
		if(vcpInfo == null)
			vcpInfo = new VcpInfo(host);
		return vcpInfo;
	
	}
}
