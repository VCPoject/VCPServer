package gui; 

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

public class ParkingLot_Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<JButton> jbuttonArrFirst = new ArrayList<JButton>();
	private ArrayList<JButton> jbuttonArrSecond = new ArrayList<JButton>();
	private ArrayList<JButton> jbuttonArrThird = new ArrayList<JButton>();
	private int size;
	
	public ParkingLot_Panel(int num) {
		super();
		size = num;
		initialize();
	}

	private void initialize() {
		setLayout(null);
		this.setSize(795, 575);
		
		JPanel panelFloor1 = new JPanel();
		panelFloor1.setBorder(new TitledBorder(null, "First floor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFloor1.setBounds(23, 30, 520, 180);
		add(panelFloor1);
		panelFloor1.setLayout(null);
		JPanel panelFloor2 = new JPanel();
		panelFloor2.setBorder(new TitledBorder(null, "Second floor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFloor2.setBounds(23, 211, 520, 180);
		add(panelFloor2);
		panelFloor2.setLayout(null);
		
		JPanel panelFloor3 = new JPanel();
		panelFloor3.setBorder(new TitledBorder(null, "Third floor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFloor3.setBounds(23, 390, 520, 180);
		add(panelFloor3);
		panelFloor3.setLayout(null);
		
		int parkNum = 1;
		for(int i = 0; i<3 ; i++){
			JButton newButtonFirst;
			JButton newButtonSecond;
			JButton newButtonThird;
			for(Integer j = 0; j<size ; j++){
				newButtonFirst = new JButton(String.valueOf(parkNum));
				newButtonFirst.setEnabled(false);
				newButtonSecond = new JButton(String.valueOf(parkNum));
				newButtonSecond.setEnabled(false);
				newButtonThird = new JButton(String.valueOf(parkNum));
				newButtonThird.setEnabled(false);
				newButtonFirst.setBounds(17 + 62*j, 20 + 51*i, 52, 40);
				newButtonSecond.setBounds(17 + 62*j, 20 + 51*i, 52, 40);
				newButtonThird.setBounds(17 + 62*j, 20 + 51*i, 52, 40);
				jbuttonArrFirst.add(newButtonFirst);
				jbuttonArrSecond.add(newButtonSecond);
				jbuttonArrThird.add(newButtonThird);
				parkNum++;
			}
		}
		
		for(JButton addbtn : jbuttonArrFirst){
			panelFloor1.add(addbtn);
		}
		
		for(JButton addbtn : jbuttonArrSecond){
			panelFloor2.add(addbtn);
		}
		
		for(JButton addbtn : jbuttonArrThird){
			panelFloor3.add(addbtn);
		}
		
		JLabel lblParkingLot = new JLabel("Parking Lot");
		lblParkingLot.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblParkingLot.setBounds(326, 0, 137, 29);
		add(lblParkingLot);
	}
}
