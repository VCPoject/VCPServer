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
	private JComboBox <String> comboBoxParkingLotFull;
	private JComboBox <String> comboBoxParkingLotAlternative;
	private int fullParkingLotId;
	private int altParkingLotId;
	private int defaultParkingLot;
	private JButton btnExit;
	private JButton btnSave;
	private JLabel lblParkingLotNumber;
	private JLabel lblParkingLotNumber_1;
	private JPanel panelFullParkingLot;
	private JPanel panelAlternativeParkingLot;
	
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
		
		panelFullParkingLot = new JPanel();
		panelFullParkingLot.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Full parking lot", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFullParkingLot.setBounds(236, 109, 313, 100);
		add(panelFullParkingLot);
		panelFullParkingLot.setLayout(null);
		
		JLabel lblSignInFullLot = new JLabel("Please sign in the full parking lot:");
		lblSignInFullLot.setBounds(6, 16, 266, 27);
		panelFullParkingLot.add(lblSignInFullLot);
		lblSignInFullLot.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		
		comboBoxParkingLotFull = new JComboBox<String>();
		comboBoxParkingLotFull.setBounds(217, 71, 90, 22);
		panelFullParkingLot.add(comboBoxParkingLotFull);
		
		lblParkingLotNumber = new JLabel("Parking lot number:");
		lblParkingLotNumber.setBounds(6, 71, 181, 22);
		panelFullParkingLot.add(lblParkingLotNumber);
		lblParkingLotNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBoxParkingLotFull.addItem(" ");
		fillParkinglotcombobox();
		
		panelAlternativeParkingLot = new JPanel();
		panelAlternativeParkingLot.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Alternative parking lot", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAlternativeParkingLot.setBounds(236, 248, 313, 121);
		add(panelAlternativeParkingLot);
		panelAlternativeParkingLot.setLayout(null);
		
		JLabel lblPleaseChooseAlternative = new JLabel("Please choose alternative parking lot\r\n:");
		lblPleaseChooseAlternative.setBounds(6, 16, 266, 27);
		panelAlternativeParkingLot.add(lblPleaseChooseAlternative);
		lblPleaseChooseAlternative.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		
		comboBoxParkingLotAlternative = new JComboBox<String>();
		comboBoxParkingLotAlternative.setBounds(217, 92, 90, 20);
		panelAlternativeParkingLot.add(comboBoxParkingLotAlternative);
		
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
		btnSave.setEnabled(false);
		
	}
	/**
	 * Fill the combobox of parking lot with 
	 * the default parking lot number
	 */
	public void fillParkinglotcombobox() {
			comboBoxParkingLotFull.addItem((Integer.toString(defaultParkingLot)));
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
			ParkingLotController=new ParkingLot_controller( host, port,vcpInfo);
		
		return ParkingLotController;
	}
}
