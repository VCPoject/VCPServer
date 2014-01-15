package controler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.sun.rmi.rmid.ExecPermission;

import entity.NotWorkingPlaces;
import entity.Order;
import entity.Parking_Lot;
import entity.Parking_Places;
import entity.Reservation;
import entity.Subscribe;
/**
 * This class is the parking lot controller and in charge to create the parking lot entity.
 * 
 *
 */
public class ParkingLot_controller extends Controller{
	private ArrayList<Parking_Lot> parkingLotList;
	private ArrayList<Parking_Places> parkingPlaces;
	private ArrayList<Parking_Places> vaccantParkingPlaces;
	private HashMap<Integer, Order>  ordersMap;
	private HashMap<Integer,Subscribe>subscribeMap;
	private VcpInfo vcpInfo;
	
	
	public ParkingLot_controller(String host, int port, VcpInfo vcpInfo) {
		super(host, port);
		this.vcpInfo=vcpInfo;
		this.parkingPlaces=vcpInfo.getParkingPlaces(); 
		this.ordersMap=vcpInfo.getAllOrders();
		this.subscribeMap=vcpInfo.getAllSubscribed();
		this.parkingLotList=vcpInfo.getParkingLot();
	}
	
	/**
	 * This method get all the vacant parking place in a specific parking lot,consider reservation. 
	 * @param parkinglotId-the parking lot id
	 * @param arrivalDate-the arrival date.
	 * @return array list of parking place.
	 * @throws ParseException
	 */
	public ArrayList<Parking_Places>  getVaccantParkingPlaces(int parkinglotId,Date arrivalDate) throws ParseException{
		Date departureDate;
		vaccantParkingPlaces=new ArrayList<Parking_Places>();
		
		for(Parking_Places parkingplace:parkingPlaces){
			
				if(parkingplace.getIdparkinglot()==parkinglotId && parkingplace.getStatus().equals("vaccant"))
					vaccantParkingPlaces.add(parkingplace);
				
				else if(parkingplace.getIdparkinglot()==parkinglotId && parkingplace.getStatus().equals("occipuy")){
					
					if(parkingplace.getIdorder()!=null){
					Order order=ordersMap.get(parkingplace.getIdorder());
					departureDate=StringToDate(order.getDepartureDate(), order.getDepartureTime());
						if(arrivalDate.after(departureDate))
								vaccantParkingPlaces.add(parkingplace);
					}
				
					else{
					Subscribe subscribe=subscribeMap.get(parkingplace.getSubscribeNum());
						if(subscribe.getSubscribeNum().equals("partially")){
							Date currentDate=new Date();
							SimpleDateFormat formatOnlyDate=new SimpleDateFormat("yyyy-MM-dd");
							departureDate=StringToDate(formatOnlyDate.format(currentDate), subscribe.getDepartureTime());
							if(arrivalDate.after(departureDate))
								vaccantParkingPlaces.add(parkingplace);
						}
						
						else{
							Date fullSubscribeDepartureDate=StringToDate(subscribe.getStartDate(), "00:00:00");
							departureDate=addDays(fullSubscribeDepartureDate, 14);
							if(arrivalDate.after(departureDate))
								vaccantParkingPlaces.add(parkingplace);
						}
					}
												
				}
			}
		
		return vaccantParkingPlaces;
		}
		
		
	
	/**
	 * This method save parking place in specific parking lot.
	 * @param parkinglotId-The parking lot no.
	 * @param arrivalDate-The arrival date.
	 * @param departureDate-the Departure date.
	 * @param arrivalTime-The arrival date.
	 * @param departutreTime-The departure time.
	 * @param parkingPlaceNum-The parking place no.
	 * @param lineNum-the row no.
	 * @param floorNum-the floor no.
	 */
	
