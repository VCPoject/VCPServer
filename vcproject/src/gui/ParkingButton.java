package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

import entity.Parking_Places;

public class ParkingButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object whoIsParking;
	
	public ParkingButton() {
	}

	public ParkingButton(Icon icon) {
		super(icon);
	}

	public ParkingButton(String name) {
		super(name);
		setVacant();
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
	}

	public ParkingButton(Action a) {
		super(a);
	}

	public ParkingButton(String text, Icon icon) {
		super(text, icon);
	}
	
	public void setSaveSpace(){
		this.setBackground(Color.BLUE);
		this.setEnabled(true);
		this.repaint();
	}
	
	public void setVacant(){
		this.setBackground(Color.GREEN);
		this.setEnabled(false);
		this.repaint();
	}
	
	public void setOccupu(){
		this.setBackground(Color.WHITE);
		this.setEnabled(true);
		this.repaint();
	}
	
	public void setNotWorking(){
		this.setBackground(Color.RED);
		this.setEnabled(false);
		this.repaint();
	}

	public Object getWhoIsParking() {
		return whoIsParking;
	}

	public void setWhoIsParking(Object whoIsParking) {
		this.whoIsParking = whoIsParking;
	}

}
