package gui;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import controler.ManagerStats;
import controler.VcpInfo;
import com.toedter.calendar.JDateChooser;

public class ReportsGui extends JPanel {
	private JButton btnReturn;
	private JButton btnActivity;
	private static final long serialVersionUID = 1L;
	private JTable table;
	private ManagerStats managController;
	private String host;
	private int port = 5555;
	private JButton btnAbnormalWorking;
	private JScrollPane scrollPane;
	private JDateChooser start;
	private JDateChooser end;
	private VcpInfo vcpInfo;
	private JButton btnPreformence;
	



	public ReportsGui(String host, int dEFAULT_PORT,VcpInfo vcpInfo) {
		super();
		this.host=host;
		this.port=dEFAULT_PORT;
		this.vcpInfo=vcpInfo;
		initialize();
		listners();
		
	}


	private void initialize() {
		setLayout(null);
		this.setSize(785, 575);
		 
		 
		 btnReturn = new JButton("Return");
		 btnReturn.setBounds(10, 519, 93, 35);
		 add(btnReturn);
		 
		 JLabel lblReports = new JLabel("Reports");
		 lblReports.setFont(new Font("Tahoma", Font.BOLD, 22));
		 lblReports.setBounds(331, 11, 123, 28);
		 add(lblReports);
		
		 scrollPane = new JScrollPane();
		 scrollPane.setBounds(10, 236, 765, 271);
		 add(scrollPane);
		 table=new JTable(){
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int data,int columnNames){
				 return false;
			 }
				
			
		 };
		 scrollPane.setViewportView(table);
		 
		 btnAbnormalWorking = new JButton("Abnormal Working");
		 btnAbnormalWorking.setFont(new Font("Tahoma", Font.BOLD, 12));
		 btnAbnormalWorking.setBounds(10, 107, 155, 28);
		 add(btnAbnormalWorking);
		 
		 btnActivity = new JButton("Activity");
		 btnActivity.setEnabled(false);
		 btnActivity.setFont(new Font("Tahoma", Font.BOLD, 12));
		 btnActivity.setBounds(10, 68, 155, 28);
		 add(btnActivity);
		 
		 start = new JDateChooser();
		 start.setBounds(277, 68, 93, 28);
		 add(start);
		 
		 end = new JDateChooser();
		 end.getCalendarButton().addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		btnActivity.setEnabled(true);
		 	}
		 });
		 end.setBounds(507, 68, 93, 28);
		 add(end);
		 
		 JLabel lblStart = new JLabel("Start:");
		 lblStart.setFont(new Font("Tahoma", Font.BOLD, 18));
		 lblStart.setBounds(185, 76, 82, 20);
		 add(lblStart);
		 
		 JLabel lblEnd = new JLabel("End:");
		 lblEnd.setFont(new Font("Tahoma", Font.BOLD, 18));
		 lblEnd.setBounds(421, 76, 58, 20);
		 add(lblEnd);
		 
		 btnPreformence = new JButton("Preformence");
		 btnPreformence.setFont(new Font("Tahoma", Font.BOLD, 12));
		 btnPreformence.setBounds(14, 146, 151, 28);
		 add(btnPreformence);
		 getManagerStats();
	}


	private void listners() {
		
		getAbnormal().addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		 table.setModel(new DefaultTableModel(managController.AbnormalWorking(),managController.obtainFields()));
		 	}
		 });
		
		getActivity().addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		table.setModel(new DefaultTableModel(managController.ActivityReport(start.getDate(),end.getDate()),managController.obtainFields()));
		 	}
		 });		
		
		 btnPreformence.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent arg0) {
			 		Vector<String> s = new Vector<String>(2);
			 		s.add("");
			 		s.add("Value");
			 		table.setModel(new DefaultTableModel(managController.Preformence(),s));
			 	}
			 });
	}
	
	public JButton getBtnReturn() {
		return btnReturn;
	}
	
	public JButton getAbnormal(){
		return btnAbnormalWorking;
	}
	
	public JButton getActivity(){
		return btnActivity;
	}
	
	private ManagerStats getManagerStats() {
		if(managController == null || !managController.isConnected()){
			managController = new ManagerStats(host,port,vcpInfo);
		}
			
		return managController;
	}
}
