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
import gui.NotWorkingPlaces_Panel;

public class ParkingLot_controller extends Controller{
	private ArrayList<Parking_Lot> parkingLotList;
	private ArrayList<Parking_Places> parkingPlaces;
	private ArrayList<Parking_Places> vaccantParkingPlaces;
	private HashMap<Integer, Order>  ordersMap;
	private HashMap<Integer,Subscribe>subscribeMap;
	private VcpInfo vcpInfo;
	
	public ParkingLot_controller(){
		super();
	}
	
	
	public ParkingLot_controller(String host, int port, VcpInfo vcpInfo) {
		super(host, port);
		this.vcpInfo=vcpInfo;
		this.parkingPlaces=vcpInfo.getParkingPlaces(); 
		this.ordersMap=vcpInfo.getAllOrders();
		this.subscribeMap=vcpInfo.getAllSubscribed();
		this.parkingLotList=vcpInfo.getParkingLot();
	}
	
	
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
		if(result.get(0).equals("done")) 
			showSeccussesMsg("Parking Place has been saved");
			
		else
			showWarningMsg("Couldn't save Parking Palce");
		
		closeConnection();
	}
	
	
	public ArrayList<Parking_Places> getAllparkingLotplaces(int parkinglotId){
		return parkingPlaces;
	}
	
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
				else
					parkingLot.setStatus("not working");
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
	

	
	public ArrayList<Parking_Lot> findAvailableParkingLots(){
		ArrayList<Parking_Lot> availableParkingLots=new ArrayList<Parking_Lot>();
		for(Parking_Lot parkingLot: parkingLotList)
			if(parkingLot.getStatus().equals("available") && parkingLot.getIdparkinglot()!=vcpInfo.getDefultParkingLot()
			.getIdparkinglot())
				availableParkingLots.add(parkingLot);
		
		return availableParkingLots;
	}
	
	public void updateParkingLotAsFull(int parkinglotId){
		ArrayList<Object> result = null;
		Object[] sqlmsg={ "UPDATE  vcp_db.parking_lot SET status=? WHERE idparking=?;" ,"full",parkinglotId};
		sendQueryToServer(sqlmsg);
		result=getResult();
		closeConnection();
		
		if(result.get(0).equals("done")) {
			showSeccussesMsg("Parking Lot has been signed up as full");
			for(Parking_Lot parkingLot:parkingLotList)
				if(parkingLot.getIdparkinglot()==parkinglotId)
					parkingLot.setStatus("full");
		}
		
		else
			showWarningMsg("Couldn't signed up Parking lot as full");
	}
	
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
	
	public void updateParkingLotAsWorking(int parkinglotId){
		ArrayList<Object> result = null;
		ArrayList<Parking_Places> parkingPlace= getAllparkingLotplaces(parkinglotId);
		try{
			for(Parking_Lot parkingLot:parkingLotList)
				if(parkingLot.getIdparkinglot()==parkinglotId){
					if(checkIfPlaceWorking(parkingLot)==false)
						throw new Exception("This Parking Lot is already assigned as working");
					else
						parkingLot.setStatus("available");
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
				}
		}
		
		else
			showWarningMsg("Couldn't signed up Parking lot as available");
	}
	
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
	
	public Date StringToDate(String strDate,String strTime) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date departDate = format.parse(strDate+" "+strTime);
		return departDate;
	}


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
