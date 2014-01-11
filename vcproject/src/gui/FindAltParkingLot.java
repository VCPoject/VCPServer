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
	private VcpInfo vcpInfo;
	private ArrayList<Parking_Lot> parkingLot;
	private ArrayList<Parking_Lot> altParkingLots;
	private ParkingLot_controller ParkingLotController;
	private JComboBox <String> comboBoxParkingLot1;
	private JComboBox <String> comboBoxParkingLot2;
	private int fullParkingLotId;
	private int altParkingLotId;
	private int defaultParkingLot;
	private JButton btnExit;
	private JButton btnSave;
	private JLabel lblParkingLotNumber;
	private JLabel lblParkingLotNumber_1;
	private JPanel panel;
	private JPanel panel_1;
	
	public FindAltParkingLot(String host,int port,VcpInfo vcpInfo){
		super();
		this.vcpInfo=vcpInfo;
		this.defaultParkingLot=vcpInfo.getDefultParkingLot().getIdparkinglot();
		this.parkingLot=vcpInfo.getParkingLot();
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
		lbFindAltParkinLot.setBounds(223, 11, 339, 43);
		add(lbFindAltParkinLot);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Full parking lot", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(236, 109, 313, 100);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblSignInFullLot = new JLabel("Please sign in the full parking lot:");
		lblSignInFullLot.setBounds(6, 16, 266, 27);
		panel.add(lblSignInFullLot);
		lblSignInFullLot.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		
		comboBoxParkingLot1 = new JComboBox<String>();
		comboBoxParkingLot1.setBounds(217, 71, 90, 22);
		panel.add(comboBoxParkingLot1);
		
		lblParkingLotNumber = new JLabel("Parking lot number:");
		lblParkingLotNumber.setBounds(6, 71, 181, 22);
		panel.add(lblParkingLotNumber);
		lblParkingLotNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBoxParkingLot1.addItem(" ");
		fillParkinglotcombobox();
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Alternative parking lot", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(236, 248, 313, 121);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblPleaseChooseAlternative = new JLabel("Please choose alternative parking lot\r\n:");
		lblPleaseChooseAlternative.setBounds(6, 16, 266, 27);
		panel_1.add(lblPleaseChooseAlternative);
		lblPleaseChooseAlternative.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		
		comboBoxParkingLot2 = new JComboBox<String>();
		comboBoxParkingLot2.setBounds(217, 92, 90, 20);
		panel_1.add(comboBoxParkingLot2);
		
		lblParkingLotNumber_1 = new JLabel("Parking lot number:");
		lblParkingLotNumber_1.setBounds(6, 92, 181, 22);
		panel_1.add(lblParkingLotNumber_1);
		lblParkingLotNumber_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		btnExit = new JButton("Return");
		btnExit.setBounds(10, 519, 109, 45);
		add(btnExit);
		
		btnSave = new JButton("Save\r\n");
		btnSave.setBounds(338, 390, 109, 45);
		add(btnSave);
		btnSave.setEnabled(false);
		
	}
	
	public void fillParkinglotcombobox() {
			comboBoxParkingLot1.addItem((Integer.toString(defaultParkingLot)));
	}
	
	public void fillAltParkinglotcombox(){
		altParkingLots=getParkingLot_controller().findAvailableParkingLots();
			for(Parking_Lot parkingLot: altParkingLots)
				comboBoxParkingLot2.addItem((Integer.toString(parkingLot.getIdparkinglot())));
	}

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
