package controler;

import java.util.ArrayList;
import java.util.HashMap;

import entity.*;

public class VcpInfo extends Controller {

	private ArrayList<Parking_Lot> parkingLot;
	private ArrayList<Parking_Places> parkingPlaces;
	private HashMap<String,Employee> employeeMap=new HashMap<String,Employee>();
	private Pricing pricing;
	private Parking_Lot defultParkingLot;
	private boolean systemEnable = false;
	

	public VcpInfo(String host) {
		super(host);
		getParkingLotInfo();
		getEmployeeInfo();
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
	
	public void setEmployee(HashMap<String,Employee> employeeMap){
		this.employeeMap=employeeMap;
	}
	
	public HashMap<String,Employee> getEmployee(){
		return employeeMap;
	}

	public ArrayList<Parking_Lot> getParkingLotInfo() {
		Object[] parkingLotQuery = { "SELECT * FROM `vcp_db`.`parking_lot`;" };
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
				pLot.setIdparkinglot(Integer.parseInt(result.get(i++).toString()));
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
	
	public void getEmployeeInfo(){
		int i=0;
		Object[] employeeQuery={"SELECT * FROM vcp_employ.employ;"};
		sendQueryToServer(employeeQuery);
		ArrayList<Object> result = getResult();
		HashMap<String,Employee> employeeMap=new   HashMap<String,Employee>();
		while(i<result.size()){
			Employee employee=new Employee();
			employee.setIdEmployee(Integer.parseInt(result.get(i++).toString()));
			employee.setFirstName(result.get(i++).toString());
			employee.setLastName(result.get(i++).toString());
			employee.setRole(result.get(i++).toString());
			employee.setUserName(result.get(i++).toString());
			employee.setPassword(result.get(i++).toString());
			employee.setEmail(result.get(i++).toString());
			employee.setLogin(result.get(i++).toString());
			employeeMap.put(employee.getUserName(),employee);
		}
		
		setEmployee(employeeMap);
	}

	public Parking_Lot getDefultParkingLot() {
		return defultParkingLot;
	}

	public void setDefultParkingLot(Parking_Lot defultParkingLot) {
		showSeccussesMsg("You are now in parking lot:"+" "+defultParkingLot.getIdparkinglot());
		this.defultParkingLot = defultParkingLot;
	}

	public boolean isSystemEnable() {
		return systemEnable;
	}

	public void setSystemEnable(boolean systemEnable) {
		this.systemEnable = systemEnable;
	}

}
