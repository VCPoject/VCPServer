package gui;

import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controler.CancelOrderController;
import entity.Pricing;
import entity.CancelOrderEntity;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class CancelOrder_Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton btnReturn;
	private JLabel lblCarNumber ;
	private JLabel lblIdNumber;
	/**
	 * pricing holds the system pricing for parking lot
	 */
	private Pricing pricing;
	private JButton btnSubmit;
	private JButton btnSubmit_1;
	private JTextField textFieldIdNumber;
	private JFormattedTextField textFieldCarNumber;
	private JTextField textField;
	private String host;
	private int port = 5555;
	private CancelOrderController cController;
	private JComboBox<String> comboBox;
	private CancelOrderEntity coEntity;


	/**
	 * @param host for make connection with server side
	 * @param port for make connection with server side
	 * @param pricing holds the system pricing for parking lot
	 */
	public CancelOrder_Panel(String host,int port,Pricing pricing) {
		super();
		this.pricing = pricing;
		initialize();
		listners();
	}
	/**
	 * Initialize the panel of saving parking place
	 */
	private void initialize() {
		setLayout(null);
		this.setSize(785, 575);
		 setBackground(SystemColor.activeCaption);
		 
		 JLabel lblCancelOrder = new JLabel("Cancel Order");
		 lblCancelOrder.setFont(new Font("Tahoma", Font.BOLD, 24));
		 lblCancelOrder.setBounds(316, 11, 153, 29);
		 add(lblCancelOrder);
		 
		 btnReturn = new JButton("Return");
		 btnReturn.setBounds(10, 519, 93, 35);
		 add(btnReturn);
		 
		 lblIdNumber = new JLabel("ID number:");
			lblIdNumber.setBounds(234, 131, 197, 22);
			lblIdNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
			add(lblIdNumber);
			
			textFieldIdNumber = new JTextField();
			textFieldIdNumber.setBounds(441, 131, 137, 24);
			add(textFieldIdNumber);
			textFieldIdNumber.setColumns(10);
			
			lblCarNumber = new JLabel("Car number:");
			lblCarNumber.setBounds(234, 164, 197, 22);
			add(lblCarNumber);
			lblCarNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
			
			try {
				MaskFormatter formatter = new MaskFormatter("##-###-##");
				formatter.setValidCharacters("0123456789");
				textFieldCarNumber = new JFormattedTextField(formatter);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(this,"Formatter error: " + e.getMessage() , "Formatter ERRORE", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

			textFieldCarNumber.setBounds(441, 164, 137, 24);
			add(textFieldCarNumber);
			textFieldCarNumber.setColumns(10);
			
			btnSubmit = new JButton("Submit");
			btnSubmit.setBounds(353, 235, 103, 38);
			add(btnSubmit);
			
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(441, 470, 137, 29);
			add(textField);
			textField.setColumns(10);
			
			JLabel lblAmountOfCredit = new JLabel("Amount of Credit");
			lblAmountOfCredit.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblAmountOfCredit.setBounds(202, 470, 190, 29);
			add(lblAmountOfCredit);
			
			JLabel lblThisAmountOf = new JLabel("The cradit will be add to the user money card");
			lblThisAmountOf.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblThisAmountOf.setBounds(234, 510, 292, 15);
			add(lblThisAmountOf);
			
			comboBox = new JComboBox<String>();
			comboBox.setBounds(389, 306, 292, 20);
			add(comboBox);
			
			btnSubmit_1 = new JButton("Submit");
			btnSubmit_1.setEnabled(false);
			btnSubmit_1.setBounds(441, 369, 89, 23);
			add(btnSubmit_1);
			
			JLabel lblPleaseChooseOrder = new JLabel("Please Choose Order :");
			lblPleaseChooseOrder.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblPleaseChooseOrder.setBounds(143, 304, 190, 22);
			add(lblPleaseChooseOrder);
		 
	}
	/**
	 * Listeners of the GUI components.
	 */
	private void listners(){
		getBtnSubmit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();
				coEntity = new CancelOrderEntity();
				coEntity.setUserid(Integer.parseInt(textFieldIdNumber.getText()));
				coEntity.setCarid(Integer.parseInt(textFieldCarNumber.getText().replaceAll("-", "")));
				if(getCancelOrderController().checkID(coEntity.getUserid(),coEntity.getCarid()))
				{
					ArrayList<String> orders = cController.getOrders(coEntity.getUserid());
					comboBox.setEnabled(true);
					for(int i=0;i<orders.size();i++)
					comboBox.addItem(orders.get(i));
				}
				getBtnSubmit_1().setEnabled(true);
				}
		});
		
		getBtnSubmit_1().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s =cController.calculatePrice((String)comboBox.getSelectedItem());
						if(s!="done"){
							textField.setText(s);
							cController.showSeccussesMsg("Your order has deleted and your money has been refound");
						}
						else
							cController.showWarningMsg("There was an error in you input");
						comboBox.removeAllItems();
				}
							
		});
	}
	
	public JButton getBtnReturn() {
		return btnReturn;
	}
	public JButton getBtnSubmit() {
		return btnSubmit;
	}
	public JButton getBtnSubmit_1() {
		return btnSubmit_1;
	}
	
	private CancelOrderController getCancelOrderController() {
		if(cController == null || !cController.isConnected()){
			cController = new CancelOrderController(host,port,pricing,coEntity);
		}
			
		return cController;
	}
}