	public void saveParkingPlace(int parkinglotId,String arrivalDate,String departureDate,
			String arrivalTime,String departutreTime ,int parkingPlaceNum,int lineNum,int floorNum){
		
		ArrayList<Object> result = null;
		Object[] svaeParkingPlace={"UPDATE  vcp_db.parking_place SET status=? WHERE idparking=? and "
		+ "vcp_db.parking_place.column=?;" ,"save",parkinglotId,parkingPlaceNum};
		sendQueryToServer(svaeParkingPlace);
		result=getResult();
		if(result.get(0).equals("done")) 
			showSeccussesMsg("Parking Place has been saved");
			
		else
			showWarningMsg("Couldn't save Parking Palce");
		
		Reservation reservation=new Reservation();
		reservation.setArrivalDate(arrivalDate);
		reservation.setArrivalTime(arrivalTime);
		reservation.setDepartureDate(departureDate);
		reservation.setDepartureTime(departutreTime);
		reservation.setParkingLotNum(parkinglotId);
		reservation.setParkingPlaceNum(parkingPlaceNum);
		reservation.setFloorNum(floorNum);
		reservation.setLineNum(lineNum);
		String addReservationQuery = "INSERT INTO `vcp_db`.`reservation`"
				+ "(`parkingNum`,`idparking`,`floor`,`line`,"
				+ "`arrivalDate`,`arrivalTime`,`departureDate`,`departureTime`)"
				+ "VALUES(?,?,?,?,?,?,?,?);";
		reservation.setQuery(addReservationQuery);
		sendQueryToServer(reservation);
		result=getResult();
		if(result.get(0).equals("done")) {
			showSeccussesMsg("Parking Place has been saved");
			vcpInfo.getReservation().put(reservation.getParkingPlaceNum(), reservation);
		}
			
		else
			showWarningMsg("Couldn't save Parking Palce");
		
		closeConnection();
	}
	
	/**
	 * 
	 * @param parkinglotId=-the parking lot no.
	 * @return Array List with all parking places of all parking lots.
	 */
	public ArrayList<Parking_Places> getAllparkingLotplaces(int parkinglotId){
		return parkingPlaces;
	}
	
	/**
	 * This method update parking place as not working, only if the operation worker choose assign all paekin lot as
	 * not working. 
	 * @param parkingplace-The parking place no.
	 * @param flag-flag to determine not to print messages for every parking place.
	 */
	public void updateParkingPlaceAsnotWorking(Parking_Places parkingplace,int flag){
		ArrayList<Object> result = null;
		Object[] sqlmsg={ "UPDATE  vcp_db.parking_place SET status=? WHERE idparking=? and vcp_db.parking_place.column=?"
		, "not working",parkingplace.getIdparkinglot(),parkingplace.getColumn()};
		sendQueryToServer(sqlmsg);
		result=getResult();
		
		if(result.get(0).equals("done")){ 
			if(flag==0)
				showSeccussesMsg("Parking Place has been signed up as not working");
		
		parkingplace.setStatus("not working");
		}
		
		else{
			if(flag==0)
				showWarningMsg("Couldn't signed up Parking place as not working");
		}
		
		
	}
	 
