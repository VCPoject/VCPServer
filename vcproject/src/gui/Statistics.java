package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import controler.DailyStatistic;
import controler.WeeklyStats;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import com.toedter.calendar.JDateChooser;

import javax.swing.border.TitledBorder;

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
	private JScrollPane scrollPane;
	private JDateChooser dateChooser;
	private DailyStatistic dailyStatistic;
	private JPanel panel_1;

	public Statistics(String host, int dEFAULT_PORT) {
		super();
		this.host=host;
		this.port=dEFAULT_PORT;
		initialize();
		listners();
		
	}
/**
 * intizlize vlaue for GUI
 */
	private void initialize() {
		setLayout(null);
		this.setSize(785, 575);
		 
		 btnReturn = new JButton("Return");
		 btnReturn.setBounds(10, 519, 93, 35);
		 add(btnReturn);
		 
		 JLabel lblStatistics = new JLabel("Statistics");
		 lblStatistics.setFont(new Font("Tahoma", Font.BOLD, 24));
		 lblStatistics.setBounds(337, 0, 110, 29);
		 add(lblStatistics);
		 
		 table=new JTable(){
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int data,int columnNames){
				 return false;
			 }
			public boolean getScrollableTracksViewportWidth() {
				   return getPreferredSize().width < getParent().getWidth();
				 }
		 };
		 scrollPane = new JScrollPane(table);
		 scrollPane.setBounds(10, 175, 765, 332);
		 add(scrollPane);
		 
		 panel_1 = new JPanel();
		 panel_1.setBorder(new TitledBorder(null, "Weekly Statistics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 panel_1.setBounds(430, 70, 158, 84);
		 add(panel_1);
		 panel_1.setLayout(null);
		 
		 btnWeeklyStatistics = new JButton("Weekly Statistics");
		 btnWeeklyStatistics.setBounds(10, 21, 137, 28);
		 panel_1.add(btnWeeklyStatistics);
		 btnWeeklyStatistics.setFont(new Font("Tahoma", Font.BOLD, 12));
		 
		 JPanel panel = new JPanel();
		 panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Daily Statistics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 panel.setBounds(182, 70, 149, 84);
		 add(panel);
		 panel.setLayout(null);

		 btnDailyStatistics = new JButton("Daily Statistics");
		 btnDailyStatistics.setEnabled(false);
		 btnDailyStatistics.setBounds(6, 16, 137, 28);
		 panel.add(btnDailyStatistics);
		 btnDailyStatistics.setFont(new Font("Tahoma", Font.BOLD, 12));
		 
		 dateChooser = new JDateChooser();
		 dateChooser.setBounds(6, 57, 137, 20);
		 panel.add(dateChooser);
	}
	
	private void listners(){
		/**
		 * Action listener for Week Statistics
		 */
		getbtnWeeklyStatistics().addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		getWeeklyStats();
		 		table.setModel(new DefaultTableModel(weekController.toVector(),weekController.obtainFields()));
		 		arrangeTable();
		 	}
		 });
		
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		btnDailyStatistics.setEnabled(true);
		 	}
		 });
		/**
		 * listener for daily statistics
		 */
		btnDailyStatistics.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		String[] columnNames ={"Statistic num", "Implement Order", "Canceled Orders", "No. of members", "Members with more then one car", "Late","Date"}; 
				int columnLength = columnNames.length;
				Vector<String> cols = new Vector<String>(columnLength);
				for (int i = 0; i < columnLength; i++)
					cols.add(columnNames[i]);
		 		Date date = dateChooser.getDate();
		 		Vector<Vector<Object>> data = getDailyStatistic().getDailyStatistic(date);
		 		if(data != null){
		 			table.setModel(new DefaultTableModel(data,cols));
		 			arrangeTable();
		 		}
		 	}
		 });
		
	}
	/**
	 * auto arrange column in table
	 */
	public void arrangeTable(){
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
 	        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
 	        table.setDefaultRenderer(String.class, centerRenderer);
 	        for(int i=0;i<table.getColumnCount();i++){
 	        	table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
 	        }
			TableCellRenderer rendererHeader = table.getTableHeader().getDefaultRenderer();
			JLabel label = (JLabel)rendererHeader;
			label.setHorizontalAlignment(JLabel.CENTER);
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumnAdjuster tca = new TableColumnAdjuster(table);
			tca.adjustColumns();
			scrollPane.revalidate();
			scrollPane.repaint();
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

	public DailyStatistic getDailyStatistic() {
		if(dailyStatistic == null)
			dailyStatistic = new DailyStatistic(host, port);
		return dailyStatistic;
	}
}
