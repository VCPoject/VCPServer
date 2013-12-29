package gui;

import javax.swing.*;
import java.awt.Font;

public class NotWorkingPlaces_Panel extends JPanel {
	public NotWorkingPlaces_Panel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Not working places\r\n");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(140, 25, 354, 54);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Choose parking lot/place:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(26, 90, 189, 25);
		add(lblNewLabel_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("parking place");
		rdbtnNewRadioButton.setBounds(139, 139, 109, 23);
		add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnParkingLot = new JRadioButton("parking lot");
		rdbtnParkingLot.setBounds(345, 139, 109, 23);
		add(rdbtnParkingLot);
		
		JRadioButton rdbtnParkingLot_1 = new JRadioButton("parking lot");
		rdbtnParkingLot_1.setBounds(6, 200, 109, 23);
		add(rdbtnParkingLot_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(118, 201, 364, 20);
		add(comboBox);
		
		JRadioButton rdbtnFloorNo = new JRadioButton("floor no.");
		rdbtnFloorNo.setBounds(6, 262, 109, 23);
		add(rdbtnFloorNo);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(118, 263, 364, 20);
		add(comboBox_1);
		
		JRadioButton rdbtnLineNo = new JRadioButton("Line no.");
		rdbtnLineNo.setBounds(6, 324, 109, 23);
		add(rdbtnLineNo);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(118, 325, 364, 20);
		add(comboBox_2);
		
		JRadioButton rdbtnParkingPlaceNo = new JRadioButton("parking place no.");
		rdbtnParkingPlaceNo.setBounds(6, 380, 109, 23);
		add(rdbtnParkingPlaceNo);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(118, 381, 364, 20);
		add(comboBox_3);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBounds(473, 443, 109, 56);
		add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(51, 443, 109, 56);
		add(btnExit);
	}
	
	private static final long serialVersionUID = 1L;
}