	/**
	 * This method update only one parking place as not working.
	 * @param parkinglotId-The parking lot no.
	 * @param parkingPlaceNum-The parking place no. 
	 */
	public void updateParkingPlaceAsnotWorking(int parkinglotId,int parkingPlaceNum){
		
			try{
				for(Parking_Places parkingPlace:parkingPlaces){
					if(parkingPlace.getIdparkinglot()==parkinglotId && parkingPlace.getColumn()==parkingPlaceNum){
						
						if(checkIfPlaceNotWorking(parkingPlace)==false){
							ArrayList<Object> result = null;
							Object[] sqlmsg={ "UPDATE  vcp_db.parking_place SET status=? WHERE idparking=? and vcp_db.parking_place.column=?;"
							, "not working",parkinglotId,parkingPlaceNum};
							sendQueryToServer(sqlmsg);
							result=getResult();
								if(result.get(0).equals("done")){ 
									parkingPlace.setStatus("not working");
									NotWorkingPlaces npwPlace=new NotWorkingPlaces();
									npwPlace.setParkingLotid(parkinglotId);
									npwPlace.setParkingPlaceNum(parkingPlace.getColumn());
									npwPlace.setFloorNum(parkingPlace.getFloor());
									npwPlace.setLineNum(parkingPlace.getRow());
									Date currentDate=new Date();
									SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
									npwPlace.setStartDate(dateFormat.format(currentDate));
									npwPlace.setEndDate(null);
									String query= "INSERT INTO `vcp_db`.`not_working_places`"
									+ "(`parkingPlaceNum`,`idparking`,`floorNum`,`lineNum`) VALUES(?,?,?,?);";
									npwPlace.setQuery(query);
									sendQueryToServer(npwPlace);
									showSeccussesMsg("Parking Place has been signed up as not working");
								}
								else
									showWarningMsg("Couldn't signed up Parking lot as not working");
						
						}
						
						else
							throw new Exception("This parking place is already assigned as working");
					}
				}
			
			}
			catch(Exception e2){
				showWarningMsg(e2.getMessage());
			}
		}
	
	/**
	 * This method checks whether the parking place is not working.
	 * @param place-initialize as object so it can contain parking lot or parking place.
	 * @return true if parking place dosen't works else false.
	 */
	public boolean checkIfPlaceNotWorking(Object place){
		
		if(place instanceof Parking_Places){
			
			if(((Parking_Places)place).getStatus().equals("not working"))
					return true;
		}
		
		
		else if(place instanceof Parking_Lot){
		
			if(((Parking_Lot)place).getStatus().equals("not working"))
					return true;
		}
		
		return false;
	}
	
	/**
	 * This method checks whether the parking place is working.
	 * @param place-initialize as object so it can contain parking lot or parking place.
	 * @return true if parking place dose works else false.
	 */
	public boolean checkIfPlaceWorking(Object place){
		
		if(place instanceof Parking_Places){
			
			if(!((Parking_Places)place).getStatus().equals("not working"))
					return true;
		}
		
		else if(place instanceof Parking_Lot){
			
			if(!((Parking_Lot)place).equals("not working"))
						return true;
		}
		
		return false;
	}
	
	
	public void updateParkingLotAsNotWorking(int parkinglotId){
		ArrayList<Object> result = null;
		ArrayList<Parking_Places> parkingPlace= getAllparkingLotplaces(parkinglotId);
		try{
		for(Parking_Lot parkingLot:parkingLotList)
			if(parkingLot.getIdparkinglot()==parkinglotId){
				if(checkIfPlaceNotWorking(parkingLot))
					throw new Exception("This Parking Lot is already assigned as not working");
				else{
					parkingLot.setStatus("not working");
					if(parkingLot.getIdparkinglot().equals(vcpInfo.getDefultParkingLot().getIdparkinglot()))
						vcpInfo.setDefultParkingLot(parkingLot);
				}
					
				
			}
				
		
		
		for(Parking_Places parkingplace:parkingPlace)
			if(parkingplace.getIdparkinglot()==parkinglotId)
				updateParkingPlaceAsnotWorking(parkingplace,1);
			
		
		
		Object[] sqlmsg={ "UPDATE  vcp_db.parking_lot SET status=? WHERE idparking=?;","not working",parkinglotId};
		sendQueryToServer(sqlmsg);
		result=getResult();
		
		if(result.get(0).equals("done")) 
			showSeccussesMsg("Parking Lot has been signed up as not working");
			
		else
			showWarningMsg("Couldn't signed up Parking lot as not working");
		
		NotWorkingPlaces npwPlace=new NotWorkingPlaces();
		npwPlace.setParkingLotid(parkinglotId);
		npwPlace.setParkingPlaceNum(0);
		npwPlace.setFloorNum(0);
		npwPlace.setLineNum(0);
		Date currentDate=new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		npwPlace.setStartDate(dateFormat.format(currentDate));
		npwPlace.setEndDate(null);
		String query= "INSERT INTO `vcp_db`.`not_working_places`"
		+ "(`parkingPlaceNum`,`idparking`,`floorNum`,`lineNum`) VALUES(?,?,?,?);";
		npwPlace.setQuery(query);
		sendQueryToServer(npwPlace);
		}
		
		catch(Exception e1){
			showWarningMsg(e1.getMessage());
		}
	}
	

