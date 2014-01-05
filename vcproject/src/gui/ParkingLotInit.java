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
	private JRadioButton rdbtnParkingLotNum;
	private JComboBox <String> comboBoxParkingLot;
	private JButton btnExit;
	private JButton btnSave;
	private VcpInfo vcpInfo;
	private String host;
	
	public ParkingLotInit(String host, ArrayList<Parking_Lot> parkingLotInfo) {
		super();
		this.host=host;
		initialize();
		setLayout(null);
	}

	private void initialize() {
		this.setSize(500, 400);
		JLabel lblParkingLotInIt= new JLabel("Parking lot initialization");
		lblParkingLotInIt.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 36));
		lblParkingLotInIt.setBounds(51, 29, 456, 63);
		add(lblParkingLotInIt);
		
		JLabel lblChooseParkingLot = new JLabel("Please choose parking lot:");
		lblChooseParkingLot.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblChooseParkingLot.setBounds(20, 103, 191, 32);
		add(lblChooseParkingLot);
		
		rdbtnParkingLotNum = new JRadioButton("New radio button");
		rdbtnParkingLotNum.setBounds(20, 168, 109, 23);
		add(rdbtnParkingLotNum);
		
		comboBoxParkingLot = new JComboBox <String>();
		comboBoxParkingLot.setBounds(154, 169, 271, 20);
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
	
	public JRadioButton getrdbtnParkingLotNum(){
		return rdbtnParkingLotNum;
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
