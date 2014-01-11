package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import controler.PricingController;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entity.Pricing;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PricingRequestPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String host;
	private int port;
	private JTable table;
	private JScrollPane scrollPane;
	private PricingController pricingController;
	/**
	 * result contains the result from the DB.
	 */
	private ArrayList<Object> result;
	/**
	 * cols contains the columns names of the table.
	 */
	private Vector<String> cols;
	private JLabel lblSelectRequestsFrom;
	private JButton btnReturn;
	private JButton btnApproval;
	private JButton btnDisapprove;
	/**
	 * pricing contains the pricing the parking lot.
	 */
	private Pricing pricing;
	/**
	 * This panel is for view the pricing request from employees.
	 * @param host for make connection with server side
	 * @param port for make connection with server side
	 * @param pricing contains the pricing the parking lot.
	 */
	public PricingRequestPanel(String host,int port, Pricing pricing) {
		super();
		this.host = host;
		this.port = port;
		this.pricing = pricing;
		initialize();
		listners();
	}
	/**
	 * Initialize the panel of PricingRequestPanel
	 */
	private void initialize() {
		setLayout(null);
		this.setSize(785, 575);
		
		JLabel lblPricingRequests = new JLabel("Pricing Requests");
		lblPricingRequests.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblPricingRequests.setBounds(293, 0, 199, 29);
		add(lblPricingRequests);
		String[] columnNames ={"Change number", "Employee ID", "Occasional", "One-Time", "Date", "Status"}; 
		int columnLength = columnNames.length;
		cols = new Vector<String>(columnLength);
		for (int i = 0; i < columnLength; i++)
			cols.add(columnNames[i]);
		result = getPricingController().getPricingRequests();
		
		Vector<Object> row = new Vector<Object>(columnLength);
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for (Object value : result) {
			if (!value.equals("no value")){
				if(row.size() == 4){
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
					String dateAndTime = dateFormat.format(value);
					row.add(dateAndTime);
				}else
					row.add(value);
			}
			else
				row.add("0");
			if(row.size() == 6){
				data.add(row);
				row = new Vector<Object>(columnNames.length);
			}
		}
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 92, 720, 293);
		add(scrollPane);
		
		table = new JTable(data, cols) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int data,	int columnNames) {
				return false;
			}
		};
		table.setSurrendersFocusOnKeystroke(true);
		scrollPane.setViewportView(table);
		
		
		
		lblSelectRequestsFrom = new JLabel("Select requests from the table: ");
		lblSelectRequestsFrom.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSelectRequestsFrom.setBounds(10, 53, 283, 22);
		add(lblSelectRequestsFrom);
		
		btnReturn = new JButton("Return");
		btnReturn.setBounds(10, 523, 100, 41);
		add(btnReturn);
		
		btnApproval = new JButton("Approval");
		btnApproval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnApproval.setEnabled(false);
		btnApproval.setBounds(664, 396, 89, 23);
		add(btnApproval);
		
		btnDisapprove = new JButton("Disapprove");
		btnDisapprove.setEnabled(false);
		btnDisapprove.setBounds(565, 396, 89, 23);
		add(btnDisapprove);
		
		
		
	}
	/**
	 * Listeners of the GUI components.
	 */
	private void listners() {
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		    	btnApproval.setEnabled(true);
		    	btnDisapprove.setEnabled(true);
		    }
		});
		
		btnApproval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Float occasional = Float.parseFloat(table.getValueAt(table.getSelectedRow(), 2).toString());
				Float oneTime = Float.parseFloat(table.getValueAt(table.getSelectedRow(), 3).toString());
				if(getPricingController().updatePricing(occasional, oneTime, pricing)){
					int idPricingRequest = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					if(getPricingController().updateChangePriceStatus("Approved", idPricingRequest))
					{
						table.setValueAt("Approved",table.getSelectedRow(), 5);
						getPricingController().showSeccussesMsg("Update approved, pricing changed");
					}
					else{
						getPricingController().showWarningMsg("Update Failled, DB problem");
					}
				}
			}
		});
		
		btnDisapprove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idPricingRequest = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				if(getPricingController().updateChangePriceStatus("Disapproved", idPricingRequest)){
					table.setValueAt("Disapproved",table.getSelectedRow(), 5);
					getPricingController().showSeccussesMsg("Update request to disapproved");
				}
				else{
					getPricingController().showSeccussesMsg("Update Failled, DB problem");
				}
			}
		});
		
	}
	
	public JButton getBtnReturn() {
		return btnReturn;
	}

	public PricingController getPricingController() {
		if(pricingController == null){
			pricingController = new PricingController(host,port);
		}
		return pricingController;
	}
}
