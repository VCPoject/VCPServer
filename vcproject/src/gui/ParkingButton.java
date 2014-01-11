package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class ParkingButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * whoIsParking contains the entity that park in the parking place
	 */
	private Object whoIsParking;
	/**
	 * showWhoParkFrame this frame will display the 
	 * info of the entity that park in the parking place
	 */
	private ShowWhoParkFrame showWhoParkFrame;

	/**
	 * This is custom button that can hold info about entity
	 * that in case the button is clicked will be show.
	 * @param name of the button to display.
	 */
	public ParkingButton(String name) {
		super(name);
		setVacant();
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getShowWhoParkFrame();
				try {
					getShowWhoParkFrame().getBtnClose().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							getShowWhoParkFrame().setVisible(false);
							getShowWhoParkFrame().dispose();
							showWhoParkFrame = null;
						}
					});
				} catch (Exception e) {
				}

			}
		});
	}
	
	/**
	 * set blue color to the button
	 * also set tip text as saved place
	 */
	public void setSaveSpace(){
		this.setBackground(Color.BLUE);
		this.setEnabled(true);
		this.setToolTipText("Saved place");
		this.repaint();
	}
	/**
	 * set green color to the button
	 * also set tip text as Vacant
	 */
	public void setVacant(){
		this.setBackground(Color.GREEN);
		this.setEnabled(false);
		this.setToolTipText("Vacant");
		this.repaint();
	}
	/**
	 * set white color to the button
	 * also set tip text as Occupy
	 */
	public void setOccupy(){
		this.setBackground(Color.WHITE);
		this.setEnabled(true);
		this.setToolTipText("Occupy");
		this.repaint();
	}
	/**
	 * set red color to the button
	 * also set tip text as Not working
	 */
	public void setNotWorking(){
		this.setBackground(Color.RED);
		this.setEnabled(false);
		this.setToolTipText("Not working");
		this.repaint();
	}

	public Object getWhoIsParking() {
		return whoIsParking;
	}

	public void setWhoIsParking(Object whoIsParking) {
		this.whoIsParking = whoIsParking;
	}

	public ShowWhoParkFrame getShowWhoParkFrame() {
		if(showWhoParkFrame == null && getWhoIsParking() != null){
			showWhoParkFrame = new ShowWhoParkFrame(getWhoIsParking());
		}
		return showWhoParkFrame;
	}

}
