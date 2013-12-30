package gui;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotWorkingPlaces_Panel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JRadioButton rdbtnParkingLot;
	private JRadioButton rdbtnParkingPlace;
	private JRadioButton rdbtnParkingLot1;
	private JRadioButton rdbtnFloorNo;
	private JRadioButton rdbtnLineNo;
	private JRadioButton rdbtnParkingPlaceNo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnSave;
	private JButton btnExit;
	private JComboBox <String> comboBoxParkingLot;
	private JComboBox <String> comboBoxParkingPlace;
	private JComboBox <String> comboBoxLine;
	private JComboBox <String> comboBoxFloor;
	
	public NotWorkingPlaces_Panel() {
		initialize();
		setLayout(null);
		listners();
	}
	
	public void initialize(){
		this.setSize(785, 575);
		JLabel lblNewLabel = new JLabel("Not working places\r\n");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(140, 25, 354, 54);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Choose parking lot/place:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(26, 90, 189, 25);
		add(lblNewLabel_1);
		
		rdbtnParkingPlace = new JRadioButton("parking place");
		rdbtnParkingPlace.setBounds(139, 139, 109, 23);
		buttonGroup.add(rdbtnParkingPlace);
		add(rdbtnParkingPlace);
		
		rdbtnParkingLot = new JRadioButton("parking lot");
		rdbtnParkingLot.setBounds(345, 139, 109, 23);
		buttonGroup.add(rdbtnParkingLot);
		add(rdbtnParkingLot);
		
		rdbtnParkingLot1 = new JRadioButton("parking lot");
		rdbtnParkingLot1.setBounds(6, 200, 109, 23);
		add(rdbtnParkingLot1);
		
		comboBoxParkingLot = new <String> JComboBox<String>();
		comboBoxParkingLot.setBounds(118, 201, 364, 20);
		add(comboBoxParkingLot);
		
		rdbtnFloorNo = new JRadioButton("floor no.");
		rdbtnFloorNo.setBounds(6, 262, 109, 23);
		buttonGroup.add(rdbtnFloorNo);
		add(rdbtnFloorNo);
		
		comboBoxFloor = new <String> JComboBox<String>();
		comboBoxFloor.setBounds(118, 263, 364, 20);
		add(comboBoxFloor);
		
		rdbtnLineNo = new JRadioButton("Line no.");
		rdbtnLineNo.setBounds(6, 324, 109, 23);
		buttonGroup.add(rdbtnLineNo );
		add(rdbtnLineNo);
		
		comboBoxLine = new <String> JComboBox<String>();
		comboBoxLine.setBounds(118, 325, 364, 20);
		add(comboBoxLine);
		
		rdbtnParkingPlaceNo = new JRadioButton("parking place no.");
		rdbtnParkingPlaceNo.setBounds(6, 380, 109, 23);
		buttonGroup.add(rdbtnParkingPlaceNo );
		add(rdbtnParkingPlaceNo);
		
		comboBoxParkingPlace = new <String> JComboBox<String>();
		comboBoxParkingPlace.setBounds(118, 381, 364, 20);
		add(comboBoxParkingPlace);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(473, 443, 109, 56);
		add(btnSave);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(51, 443, 109, 56);
		add(btnExit);
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
				
				
			}
		});
		
		rdbtnFloorNo.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
			
				
			}
		});
		
		rdbtnLineNo.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		rdbtnParkingPlaceNo.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
			
				
			}
		});
	}
	
	private void setParkingPlaceAsNotWorking() {
		rdbtnFloorNo.setVisible(true);
		rdbtnLineNo.setVisible(true);
		rdbtnParkingPlaceNo.setVisible(true);
		comboBoxFloor.setVisible(true);
		comboBoxLine.setVisible(true);
		comboBoxParkingPlace.setVisible(true);
		
	}
	
	private void setParkingLotsNotWorking(){
		rdbtnFloorNo.setVisible(false);
		rdbtnLineNo.setVisible(false);
		rdbtnParkingPlaceNo.setVisible(false);
		comboBoxFloor.setVisible(false);
		comboBoxLine.setVisible(false);
		comboBoxParkingPlace.setVisible(false);
	}
	
	public JButton getbtnExit(){
		return btnExit;
	}
	
	public JButton getbtnSave(){
		return btnSave;
	}
}
