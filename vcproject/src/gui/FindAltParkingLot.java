package gui;

import javax.swing.*;

import controler.*;
import entity.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.border.TitledBorder;

public class FindAltParkingLot extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private String host;
	private int port;
	/** vcpInfo is a controller that run on start-up of the 
	 * application and download all the info form the DB
	 * its contains all:
	 * order,subscribed,reservation,employees,parking lot,
	 * parking places,clients,default parking lot,cars
	 */
	private VcpInfo vcpInfo;
	private ArrayList<Parking_Lot> altParkingLots;
	private ParkingLot_controller ParkingLotController;
	private JComboBox <String> comboBoxParkingLotAlternative;
	private int altParkingLotId;
	private int defaultParkingLot;
	private JButton btnExit;
	private JButton btnSave;
	private JLabel lblParkingLotNumber_1;
	private JPanel panelAlternativeParkingLot;
	private JButton btnRemove;
	
	/**
	 * This panel is for setting full parking lot and assign alternative one.
	 * @param host for make connection with server side
	 * @param port for make connection with server side
	 * @param vcpInfo for get info from DB
	 */
	public FindAltParkingLot(String host,int port,VcpInfo vcpInfo){
		super();
		this.vcpInfo=vcpInfo;
		this.defaultParkingLot=vcpInfo.getDefultParkingLot().getIdparkinglot();
		this.host=host;
		this.port=port;
		initalize();
		setLayout(null);
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(524, 390, 109, 45);
		add(btnRemove);
		listners();
	}
	/**
	 * Initialize the panel of saving parking place
	 */
	private void initalize() {
		this.setSize(785, 575);
		JLabel lbFindAltParkinLot = new JLabel("Find alt. parking lot");
		lbFindAltParkinLot.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 36));
		lbFindAltParkinLot.setBounds(223, 11, 339, 43);
		add(lbFindAltParkinLot);
		
		panelAlternativeParkingLot = new JPanel();
		panelAlternativeParkingLot.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Alternative parking lot", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAlternativeParkingLot.setBounds(249, 157, 313, 121);
		add(panelAlternativeParkingLot);
		panelAlternativeParkingLot.setLayout(null);
		
		JLabel lblPleaseChooseAlternative = new JLabel("Please choose alternative parking lot\r\n:");
		lblPleaseChooseAlternative.setBounds(6, 16, 266, 27);
		panelAlternativeParkingLot.add(lblPleaseChooseAlternative);
		lblPleaseChooseAlternative.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		
		comboBoxParkingLotAlternative = new JComboBox<String>();
		comboBoxParkingLotAlternative.setBounds(217, 92, 90, 20);
		panelAlternativeParkingLot.add(comboBoxParkingLotAlternative);
		fillAltParkinglotcombox();
		
		lblParkingLotNumber_1 = new JLabel("Parking lot number:");
		lblParkingLotNumber_1.setBounds(6, 92, 181, 22);
		panelAlternativeParkingLot.add(lblParkingLotNumber_1);
		lblParkingLotNumber_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		btnExit = new JButton("Return");
		btnExit.setBounds(10, 519, 109, 45);
		add(btnExit);
		
		btnSave = new JButton("Save\r\n");
		btnSave.setBounds(338, 390, 109, 45);
		add(btnSave);
		
	}
	
	/**
	 * Fill the combobox of alternative parking lot with 
	 * the default parking lot number
	 */
	public void fillAltParkinglotcombox(){
		altParkingLots=getParkingLot_controller().findAvailableParkingLots();
			for(Parking_Lot parkingLot: altParkingLots)
				comboBoxParkingLotAlternative.addItem((Integer.toString(parkingLot.getIdparkinglot())));
	}
	/**
	 * Listeners of the GUI components.
	 * In this case only save buttons
	 */
	public void listners(){
		
		getbtnSave().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try{
					
					if(comboBoxParkingLotAlternative.getSelectedItem().toString().equals(" "))
						throw new Exception("Yoy didn't insert Alternative parking lot");
				
					if(getParkingLot_controller().checkAltParkingLot(altParkingLotId,defaultParkingLot))
						throw new Exception("Parking lot"+" "+defaultParkingLot+" "+"has already alternative park");
					
				getParkingLot_controller().updateparkingLotAsAlt(defaultParkingLot, altParkingLotId);
				}
				
				catch(Exception e1){
				getParkingLot_controller().showWarningMsg(e1.getMessage());
				}
			}
		});
		
		getbtnRemove().addActionListener(new ActionListener() {
			
			
				public void actionPerformed(ActionEvent arg0) {
					try{
						if(comboBoxParkingLotAlternative.getSelectedItem().toString().equals(" "))
							throw new Exception("Yoy didn't insert Alternative parking lot");
			
						if(getParkingLot_controller().checkAltParkingLot(altParkingLotId,defaultParkingLot)==false)
							throw new Exception("Parking lot"+" "+altParkingLotId+" "+"is not the"
							+" "+ "alternative parking lot of parking lot"+" "+defaultParkingLot);
				
					getParkingLot_controller().RemoveparkingLotAsAlt(defaultParkingLot, altParkingLotId);
					}
			
					catch(Exception e1){
						getParkingLot_controller().showWarningMsg(e1.getMessage());
					}
				}
		});
		
		comboBoxParkingLotAlternative.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try{
					
					altParkingLotId=Integer.parseInt(comboBoxParkingLotAlternative.getSelectedItem().toString());
				}
				
				catch(Exception e1){
					getParkingLot_controller().showWarningMsg(e1.getMessage());
				}
			}
		});
	}
	
	public JButton getbtnExit(){
		return btnExit;
	}
	
	public JButton getbtnSave(){
		return btnSave;
	}
	
	public JButton getbtnRemove(){
		return btnRemove;
	}
	
	public ParkingLot_controller getParkingLot_controller(){
		
		if(ParkingLotController==null)
			ParkingLotController=new ParkingLot_controller( host, port,vcpInfo);
		
		return ParkingLotController;
	}
}
