package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Employee_Panel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public Employee_Panel() {
		super();
		initialize();
		setLayout(null);
		
		
	}
	
	public void initialize(){
		this.setSize(785, 575);
		JButton btnNewButton = new JButton("Save parking place");
		btnNewButton.setBounds(26, 128, 129, 49);
		add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("VCP-Employee");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(127, 39, 371, 49);
		add(lblNewLabel);
		
		JButton btnFindAltParkin = new JButton("Find alt. parkin lot");
		btnFindAltParkin.setBounds(26, 202, 129, 49);
		add(btnFindAltParkin);
		
		JButton btnSetupParkinLot = new JButton("Setup parkin lot");
		btnSetupParkinLot.setBounds(26, 280, 129, 49);
		add(btnSetupParkinLot);
		
		JButton btnSignAsnot = new JButton("Not working  places");
		btnSignAsnot.setBounds(26, 360, 129, 49);
		add(btnSignAsnot);
		
		JButton btnComplains = new JButton("Complains");
		btnComplains.setBounds(385, 360, 129, 49);
		add(btnComplains);
		
		JButton btnCreateReport = new JButton("Reports");
		btnCreateReport.setBounds(385, 128, 129, 49);
		add(btnCreateReport);
		
		JButton btnStatistics = new JButton("Statistics");
		btnStatistics.setBounds(385, 202, 129, 49);
		add(btnStatistics);
		
		JButton btnParkingStatus = new JButton("Parking status");
		btnParkingStatus.setBounds(385, 280, 129, 49);
		add(btnParkingStatus);
		
		JLabel lblNewLabel_1 = new JLabel("Please choose option:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(26, 99, 235, 21);
		add(lblNewLabel_1);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(26, 453, 129, 49);
		add(btnExit);
	
	}
	

}
