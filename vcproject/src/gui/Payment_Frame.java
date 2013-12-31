package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Payment_Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Payment_Panel paymentPanel = null;
	private Float needToPay = null;

	public Payment_Frame() {
		super();
		initialize();
	}

	public Payment_Frame(Float needToPay) {
		super();
		this.needToPay = needToPay;
		initialize();
	}

	private void initialize() {
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(this,
					"setLookAndFeel error: " + e.getMessage(),
					"setLookAndFeel ERRORE", JOptionPane.ERROR_MESSAGE);
		}
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				getBtnReturn().doClick();
			}
		});
		this.setSize(500, 400);
		this.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);

		this.setContentPane(getPaymentPanel());

	}

	public Payment_Panel getPaymentPanel() {
		if (paymentPanel == null) {
			paymentPanel = new Payment_Panel();
			paymentPanel.setNeedToPay(needToPay);
		}
		return paymentPanel;
	}

	public JButton getBtnReturn() {
		return getPaymentPanel().getBtnReturn();
	}

	public void showWarningMsg() {
		String msg = "Not all fields are filled";
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, msg, "Warning",
				JOptionPane.WARNING_MESSAGE);
	}

	public void showSeccussesMsg() {
		String msg = "Payment Accepted";
		JFrame frame = new JFrame();
		JOptionPane
				.showMessageDialog(frame, msg, "", JOptionPane.PLAIN_MESSAGE);
	}
}