	/**
	 * 
	 * @return all available parking places for alternative parking lot.
	 */
	public ArrayList<Parking_Lot> findAvailableParkingLots(){
		ArrayList<Parking_Lot> availableParkingLots=new ArrayList<Parking_Lot>();
		for(Parking_Lot parkingLot: parkingLotList)
			if(parkingLot.getStatus().equals("available") && parkingLot.getIdparkinglot()!=vcpInfo.getDefultParkingLot()
			.getIdparkinglot())
				availableParkingLots.add(parkingLot);
		
		return availableParkingLots;
	}
	/**
	 * This method update parking lot as full. 
	 * @param parkinglotId-parking lot id  no.
	 */
	public void updateParkingLotAsFull(int parkinglotId){
		ArrayList<Object> result = null;
		Object[] sqlmsg={ "UPDATE  vcp_db.parking_lot SET status=? WHERE idparking=?;" ,"full",parkinglotId};
		sendQueryToServer(sqlmsg);
		result=getResult();
		closeConnection();
		
		if(result.get(0).equals("done")) {
			if(checkIfParkngLotIsFull(parkinglotId)==false);
				showSeccussesMsg("Parking Lot"+" "+parkinglotId+" has been signed up as full");
			for(Parking_Lot parkingLot:parkingLotList)
				if(parkingLot.getIdparkinglot()==parkinglotId){
					parkingLot.setStatus("full");
					if(parkingLot.getIdparkinglot().equals(vcpInfo.getDefultParkingLot().getIdparkinglot()))
						vcpInfo.setDefultParkingLot(parkingLot);
				}
					
					
		}
		
		else
			showWarningMsg("Couldn't signed up Parking lot as full");
	}
	
