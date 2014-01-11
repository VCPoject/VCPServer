package gui;

import java.awt.SystemColor;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import controler.CheckInController;
import controler.MakeOrderController;
import controler.ParkingPlaceController;
import controler.VcpInfo;
import entity.Order;
import entity.Parking_Lot;
import entity.Parking_Places;
import entity.Subscribe;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckIn_Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFormattedTextField textFieldCarNumber;
	private JButton btnReturn;
	private JTextField textFieldMemberID;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnOrder;
	private JRadioButton rdbtnSubscribeCheckin;
	private JButton btnCheckin;
	private String host;
	private int port;
	/** vcpInfo is a controller that run on start-up of the 
	 * application and download all the info form the DB
	 * its contains all:
	 * order,subscribed,reservation,employees,parking lot,
	 * parking places,clients,default parking lot,cars
	 */
	private VcpInfo vcpInfo;
	private CheckInController checkInController;
	private ParkingPlaceController parkingPlaceController;
	private MakeOrderController makeOrderController;

	/**
	 * This panel is for make check in to parking lot.
	 * @param host for make connection with server side
	 * @param port for make connection with server side
	 * @param vcpInfo for get info from DB
	 */
	public CheckIn_Panel(String host,int port, VcpInfo vcpInfo) {
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

		JLabel lblCheckIn = new JLabel("Check - In");
		lblCheckIn.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblCheckIn.setBounds(170, 0, 123, 29);
		add(lblCheckIn);

		JLabel lblCarNumber = new JLabel("Car number:");
		lblCarNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCarNumber.setBounds(126, 144, 114, 22);
		add(lblCarNumber);

		try {
			MaskFormatter formatter = new MaskFormatter("##-###-##");
			formatter.setValidCharacters("0123456789");
			textFieldCarNumber = new JFormattedTextField(formatter);
			textFieldCarNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
			textFieldCarNumber.setHorizontalAlignment(SwingConstants.CENTER);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this,
					"Formatter error: " + e.getMessage(), "Formatter ERRORE",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		textFieldCarNumber.setBounds(272, 144, 73, 24);
		add(textFieldCarNumber);

		btnCheckin = new JButton("Check-In");
		btnCheckin.setBounds(187, 231, 95, 35);
		add(btnCheckin);

		btnReturn = new JButton("Return");
		btnReturn.setBounds(10, 294, 95, 35);
		add(btnReturn);

		JLabel lblMemberId = new JLabel("Member ID:");
		lblMemberId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMemberId.setBounds(126, 177, 109, 22);
		add(lblMemberId);

		textFieldMemberID = new JTextField();
		textFieldMemberID.setEditable(false);
		textFieldMemberID.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldMemberID.setBounds(272, 179, 73, 24);
		add(textFieldMemberID);
		textFieldMemberID.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Check-in type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(213, 46, 95, 72);
		add(panel);
		panel.setLayout(null);
		
		rdbtnOrder = new JRadioButton("Order");
		rdbtnOrder.setBounds(6, 16, 53, 23);
		panel.add(rdbtnOrder);
		rdbtnOrder.setBackground(SystemColor.activeCaption);
		rdbtnOrder.setSelected(true);
		buttonGroup.add(rdbtnOrder);
		
		rdbtnSubscribeCheckin = new JRadioButton("Subscribe");
		rdbtnSubscribeCheckin.setBounds(6, 42, 71, 23);
		panel.add(rdbtnSubscribeCheckin);
		rdbtnSubscribeCheckin.setBackground(SystemColor.activeCaption);
		buttonGroup.add(rdbtnSubscribeCheckin);
		
		JLabel lblCheckinType = new JLabel("Check-in type:");
		lblCheckinType.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCheckinType.setBounds(81, 74, 132, 22);
		add(lblCheckinType);

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
		
		rdbtnSubscribeCheckin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldMemberID.setEditable(true);
			}
		});
		
		btnCheckin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Subscribe subscribe = null;
					Order order = null;
					Parking_Places pPlace = null;
					Integer[] parkingInfo = null;
					String departDateStr = "";
					String carNumStr = textFieldCarNumber.getText();
					//Check car number //
					if(carNumStr.equals("  -   -  "))
						throw new Exception("You didnt enter any car number");
					Integer carNum = Integer.parseInt(carNumStr.replace("-", ""));
					
					if(rdbtnOrder.isSelected()){
						order = getCheckInController().getCarOrder(carNum);
						if(order == null)
							throw new Exception("There is no order for car number: " + carNumStr);
						for(Parking_Lot parkinglot:getVcpInfo().getParkingLot())
							if(order.getIdparking()==parkinglot.getIdparkinglot())
								parkingInfo = getCheckInController().Algo(getVcpInfo(),order,parkinglot);
						
						departDateStr = order.getDepartureDate() + " " + order.getDepartureTime();
						pPlace = getParkingPlaceController().getParkingPlaceByCoordinate(parkingInfo);
						
						
						pPlace.setIdorder(order.getIdorder());
						pPlace.setSubscribeNum(null);
						
						Date date = new Date();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String[] strDate = format.format(date).split("\\s");
						order.setCheckInDate(strDate[0]);
						order.setCheckInTime(strDate[1]);
						order.setStatus("checked in");
						getMakeOrderController().UpdateOrder(order);
						if(!getMakeOrderController().getResult().equals("done"))
							throw new Exception("Error: Can't update order");
					}/*else{
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
						subscribe = getCheckInController().getSubscribeByNum(memberID,carNum);
						Date date = new Date();
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						departDateStr = dateFormat.format(date) + " " + subscribe.getDepartureTime();
						//for(Parking_Lot parkinglot:getVcpInfo().getParkingLot())
						//	if(pPlace.getIdparkinglot()==parkinglot.getIdparkinglot())
								//parkingInfo = getCheckInController().Algo(getVcpInfo(),subscribe,parkinglot);//Algorithm
						pPlace = getParkingPlaceController().getParkingPlaceByCoordinate(parkingInfo);
						pPlace.setSubscribeNum(subscribe.getSubscribeNum());
						pPlace.setIdorder(null);
					}*/
					
					pPlace.setStatus("occupy");
					getParkingPlaceController().updateParkingPlace(pPlace);
					if(!getMakeOrderController().getResult().equals("done"))
						throw new Exception("Error: Can't update parking place");
					getCheckInController().showSeccussesMsg("Check-in succeed");
				} catch (Exception e2) {
					getCheckInController().showWarningMsg(e2.getMessage());
				}

			}
		});
		
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	public VcpInfo getVcpInfo() {
		return vcpInfo;
	}

	public CheckInController getCheckInController() {
		if(checkInController == null){
			checkInController = new CheckInController(host, port, getVcpInfo());
		}
		return checkInController;
	}
	
	/**
	 * StringToDate convert String with date to Date instance
	 * @param strDate is String with a date
	 * @return the String in Date format
	 * @throws ParseException
	 */
	private Date StringToDate(String strDate) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date departDate = format.parse(strDate);
		return departDate;
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

}
