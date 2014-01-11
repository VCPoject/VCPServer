package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;

import controler.VcpInfo;
import entity.Parking_Lot;

public class ParkingLotInit extends JPanel{
	private static final long serialVersionUID = 1L;
	private JComboBox <String> comboBoxParkingLot;
	private JButton btnExit;
	private JButton btnSave;
	private VcpInfo vcpInfo;
	private String host;
	private JLabel lblParkigLot;
	
	public ParkingLotInit(String host, ArrayList<Parking_Lot> parkingLotInfo) {
		super();
		this.host=host;
		initialize();
		setLayout(null);
		
		lblParkigLot = new JLabel("Parkig lot:");
		lblParkigLot.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblParkigLot.setBounds(144, 190, 93, 22);
		add(lblParkigLot);
	}

	private void initialize() {
		this.setSize(500, 400);
		JLabel lblParkingLotInIt= new JLabel("Parking lot initialization");
		lblParkingLotInIt.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 36));
		lblParkingLotInIt.setBounds(48, 11, 404, 43);
		add(lblParkingLotInIt);
		
		JLabel lblChooseParkingLot = new JLabel("Please choose parking lot:");
		lblChooseParkingLot.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblChooseParkingLot.setBounds(20, 134, 191, 32);
		add(lblChooseParkingLot);
		
		comboBoxParkingLot = new JComboBox <String>();
		comboBoxParkingLot.setBounds(247, 190, 90, 20);
		add(comboBoxParkingLot);
		fillcomboBoxParkinLot();
		
		btnSave = new JButton("Save");
		btnSave.setBounds(360, 288, 109, 55);
		add(btnSave);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(20, 288, 109, 55);
		add(btnExit);
		
	}
	
	public void fillcomboBoxParkinLot() {
		for(Integer i=1;i<7;i++)
			comboBoxParkingLot.addItem(i.toString());
	}

	public JButton getbtnExit(){
		return btnExit;
	}
	
	public JButton getbtnSave(){
		return btnSave;
	}
	
	public JComboBox <String> getcomboBoxParkingLot(){
		return comboBoxParkingLot;
	}
	
	public VcpInfo getVcpInfo() {
		if(vcpInfo == null)
			vcpInfo = new VcpInfo(host);
		return vcpInfo;
	
	}
	
	
}
