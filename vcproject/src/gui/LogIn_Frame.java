package gui;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class LogIn_Frame extends JFrame{

	private static final long serialVersionUID = 1L;
	private LogIn_Panel loginpanel;
	
	public LogIn_Frame(){
		super();
		initialize();
	}
	
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(this,
					"setLookAndFeel error: " + e.getMessage(),
					"setLookAndFeel ERRORE", JOptionPane.ERROR_MESSAGE);
		}
		this.setSize(800, 600);
		this.setResizable(false);
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
		this.setContentPane(getLogIn_Panel());

	}
	
	public LogIn_Panel getLogIn_Panel() {
		
		if(loginpanel==null)
			loginpanel=new LogIn_Panel();
		
		return loginpanel; 
	}
}
