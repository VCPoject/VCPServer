package gui;

import java.awt.SystemColor;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.ParseException;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import entity.Order;
import entity.Subscribe;

public class ShowWhoParkPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * entity contain instance of Order or Subscribe
	 */
	private Object entity;
	private JButton btnClose;
	private JTextField textFieldOrderNum;
	private JFormattedTextField formattedTextFieldCarNum;
	private JTextField textFieldClientID;
	private JTextField textFieldArrivalDate;
	private JTextField textFieldtextFieldArrivalTime;
	private JTextField textFieldDepartureDate;
	private JTextField textFieldDepartureTime;
	private JTextField textFieldCheckinDate;
	private JTextField textFieldCheckinTime;
	private JPanel panelOrder;
	private JLabel lblParkingPlace;
	private JLabel lblOrderNumber;
	private JLabel lblCarNumber;
	private JLabel lblClientId;
	private JLabel lblArrivalDate;
	private JLabel lblArrivalTime;
	private JLabel lblDepartureDate;
	private JLabel lblDepartureTime;
	private JLabel lblCheckinDate;
	private JLabel lblCheckinTime;
	private JTextField textFieldMemberNum;
	private JTextField textFieldClientIdSub;
	private JFormattedTextField formattedTextFieldCarNumSub;
	private JLabel lblDepartureTime_1;
	private JTextField textFieldDepartureTimeSub;
	private JPanel panelMember;
	private JLabel lblMemberNumber;
	private JLabel lblClientIdSub;
	private JLabel lblCarNumberSub;

	/**
	 * On this panel display info about Order or Subscribe
	 * @param entity contains entity of Order or Subscribe
	 */
	public ShowWhoParkPanel(Object entity) {
		super();
		this.entity = entity;
		initialize();
	}
	
	
	/**
	 * Initialize the GUI of the panel
	 */
	private void initialize() {
		this.setBounds(10, 11, 590, 341);
		setLayout(null);
		setBackground(SystemColor.activeCaption);

		lblParkingPlace = new JLabel("Parking Place");
		lblParkingPlace.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblParkingPlace.setBounds(214, 0, 162, 29);
		add(lblParkingPlace);

		btnClose = new JButton("Close");
		btnClose.setBounds(10, 298, 71, 31);
		add(btnClose);
		if (entity instanceof Order)
			setPanelForOrder((Order) entity);
		else
			setPanelForMember((Subscribe) entity);

	}
	
	/**
	 * Initialize the panel with order info
	 * @param entity of order to get all the order info 
	 */
	private void setPanelForOrder(Order entity) {
		lblOrderNumber = new JLabel("Order number:");
		lblOrderNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOrderNumber.setBounds(161, 76, 135, 22);
		add(lblOrderNumber);

		textFieldOrderNum = new JTextField();
		textFieldOrderNum.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOrderNum.setEditable(false);
		textFieldOrderNum.setBounds(311, 76, 86, 22);
		textFieldOrderNum.setText(entity.getIdorder().toString());
		add(textFieldOrderNum);
		textFieldOrderNum.setColumns(10);

		panelOrder = new JPanel();
		panelOrder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Order info",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOrder.setBounds(35, 113, 526, 136);
		add(panelOrder);
		panelOrder.setLayout(null);

		lblCarNumber = new JLabel("Car number:");
		lblCarNumber.setBounds(6, 16, 114, 22);
		panelOrder.add(lblCarNumber);
		lblCarNumber.setFont(new Font("Tahoma", Font.BOLD, 18));

		try {
			MaskFormatter formatter = new MaskFormatter("##-###-##");
			formatter.setValidCharacters("0123456789");
			formattedTextFieldCarNum = new JFormattedTextField(formatter);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this,
					"Formatter error: " + e.getMessage(), "Formatter ERRORE",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		formattedTextFieldCarNum.setBounds(127, 16, 86, 22);
		formattedTextFieldCarNum.setText(entity.getCar().toString());
		panelOrder.add(formattedTextFieldCarNum);
		formattedTextFieldCarNum.setEditable(false);
		formattedTextFieldCarNum.setHorizontalAlignment(SwingConstants.CENTER);

		lblClientId = new JLabel("Client ID:");
		lblClientId.setBounds(6, 46, 87, 22);
		panelOrder.add(lblClientId);
		lblClientId.setFont(new Font("Tahoma", Font.BOLD, 18));

		textFieldClientID = new JTextField();
		textFieldClientID.setBounds(127, 47, 86, 22);
		panelOrder.add(textFieldClientID);
		textFieldClientID.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldClientID.setEditable(false);
		textFieldClientID.setColumns(10);
		textFieldClientID.setText(entity.getClient().toString());

		lblArrivalDate = new JLabel("Arrival date:");
		lblArrivalDate.setBounds(6, 76, 111, 22);
		panelOrder.add(lblArrivalDate);
		lblArrivalDate.setFont(new Font("Tahoma", Font.BOLD, 18));

		textFieldArrivalDate = new JTextField();
		textFieldArrivalDate.setBounds(127, 77, 86, 22);
		panelOrder.add(textFieldArrivalDate);
		textFieldArrivalDate.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldArrivalDate.setEditable(false);
		textFieldArrivalDate.setColumns(10);
		textFieldArrivalDate.setText(entity.getArrivalDate());

		lblArrivalTime = new JLabel("Arrival time:");
		lblArrivalTime.setBounds(6, 106, 111, 22);
		panelOrder.add(lblArrivalTime);
		lblArrivalTime.setFont(new Font("Tahoma", Font.BOLD, 18));

		textFieldtextFieldArrivalTime = new JTextField();
		textFieldtextFieldArrivalTime.setBounds(127, 107, 86, 22);
		panelOrder.add(textFieldtextFieldArrivalTime);
		textFieldtextFieldArrivalTime
				.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldtextFieldArrivalTime.setEditable(false);
		textFieldtextFieldArrivalTime.setColumns(10);
		textFieldtextFieldArrivalTime.setText(entity.getArrivalTime());

		lblDepartureDate = new JLabel("Departure date:");
		lblDepartureDate.setBounds(284, 76, 145, 22);
		panelOrder.add(lblDepartureDate);
		lblDepartureDate.setFont(new Font("Tahoma", Font.BOLD, 18));

		textFieldDepartureDate = new JTextField();
		textFieldDepartureDate.setBounds(434, 76, 86, 22);
		panelOrder.add(textFieldDepartureDate);
		textFieldDepartureDate.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDepartureDate.setEditable(false);
		textFieldDepartureDate.setColumns(10);
		textFieldDepartureDate.setText(entity.getDepartureDate());

		lblDepartureTime = new JLabel("Departure time:");
		lblDepartureTime.setBounds(284, 106, 145, 22);
		panelOrder.add(lblDepartureTime);
		lblDepartureTime.setFont(new Font("Tahoma", Font.BOLD, 18));

		textFieldDepartureTime = new JTextField();
		textFieldDepartureTime.setBounds(434, 106, 86, 22);
		panelOrder.add(textFieldDepartureTime);
		textFieldDepartureTime.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDepartureTime.setEditable(false);
		textFieldDepartureTime.setColumns(10);
		textFieldDepartureTime.setText(entity.getDepartureTime());

		lblCheckinDate = new JLabel("Check-In date:");
		lblCheckinDate.setBounds(284, 16, 137, 22);
		panelOrder.add(lblCheckinDate);
		lblCheckinDate.setFont(new Font("Tahoma", Font.BOLD, 18));

		textFieldCheckinDate = new JTextField();
		textFieldCheckinDate.setBounds(434, 16, 86, 22);
		panelOrder.add(textFieldCheckinDate);
		textFieldCheckinDate.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCheckinDate.setEditable(false);
		textFieldCheckinDate.setColumns(10);
		textFieldCheckinDate.setText(entity.getCheckInDate());

		lblCheckinTime = new JLabel("Check-In time:");
		lblCheckinTime.setBounds(284, 46, 137, 22);
		panelOrder.add(lblCheckinTime);
		lblCheckinTime.setFont(new Font("Tahoma", Font.BOLD, 18));

		textFieldCheckinTime = new JTextField();
		textFieldCheckinTime.setBounds(434, 46, 86, 22);
		panelOrder.add(textFieldCheckinTime);
		textFieldCheckinTime.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCheckinTime.setEditable(false);
		textFieldCheckinTime.setColumns(10);
		textFieldCheckinTime.setText(entity.getCheckInTime());

	}

	
	/**
	 * Initialize the panel with Subscribe info
	 * @param entity of Subscribe to get all the subscribe info 
	 */
	private void setPanelForMember(Subscribe entity) {
		lblMemberNumber = new JLabel("Member number:");
		lblMemberNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMemberNumber.setBounds(173, 76, 157, 22);
		add(lblMemberNumber);
		if (entity != null) {
			textFieldMemberNum = new JTextField();
			textFieldMemberNum.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldMemberNum.setEditable(false);
			textFieldMemberNum.setBounds(343, 76, 86, 22);
			textFieldMemberNum.setText(entity.getSubscribeNum().toString());
			add(textFieldMemberNum);
			textFieldMemberNum.setColumns(10);

			panelMember = new JPanel();
			panelMember.setBorder(new TitledBorder(UIManager
					.getBorder("TitledBorder.border"), "Member Info",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelMember.setBounds(37, 119, 512, 80);
			add(panelMember);
			panelMember.setLayout(null);

			lblClientIdSub = new JLabel("Client ID:");
			lblClientIdSub.setBounds(271, 16, 87, 22);
			panelMember.add(lblClientIdSub);
			lblClientIdSub.setFont(new Font("Tahoma", Font.BOLD, 18));

			textFieldClientIdSub = new JTextField();
			textFieldClientIdSub.setBounds(420, 16, 86, 22);
			panelMember.add(textFieldClientIdSub);
			textFieldClientIdSub.setEditable(false);
			textFieldClientIdSub.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldClientIdSub.setColumns(10);
			textFieldClientIdSub.setText(entity.getIdClient().toString());

			lblCarNumberSub = new JLabel("Car number:");
			lblCarNumberSub.setBounds(6, 16, 114, 22);
			panelMember.add(lblCarNumberSub);
			lblCarNumberSub.setFont(new Font("Tahoma", Font.BOLD, 18));

			try {
				MaskFormatter formatter = new MaskFormatter("##-###-##");
				formatter.setValidCharacters("0123456789");
				formattedTextFieldCarNumSub = new JFormattedTextField(formatter);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(this,
						"Formatter error: " + e.getMessage(),
						"Formatter ERRORE", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

			formattedTextFieldCarNumSub.setBounds(153, 16, 86, 22);
			formattedTextFieldCarNumSub.setText(entity.getCarNum().toString());
			panelMember.add(formattedTextFieldCarNumSub);
			formattedTextFieldCarNumSub.setEditable(false);
			formattedTextFieldCarNumSub
					.setHorizontalAlignment(SwingConstants.CENTER);

			lblDepartureTime_1 = new JLabel("Departure time:");
			lblDepartureTime_1.setBounds(100, 50, 145, 22);
			panelMember.add(lblDepartureTime_1);
			lblDepartureTime_1.setFont(new Font("Tahoma", Font.BOLD, 18));

			textFieldDepartureTimeSub = new JTextField();
			textFieldDepartureTimeSub.setBounds(255, 50, 86, 22);
			textFieldDepartureTimeSub.setText(entity.getDepartureTime());
			panelMember.add(textFieldDepartureTimeSub);
			textFieldDepartureTimeSub
					.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldDepartureTimeSub.setEditable(false);
			textFieldDepartureTimeSub.setColumns(10);
		}
	}

	public JButton getBtnClose() {
		return btnClose;
	}
}
