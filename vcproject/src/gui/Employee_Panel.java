package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Employee_Panel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JButton btnFindAltParkin;
	private JButton btnSetupParkinLot;
	private JButton btnSignAsnotWorking;
	private JButton btnComplains;
	private JButton btnCreateReport;
	private JButton btnStatistics;
	private JButton btnParkingStatus;
	private JButton btnExit;
	private JButton btnSaveParkin;
	
	
	public Employee_Panel() {
		super();
		initialize();
		setLayout(null);
	}
	
	public void initialize(){
		this.setSize(785, 575);
		btnSaveParkin = new JButton("Save parking place");
		btnSaveParkin.setBounds(26, 128, 129, 49);
		add(btnSaveParkin);
		
		JLabel vcpEmployeeLabel = new JLabel("VCP-Employee");
		vcpEmployeeLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 36));
		vcpEmployeeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		vcpEmployeeLabel.setBounds(127, 39, 371, 49);
		add(vcpEmployeeLabel);
		
		btnFindAltParkin = new JButton("Find alt. parkin lot");
		btnFindAltParkin.setBounds(26, 202, 129, 49);
		add(btnFindAltParkin);
		
	    btnSetupParkinLot = new JButton("Setup parkin lot");
		btnSetupParkinLot.setBounds(26, 280, 129, 49);
		add(btnSetupParkinLot);
		
		btnSignAsnotWorking = new JButton("Not working  places");
		btnSignAsnotWorking.setBounds(26, 360, 129, 49);
		add(btnSignAsnotWorking);
		
		btnComplains = new JButton("Complains");
		btnComplains.setBounds(385, 360, 129, 49);
		add(btnComplains);
		
		btnCreateReport = new JButton("Reports");
		btnCreateReport.setBounds(385, 128, 129, 49);
		add(btnCreateReport);
		
		btnStatistics = new JButton("Statistics");
		btnStatistics.setBounds(385, 202, 129, 49);
		add(btnStatistics);
		 
		btnParkingStatus = new JButton("Parking status");
		btnParkingStatus.setBounds(385, 280, 129, 49);
		add(btnParkingStatus);
		
		JLabel optionLabel = new JLabel("Please choose option:");
		optionLabel.setFont(new Font("Arial", Font.BOLD, 14));
		optionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		optionLabel.setBounds(26, 99, 235, 21);
		add(optionLabel);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(26, 453, 129, 49);
		add(btnExit);
	
	}
	
	public void listners(){
		
	}
	
	public JButton getbtnSaveParkin(){
		return btnSaveParkin;
	}
	
	public JButton getbtnFindAltParkin(){
		return btnFindAltParkin;
	}
	
	public JButton getbtnSetupParkinLot(){
		return btnSetupParkinLot;
	}
	
	public JButton getbtnSignAsnotWorking(){
		return btnSignAsnotWorking;
	}
	
	public JButton getbtnComplains(){
		return btnComplains;
	}
	
	public JButton getbtnCreateReport(){
		return btnCreateReport;
	}
	
	public JButton getbtnStatistics(){
		return btnStatistics;
	}
	
	public JButton getbtnParkingStatus(){
		return btnParkingStatus;
	}
	
	public JButton getbtnExit(){
		return btnExit;
	}

}
