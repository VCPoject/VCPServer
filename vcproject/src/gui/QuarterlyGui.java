package gui;

import java.awt.SystemColor;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controler.VcpInfo;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;

public class QuarterlyGui extends JPanel {
	
	private JButton btnReturn;

	public QuarterlyGui(String host, int dEFAULT_PORT, VcpInfo vcpInfo) {
		super();
		initialize();
		listners();
	}

	private void listners() {
		setLayout(null);
		this.setSize(785, 575);
		 setBackground(SystemColor.activeCaption);
		 
		 
		 
		
	}

	private void initialize() {
		
		setLayout(null);
		this.setSize(785, 575);
		 setBackground(SystemColor.activeCaption);
		 

		 
		btnReturn = new JButton("Return");
		 btnReturn.setBounds(10, 519, 93, 35);
		 add(btnReturn);
		 
		 JLabel lblQuatetlyReports = new JLabel("Quarterly Reports");
		 lblQuatetlyReports.setFont(new Font("Tahoma", Font.BOLD, 24));
		 lblQuatetlyReports.setBounds(269, 11, 247, 42);
		 add(lblQuatetlyReports);
		 
		 JButton btnOrdersReport = new JButton("Orders Report");
		 btnOrdersReport.setEnabled(false);
		 btnOrdersReport.setFont(new Font("Tahoma", Font.BOLD, 12));
		 btnOrdersReport.setBounds(10, 174, 200, 35);
		 add(btnOrdersReport);
		 
		 JButton btnNewButton = new JButton("Complain Report");
		 btnNewButton.setEnabled(false);
		 btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		 btnNewButton.setBounds(237, 174, 235, 35);
		 add(btnNewButton);
		 
		 JButton btnNewButton_1 = new JButton("Num Of Not Working \r\nParking Places");
		 btnNewButton_1.setEnabled(false);
		 btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		 btnNewButton_1.setBounds(498, 174, 265, 35);
		 add(btnNewButton_1);
		 
		 JDateChooser dateChooser = new JDateChooser();
		 dateChooser.setBounds(330, 77, 121, 27);
		 add(dateChooser);
		 
		 JDateChooser dateChooser_1 = new JDateChooser();
		 dateChooser_1.setBounds(622, 77, 110, 27);
		 add(dateChooser_1);
		 
		 JLabel lblEnd = new JLabel("End :");
		 lblEnd.setFont(new Font("Tahoma", Font.BOLD, 14));
		 lblEnd.setBounds(539, 77, 73, 27);
		 add(lblEnd);
		 
		 JLabel lblStart = new JLabel("Start :");
		 lblStart.setFont(new Font("Tahoma", Font.BOLD, 14));
		 lblStart.setBounds(253, 77, 67, 27);
		 add(lblStart);
		 
		 JLabel lblSelectDate = new JLabel("Select Date - >");
		 lblSelectDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		 lblSelectDate.setBounds(38, 77, 160, 27);
		 add(lblSelectDate);
		 
		
	}
	
	
	public JButton getBtnReturn() {
		return btnReturn;
	}
}
