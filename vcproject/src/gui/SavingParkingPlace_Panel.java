package gui;

import javax.swing.*;

import controler.ParkingLot_controller;
import controler.VcpInfo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.AbstractButton;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;

import entity.Order;
import entity.Parking_Lot;
import entity.Parking_Places;
import entity.Reservation;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class SavingParkingPlace_Panel extends JPanel {
	
	private JRadioButton rdbtnParkinLot;
	private JRadioButton rdbtnFloor;
	private JRadioButton rdbtnLine;
	private JRadioButton rdbtnArrivalDate;
	private JRadioButton rdbtnaDepartureDate;
	private JRadioButton rdbtnArrivalTime;
	private JRadioButton rdbtnaDepartureTime;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnParkingPlaceNo;
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
	private ArrayList<Parking_Places> parkingPlaces1;
	private int parkinglotId;
	private int floorNum;
	private int lineNum;
	private int parkingplaceNum;
	private String host;
	private VcpInfo vcpInfo;
	private int port;
	private int defaultParkingLot;
	private JDateChooser arrivalDate;
	private JDateChooser departureDate;
	private String Datearrival;
	private String Datedeparture; 
	private String arrivalTimeMin; 
	private  String arrivalTimeHour; 
	private String departureTimeMin;
	private String departureTimeHour;
	private Date ArrivalDate;
	private Date DepartureDate;
	private static final long serialVersionUID = 1L;
	
	public SavingParkingPlace_Panel(String host, int port, VcpInfo vcpInfo){
		super();
		this.vcpInfo=vcpInfo;
		this.defaultParkingLot=vcpInfo.getDefultParkingLot().getIdparkinglot();
		this.parkingPlaces=vcpInfo.getParkingPlaces();
		this.host=host;
		this.port=port;
		initialize();
		listners();
	}
	
	private void initialize() {
		this.setSize(785, 575);
		setLayout(null);
		JLabel lblReserv = new JLabel("Reservation");
		lblReserv.setBounds(176, 34, 231, 52);
		lblReserv.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblReserv.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblReserv);
		
		rdbtnParkinLot = new JRadioButton("parking lot");
		rdbtnParkinLot.setBounds(338, 150, 109, 23);
		buttonGroup.add(rdbtnParkinLot);
		add(rdbtnParkinLot);
		
		rdbtnFloor = new JRadioButton("floor");
		rdbtnFloor.setBounds(338, 201, 109, 23);
		buttonGroup.add(rdbtnFloor);
		add(rdbtnFloor);
		
		rdbtnLine = new JRadioButton("line");
		rdbtnLine.setBounds(338, 260, 109, 23);
		buttonGroup.add(rdbtnLine);
		add(rdbtnLine);
		
		rdbtnParkingPlaceNo = new JRadioButton("parking place no.");
		rdbtnParkingPlaceNo.setBounds(338, 323, 109, 23);
		buttonGroup.add(rdbtnParkingPlaceNo);
		add(rdbtnParkingPlaceNo);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(462, 413, 89, 43);
		add(btnSave);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(51, 413, 89, 43);
		add(btnExit);
		
		comboBoxParkinglot = new JComboBox<String> ();
		comboBoxParkinglot.setBounds(469, 151, 82, 20);
		add(comboBoxParkinglot);
		comboBoxParkinglot.addItem(" ");
		fillParkinglotcombobox();
		
		comboBoxFloor = new JComboBox<String> ();
		comboBoxFloor.setBounds(469, 202, 82, 20);
		add(comboBoxFloor);
		comboBoxFloor.addItem(" ");
		fillFloorcomboBox();
		
		comboBoxLine = new JComboBox<String> ();
		comboBoxLine.setBounds(469, 261, 82, 20);
		add(comboBoxLine);
		comboBoxLine.addItem(" ");
		fillLinecomboBox();
		
		comboBoxParkingPlace = new JComboBox<String>();
		comboBoxParkingPlace.setBounds(469, 324, 82, 20);
		add(comboBoxParkingPlace);
		
		arrivalDate= new JDateChooser();
		arrivalDate.setBounds(166, 150, 87, 20);
		add(arrivalDate);
		
		departureDate = new JDateChooser();
		departureDate.setBounds(166, 201, 87, 20);
		add(departureDate);
		
		rdbtnArrivalDate = new JRadioButton("arrival date\r\n");
		rdbtnArrivalDate.setBounds(51, 150, 109, 23);
		buttonGroup.add(rdbtnArrivalDate);
		add(rdbtnArrivalDate);
		
		rdbtnaDepartureDate = new JRadioButton("departure date");
		rdbtnaDepartureDate.setBounds(51, 201, 109, 23);
		buttonGroup.add(rdbtnaDepartureDate);
		add(rdbtnaDepartureDate);
		
		rdbtnArrivalTime = new JRadioButton("arrival time");
		rdbtnArrivalTime.setBounds(51, 260, 109, 23);
		buttonGroup.add(rdbtnArrivalTime);
		add(rdbtnArrivalTime);
		
		
		comboBoxArrivalHour = new JComboBox<String>();
		comboBoxArrivalHour.setBounds(168, 261, 48, 20);
		add(comboBoxArrivalHour);
		
		comboBoxArrivalMin = new JComboBox<String>();
		comboBoxArrivalMin.setBounds(220, 261, 48, 20);
		add(comboBoxArrivalMin);
		
		
		rdbtnaDepartureTime = new JRadioButton("departure time");
		rdbtnaDepartureTime.setBounds(51, 323, 109, 23);
		buttonGroup.add(rdbtnaDepartureTime);
		add(rdbtnaDepartureTime);
		
		comboBoxDepartureHour = new JComboBox<String>();
		comboBoxDepartureHour.setBounds(168, 324, 48, 20);
		add(comboBoxDepartureHour);
		
		comboBoxDepartureMin = new JComboBox<String>();
		comboBoxDepartureMin.setBounds(220, 324, 48, 20);
		add(comboBoxDepartureMin);
		fillcomboBoxHour();
		fillcomboBoxMin();
	}

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

	public void fillParkinglotcombobox(){
		comboBoxParkinglot.addItem((Integer.toString(defaultParkingLot)));
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
						try{
						parkinglotId =Integer.parseInt(comboBoxParkinglot.getSelectedItem().toString());
						String arrivalTime=arrivalTimeHour+":"+arrivalTimeMin+":"+"00";
						SimpleDateFormat timedateparser=new SimpleDateFormat("yyyy-MM-dd");
						String ArrivalDate=timedateparser.format(arrivalDate.getDate());
						parkingPlaces1=new ArrayList<Parking_Places>();
						parkingPlaces1=getParkingLot_controller().getVaccantParkingPlaces(parkinglotId,ArrivalDate,arrivalTime);
						}
						
						catch(Exception e1){
							parkinglotId=0;
						}
						
					}
				});
			}
		});
		
		rdbtnFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxFloor.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try{
						floorNum =Integer.parseInt(comboBoxFloor.getSelectedItem().toString());
						}
						
						catch(Exception e1){
							floorNum=0;
						}
					}
				});
			}
		});
		

		rdbtnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxLine.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try{
							lineNum =Integer.parseInt(comboBoxLine.getSelectedItem().toString());
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
				
				comboBoxParkingPlace.addActionListener(new ActionListener(){
					
					public void actionPerformed(ActionEvent e) {
						try{
							parkingplaceNum=Integer.parseInt(comboBoxParkingPlace.getSelectedItem().toString());
						}
						
						catch(Exception e1){
							parkingplaceNum=0;
						}
					}
				});
			}
		});
		
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{
					
					if(parkinglotId==0)
						throw new Exception("You did'nt insert parking lot no.");
					
					else if(floorNum==0)
						throw new Exception("You did'nt insert floor no.");
					
					else if(lineNum==0)
						throw new Exception("You did'nt insert line no.");
					
					else if(parkingplaceNum==0)
						throw new Exception("You did'nt insert parking place no.");
					
					ArrivalDate=arrivalDate.getDate();
					DepartureDate=departureDate.getDate();
					
					if(ArrivalDate == null)
						throw new Exception("You didnt select departure date");
					

					DateFormat formatArrivalDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Datearrival=formatArrivalDate.format(ArrivalDate).toString();
					String arrivalTime=arrivalTimeHour+":"+arrivalTimeMin+":"+"00";
					Date arrival=formatArrivalDate.parse(Datearrival+" "+arrivalTime);
					
					 if(DepartureDate == null)
						throw new Exception("You didnt select departure date");
					 
					 

					
					DateFormat formatDepartureDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Datedeparture=formatDepartureDate.format(DepartureDate).toString();
					String departureTime=departureTimeHour+":"+departureTimeMin+":"+"00";
					Date departure=formatDepartureDate.parse(Datedeparture+" "+departureTime);
					
					if(arrivalTime.contains("Min") || arrivalTime.contains("Hour"))
						throw new Exception("You did'nt insert arrival time");
					
					else if(departureTime.contains("Min") || departureTime.contains("Hour"))
						throw new Exception("You did'nt insert departure time");
					
					 if(arrival.after(departure))
						throw new Exception("You're arrival date is further then your departure date");
					
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date currentDate = new Date();
				
					
					if(arrival.before(dateFormat.parse(dateFormat.format(currentDate))))
						throw new Exception("You're arrival date has been expired");
					
					else if(departure.before(dateFormat.parse(dateFormat.format(currentDate))))
						throw new Exception("You're arrival date has been expired");
					
					
					
					
					
					/*String dateArrival[]=arrivalTime.split(":");
					String dateDeparture[]=departureTime.split(":");
					Integer arrivalHour=Integer.parseInt(dateArrival[0]);
					Integer arrivalMin=Integer.parseInt(dateArrival[1]);	
					Integer arrivalSec=Integer.parseInt(dateArrival[2]);
					Integer departureHour=Integer.parseInt(dateDeparture[0]);
					Integer departurelMin=Integer.parseInt(dateDeparture[1]);	
					Integer departurelSec=Integer.parseInt(dateDeparture[2]);
					
					if(arrivalHour>departureHour || arrivalMin>departurelMin || arrivalSec>departurelSec)
						throw new Exception("You're arrival time is further then your departure time");*/
					
					getParkingLot_controller().saveParkingPlace(parkinglotId,Datearrival,arrivalTime,Datedeparture,
					departureTime,parkingplaceNum,lineNum,floorNum);
				}
				catch(Exception e1){
					getParkingLot_controller().showWarningMsg(e1.getMessage());
				}
			}
		});
		
		rdbtnArrivalDate.addActionListener(new ActionListener() {
			
	
			public void actionPerformed(ActionEvent arg0) {
				try{
					ArrivalDate=arrivalDate.getDate();
					
				}
				
				catch(Exception e){
					ArrivalDate=null;
				}
				
			}
		});
	
	
	
		rdbtnaDepartureDate.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) {
				try{
					DepartureDate=departureDate.getDate();
					
				}
			
				catch(Exception e){
					DepartureDate=null;;
				}
			
			}
		});
		
		rdbtnArrivalTime.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			
			comboBoxArrivalMin.addActionListener(new ActionListener() {
				
					public void actionPerformed(ActionEvent e) {
						arrivalTimeMin=comboBoxArrivalMin.getSelectedItem().toString();
					}
				});
			
			comboBoxArrivalHour.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					arrivalTimeHour=comboBoxArrivalHour.getSelectedItem().toString();
				}
			});
			
			}
		});	
		
		
		rdbtnaDepartureTime.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
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
				
			}
		});	
			
	

	}

	
	public void fillParkingPlacecombox(){
	
		comboBoxParkingPlace.removeAllItems();
		comboBoxParkingPlace.addItem(" ");
			for(Parking_Places parking_place: parkingPlaces1)
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
	
	public VcpInfo getVcpInfo() {
		if(vcpInfo == null)
			vcpInfo = new VcpInfo(host);
		return vcpInfo;
	
	}
}
