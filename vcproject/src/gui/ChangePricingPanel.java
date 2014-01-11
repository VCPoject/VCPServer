package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import controler.PricingController;
import entity.Employee;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePricingPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * conectedEmployee is the connected employee that use the panel
	 */
	private Employee conectedEmployee;
	private PricingController pricingController;
	private JTextField textFieldOccasional;
	private JTextField textFieldOneTime;
	private JButton btnConfirm;
	private JButton btnReturn;
	private String host;
	private int port;

	/**
	 * This panel is for create request to change the parking lot pricing
	 * @param host for make connection with server side
	 * @param port for make connection with server side
	 */
	public ChangePricingPanel(String host,int port) {
		super();
		this.host = host;
		this.port = port;
		initialize();
		listners();
	}
	/**
	 * Initialize the panel of saving parking place
	 */
	private void initialize() {
		setLayout(null);
		this.setSize(785, 575);
		
		JLabel lblChangePricing = new JLabel("Change Pricing");
		lblChangePricing.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblChangePricing.setBounds(303, 0, 179, 29);
		add(lblChangePricing);
		
		btnReturn = new JButton("Return");
		btnReturn.setBounds(10, 517, 89, 34);
		add(btnReturn);
		
		JPanel panelChange = new JPanel();
		panelChange.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Change pricing", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelChange.setBounds(269, 175, 244, 144);
		add(panelChange);
		panelChange.setLayout(null);
		
		JLabel lblSetOccasional = new JLabel("Set occasional:");
		lblSetOccasional.setBounds(6, 16, 136, 22);
		panelChange.add(lblSetOccasional);
		lblSetOccasional.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblSetOnetime = new JLabel("Set one-time:");
		lblSetOnetime.setBounds(6, 49, 123, 22);
		panelChange.add(lblSetOnetime);
		lblSetOnetime.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		textFieldOccasional = new JTextField();
		textFieldOccasional.setBounds(152, 16, 86, 20);
		panelChange.add(textFieldOccasional);
		textFieldOccasional.setColumns(10);
		
		textFieldOneTime = new JTextField();
		textFieldOneTime.setBounds(152, 49, 86, 20);
		panelChange.add(textFieldOneTime);
		textFieldOneTime.setColumns(10);
		
		btnConfirm = new JButton("Confirm");
		
		btnConfirm.setBounds(60, 82, 123, 45);
		panelChange.add(btnConfirm);
	}
	/**
	 * Listeners of the GUI components.
	 */
	private void listners() {
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Float occasional;
					Float oneTime;
					try {
						occasional = Float.parseFloat(textFieldOccasional.getText());
						oneTime = Float.parseFloat(textFieldOneTime.getText());
					} catch (Exception e2) {
						throw new Exception("You insert invalid number");
					}
					
					getPricingController().changePrice(occasional, oneTime, getConectedEmployee().getIdEmployee());
					if(!getPricingController().getResult().get(0).equals("done"))
						throw new Exception("Error: Cant insert the request");
					
					showSeccussesMsg("Request send to network manager.");
					getBtnReturn().doClick();
					
					
				} catch (Exception e2) {
					showWarningMsg(e2.getMessage());
				}
			}
		});
		
	}

	public JButton getBtnConfirm() {
		return btnConfirm;
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}
	
	public void showWarningMsg(String msg) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, msg, "Warning",JOptionPane.WARNING_MESSAGE);
	}

	public void showSeccussesMsg(String msg) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, msg, "", JOptionPane.PLAIN_MESSAGE);
	}
	
	public Employee getConectedEmployee() {
		return conectedEmployee;
	}

	public void setConectedEmployee(Employee conectedEmployee) {
		this.conectedEmployee = conectedEmployee;
	}

	public PricingController getPricingController() {
		if(pricingController == null){
			pricingController = new PricingController(host,port);
		}
		return pricingController;
	}

}
