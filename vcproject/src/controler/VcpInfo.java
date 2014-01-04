package controler;

import java.util.ArrayList;

import entity.*;

public class VcpInfo extends Controller {

	private ArrayList<Parking_Lot> parkingLot;
	private ArrayList<Parking_Places> parkingPlaces;
	private ArrayList<ClientEntity> allClients;
	private ArrayList<Order> allOrders;
	private ArrayList<Car> allCars;
	private ArrayList<Subscribe> allSubscribed;
	private Pricing pricing;
	private Parking_Lot defultParkingLot;
	private boolean systemEnable = false;

	public VcpInfo(String host) {
		super(host);
		getParkingLotInfo();
		getParkingPlacesInfo();
		getDefultParkingLot();
		getAllClients();
		getAllOrders();
		getAllSubscribed();
		getAllCars();
		getParkingPricingInfo();
		closeConnection();
	}

	public ArrayList<Car> getAllCars() {
		if (allCars == null) {
			Object[] getallcars = { "SELECT * FROM `vcp_db`.`car`;" };
			sendQueryToServer(getallcars);
			ArrayList<Object> result = getResult();
			ArrayList<Car> tempcars = new ArrayList<Car>();
			if (result != null && !result.get(0).equals("No Result")) {
				for (int i = 0; i < result.size(); i++) {
					Car addCar = new Car();
					addCar.setCarNum(Integer.parseInt(result.get(i++)
							.toString()));
					addCar.setClient(Integer.parseInt(result.get(i).toString()));
					tempcars.add(addCar);
				}
				setAllCars(tempcars);
			}
		}
		return allCars;
	}

	public void setAllCars(ArrayList<Car> allCars) {
		this.allCars = allCars;
	}

	public ArrayList<Subscribe> getAllSubscribed() {
		if (allSubscribed == null) {
			Object[] getallsubscribed = { "SELECT * FROM `vcp_db`.`subscribe`;" };
			sendQueryToServer(getallsubscribed);
			ArrayList<Object> result = getResult();
			ArrayList<Subscribe> tempSubscribeList = new ArrayList<Subscribe>();

			if (result != null && !result.get(0).equals("No Result")) {
				for (int i = 0; i < result.size(); i++) {
					Subscribe addsubscribe = new Subscribe();
					addsubscribe.setSubscribeNum(Integer.parseInt(result.get(
							i++).toString()));
					addsubscribe.setIdClient(Integer.parseInt(result.get(i++)
							.toString()));
					addsubscribe.setCarNum(Integer.parseInt(result.get(i++)
							.toString()));
					if(!result.get(i).toString().equals("no value"))
						addsubscribe.setIdparking(Integer.parseInt(result.get(i++).toString()));
					else
						i++;
					addsubscribe.setStartDate(result.get(i++).toString());
					addsubscribe.setSubscribType(result.get(i++).toString());
					addsubscribe.setCustomerType(result.get(i++).toString());
					if(!result.get(i).toString().equals("no value"))
						addsubscribe.setDepartureTime(result.get(i++).toString());
					else
						i++;
					addsubscribe.setEntriesDay(Integer.parseInt(result.get(i).toString()));
					tempSubscribeList.add(addsubscribe);
				}
			}
			setAllSubscribed(tempSubscribeList);
		}
		return allSubscribed;
	}

	public void setAllSubscribed(ArrayList<Subscribe> allSubscribed) {
		this.allSubscribed = allSubscribed;
	}

	private ArrayList<Order> getAllOrders() {
		if (allOrders == null) {
			Object[] getallorders = { "SELECT * FROM `vcp_db`.`order`;" };
			sendQueryToServer(getallorders);
			ArrayList<Object> result = getResult();
			ArrayList<Order> tempOrderList = new ArrayList<Order>();
			if (result != null && !result.get(0).equals("No Result")) {
				for (int i = 0; i < result.size(); i++) {
					Order order = new Order();
					order.setIdorder(Integer.parseInt(result.get(i++)
							.toString()));
					order.setCar(Integer.parseInt(result.get(i++).toString()));
					order.setClient(Integer
							.parseInt(result.get(i++).toString()));
					order.setIdparking(Integer.parseInt(result.get(i++)
							.toString()));
					order.setArrivalDate(result.get(i++).toString());
					order.setArrivalTime(result.get(i++).toString());
					order.setDepartureDate(result.get(i++).toString());
					order.setDepartureTime(result.get(i++).toString());

					String checkInDate = result.get(i++).toString();
					if (checkInDate.equals("no value"))
						order.setCheckInDate(null);
					else
						order.setCheckInDate(checkInDate);

					String checkInTime = result.get(i++).toString();
					if (checkInTime.equals("no value"))
						order.setCheckInTime(null);
					else
						order.setCheckInTime(checkInTime);

					String checkOutDate = result.get(i++).toString();
					if (checkOutDate.equals("no value"))
						order.setCheckOutDate(null);
					else
						order.setCheckOutDate(checkOutDate);

					String checkOutTime = result.get(i++).toString();
					if (checkOutTime.equals("no value"))
						order.setCheckOutTime(null);
					else
						order.setCheckOutTime(checkOutTime);

					order.setStatus(result.get(i++).toString());
					order.setType(result.get(i).toString());
					tempOrderList.add(order);
				}
			}
			setAllOrders(tempOrderList);
		}

		return allOrders;
	}

	public void setAllOrders(ArrayList<Order> allOrders) {
		this.allOrders = allOrders;
	}

	public ArrayList<ClientEntity> getAllClients() {
		if (allClients == null) {
			Object[] getallclients = { "SELECT * FROM `vcp_db`.`client`;" };
			sendQueryToServer(getallclients);
			ArrayList<Object> result = getResult();
			ArrayList<ClientEntity> tempClientList = new ArrayList<ClientEntity>();

			if (result != null && !result.get(0).equals("No Result")) {
				for (int i = 0; i < result.size(); i++) {
					ClientEntity client = new ClientEntity();
					client.setIdClient(Integer.parseInt(result.get(i++)
							.toString()));
					client.setFirstName(result.get(i++).toString());
					client.setLastName(result.get(i++).toString());
					client.setEmail(result.get(i).toString());
					tempClientList.add(client);
				}
				setAllClients(tempClientList);
			}

		}

		return allClients;
	}

	public void setAllClients(ArrayList<ClientEntity> allClients) {
		this.allClients = allClients;
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
	
	

	public ArrayList<Parking_Lot> getParkingLotInfo() {
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
				pLot.setStatus(result.get(i++).toString());
				pLot.setAltparkinglot(Integer
						.parseInt(result.get(i).toString()));
				tempLot.add(pLot);
			}
			setParkingLot(tempLot);
		}
		return parkingLot;
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
				pLot.setStatus(result.get(i).toString());
				tempPlace.add(pLot);
			}
			setParkingPlaces(tempPlace);
		}

	}

	public Parking_Lot getDefultParkingLot() {
		if (defultParkingLot == null) {
			defultParkingLot = getParkingLotInfo().get(0);
		}
		return defultParkingLot;
	}

	public void setDefultParkingLot(Parking_Lot defultParkingLot) {
		this.defultParkingLot = defultParkingLot;
	}

	public boolean isSystemEnable() {
		return systemEnable;
	}

	public void setSystemEnable(boolean systemEnable) {
		this.systemEnable = systemEnable;
	}

}
