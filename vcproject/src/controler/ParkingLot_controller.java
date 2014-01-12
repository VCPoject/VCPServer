package controler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import entity.Order;
import entity.Parking_Lot;
import entity.Parking_Places;
import entity.Reservation;
import entity.Subscribe;

public class ParkingLot_controller extends Controller{
	private ArrayList<Parking_Lot> parkingLotList;
	private ArrayList<Parking_Places> parkingPlaces;
	private ArrayList<Parking_Places> vaccantParkingPlaces;
	private ArrayList<Parking_Lot> availableParkingLots;
	private HashMap<Integer, Order>  ordersMap;
	private HashMap<Integer,Subscribe>subscribeMap;
	
	public ParkingLot_controller(){
		super();
		
	}
	
	
	public ParkingLot_controller(String host, int port, VcpInfo vcpInfo) {
		super(host, port);
		this.parkingPlaces=vcpInfo.getParkingPlaces(); 
		this.ordersMap=vcpInfo.getAllOrders();
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
							departureDate=StringToDate(currentDate.toString(), subscribe.getDepartureTime());
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
		Object[] svaeParkingPlace={"UPDATE  vcp_db.parking_place SET status=? WHERE idparking=? and parking_place.column=?;" 
		,"save",parkinglotId,parkingPlaceNum};
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
	
	public void updateParkingPlaceAsnotWorking(int parkinglotId,int parkingPlaceNum,int flag){
		ArrayList<Object> result = null;
		Object[] sqlmsg={ "UPDATE  vcp_db.parking_place SET status=? WHERE idparking=? and parkingNum=?;", "not working"
		,parkinglotId,parkingPlaceNum};
		sendQueryToServer(sqlmsg);
		result=getResult();
		
		if(result.get(0).equals("done")){ 
			if(flag==0)
				showSeccussesMsg("Parking Place has been signed up as not working");
			
			for(Parking_Places parkingPlace:parkingPlaces){
				if(parkingPlace.getIdparkinglot()==parkinglotId && parkingPlace.getColumn()==parkingPlaceNum)
						parkingPlace.setStatus("not working");
			}
		}
		
		else{
			if(flag==0)
				showWarningMsg("Couldn't signed up Parking place as not working");
		}
}
	
	public void updateParkingLotAsNotWorking(int parkinglotId){
		ArrayList<Object> result = null;
		ArrayList<Parking_Places> parkingPlace= getAllparkingLotplaces(parkinglotId);
		
		for(Parking_Places parkingplace:parkingPlace)
			updateParkingPlaceAsnotWorking(parkinglotId,parkingplace.getColumn(),1);
		
		Object[] sqlmsg={ "UPDATE  vcp_db.parking_lot SET status=? WHERE idparking=?;" ,"not working",parkinglotId};
		sendQueryToServer(sqlmsg);
		result=getResult();
		closeConnection();
		
		if(result.get(0).equals("done")) {
			showSeccussesMsg("Parking Lot has been signed up as not working");
			for(Parking_Lot parkingLot:parkingLotList)
				if(parkingLot.getIdparkinglot()==parkinglotId)
					parkingLot.setStatus("not working");
		}
		
		else
			showWarningMsg("Couldn't signed up Parking lot as not working");
	}
	
	public ArrayList<Parking_Lot> findAvailableParkingLots(){
		for(Parking_Lot parkingLot: parkingLotList)
			if(parkingLot.getStatus().equals("avilable"))
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
		Object[] sqlmsg={ "UPDATE vcp_db.parking_lot SET alt_parking_lot=? WHERE idparking=?;" ,altParkinglotId,
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

	
}
