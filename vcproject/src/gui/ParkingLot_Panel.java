package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import controler.VcpInfo;
import javax.swing.JComboBox;
import entity.Parking_Lot;
import entity.Parking_Places;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ParkingLot_Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ParkingButton> jbuttonArrFirst = new ArrayList<ParkingButton>();
	private ArrayList<ParkingButton> jbuttonArrSecond = new ArrayList<ParkingButton>();
	private ArrayList<ParkingButton> jbuttonArrThird = new ArrayList<ParkingButton>();
	private int size;
	private JPanel panelFloor1;
	private JPanel panelFloor2;
	private JPanel panelFloor3;
	private VcpInfo vcpInfo;
	private JComboBox<Integer> comboBoxSelectParkingLot;
	private JButton btnShowStatus;
	private Parking_Lot SelectedPlot;
	private ParkingButton buttonSave;
	private ParkingButton btnVacant;
	private ParkingButton buttonOccupy;
	private ParkingButton btnNotWorking;
	private JLabel lblSaveSpace;
	private JLabel lblVacant;
	private JLabel lblOccupy;
	private JLabel lblNotWorking;
	private JButton btnReturn;
	private JButton btnMakePdf;

	public ParkingLot_Panel(VcpInfo vcpInfo) {
		super();
		this.vcpInfo = vcpInfo;
		initialize();
		listners();
	}

	private void initialize() {
		setLayout(null);
		this.setSize(795, 575);

		panelFloor1 = new JPanel();
		panelFloor1.setBorder(new TitledBorder(null, "First floor",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFloor1.setBounds(23, 30, 520, 180);
		add(panelFloor1);
		panelFloor1.setLayout(null);

		panelFloor2 = new JPanel();
		panelFloor2.setBorder(new TitledBorder(null, "Second floor",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFloor2.setBounds(23, 211, 520, 180);
		add(panelFloor2);
		panelFloor2.setLayout(null);

		panelFloor3 = new JPanel();
		panelFloor3.setBorder(new TitledBorder(null, "Third floor",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFloor3.setBounds(23, 390, 520, 180);
		add(panelFloor3);
		panelFloor3.setLayout(null);

		JLabel lblParkingLot = new JLabel("Parking Lot");
		lblParkingLot.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblParkingLot.setBounds(326, 0, 137, 29);
		add(lblParkingLot);

		JLabel lblSelectParkingLot = new JLabel("Select parking lot:");
		lblSelectParkingLot.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSelectParkingLot.setBounds(553, 30, 164, 22);
		add(lblSelectParkingLot);

		comboBoxSelectParkingLot = new JComboBox<Integer>();
		comboBoxSelectParkingLot.setBounds(727, 30, 58, 24);
		for (Parking_Lot pLot : getVcpInfo().getParkingLot())
			comboBoxSelectParkingLot.addItem(pLot.getIdparkinglot());
		add(comboBoxSelectParkingLot);

		btnShowStatus = new JButton("Show Status");

		btnShowStatus.setBounds(628, 63, 93, 29);
		add(btnShowStatus);
		
		buttonSave = new ParkingButton("");
		buttonSave.setBounds(733, 215, 52, 40);
		buttonSave.setSaveSpace();
		add(buttonSave);
		
		btnVacant = new ParkingButton("");
		btnVacant.setBounds(733, 266, 52, 40);
		btnVacant.setVacant();
		add(btnVacant);
		
		buttonOccupy = new ParkingButton("");
		buttonOccupy.setBounds(733, 317, 52, 40);
		buttonOccupy.setOccupy();
		add(buttonOccupy);
		
		btnNotWorking = new ParkingButton("");
		btnNotWorking.setBounds(733, 368, 52, 40);
		btnNotWorking.setNotWorking();
		add(btnNotWorking);
		
		lblSaveSpace = new JLabel("Save Space:");
		lblSaveSpace.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSaveSpace.setBounds(589, 225, 109, 22);
		add(lblSaveSpace);
		
		lblVacant = new JLabel("Vacant:");
		lblVacant.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVacant.setBounds(628, 275, 70, 22);
		add(lblVacant);
		
		lblOccupy = new JLabel("Occupy:");
		lblOccupy.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOccupy.setBounds(628, 325, 74, 22);
		add(lblOccupy);
		
		lblNotWorking = new JLabel("Not Working:");
		lblNotWorking.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNotWorking.setBounds(577, 375, 121, 22);
		add(lblNotWorking);
		
		btnReturn = new JButton("Return");
		
		btnReturn.setBounds(684, 524, 101, 40);
		add(btnReturn);
		
		btnMakePdf = new JButton("Make PDF");
		btnMakePdf.setBounds(553, 524, 109, 40);
		add(btnMakePdf);
	}

	private void listners() {
		btnShowStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (Parking_Lot pLot : getVcpInfo().getParkingLot()) {
					if (pLot.getIdparkinglot().equals(
							comboBoxSelectParkingLot.getSelectedItem())) {
						setSelectedPlot(pLot);
						setPlotSize(getSelectedPlot().getWidth());
						setParkingLot(getPlotSize());
						break;
					}
				}
			}
		});

	}

	public void setParkingLot(Integer size) {
		panelFloor1.removeAll();
		panelFloor2.removeAll();
		panelFloor3.removeAll();

		jbuttonArrFirst = new ArrayList<ParkingButton>();
		jbuttonArrSecond = new ArrayList<ParkingButton>();
		jbuttonArrThird = new ArrayList<ParkingButton>();
		// setFloorsPanels();
		int parkNum = 1;
		ParkingButton newButtonFirst;
		ParkingButton newButtonSecond;
		ParkingButton newButtonThird;
		for (int i = 0; i < 3; i++) {
			for (Integer j = 0; j < size; j++) {
				newButtonFirst = new ParkingButton(String.valueOf(parkNum));
				newButtonFirst.setBounds(17 + 62 * j, 20 + 51 * i, 52, 40);
				jbuttonArrFirst.add(newButtonFirst);

				newButtonSecond = new ParkingButton(String.valueOf(3 * size	+ parkNum));
				newButtonSecond.setBounds(17 + 62 * j, 20 + 51 * i, 52, 40);
				jbuttonArrSecond.add(newButtonSecond);

				newButtonThird = new ParkingButton(String.valueOf(2 * 3 * size + parkNum));
				newButtonThird.setBounds(17 + 62 * j, 20 + 51 * i, 52, 40);
				jbuttonArrThird.add(newButtonThird);
				parkNum++;
				this.repaint();
			}
		}

		for (JButton addbtn : jbuttonArrFirst) {
			panelFloor1.add(addbtn);
			panelFloor1.repaint();
		}

		for (JButton addbtn : jbuttonArrSecond) {
			panelFloor2.add(addbtn);
			panelFloor2.repaint();
		}

		for (JButton addbtn : jbuttonArrThird) {
			panelFloor3.add(addbtn);
			panelFloor3.repaint();
		}
		this.repaint();

		for (Parking_Places pPlace : getVcpInfo().getParkingPlaces()) {
			setButtonPlaces(pPlace);
		}

	}

	public VcpInfo getVcpInfo() {
		return vcpInfo;

	}

	public Integer getPlotSize() {
		return size;
	}

	public void setPlotSize(int size) {
		this.size = size;
	}

	public void setFloorsPanels() {
		panelFloor1 = new JPanel();
		panelFloor1.setBorder(new TitledBorder(null, "First floor",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFloor1.setBounds(23, 30, 520, 180);
		add(panelFloor1);
		panelFloor1.setLayout(null);

		panelFloor2 = new JPanel();
		panelFloor2.setBorder(new TitledBorder(null, "Second floor",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFloor2.setBounds(23, 211, 520, 180);
		add(panelFloor2);
		panelFloor2.setLayout(null);

		panelFloor3 = new JPanel();
		panelFloor3.setBorder(new TitledBorder(null, "Third floor",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFloor3.setBounds(23, 390, 520, 180);
		add(panelFloor3);
		panelFloor3.setLayout(null);
	}

	public Parking_Lot getSelectedPlot() {
		return SelectedPlot;
	}

	public void setSelectedPlot(Parking_Lot selectedPlot) {
		SelectedPlot = selectedPlot;
	}

	public void setButtonPlaces(Parking_Places pPlace) {
		ParkingButton tempBtn;
		if (getSelectedPlot().getIdparkinglot().equals(pPlace.getIdparkinglot())) {
			if (pPlace.getFloor() == 1) {
				tempBtn = jbuttonArrFirst.get(pPlace.getColumn() - 1);
				if(pPlace.getIdorder() != null)
					tempBtn.setWhoIsParking(getVcpInfo().getAllOrders().get(pPlace.getIdorder()));
				else if(pPlace.getSubscribeNum() != null){
					tempBtn.setWhoIsParking(getVcpInfo().getAllSubscribed().get(pPlace.getSubscribeNum()));
				}
			} else if (pPlace.getFloor() == 2) {
				Integer first = 3 * getPlotSize();
				tempBtn = jbuttonArrSecond.get(pPlace.getColumn() - (first + 1));
				if(pPlace.getIdorder() != null)
					tempBtn.setWhoIsParking(getVcpInfo().getAllOrders().get(pPlace.getIdorder()));
				else{
					tempBtn.setWhoIsParking(getVcpInfo().getAllSubscribed().get(pPlace.getSubscribeNum()));
				}
			} else {
				Integer first = 2 * 3 * getPlotSize();
				tempBtn = jbuttonArrThird.get(pPlace.getColumn() - first - 1);
				if(pPlace.getIdorder() != null)
					tempBtn.setWhoIsParking(getVcpInfo().getAllOrders().get(pPlace.getIdorder()));
				else{
					tempBtn.setWhoIsParking(getVcpInfo().getAllSubscribed().get(pPlace.getSubscribeNum()));
				}
			}

			if (pPlace.getStatus().equals("occupy")) {
				tempBtn.setOccupy();
			} else if (pPlace.getStatus().equals("not working")) {
				tempBtn.setNotWorking();
			} else if (pPlace.getStatus().equals("save")) {
				tempBtn.setSaveSpace();
			} else if (pPlace.getStatus().equals("vaccant")) {
				tempBtn.setVacant();
			}
		}

	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	public JButton getBtnMakePdf() {
		return btnMakePdf;
	}

	public JPanel getPanelFloor1() {
		return panelFloor1;
	}

	public JPanel getPanelFloor2() {
		return panelFloor2;
	}

	public JPanel getPanelFloor3() {
		return panelFloor3;
	}

}
