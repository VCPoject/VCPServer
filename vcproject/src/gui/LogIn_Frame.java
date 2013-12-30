package gui;
 

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controler.LogIn_controller;
import controler.Reminder;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
public class LogIn_Frame extends JFrame{

	private static final long serialVersionUID = 1L;
	private LogIn_Panel loginpanel;
	private Reminder r;
	private String host;
	
	public LogIn_Frame(String host){
		super();
		this.host = host;
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
		this.setSize(500, 400);
		this.setResizable(false);
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
		this.setContentPane(getLogIn_Panel());
		r=new Reminder(120,this);
	}
	 
	public LogIn_Panel getLogIn_Panel() { 
		
		if(loginpanel==null)
			loginpanel=new LogIn_Panel(host);
		return loginpanel; 
	}
	
	public void closeLoginFrame() {
		this.setVisible(false);
		this.dispose();
		r.timer.cancel();
	}
	
	public LogIn_controller getLogincontroller(){
		return getLogIn_Panel().getLogincontroller();
		
	}
	
}
