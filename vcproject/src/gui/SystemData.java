package gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JYearChooser;

import controler.Quarterly;
import controler.SystemDataControler;
import controler.VcpInfo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JScrollPane;

public class SystemData extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnReturn;
	private JTable table;
	private JComboBox<Integer> comboBox;
	private JButton btnOrders;
	private JButton btnEmployees;
	private JButton btnComplains;
	private JButton btnReports;
	private JYearChooser yearChooser;
	private Quarterly qControler;
	private VcpInfo vcpInfo;
	private String host;
	private int port = 5555;
	private JComboBox<String> comboBox_1;
	private JLabel lblLot;
	private JComboBox<Integer> comboBox_2;
	private JScrollPane scrollPane;
	private SystemDataControler sControler;
	public SystemData(String host, int dEFAULT_PORT,VcpInfo vcpInfo) {
		super();
		this.vcpInfo=vcpInfo;
		this.host=host;
		this.port=port;
		initialize();
		listners();
	}

	
	
	private void initialize() {
		setLayout(null);
		this.setSize(785, 575);

		btnReturn = new JButton("Return");
		 btnReturn.setBounds(10, 519, 93, 35);
		 add(btnReturn);
		 
		 JLabel lblSystemDataread = new JLabel("System Data (Read Only)");
		 lblSystemDataread.setFont(new Font("Tahoma", Font.BOLD, 24));
		 lblSystemDataread.setBounds(236, 11, 313, 35);
		 add(lblSystemDataread);
		 
		 btnOrders = new JButton("Orders");
		 btnOrders.setBounds(10, 74, 89, 23);
		 add(btnOrders);
		 
		 btnEmployees = new JButton("Employees");
		 btnEmployees.setBounds(109, 74, 89, 23);
		 add(btnEmployees);
		 
		 btnComplains = new JButton("Complains");
		 btnComplains.setBounds(208, 74, 89, 23);
		 add(btnComplains);
		 
		 btnReports = new JButton("Quarterly Reports");
		 btnReports.setEnabled(false);
		 btnReports.setBounds(10, 118, 124, 23);
		 add(btnReports);
		 
		 scrollPane = new JScrollPane();
		 scrollPane.setBounds(10, 170, 765, 338);
		 add(scrollPane);
		 
		 table = new JTable();
		 scrollPane.setViewportView(table);
		 
		 comboBox = new JComboBox<Integer>();
		 comboBox.setBounds(144, 119, 72, 20);
		 for(int i=1;i<5;i++)
		 comboBox.addItem(i);
		 add(comboBox);
		 
		 yearChooser = new JYearChooser();
		 yearChooser.setBounds(234, 118, 63, 20);
		 add(yearChooser);
		 
		 comboBox_1 = new JComboBox<String>();
		 comboBox_1.setBounds(307, 119, 146, 20);
		 comboBox_1.addItem("Orders Report");
		 comboBox_1.addItem("Complain Report");
		 comboBox_1.addItem("Not Working Lot Report");
		 add(comboBox_1);
		 
		 lblLot = new JLabel("Lot:");
		 lblLot.setFont(new Font("Tahoma", Font.BOLD, 14));
		 lblLot.setBounds(468, 122, 46, 14);
		 add(lblLot);
		 
		 comboBox_2 = new JComboBox<Integer>();
		 comboBox_2.setBounds(509, 119, 82, 20);
		 for(int i=1;i<7;i++)
			 comboBox_2.addItem(i);
		 add(comboBox_2);
		
	}
	
	
	
	
	private void listners() {
		
		 comboBox.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		btnReports.setEnabled(true);
			 		
			 	}
			 });
		 
		 btnComplains.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		table.setModel((new DefaultTableModel(getSystemDataControler().getComplains(),getSystemDataControler().ComplainRowName())));
			 		arrangeTable();
			 	}
			 });
		 
		 
		 btnEmployees.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
					table.setModel((new DefaultTableModel(getSystemDataControler().getEmployees(),getSystemDataControler().EmployeeRowName())));
			 		arrangeTable();
			 	}
			 });
		 
		 btnOrders.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		table.setModel((new DefaultTableModel(getSystemDataControler().getOrders(),getSystemDataControler().OrdersRowName())));
			 		arrangeTable();
			 	}
			 });
		
		 btnReports.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		if(comboBox_1.getSelectedItem().toString().equals("Orders Report")){
			 			Vector<Vector<Object>> data=getQuarterly().OrderStatus(Integer.parseInt(comboBox.getSelectedItem().toString()), yearChooser.getYear(), Integer.parseInt(comboBox_2.getSelectedItem().toString()));
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
				 		table.setModel((new DefaultTableModel(data,s)));
				 		arrangeTable();
			 		}
			 		else if(comboBox_1.getSelectedItem().toString().equals("Complain Report")){
			 			Vector<Vector<Object>> data=getQuarterly().ComplainStatus(Integer.parseInt(comboBox.getSelectedItem().toString()), yearChooser.getYear(), Integer.parseInt(comboBox_2.getSelectedItem().toString()));
			 			Vector<String> s = new Vector<String>(6);
				 		s.add("Complain Num");
				 		s.add("Client ID");
				 		s.add("Description");
				 		s.add("Status");
				 		s.add("Opened On Date");
				 		s.add("Status");
				 		table.setModel((new DefaultTableModel(data,s)));
				 		arrangeTable();
			 		}
			 		else if(comboBox_1.getSelectedItem().toString().equals("Not Working Lot Report")){
			 			Vector<Object> data=getQuarterly().NotWorking(Integer.parseInt(comboBox.getSelectedItem().toString()), yearChooser.getYear(), Integer.parseInt(comboBox_2.getSelectedItem().toString()));
				 		Vector<String> s = new Vector<String>(2);
				 		s.add("Lot Number");
				 		s.add("Number Of Not Working Place");
				 		table.setModel((new DefaultTableModel(data,s)));
				 		arrangeTable();
			 		}

			 	}
			 });
		 
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

	private Quarterly getQuarterly() {
		if(qControler == null || !qControler.isConnected()){
			qControler = new Quarterly(host,port,vcpInfo);
		}
			
		return qControler;
	}
	
	private SystemDataControler getSystemDataControler() {
		if(sControler == null || !sControler.isConnected()){
			sControler = new SystemDataControler(host,port,vcpInfo);
		}
			
		return sControler;
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}
}
