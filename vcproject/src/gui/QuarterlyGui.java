package gui;



import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controler.Quarterly;
import controler.VcpInfo;

import java.awt.Font;

import com.toedter.calendar.JYearChooser;

import entity.Employee;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class QuarterlyGui extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnReturn;
	private JComboBox<Integer> comboBox;
	private JButton btnOrdersReport;
	private JButton btnComplain;
	private JButton btnNotWorking;
	private JYearChooser yearChooser;
	private VcpInfo vcpInfo;
	private Quarterly qControler;
	private String host;
	private int port = 5555;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblSelectParkingLot;
	private JComboBox<Integer> comboBox_1;
	private Employee conectedEmployee;

	
	public QuarterlyGui(String host, int dEFAULT_PORT, VcpInfo vcpInfo, Employee employee) {
		super();
		this.vcpInfo=vcpInfo;
		this.host=host;
		this.conectedEmployee = employee;
		initialize();
		listners();
	}

	private void listners() {
		 
		 comboBox.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		btnComplain.setEnabled(true);
			 		btnNotWorking.setEnabled(true);
			 		btnOrdersReport.setEnabled(true);
			 		
			 	}
			 });
		 
		 btnOrdersReport.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		Vector<String> s = new Vector<String>(14);
			 		s.add("Order ID");
			 		s.add("Car Number");
			 		s.add("Client ID");
			 		s.add("Parking ID");
			 		s.add("Arrival Date");
			 		s.add("Arrival Time");
			 		s.add("Departure Date");
			 		s.add("Departure Time");
			 		s.add("Check In Date");
			 		s.add("Check In Time");
			 		s.add("Check Out Date");
			 		s.add("Check Out Time");
			 		s.add("Status");
			 		s.add("Client Type");
			 		if(getConectedEmployee().getRole().equals("network manager")){
			 		Vector<Vector<Object>> data = getQuarterly().OrderStatus(Integer.parseInt(comboBox.getSelectedItem().toString()), 
			 				yearChooser.getYear(),Integer.parseInt(comboBox_1.getSelectedItem().toString()));
			 		table.setModel((new DefaultTableModel(data,s)));
			 		arrangeTable();
			 		}
			 		else {
			 			Vector<Vector<Object>> data = getQuarterly().OrderStatus(Integer.parseInt(comboBox.getSelectedItem().toString()), 
				 				yearChooser.getYear(),vcpInfo.getDefultParkingLot().getIdparkinglot());
				 		table.setModel((new DefaultTableModel(data,s)));
				 		arrangeTable();
			 		}

			 		
			 	}
			 });
		 
		 btnComplain.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		Vector<String> s = new Vector<String>(6);
			 		s.add("Complain Num");
			 		s.add("Client ID");
			 		s.add("Description");
			 		s.add("Status");
			 		s.add("Opened On Date");
			 		s.add("Status");
			 		if(getConectedEmployee().getRole().equals("network manager")){
			 			Vector<Vector<Object>> data = getQuarterly().ComplainStatus(Integer.parseInt(comboBox.getSelectedItem().toString()), 
			 				yearChooser.getYear(),Integer.parseInt(comboBox_1.getSelectedItem().toString()));
				 		table.setModel((new DefaultTableModel(data,s)));
				 		arrangeTable();
			 		}
			 		else {
			 			Vector<Vector<Object>> data = getQuarterly().ComplainStatus(Integer.parseInt(comboBox.getSelectedItem().toString()), 
				 				yearChooser.getYear(),vcpInfo.getDefultParkingLot().getIdparkinglot());
				 		table.setModel((new DefaultTableModel(data,s)));
				 		arrangeTable();
			 		}


			 	}
			 });

		 btnNotWorking.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		Vector<String> s = new Vector<String>(2);
			 		s.add("Lot Number");
			 		s.add("Number Of Not Working Place");
			 		if(getConectedEmployee().getRole().equals("network manager")){
			 			Vector<Object> data = getQuarterly().NotWorking(Integer.parseInt(comboBox.getSelectedItem().toString()), 
			 				yearChooser.getYear(),Integer.parseInt(comboBox_1.getSelectedItem().toString()));
			 		table.setModel((new DefaultTableModel(data,s)));
			 		arrangeTable();
			 		}
			 		else {
			 			Vector<Object> data = getQuarterly().NotWorking(Integer.parseInt(comboBox.getSelectedItem().toString()), 
				 				yearChooser.getYear(),vcpInfo.getDefultParkingLot().getIdparkinglot());
				 		table.setModel((new DefaultTableModel(data,s)));
				 		arrangeTable();
			 		}


			 		
			 	}
			 });
		 
		 
		 
	}

	private void initialize() {
		
		setLayout(null);
		this.setSize(785, 575);

		 

		 
		btnReturn = new JButton("Return");
		 btnReturn.setBounds(10, 519, 93, 35);
		 add(btnReturn);
		 
		 JLabel lblQuatetlyReports = new JLabel("Quarterly Reports");
		 lblQuatetlyReports.setFont(new Font("Tahoma", Font.BOLD, 24));
		 lblQuatetlyReports.setBounds(269, 11, 247, 42);
		 add(lblQuatetlyReports);
		 
		 btnOrdersReport = new JButton("Orders Report");
		 btnOrdersReport.setEnabled(false);
		 btnOrdersReport.setFont(new Font("Tahoma", Font.BOLD, 12));
		 btnOrdersReport.setBounds(10, 174, 200, 35);
		 add(btnOrdersReport);
		 
		 btnComplain = new JButton("Complain Report");
		 btnComplain.setEnabled(false);
		 btnComplain.setFont(new Font("Tahoma", Font.BOLD, 12));
		 btnComplain.setBounds(237, 174, 235, 35);
		 add(btnComplain);
		 
		 btnNotWorking = new JButton("Num Of Not Working \r\nParking Places");
		 btnNotWorking.setEnabled(false);
		 btnNotWorking.setFont(new Font("Tahoma", Font.BOLD, 12));
		 btnNotWorking.setBounds(498, 174, 265, 35);
		 add(btnNotWorking);
		 
		 
		 JLabel lblEnd = new JLabel("Year :");
		 lblEnd.setFont(new Font("Tahoma", Font.BOLD, 14));
		 lblEnd.setBounds(539, 77, 73, 27);
		 add(lblEnd);
		 
		 JLabel lblStart = new JLabel("Quarter Number :");
		 lblStart.setFont(new Font("Tahoma", Font.BOLD, 14));
		 lblStart.setBounds(257, 77, 130, 27);
		 add(lblStart);
		 
		 JLabel lblSelectDate = new JLabel("Select Quarter - >");
		 lblSelectDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		 lblSelectDate.setBounds(31, 79, 160, 27);
		 add(lblSelectDate);
		 
		 comboBox = new JComboBox<Integer>();
		 comboBox.setBounds(397, 84, 101, 20);
		 for(int i=1;i<5;i++)
			 comboBox.addItem(i);
		 add(comboBox);
		 
		 yearChooser = new JYearChooser();
		 yearChooser.setBounds(601, 84, 93, 20);
		 add(yearChooser);
		 
		 
		 scrollPane = new JScrollPane();
		 scrollPane.setBounds(31, 252, 732, 244);
		 add(scrollPane);
		 table = new JTable();
		 scrollPane.setViewportView(table);
		 if(getConectedEmployee().getRole().equals("network manager"))
		 {
		 lblSelectParkingLot = new JLabel("Select Parking Lot :");
		 lblSelectParkingLot.setFont(new Font("Tahoma", Font.BOLD, 14));
		 lblSelectParkingLot.setBounds(269, 127, 145, 20);
		 add(lblSelectParkingLot);
		 comboBox_1 = new JComboBox<Integer>();
		 comboBox_1.setBounds(412, 129, 86, 20);
		 for(int i=1;i<7;i++)
			 comboBox_1.addItem(i);
		 add(comboBox_1);
		 }
		
	}
	
	private Quarterly getQuarterly() {
		if(qControler == null || !qControler.isConnected()){
			qControler = new Quarterly(host,port,vcpInfo);
		}
			
		return qControler;
	}
	
	

	
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
	
	public void setConectedEmployee(Employee conectedEmployee) {
		this.conectedEmployee = conectedEmployee;
	}
	public Employee getConectedEmployee() {
		return conectedEmployee;
	}

	public JButton getBtnReturn() { 
		return btnReturn;
	}


}
