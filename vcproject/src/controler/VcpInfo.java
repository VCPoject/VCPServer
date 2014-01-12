package controler;   

import java.util.ArrayList;
import java.util.HashMap;

import entity.*;

public class VcpInfo extends Controller  {

	private ArrayList<Parking_Lot> parkingLot;
	private ArrayList<Parking_Places> parkingPlaces;
	private ArrayList<ClientEntity> allClients;
	private HashMap<Integer,Order>orderMap;
	private HashMap <Integer,Subscribe>subscribeMap;
	private HashMap<String, Employee> employeeMap;
	private ArrayList<Car> allCars;
	private HashMap<Integer, Reservation> reservationList;
	private Pricing pricing;
	private Parking_Lot defultParkingLot;
	private boolean systemEnable = false;
	private ParkingLot_controller parkingLotController;
	private int[] fullPositionCounter;

	public VcpInfo(String host) {
		super(host);
		getParkingLotInfo();
		getParkingPlacesInfo();
		getDefultParkingLot();
		getEmployeeInfo();
		getAllClients();
		getAllOrders();
		getAllSubscribed();
		getReservationInfo();
		getAllCars();
		getParkingPricingInfo();
		closeConnection();
	}
	public ArrayList<Car> getAllCars(){
	
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

	public HashMap<Integer, Subscribe> getAllSubscribed() {
		if (subscribeMap == null) {
			Object[] getallsubscribed = { "SELECT * FROM `vcp_db`.`subscribe`;" };
			sendQueryToServer(getallsubscribed);
			ArrayList<Object> result = getResult();
			HashMap<Integer,Subscribe> tempSubscribeMap = new HashMap<Integer,Subscribe>();

			if (result != null && !result.get(0).equals("No Result")) {
				for (int i = 0; i < result.size(); i++) {
					Subscribe addsubscribe = new Subscribe();
					addsubscribe.setSubscribeNum(Integer.parseInt(result.get(i++).toString()));
					addsubscribe.setIdClient(Integer.parseInt(result.get(i++).toString()));
					addsubscribe.setCarNum(Integer.parseInt(result.get(i++).toString()));
					if (!result.get(i).toString().equals("no value"))
						addsubscribe.setIdparking(Integer.parseInt(result.get(i++).toString()));
					
					else
						i++;
					
					addsubscribe.setStartDate(result.get(i++).toString());
					addsubscribe.setEndDate(result.get(i++).toString());
					addsubscribe.setSubscribeType(result.get(i++).toString());
					addsubscribe.setCustomerType(result.get(i++).toString());
					
					if (!result.get(i).toString().equals("no value"))
						addsubscribe.setDepartureTime(result.get(i++).toString());
					else
						i++;
					
					if(!result.get(i).toString().equals("no value"))
						addsubscribe.setEntriesDay(Integer.parseInt(result.get(i++).toString()));
					
					else
						i++;
					
					addsubscribe.setStatus(result.get(i).toString());
					tempSubscribeMap.put(addsubscribe.getSubscribeNum(),addsubscribe);
				}
			}
			setAllSubscribed(tempSubscribeMap);
		}
		return subscribeMap;
	}

	public void setAllSubscribed(HashMap<Integer, Subscribe> tempSubscribeMap) {
		this.subscribeMap = tempSubscribeMap;
	}

	public HashMap<Integer, Order> getAllOrders() {
		if (orderMap == null) {
			Object[] getallorders = { "SELECT * FROM `vcp_db`.`order`;" };
			sendQueryToServer(getallorders);
			ArrayList<Object> result = getResult();
			HashMap<Integer,Order>orderMap = new HashMap<Integer,Order>();
			if (result != null && !result.get(0).equals("No Result") && !result.get(0).toString().equals("no value")) {
				for (int i = 0; i < result.size(); i++) {
					Order order = new Order();
					order.setIdorder(Integer.parseInt(result.get(i++).toString()));
					order.setCar(Integer.parseInt(result.get(i++).toString()));
					order.setClient(Integer.parseInt(result.get(i++).toString()));
					order.setIdparking(Integer.parseInt(result.get(i++).toString()));
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
					orderMap.put(order.getIdorder(),order);
				}
			}
			setAllOrders(orderMap);
		}

		return orderMap;
	}

	public void setAllOrders(HashMap<Integer,Order> orderMap) {
		this.orderMap = orderMap;
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
			pricing = new Pricing();
			Object[] getPriceOccasional = {"SELECT * FROM `vcp_db`.`pricing`;"};
			sendQueryToServer(getPriceOccasional);
			ArrayList<Object> result = getResult();
			pricing.setOccasional(Float.parseFloat(result.get(1).toString()));
			pricing.setOneTime(Float.parseFloat(result.get(2).toString()));
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
		if (parkingLot == null) {
			Object[] parkingLotQuery = { "SELECT * FROM `vcp_db`.`parking_lot` ORDER BY `idparking`;" };
			sendQueryToServer(parkingLotQuery);
			ArrayList<Object> result = getResult();
			ArrayList<Parking_Lot> tempLot = new ArrayList<Parking_Lot>();

			if (result != null && !result.get(0).equals("No Result")) {
				for (int i = 0; i < result.size(); i++) {
					Parking_Lot pLot = new Parking_Lot();
					pLot.setIdparkinglot(Integer.parseInt(result.get(i++).toString()));
					pLot.setDepth(Integer.parseInt(result.get(i++).toString()));
					pLot.setHight(Integer.parseInt(result.get(i++).toString()));
					pLot.setWidth(Integer.parseInt(result.get(i++).toString()));
					pLot.setStatus(result.get(i++).toString());
					pLot.setAltparkinglot(Integer.parseInt(result.get(i).toString()));
					
					tempLot.add(pLot);
				}
				setParkingLot(tempLot);
				
			}
		}
		return parkingLot;
	}

	private ArrayList<Parking_Places> getParkingPlacesInfo() {
		if (parkingPlaces == null) {
			Object[] parkingPlaceQuery = { "SELECT * FROM `vcp_db`.`parking_place` ORDER BY idparking,floor,`vcp_db`.`parking_place`.row;" };
			sendQueryToServer(parkingPlaceQuery);
			ArrayList<Object> result = getResult();
			ArrayList<Parking_Places> tempPlace = new ArrayList<Parking_Places>();

			if (result != null && !result.get(0).equals("No Result")) {
				for (int i = 0; i < result.size(); i++) {
					Parking_Places pLot = new Parking_Places();
					pLot.setIdparkinglot(Integer.parseInt(result.get(i++).toString()));
					String idOrder = result.get(i++).toString();
						
					if(!idOrder.equals("no value"))
							pLot.setIdorder(Integer.parseInt(idOrder));
					
					String subscribeNum = result.get(i++).toString();
					
					if(!subscribeNum.equals("no value"))
						pLot.setSubscribeNum(Integer.parseInt(subscribeNum));
					
					pLot.setFloor(Integer.parseInt(result.get(i++).toString()));
					pLot.setRow(Integer.parseInt(result.get(i++).toString()));
					pLot.setColumn(Integer.parseInt(result.get(i++).toString()));
					pLot.setStatus(result.get(i).toString());
					tempPlace.add(pLot);
				}
				setParkingPlaces(tempPlace);
				
			}
		}
		
		fullPositionCounter=new int[parkingLot.size()];
		for(int i=0;i<parkingLot.size();i++)
			fullPositionCounter[i]=CountOccupyParkingPlaces(parkingLot.get(i).getIdparkinglot());
		
		return parkingPlaces;

	}

		
	
	public void getReservationInfo(){
		int i=0;
		Object[] reservationQuery={"SELECT * FROM vcp_db.reservation;"};
		sendQueryToServer(reservationQuery);
		ArrayList<Object> result = getResult();
		HashMap<Integer,Reservation> reservationList=new HashMap<Integer,Reservation>();
		while(i<result.size()){
			
			Reservation reservation=new Reservation();
			reservation.setParkingPlaceNum(Integer.parseInt(result.get(i++).toString()));
			reservation.setParkingLotNum(Integer.parseInt(result.get(i++).toString()));
			reservation.setFloorNum(Integer.parseInt(result.get(i++).toString()));
			reservation.setLineNum(Integer.parseInt(result.get(i++).toString()));
			reservation.setArrivalDate(result.get(i++).toString());
			reservation.setArrivalTime(result.get(i++).toString());
			reservation.setDepartureDate(result.get(i++).toString());
			reservation.setDepartureTime(result.get(i++).toString());
			reservationList.put(reservation.getParkingPlaceNum(), reservation);
		}
		
		setReservation(reservationList);
	}
	
	public void setReservation(HashMap<Integer, Reservation> reservationList){
		this.reservationList=reservationList;
	}
	
	public HashMap<Integer, Reservation> getReservation(){
		return reservationList;
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

	public void setEmployee(HashMap<String, Employee> employeeMap) {
		this.employeeMap = employeeMap;
	}

	public HashMap<String, Employee> getEmployeeInfo() {
		if (employeeMap == null) {
			int i = 0;
			Object[] employeeQuery = { "SELECT * FROM vcp_employ.employ;" };
			sendQueryToServer(employeeQuery);
			HashMap<String, Employee> employeeHash = new HashMap<String, Employee>();
			ArrayList<Object> result = getResult();
			while (i < result.size()) {
				Employee employee = new Employee();
				employee.setIdEmployee(Integer.parseInt(result.get(i++).toString()));
				employee.setFirstName(result.get(i++).toString());
				employee.setLastName(result.get(i++).toString());
				employee.setRole(result.get(i++).toString());
				employee.setUserName(result.get(i++).toString());
				employee.setPassword(result.get(i++).toString());
				employee.setEmail(result.get(i++).toString());
				employee.setLogin(result.get(i++).toString());
				employee.setRelevance(result.get(i++).toString());
				employeeHash.put(employee.getUserName(), employee);
			}
			setEmployee(employeeHash);
		}
		return employeeMap;
	}
	
	public int CountOccupyParkingPlaces(int parkingLotId){
		int count=0;
		for(Parking_Places parkingplace: parkingPlaces){
			if(parkingplace.getIdparkinglot()==parkingLotId){
				if(parkingplace.getStatus().equals("occupy") || parkingplace.getStatus().equals("save but occupy")
				|| parkingplace.getStatus().equals("save")|| parkingplace.getStatus().equals("not working"))
					count++;
				}
		}
		
		return count;
	}
	
	public ParkingLot_controller getParkingLot_controller(){
		
		if(parkingLotController==null)
			parkingLotController=new ParkingLot_controller();
		
		return parkingLotController;
	}
	
	public int[] fullPositionCounter(){
		return fullPositionCounter;
	}
}
