package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import entity.Employee;
import java.awt.Font;

public class Employee_Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnFindAltParkin;
	private JButton btnQuarterly;
	private JButton btnSignAsnotWorking;
	private JButton btnComplains;
	private JButton btnCreateReport;
	private JButton btnStatistics;
	private JButton btnParkingStatus;
	private JButton btnSaveParkin;
	private JButton btnChangePricing;
	private JButton btnReviewPricingRequests;
	private JButton btnExit;
	private Employee conectedEmployee;

	public Employee_Panel() {
		super();
		initialize();
		setLayout(null);
	}

	public void initialize() {
		this.setSize(785, 575);
		btnSaveParkin = new JButton("Save parking place");
		btnSaveParkin.setBounds(230, 100, 129, 49);
		add(btnSaveParkin);

		JLabel vcpEmployeeLabel = new JLabel("VCP-Employee");
		vcpEmployeeLabel
				.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 36));
		vcpEmployeeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		vcpEmployeeLabel.setBounds(207, 11, 371, 49);
		add(vcpEmployeeLabel);

		btnFindAltParkin = new JButton("Find alt. parkin lot");
		btnFindAltParkin.setBounds(230, 170, 129, 49);
		add(btnFindAltParkin);

		btnQuarterly = new JButton("Setup parkin lot");
		btnQuarterly.setBounds(230, 240, 129, 49);
		add(btnQuarterly);

		btnSignAsnotWorking = new JButton("Not working  places");
		btnSignAsnotWorking.setBounds(230, 310, 129, 49);
		add(btnSignAsnotWorking);

		btnComplains = new JButton("Complains");
		btnComplains.setBounds(462, 310, 129, 49);
		add(btnComplains);

		btnCreateReport = new JButton("Reports");
		btnCreateReport.setBounds(462, 100, 129, 49);
		add(btnCreateReport);

		btnStatistics = new JButton("Statistics");
		btnStatistics.setBounds(462, 170, 129, 49);
		add(btnStatistics);

		btnParkingStatus = new JButton("Parking status");
		btnParkingStatus.setBounds(462, 240, 129, 49);
		add(btnParkingStatus);

		JLabel optionLabel = new JLabel("Please choose option:");
		optionLabel.setFont(new Font("Arial", Font.BOLD, 14));
		optionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		optionLabel.setBounds(26, 99, 235, 21);
		add(optionLabel);

		btnExit = new JButton("Exit");
		btnExit.setBounds(26, 453, 129, 49);
		add(btnExit);
		
		btnChangePricing = new JButton("Change Pricing");
		btnChangePricing.setBounds(230, 380, 129, 49);
		add(btnChangePricing);

		btnReviewPricingRequests = new JButton("Pricing requests");
		btnReviewPricingRequests.setBounds(462, 380, 129, 49);
		add(btnReviewPricingRequests);
	}

	public void setBtnEnableByEmpRole() {
		switch (getConectedEmployee().getRole()) {
		case "Customer service":
			btnFindAltParkin.setEnabled(false);
			btnQuarterly.setEnabled(false);
			btnSignAsnotWorking.setEnabled(false);
			btnComplains.setEnabled(true);
			btnCreateReport.setEnabled(false);
			btnStatistics.setEnabled(false);
			btnParkingStatus.setEnabled(false);
			btnSaveParkin.setEnabled(true);
			btnChangePricing.setEnabled(false);
			btnReviewPricingRequests.setEnabled(false);
			break;

		case "parking manager":
			btnFindAltParkin.setEnabled(false);
			btnQuarterly.setEnabled(true);
			btnSignAsnotWorking.setEnabled(false);
			btnComplains.setEnabled(true);
			btnCreateReport.setEnabled(false);
			btnStatistics.setEnabled(false);
			btnParkingStatus.setEnabled(false);
			btnSaveParkin.setEnabled(false);
			btnChangePricing.setEnabled(true);
			btnReviewPricingRequests.setEnabled(false);
			break;

		case "network manager":
			btnFindAltParkin.setEnabled(true);
			btnQuarterly.setEnabled(true);
			btnSignAsnotWorking.setEnabled(true);
			btnComplains.setEnabled(false);
			btnCreateReport.setEnabled(true);
			btnStatistics.setEnabled(true);
			btnParkingStatus.setEnabled(true);
			btnSaveParkin.setEnabled(true);
			btnChangePricing.setEnabled(true);
			btnReviewPricingRequests.setEnabled(true);
			break;

		case "operation":
			btnFindAltParkin.setEnabled(true);
			btnQuarterly.setEnabled(true);
			btnSignAsnotWorking.setEnabled(true);
			btnComplains.setEnabled(false);
			btnCreateReport.setEnabled(true);
			btnStatistics.setEnabled(false);
			btnParkingStatus.setEnabled(false);
			btnSaveParkin.setEnabled(true);
			btnChangePricing.setEnabled(true);
			btnReviewPricingRequests.setEnabled(false);
			break;
			
		default:
			btnFindAltParkin.setEnabled(true);
			btnQuarterly.setEnabled(true);
			btnSignAsnotWorking.setEnabled(true);
			btnComplains.setEnabled(true);
			btnCreateReport.setEnabled(true);
			btnStatistics.setEnabled(true);
			btnParkingStatus.setEnabled(true);
			btnSaveParkin.setEnabled(true);
			btnChangePricing.setEnabled(true);
			btnReviewPricingRequests.setEnabled(true);
			break;
			
		}
	}

	public void listners() {

	}

	public JButton getbtnSaveParkin() {
		return btnSaveParkin;
	}

	public JButton getbtnFindAltParkin() {
		return btnFindAltParkin;
	}

	public JButton getbtnQuarterly() {
		return btnQuarterly;
	}

	public JButton getbtnSignAsnotWorking() {
		return btnSignAsnotWorking;
	}

	public JButton getbtnComplains() {
		return btnComplains;
	}

	public JButton getbtnCreateReport() {
		return btnCreateReport;
	}

	public JButton getbtnStatistics() {
		return btnStatistics;
	}

	public JButton getbtnParkingStatus() {
		return btnParkingStatus;
	}

	public JButton getbtnExit() {
		return btnExit;
	}

	public JButton getBtnChangePricing() {
		return btnChangePricing;
	}

	public JButton getBtnReviewPricingRequests() {
		return btnReviewPricingRequests;
	}

	public Employee getConectedEmployee() {
		return conectedEmployee;
	}

	public void setConectedEmployee(Employee conectedEmployee) {
		this.conectedEmployee = conectedEmployee;
	}

}
