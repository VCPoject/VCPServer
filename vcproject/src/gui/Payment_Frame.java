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
	private Payment_Panel paymentPanel;

	public Payment_Frame() {
		super();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				getBtnReturn().doClick();
			}
		});
		initialize();
	}

	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        	JOptionPane.showMessageDialog(this,"setLookAndFeel error: " + e.getMessage() , "setLookAndFeel ERRORE", JOptionPane.ERROR_MESSAGE);
        }
		this.setSize(500, 400);
		this.setResizable(false);
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		getContentPane().add(getPaymentPanel());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

	}

	public Payment_Panel getPaymentPanel() {
		if (paymentPanel == null) {
			paymentPanel = new Payment_Panel();
		}
		return paymentPanel;
	}
	
	public JButton getBtnReturn() {
		return getPaymentPanel().getBtnReturn();
	}
}