	public boolean checkIfParkngLotIsFull(int parkinglotId){
		for(Parking_Lot parkingLot:parkingLotList)
			if(parkingLot.getIdparkinglot()==parkinglotId)
				if(parkingLot.getStatus().equals("full"))
					return true;
		
		return false;
	}
	
	
	/**
	 * This method update only one parking place as not working.
	 * @param parkinglotId-The parking lot no.
	 * @param parkingPlaceNum-The parking place no. 
	 */
	public void updateparkingPlaceAsWorking(int parkinglotId,int parkingPlaceNum){
		
		try{
			for(Parking_Places parkingPlace:parkingPlaces){
				if(parkingPlace.getIdparkinglot()==parkinglotId && parkingPlace.getColumn()==parkingPlaceNum){
					if(checkIfPlaceWorking(parkingPlace)==false){
						ArrayList<Object> result = null;
						Object[] sqlmsg={ "UPDATE  vcp_db.parking_place SET status=? WHERE idparking=? and vcp_db.parking_place.column=?;"
						, "vaccant",parkinglotId,parkingPlaceNum};
						sendQueryToServer(sqlmsg);
						result=getResult();
							if(result.get(0).equals("done")){ 
								parkingPlace.setStatus("vaccant");
								NotWorkingPlaces npwPlace=new NotWorkingPlaces();
								Date currentDate=new Date();
								SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								npwPlace.setEndDate(dateFormat.format(currentDate));
								Object[] query= {"UPDATE  vcp_db.not_working_places SET endDate=? WHERE "
								+ "parkingPlaceNum=? AND idparking=?",npwPlace.getEndDate(),parkingPlaceNum,parkinglotId};
								sendQueryToServer(query);
								showSeccussesMsg("Parking Place has been signed up as working");
								break;
							}
							else
								showWarningMsg("Couldn't signed up Parking lot as working");
					
					}
					
					else
						throw new Exception("This parking place is already assigned as working");
				}
			}
		
		}
		catch(Exception e2){
			showWarningMsg(e2.getMessage());
		}
	}
	/** 
	 * This method update parking lot as working and update every parking place in the parking  as not working.
	 * @param parkinglotId the parking lot no.
	 */
	public void updateParkingLotAsWorking(int parkinglotId){
		ArrayList<Object> result = null;
		ArrayList<Parking_Places> parkingPlace= getAllparkingLotplaces(parkinglotId);
		try{
			for(Parking_Lot parkingLot:parkingLotList)
				if(parkingLot.getIdparkinglot()==parkinglotId){
					if(checkIfPlaceWorking(parkingLot)==false)
						throw new Exception("This Parking Lot is already assigned as working");
					else{
						parkingLot.setStatus("available");
						if(parkingLot.getIdparkinglot().equals(vcpInfo.getDefultParkingLot().getIdparkinglot()))
							vcpInfo.setDefultParkingLot(parkingLot);
						
					}
				}
					
			
			
			for(Parking_Places parkingplace:parkingPlace)
				if(parkingplace.getIdparkinglot()==parkinglotId)
					updateParkingPlaceAsWorking(parkingplace,1);
				
			
			
			Object[] sqlmsg={ "UPDATE  vcp_db.parking_lot SET status=? WHERE idparking=?;","available",parkinglotId};
			sendQueryToServer(sqlmsg);
			result=getResult();
			
			if(result.get(0).equals("done")) 
				showSeccussesMsg("Parking Lot has been signed up as working");
				
			else
				showWarningMsg("Couldn't signed up Parking lot as working");
			
			NotWorkingPlaces npwPlace=new NotWorkingPlaces();
			Date currentDate=new Date();
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			npwPlace.setEndDate(dateFormat.format(currentDate));
			Object[] query= {"UPDATE `vcp_db`.`not_working_places` SET endDate=? WHERE idparking=?",
			npwPlace.getEndDate(),parkinglotId};
			sendQueryToServer(query);
			}
			
			catch(Exception e1){
				showWarningMsg(e1.getMessage());
			}
	}
	
	/**
	 * This method update parking place as not working, only if the operation worker choose assign all paekin lot as
	 * not working. 
	 * @param parkingplace-The parking place no.
	 * @param flag-flag to determine not to print messages for every parking place.
	 */
	public void updateParkingPlaceAsWorking(Parking_Places parkingplace,int flag){
		ArrayList<Object> result = null;
		Object[] sqlmsg={ "UPDATE  vcp_db.parking_place SET status=? WHERE idparking=? and vcp_db.parking_place.column=?"
		, "vaccant",parkingplace.getIdparkinglot(),parkingplace.getColumn()};
		sendQueryToServer(sqlmsg);
		result=getResult();
		
		if(result.get(0).equals("done")){ 
			if(flag==0)
				showSeccussesMsg("Parking Place has been signed up as working");
		
		parkingplace.setStatus("vaccant");
		}
		
		else{
			if(flag==0)
				showWarningMsg("Couldn't signed up Parking place as working");
		}
		
		
	}
	
