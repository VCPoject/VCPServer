package gui;

import java.awt.SystemColor;

import javax.swing.*;


import controler.WeeklyStats;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Statistics extends JPanel {
	/**
	 * 
	 */
	private JButton btnReturn;
	private JButton btnWeeklyStatistics;
	private static final long serialVersionUID = 1L;
	private JTable table;
	private WeeklyStats weekController;
	private String host;
	private int port = 5555;
	private JButton btnDailyStatistics;

	public Statistics(String host, int dEFAULT_PORT) {
		super();
		this.host=host;
		this.port=dEFAULT_PORT;
		initialize();
		listners();
		
	}

	private void initialize() {
		setLayout(null);
		this.setSize(785, 575);
		 setBackground(SystemColor.activeCaption);
		 
		 btnReturn = new JButton("Return");
		 btnReturn.setBounds(10, 519, 93, 35);
		 add(btnReturn);
		 
		 JLabel lblStatistics = new JLabel("Statistics");
		 lblStatistics.setFont(new Font("Tahoma", Font.BOLD, 18));
		 lblStatistics.setBounds(331, 11, 123, 28);
		 add(lblStatistics);
		 
		 btnWeeklyStatistics = new JButton("Weekly Statistics");
		 btnWeeklyStatistics.setFont(new Font("Tahoma", Font.BOLD, 12));
		 btnWeeklyStatistics.setBounds(157, 68, 137, 28);
		 add(btnWeeklyStatistics);
		 
		 table = new JTable(){
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int data,int columnNames){
				 return false;
			 }
				
			
		 };
		 table.setBounds(10, 107, 765, 400);
		 add(table);
		 
		 btnDailyStatistics = new JButton("Daily Statistics");
		 btnDailyStatistics.setFont(new Font("Tahoma", Font.BOLD, 12));
		 btnDailyStatistics.setBounds(10, 68, 137, 28);
		 add(btnDailyStatistics);
	}
	
	private void listners(){
		
		getbtnWeeklyStatistics().addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		getWeeklyStats().getRowData();
		 		table=new JTable(weekController.toVector(),weekController.obtainFields()){
					 /**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int data,int columnNames){
						 return false;
					 }
						
					
				 };
		 		
		 	}
		 });
		
	}

	
	public JButton getBtnReturn() {
		return btnReturn;
	}
	
	public JButton getbtnWeeklyStatistics(){
		return btnWeeklyStatistics;
	}
	
	private WeeklyStats getWeeklyStats() {
		if(weekController == null || !weekController.isConnected()){
			weekController = new WeeklyStats(host,port);
		}
			
		return weekController;
	}
}
