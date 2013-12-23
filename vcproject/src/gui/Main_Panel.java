package gui;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main_Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnExit;
	private JButton btnEmploeyLogin;
	private JButton btnMakeOrder;
	private JButton btnRegister;
	private JButton btnCheckIn;
	private JButton btnCheckOut;
	private JButton cancelOrder;
	private JButton btnComplain;
	private JButton btnComplainFu;
	
	public Main_Panel() {
		super();
		setLayout(null);
		initialize();
	}

	private void initialize() {
		this.setSize(785, 575);
		 setBackground(SystemColor.activeCaption);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(10, 519, 93, 35);
		add(btnExit);
		
		JLabel lblVcp = new JLabel("VCP");
		lblVcp.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblVcp.setBounds(368, 27, 48, 23);
		add(lblVcp);
		
		btnEmploeyLogin = new JButton("Emploey login");
		btnEmploeyLogin.setBounds(648, 519, 127, 35);
		add(btnEmploeyLogin);
		
		btnMakeOrder = new JButton("Make order");
		btnMakeOrder.setBounds(499, 121, 127, 35);
		add(btnMakeOrder);
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(499, 213, 127, 35);
		add(btnRegister);
		
		btnCheckIn = new JButton("Check in");
		btnCheckIn.setBounds(217, 121, 127, 35);
		add(btnCheckIn);
		
		btnCheckOut = new JButton("Check out");
		btnCheckOut.setBounds(217, 167, 127, 35);
		add(btnCheckOut);
		
		cancelOrder = new JButton("Cancel order");
		cancelOrder.setBounds(499, 167, 127, 35);
		add(cancelOrder);
		
		btnComplain = new JButton("Complain");

		btnComplain.setBounds(217, 213, 127, 35);
		add(btnComplain);
		
		btnComplainFu = new JButton("Complain Follow Up");
		btnComplainFu.setBounds(217, 259, 127, 35);
		add(btnComplainFu);
	}

	public JButton getBtnExit() {
		return btnExit;
	}

	public JButton getBtnEmploeyLogin() {
		return btnEmploeyLogin;
	}

	public JButton getBtnMakeOrder() {
		return btnMakeOrder;
	}

	public JButton getBtnRegister() {
		return btnRegister;
	}

	public JButton getBtnCheckIn() {
		return btnCheckIn;
	}

	public JButton getBtnCheckOut() {
		return btnCheckOut;
	}
	public JButton getBtnCancelOrder() {
		return cancelOrder;
	}
	public JButton getBtnComplain() {
		return btnComplain;
	}
	
	public JButton getBtnComplainFu() {
		return btnComplainFu;
	}
}
