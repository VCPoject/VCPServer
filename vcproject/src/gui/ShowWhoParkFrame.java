package gui;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ShowWhoParkFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The panel to display in the frame
	 */
	private ShowWhoParkPanel showWhoParkPanel;
	/**
	 * entity contain instance of Order or Subscribe
	 */
	private Object entity;

	/**
	 * This frame display info about Order or Subscribe
	 * @param entity contains entity of Order or Subscribe
	 */
	public ShowWhoParkFrame(Object entity) {
		super();
		this.entity = entity;
		initialize();
	}

	/**
	 * Initialize the GUI of the frame
	 */
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
	
	public JButton getBtnClose(){
		return getShowWhoParkPanel().getBtnClose();
	}

}
