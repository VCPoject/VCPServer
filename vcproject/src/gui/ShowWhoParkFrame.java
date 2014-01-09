package gui;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;

public class ShowWhoParkFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ShowWhoParkPanel showWhoParkPanel;
	private Object entity;

	public ShowWhoParkFrame(Object entity) {
		super();
		this.entity = entity;
		initialize();
		listners();
	}

	private void listners() {
		
		
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
		this.setSize(600, 400);
		this.setResizable(false);
		this.setVisible(true);
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
		this.setContentPane(getShowWhoParkPanel());
		
	}

	public ShowWhoParkPanel getShowWhoParkPanel() {
		if(showWhoParkPanel == null){
			showWhoParkPanel = new ShowWhoParkPanel(entity);
		}
		return showWhoParkPanel;
	}
	
	private void closeMainFrame() {
		this.setVisible(false);
		this.dispose();
	}
	
	public JButton getBtnClose(){
		return getShowWhoParkPanel().getBtnClose();
	}

}
