package gui;

import javax.swing.*;

import controler.*;
import entity.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class FindAltParkingLot extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private String host;
	private int port;
	private ArrayList<Parking_Lot> parkingLot;
	private ArrayList<Parking_Lot> altParkingLots;
	private ParkingLot_controller ParkingLotController;
	private JRadioButton rdbtnparkingLotNum1;
	private JRadioButton rdbtnparkingLotNum2;
	private JComboBox <String> comboBoxParkingLot1;
	private JComboBox <String> comboBoxParkingLot2;
	private int fullParkingLotId;
	private int altParkingLotId;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnExit;
	private JButton btnSave;
	
	public FindAltParkingLot(String host,int port,ArrayList<Parking_Lot> parkingLot) {
		super();
		this.parkingLot=parkingLot;
		this.host=host;
		this.port=port;
		initalize();
		setLayout(null);
		listners();
	}

	private void initalize() {
		this.setSize(785, 575);
		JLabel lbFindAltParkinLot = new JLabel("Find alt. parking lot");
		lbFindAltParkinLot.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 36));
		lbFindAltParkinLot.setBounds(135, 26, 364, 65);
		add(lbFindAltParkinLot);
		
		JLabel lblSignInFullLot = new JLabel("Please sign in the full parking lot:");
		lblSignInFullLot.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblSignInFullLot.setBounds(10, 125, 266, 27);
		add(lblSignInFullLot);
		
		rdbtnparkingLotNum1 = new JRadioButton("parking lot no.");
		rdbtnparkingLotNum1.setBounds(10, 177, 109, 23);
		add(rdbtnparkingLotNum1);
		buttonGroup.add(rdbtnparkingLotNum1);
		
		comboBoxParkingLot1 = new JComboBox<String>();
		comboBoxParkingLot1.setBounds(103, 178, 364, 20);
		add(comboBoxParkingLot1);
		comboBoxParkingLot1.addItem(" ");
		fillParkinglotcombobox();
		
		JLabel lblPleaseChooseAlternative = new JLabel("Please choose alternative parking lot\r\n:");
		lblPleaseChooseAlternative.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblPleaseChooseAlternative.setBounds(10, 264, 266, 27);
		add(lblPleaseChooseAlternative);
		
		rdbtnparkingLotNum2 = new JRadioButton("parking lot no.");
		rdbtnparkingLotNum2.setBounds(10, 333, 109, 23);
		add(rdbtnparkingLotNum2);
		buttonGroup.add(rdbtnparkingLotNum2);
		
		comboBoxParkingLot2 = new JComboBox<String>();
		comboBoxParkingLot2.setBounds(125, 334, 364, 20);
		add(comboBoxParkingLot2);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(31, 436, 109, 45);
		add(btnExit);
		
		btnSave = new JButton("Save\r\n");
		btnSave.setBounds(470, 436, 109, 45);
		add(btnSave);
		btnSave.setEnabled(false);
		
	}
	
	public void fillParkinglotcombobox() {
		for(Integer i=1;i<7;i++)
			comboBoxParkingLot1.addItem(i.toString());
	}
	
	public void fillAltParkinglotcombox(){
		altParkingLots=getParkingLot_controller().findAvailableParkingLots();
			for(Parking_Lot parkingLot: altParkingLots)
				comboBoxParkingLot2.addItem((Integer.toString(parkingLot.getIdparkinglot())));
	}

	public void listners(){
		rdbtnparkingLotNum1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				comboBoxParkingLot1.addActionListener(new ActionListener() {
				
					public void actionPerformed(ActionEvent arg0) {
						try{
							fullParkingLotId=Integer.parseInt(comboBoxParkingLot1.getSelectedItem().toString());
							getParkingLot_controller().updateParkingLotAsFull(fullParkingLotId);
							fillAltParkinglotcombox();
							btnSave.setEnabled(true);
						}
					
						catch(Exception e1){
							fullParkingLotId=0;
							btnSave.setEnabled(false);
						}
					}
				});
			}
		});
		
		rdbtnparkingLotNum2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				comboBoxParkingLot2.addActionListener(new ActionListener() {
				
					public void actionPerformed(ActionEvent arg0) {
						try{
							altParkingLotId=Integer.parseInt(comboBoxParkingLot2.getSelectedItem().toString());
						}
					
						catch(Exception e1){
							altParkingLotId=0;
						}
					}
				});
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				getParkingLot_controller().updateparkingLotAsAlt(fullParkingLotId, altParkingLotId);
			}
		});
		
	}
	
	public JButton getbtnExit(){
		return btnExit;
	}
	
	public JButton getbtnSave(){
		return btnSave;
	}
	
	public ParkingLot_controller getParkingLot_controller(){
		if(ParkingLotController==null)
			ParkingLotController=new ParkingLot_controller( parkingLot,host, port);
		
		return ParkingLotController;
	}
}
