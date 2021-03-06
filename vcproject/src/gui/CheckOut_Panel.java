package gui;

import java.awt.Font;
import java.awt.SystemColor;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.ButtonGroup;

import controler.CheckOutController;
import controler.FinancialCardController;
import controler.MakeOrderController;
import controler.ParkingLot_controller;
import controler.ParkingPlaceController;
import controler.Parking_Algorithem;
import controler.RegisterController;
import controler.VcpInfo;
import entity.FinancialCard;
import entity.Order;
import entity.Parking_Lot;
import entity.Parking_Places;
import entity.Subscribe;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

public class CheckOut_Panel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JFormattedTextField textFieldCarNumber;
	private JButton btnReturn;
	private JTextField textFieldMemberID;
	private JRadioButton rdbtnOrder;
	private JRadioButton rdbtnSubscribeCheckout;
	private JButton btnCheckOut;
	private ParkingPlaceController parkingPlaceController;
	private MakeOrderController makeOrderController;
	private String host;
	private int port;
	private int[] fullPositionCounter;
	/** vcpInfo is a controller that run on start-up of the 
	 * application and download all the info form the DB
	 * its contains all:
	 * order,subscribed,reservation,employees,parking lot,
	 * parking places,clients,default parking lot,cars
	 */
	private VcpInfo vcpInfo;
	private CheckOutController checkOutController;
	private Parking_Algorithem parkingAlgorithem;
	private FinancialCardController financialCardController;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblPaymentType;
	private JRadioButton rdbtnCash;
	private JRadioButton rdbtnCradit;
	private JLabel lblPressButtonpay;
	private JPanel panelCheckOutInfo;
	private JPanel panelPayment;
	private JTextField textFieldAmount;
	private JComboBox<String> comboBoxMonth;
	private JComboBox<String> comboBoxYear;
	private JButton btnPay;
	private JPanel panelCradit;
	private JFormattedTextField frmtdtxtfldCreditCard;
	private RegisterController registerController;
	private ParkingLot_controller parkingLotController;
	private boolean isTempClient = false;
	private Order tempOrder;
	/**
	 * This panel is for make check - out
	 * @param host for connecting to server side
	 * @param port for connecting to server side
	 * @param vcpInfo for get info from DB
	 */
	public CheckOut_Panel(String host,int port, VcpInfo vcpInfo) {
		super();
		this.host = host;
		this.port = port;
		this.vcpInfo = vcpInfo;
		initialize();
		listners();
	}
	/**
	 * Initialize the panel of saving parking place
	 */
	private void initialize() {
		this.setBounds(10, 11, 464, 340);
		setLayout(null);
		setBackground(SystemColor.activeCaption);
		
		JLabel lblCheckIn = new JLabel("Check - Out");
		lblCheckIn.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblCheckIn.setBounds(162, 0, 139, 29);
		add(lblCheckIn);
		
		panelCheckOutInfo = new JPanel();
		panelCheckOutInfo.setBackground(SystemColor.activeCaption);
		panelCheckOutInfo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Check-Out", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCheckOutInfo.setBounds(54, 87, 356, 78);
		add(panelCheckOutInfo);
		panelCheckOutInfo.setLayout(null);
		
		JLabel lblCarNumber = new JLabel("Car number:");
		lblCarNumber.setBounds(6, 16, 114, 22);
		panelCheckOutInfo.add(lblCarNumber);
		lblCarNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		try {
			MaskFormatter formatter = new MaskFormatter("##-###-##");
			formatter.setValidCharacters("0123456789");
			textFieldCarNumber = new JFormattedTextField(formatter);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this,"Formatter error: " + e.getMessage() , "Formatter ERRORE", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		textFieldCarNumber.setBounds(152, 16, 73, 24);
		panelCheckOutInfo.add(textFieldCarNumber);
		textFieldCarNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldCarNumber.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnCheckOut = new JButton("Check-Out");
		btnCheckOut.setBounds(261, 30, 89, 23);
		panelCheckOutInfo.add(btnCheckOut);
		
		JLabel lblMemberId = new JLabel("Member ID:");
		lblMemberId.setBounds(6, 49, 109, 22);
		panelCheckOutInfo.add(lblMemberId);
		lblMemberId.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		textFieldMemberID = new JTextField();
		textFieldMemberID.setBounds(152, 51, 73, 20);
		panelCheckOutInfo.add(textFieldMemberID);
		textFieldMemberID.setEditable(false);
		textFieldMemberID.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldMemberID.setColumns(10);
		

		
		btnReturn = new JButton("Return");
		btnReturn.setBounds(74, 294, 95, 35);
		add(btnReturn);
		
		JLabel lblCheckoutType = new JLabel("Check-Out type:");
		lblCheckoutType.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCheckoutType.setBounds(74, 54, 148, 22);
		add(lblCheckoutType);
		
		JPanel panelCheckOutType = new JPanel();
		panelCheckOutType.setBackground(SystemColor.activeCaption);
		panelCheckOutType.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Check-Out type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCheckOutType.setBounds(229, 40, 162, 43);
		add(panelCheckOutType);
		panelCheckOutType.setLayout(null);
		
		rdbtnOrder = new JRadioButton("Order");
		
		rdbtnOrder.setSelected(true);
		buttonGroup.add(rdbtnOrder);
		rdbtnOrder.setBounds(6, 16, 53, 23);
		panelCheckOutType.add(rdbtnOrder);
		rdbtnOrder.setBackground(SystemColor.activeCaption);
		
		rdbtnSubscribeCheckout = new JRadioButton("Subscribe");
		buttonGroup.add(rdbtnSubscribeCheckout);
		rdbtnSubscribeCheckout.setBounds(85, 16, 71, 23);
		panelCheckOutType.add(rdbtnSubscribeCheckout);
		rdbtnSubscribeCheckout.setBackground(SystemColor.activeCaption);
		
		panelPayment = new JPanel();
		panelPayment.setBackground(SystemColor.activeCaption);
		panelPayment.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Payment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPayment.setBounds(10, 167, 444, 116);
		panelPayment.setVisible(false);
		add(panelPayment);
		panelPayment.setLayout(null);
		
		panelCradit = new JPanel();
		panelCradit.setBackground(SystemColor.activeCaption);
		panelCradit.setBounds(10, 37, 330, 68);
		panelPayment.add(panelCradit);
		panelCradit.setLayout(null);
		
		JLabel lblCraditCard = new JLabel("Cradit card:");
		lblCraditCard.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCraditCard.setBounds(10, 8, 106, 22);
		panelCradit.add(lblCraditCard);
		
		frmtdtxtfldCreditCard = new JFormattedTextField();
		frmtdtxtfldCreditCard.setBounds(168, 8, 157, 22);
		panelCradit.add(frmtdtxtfldCreditCard);
		
		JLabel lblCardExpiration = new JLabel("Card expiration:");
		lblCardExpiration.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCardExpiration.setBounds(10, 43, 146, 22);
		panelCradit.add(lblCardExpiration);
		
		comboBoxMonth = new JComboBox<String>();
		comboBoxMonth.setBounds(168, 43, 75, 22);
		panelCradit.add(comboBoxMonth);
		
		comboBoxYear = new JComboBox<String>();
		comboBoxYear.setBounds(250, 43, 75, 22);
		panelCradit.add(comboBoxYear);
		
		lblPaymentType = new JLabel("Payment Type:");
		lblPaymentType.setBounds(10, 10, 135, 22);
		panelPayment.add(lblPaymentType);
		lblPaymentType.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		rdbtnCash = new JRadioButton("Cash");
		rdbtnCash.setBounds(208, 10, 49, 23);
		panelPayment.add(rdbtnCash);
		rdbtnCash.setBackground(SystemColor.activeCaption);
		
		lblPressButtonpay = new JLabel("Press button \"Pay Cash\"");
		lblPressButtonpay.setBounds(70, 58, 219, 22);
		panelPayment.add(lblPressButtonpay);
		lblPressButtonpay.setVisible(false);
		lblPressButtonpay.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		rdbtnCradit = new JRadioButton("Cradit");
		rdbtnCradit.setSelected(true);
		rdbtnCradit.setBounds(151, 10, 55, 23);
		panelPayment.add(rdbtnCradit);
		rdbtnCradit.setBackground(SystemColor.activeCaption);
		
		btnPay = new JButton("Pay");
		btnPay.setBounds(345, 74, 89, 31);
		panelPayment.add(btnPay);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAmount.setBounds(263, 10, 78, 22);
		panelPayment.add(lblAmount);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setEditable(false);
		textFieldAmount.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAmount.setBounds(348, 10, 86, 22);
		panelPayment.add(textFieldAmount);
		textFieldAmount.setColumns(10);
		
	}
	/**
	 * Listeners of the GUI components.
	 */
	private void listners() {
		rdbtnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldMemberID.setEditable(false);
				
			}
		});
		
		rdbtnSubscribeCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldMemberID.setEditable(true);
			}
		});
		
		rdbtnCradit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCradit.setVisible(true);
				btnPay.setText("Pay cradit");
			}
		});

		rdbtnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCradit.setVisible(false);
				btnPay.setText("Pay cash");
			}
		});
		
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (rdbtnCradit.isSelected()) {
						if (frmtdtxtfldCreditCard.getText().equals(
								"                ")) {
							throw new Exception("You didnt insert cradit card");
						}
					}

					if (textFieldAmount.getText().equals("0.0"))
						throw new Exception("You didn't insert any car");
					getRegisterController().showSeccussesMsg(
							"Payment recived, you can press now submit");
					btnCheckOut.setEnabled(true);
					btnPay.setEnabled(false);
				} catch (Exception e) {
					getRegisterController().showWarningMsg(
							"Error in Payment: " + e.getMessage());
				}

			}
		});
		
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Subscribe subscribe = null;
					Order order = null;
					String carNumStr = textFieldCarNumber.getText();
					boolean isOrderExist = false;
					//Check car number //
					if(carNumStr.equals("  -   -  "))
						throw new Exception("You didnt enter any car number");
					Integer carNum = Integer.parseInt(carNumStr.replace("-", ""));
					if(rdbtnOrder.isSelected()){
						order = getCheckOutController().getCarOrder(carNum);
						if(order == null)
							throw new Exception("There is no order for car number: " + carNumStr);
						
						for(Parking_Places pPlace: getVcpInfo().getParkingPlaces()){
							if(pPlace.getIdorder().equals(order.getIdorder())){
								isOrderExist = true;
								break;
							}
						}
						if(!isOrderExist)
							throw new Exception("Car number " + order.getCar() + " is not in parking lot contact admin or try again");
						Date date = new Date();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String[] strDate = format.format(date).split("\\s");
						order.setCheckOutDate(strDate[0]);
						order.setCheckOutTime(strDate[1]);
						if(order.getType().equals("temp") && !panelPayment.isVisible()){
							Date arrived = StringToDate(order.getArrivalDate(), order.getArrivalTime());
							Date exit = StringToDate(order.getCheckOutDate(),order.getCheckOutTime());
							btnCheckOut.setEnabled(false);
							textFieldAmount.setText(findHoursToPay(arrived,exit,order).toString());
							panelPayment.setVisible(true);
							isTempClient = true;
							setTempOrder(order);
							throw new Exception("Please make a payment");
						}
						order.setStatus("checked out");
						getCheckOutController().Algo(order);
						getMakeOrderController().UpdateOrderCheckout(order);
						fullPositionCounter=getVcpInfo().fullPositionCounter();
						fullPositionCounter[(getVcpInfo().getDefultParkingLot().getIdparkinglot())-1]--;
						if(!getMakeOrderController().getResult().get(0).equals("done"))
							throw new Exception("Error: Can't update order");
						if(order.getType().equals("one time")){
							
							FinancialCard fCard = getFinancialCardController().getFinancialCard(order.getClient());
							updateFinancialCard(fCard, order);
						}
						
					}else{
						String memberIDStr = textFieldMemberID.getText();
						if(memberIDStr == null || memberIDStr.isEmpty() || memberIDStr.length() == 0){
							throw new Exception("You didnt enter any member ID number");
						}
						Integer memberID;
						try {
							memberID = Integer.parseInt(memberIDStr);
						} catch (Exception e2) {
							throw new Exception("You didnt enter a valid member ID.");
						}
						subscribe = getCheckOutController().getSubscribeByNum(memberID,carNum);
						for(Parking_Places parkingPlace:getVcpInfo().getParkingPlaces())
							if(parkingPlace.getSubscribeNum()!=null){
								if(parkingPlace.getSubscribeNum().equals(subscribe.getSubscribeNum())){
									getCheckOutController().Algo( subscribe);//Algorithm
									isOrderExist = true;
								}
							}
						if(!isOrderExist)
							throw new Exception("Car number " + subscribe.getCarNum() + " is not in parking lot contact admin or try again");
						subscribe.setStatus("not checked in");
						fullPositionCounter=getVcpInfo().fullPositionCounter();
						fullPositionCounter[(getVcpInfo().getDefultParkingLot().getIdparkinglot())-1]--;
						if(getVcpInfo().getDefultParkingLot().getStatus().equals("full"))
							getParkingLot_controller().updateParkingLotAsAvaialble
							(getVcpInfo().getDefultParkingLot().getIdparkinglot());
						getCheckOutController().updateSubscribeAscheckedout(subscribe);
					}
					
					getCheckOutController().showSeccussesMsg("Check-Out succeed");
			} catch (Exception e2) {
				getCheckOutController().showWarningMsg(e2.getMessage());
			}
			}
		});
	}
	
	public JButton getBtnReturn() {
		return btnReturn;
	}
	
	public ParkingPlaceController getParkingPlaceController() {
		if(parkingPlaceController == null){
			parkingPlaceController = new ParkingPlaceController(host, port, getVcpInfo());
		}
		return parkingPlaceController;
	}
	
	public MakeOrderController getMakeOrderController() {
		if(makeOrderController==null)
			makeOrderController=new MakeOrderController(host, port);
		
		return makeOrderController;
		
	}

	public VcpInfo getVcpInfo() {
		return vcpInfo;
	}

	public CheckOutController getCheckOutController() {
		if(checkOutController == null)
			checkOutController = new CheckOutController(host, port, vcpInfo);
		return checkOutController;
	}
	
	public void Algo(VcpInfo vcpInfo, Order order, Parking_Lot parkingLot)throws ParseException {
		getParking_Algorithem(order, parkingLot);
	}
		
	public Parking_Algorithem getParking_Algorithem(Order order,Parking_Lot parkingLot) throws ParseException {
		if(parkingAlgorithem==null)
			parkingAlgorithem = new Parking_Algorithem(host,port,order,getVcpInfo());
		
		return parkingAlgorithem;
	}
	public FinancialCardController getFinancialCardController() {
		if(financialCardController == null){
			financialCardController = new FinancialCardController(host, port);
		}
		return financialCardController;
	}
	
	/**
	 * updateFinancialCard is updating the amount in the financial card of the one time client
	 * @param fCard contains the financial card of the client
	 * @param order contains the order info
	 * @throws Exception if failed to update DB
	 */
	private void updateFinancialCard(FinancialCard fCard, Order order) throws Exception {
		Long hours = getCheckOutController().getHoursDiff(order);
		fCard.setAmount(fCard.getAmount() + hours * getVcpInfo().getParkingPricingInfo().getOneTime()); 
		if(!getFinancialCardController().updateFinancialCard(fCard))
			throw new Exception("Cant update financial card");
		if(fCard.getAmount() > 0){
			getFinancialCardController().showSeccussesMsg("You have left more " + fCard.getAmount() + " in your financial card");
		}else{
			btnCheckOut.setEnabled(false);
			getFinancialCardController().showWarningMsg("Please make payment");
			panelPayment.setVisible(true);
		}
	}
	
	public RegisterController getRegisterController() {
		if (registerController == null || !registerController.isConnected()) {
			registerController = new RegisterController(host, port);
		}

		return registerController;
	}
	
	public ParkingLot_controller getParkingLot_controller(){
		if(parkingLotController==null)
			parkingLotController=new ParkingLot_controller(host,port,getVcpInfo());
		
		return parkingLotController;
	}
	
	public Date StringToDate(String strDate,String strTime) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date departDate = format.parse(strDate+" "+strTime);
		return departDate;
	}
	
	public Float findHoursToPay(Date arrive, Date end, Order order) {
		Float payForHour;
		if(order.getType().endsWith("temp"))
			payForHour = getVcpInfo().getParkingPricingInfo().getOccasional();
		else
			payForHour = getVcpInfo().getParkingPricingInfo().getOneTime();

		// Get msec from each, and subtract.
		long diff = end.getTime() - arrive.getTime();
		long diffHours = diff / (60 * 60 * 1000);
		if (diffHours < 1) {
			return (Float) payForHour;
		} else {
			return (long) (diffHours)*payForHour;
		}
	}
	public JButton getBtnPay() {
		return btnPay;
	}
	public JRadioButton getRdbtnCradit() {
		return rdbtnCradit;
	}
	public JFormattedTextField getFrmtdtxtfldCreditCard() {
		return frmtdtxtfldCreditCard;
	}
	public JTextField getTextFieldAmount() {
		return textFieldAmount;
	}
	public JButton getBtnCheckOut() {
		return btnCheckOut;
	}
	public boolean isTempClient() {
		return isTempClient;
	}
	public Order getTempOrder() {
		return tempOrder;
	}
	public void setTempOrder(Order tempOrder) {
		this.tempOrder = tempOrder;
	}
}