	/**
	 * This method update the parking lot as avaiable.
	 * @param parkinglotId-the parking lot no.
	 */
	public void updateParkingLotAsAvaialble(int parkinglotId){
		ArrayList<Object> result = null;
		Object[] sqlmsg={ "UPDATE  vcp_db.parking_lot SET status=? 'alt_parkinglot=?"
		+ "WHERE idparking=?;" ,"available",0,parkinglotId};
		sendQueryToServer(sqlmsg);
		result=getResult();
		
		if(result.get(0).equals("done")) {
			showSeccussesMsg("Parking Lot has been signed up as Available");
			for(Parking_Lot parkingLot:parkingLotList)
				if(parkingLot.getIdparkinglot()==parkinglotId){
					parkingLot.setStatus("available");
					parkingLot.setAltparkinglot(0);
					if(parkingLot.getIdparkinglot().equals(vcpInfo.getDefultParkingLot().getIdparkinglot()))
							vcpInfo.setDefultParkingLot(parkingLot);
				}
		}
		
		else
			showWarningMsg("Couldn't signed up Parking lot as available");
	}
	
	/**
	 * This method update alternative parking lot for specific parking lot which got full position.
	 * @param fullParkinglotId-the parking which got full.
	 * @param altParkinglotId-the alternative oarking lot.
	 */
	public void updateparkingLotAsAlt(int fullParkinglotId,int altParkinglotId){
		ArrayList<Object> result = null;
		Object[] sqlmsg={ "UPDATE vcp_db.parking_lot SET alt_parkinglot=? WHERE idparking=?;" ,altParkinglotId,
		fullParkinglotId};
		sendQueryToServer(sqlmsg);
		result=getResult();
		
		if(result.get(0).equals("done")) {
			showSeccussesMsg("Parking Lot has been signed up as alternative parking lot");
			for(Parking_Lot parkingLot:parkingLotList)
				if(parkingLot.getIdparkinglot()==fullParkinglotId)
					parkingLot.setAltparkinglot(altParkinglotId);
		}
		
		else
			showWarningMsg("Couldn't signed up Parking lot as alternative parking lot");
		
	}
	
	/**This method checks wether the parking place has specific alternative parking lot.
	 * 
	 * @param altParkingLot-The alternative parking lot.
	 * @param defaultParkingLot-the default parking lot.
	 * @return true if there is alternative parking lot for default parking lot, else false.
	 */
	public boolean checkAltParkingLot(int altParkingLot,int defaultParkingLot){
		for(Parking_Lot parkinglot:parkingLotList){
			if(parkinglot.getIdparkinglot().equals(defaultParkingLot))
				if(parkinglot.getAltparkinglot().equals(altParkingLot))
					return true;
		}
		
		return false;
	}
	
	/**
	 * addDays is adding to give date number of x days
	 * @param date to be add
	 * @param days to add to date
	 * @return
	 */
	public Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
	
	/**
	 * This method convers Date and time string into date variable
	 * @param strDate-the Date.
	 * @param strTime=the time.
	 * @return date's variable containing date and time.
	 * @throws ParseException
	 */
	public Date StringToDate(String strDate,String strTime) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date departDate = format.parse(strDate+" "+strTime);
		return departDate;
	}

	/**
	 * This method remove alternative parking lot from being alternative parking lot for other parking lot, because
	 * it is not full any more.
	 * @param defaultParkingLot-the default parking lot
	 * @param altParkingLotId-the alternative parking lot.
	 */
	public void RemoveparkingLotAsAlt(int defaultParkingLot, int altParkingLotId) {
		ArrayList<Object> result = null;
		Object[] sqlmsg={ "UPDATE vcp_db.parking_lot SET alt_parkinglot=? WHERE idparking=?;",0,defaultParkingLot};
		sendQueryToServer(sqlmsg);
		result=getResult();
		
		if(result.get(0).equals("done")) {
			showSeccussesMsg("Parking Lot has been remove from being alternative parking lot");
			for(Parking_Lot parkingLot:parkingLotList)
				if(parkingLot.getIdparkinglot()==defaultParkingLot)
					parkingLot.setAltparkinglot(0);
		}
		
		else
			showWarningMsg("Couldn't signed up Parking lot as alternative parking lot");
	}

	
}
