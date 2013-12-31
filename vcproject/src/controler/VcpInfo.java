package controler;

import java.util.ArrayList;

import entity.*;

public class VcpInfo extends Controller {

	private ArrayList<Parking_Lot> parkingLot;
	private ArrayList<Parking_Places> parkingPlaces;
	private Pricing pricing;

	public VcpInfo(String host) {
		super(host);
		getParkingLotInfo();
		getParkingPlacesInfo();
		getParkingPricingInfo();
		closeConnection();
	}

	public Pricing getParkingPricingInfo() {
		if (pricing == null) {
			PricingController pricingController = new PricingController();
			pricing = new Pricing();
			pricing.setOccasional(pricingController.getOccasional());
			pricing.setOneTime(pricingController.getOneTime());
		}
		return pricing;
	}
	
	public ArrayList<Parking_Lot> getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(ArrayList<Parking_Lot> parkingLot) {
		this.parkingLot = parkingLot;
	}

	public void addParkingLot(Parking_Lot parkingLot) {
		this.parkingLot.add(parkingLot);
	}

	public ArrayList<Parking_Places> getParkingPlaces() {
		return parkingPlaces;
	}

	public void setParkingPlaces(ArrayList<Parking_Places> parkingPlaces) {
		this.parkingPlaces = parkingPlaces;
	}

	public void addParkingPlaces(Parking_Places parkingPlaces) {
		this.parkingPlaces.add(parkingPlaces);
	}

	private void getParkingLotInfo() {
		Object[] parkingLotQuery = { "SELECT * FROM `vcp_db`.`parking_lot`;" };
		sendQueryToServer(parkingLotQuery);
		ArrayList<Object> result = getResult();
		ArrayList<Parking_Lot> tempLot = new ArrayList<Parking_Lot>();

		if (result != null && !result.get(0).equals("No Result")) {
			for (int i = 0; i < result.size(); i++) {
				Parking_Lot pLot = new Parking_Lot();
				pLot.setIdparkinglot(Integer.parseInt(result.get(i++)
						.toString()));
				pLot.setDepth(Integer.parseInt(result.get(i++).toString()));
				pLot.setHight(Integer.parseInt(result.get(i++).toString()));
				pLot.setWidth(Integer.parseInt(result.get(i++).toString()));
				pLot.setStatus(result.get(i).toString());
				tempLot.add(pLot);
			}
			setParkingLot(tempLot);
		}

	}

	private void getParkingPlacesInfo() {
		Object[] parkingPlaceQuery = { "SELECT * FROM `vcp_db`.`parking_place`;" };
		sendQueryToServer(parkingPlaceQuery);
		ArrayList<Object> result = getResult();
		ArrayList<Parking_Places> tempPlace = new ArrayList<Parking_Places>();

		if (result != null && !result.get(0).equals("No Result")) {
			for (int i = 0; i < result.size(); i++) {
				Parking_Places pLot = new Parking_Places();
				pLot.setIdparkinglot(Integer.parseInt(result.get(i++)
						.toString()));
				pLot.setIdorder(Integer.parseInt(result.get(i++).toString()));
				pLot.setFloor(Integer.parseInt(result.get(i++).toString()));
				pLot.setRow(Integer.parseInt(result.get(i++).toString()));
				pLot.setColumn(Integer.parseInt(result.get(i++).toString()));
				pLot.setStatus(result.get(i++).toString());
				tempPlace.add(pLot);
			}
			setParkingPlaces(tempPlace);
		}

	}

}
