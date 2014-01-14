package controler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import entity.Order;
import entity.Parking_Lot;
import entity.Parking_Places;
import entity.Reservation;
import entity.Subscribe;
/**
 * This calss implemented the parking lot algorithem.
 * which sort the the order's and subscribe's check in and send to the robot 
 *to park the car in the appropriate parkin place so the orders with early time will be park
 *at the front of the parking lot and there will be no moves of the robot when it removing car
 *from the parking lot.
 */
public class Parking_Algorithem extends Controller{
	private String host;
	private int port;
	
	public Parking_Algorithem(String host, int port,VcpInfo vcpInfo,Object checkIn) throws ParseException{
		super(host,port);
		this.host = host;
		this.port = port;
		this.parkingPlacesList=vcpInfo.getParkingPlaces();
		this.parkingLot=vcpInfo.getDefultParkingLot();
		this.reservation=vcpInfo.getReservation();
		this.orderMap=vcpInfo.getAllOrders();
		this.subscribeMap=vcpInfo.getAllSubscribed();
		if(checkIn instanceof Order)
			this.checkIn=(Order)checkIn;
		
		else if(checkIn instanceof Subscribe)
			this.checkIn=(Subscribe)checkIn;
		
		getCheckedInOrders();
		getParkingLotParkingPalces();
		sortedParkingPlaceInIt();
		sortOrders();
	}
	
	public Parking_Algorithem(String host, int port,Object checkOut,VcpInfo vcpInfo){
		super(host,port);
		this.host = host;
		this.port = port;
		this.parkingPlacesList=vcpInfo.getParkingPlaces();
		this.parkingLot=vcpInfo.getDefultParkingLot();
		this.orderMap=vcpInfo.getAllOrders();
		this.subscribeMap=vcpInfo.getAllSubscribed();
		
		if(checkOut instanceof Order)
			checkOut=(Order)checkOut;
	
		else if(checkOut instanceof Subscribe)
			checkOut=(Subscribe)checkOut;
		
		findCar(checkOut);
	}

	/**
	 * arrayList witch contain all parking places from all parking lots.
	 */
	private ArrayList <Parking_Places> parkingPlacesList;
	/**
	 * Hash map which contains all activate orders.  
	 */
	private HashMap<Integer, Order>  orderMap;
	/**
	 * Hash map which contains all checked in orders.  
	 */
	private HashMap<Integer, Object>  checkInorderMap;
	/**
	 * Hash map which contains all subscribes.  
	 */
	private HashMap<Integer,Subscribe>subscribeMap;
	/**
	 * Hash map which contains all checked in subscribes.  
	 */
	private HashMap<Integer,Object>checkInsubscribeMap;
	/**
	 * Array list which contain all parking places sorted by orders.
	 */
	private ArrayList<Parking_Places> sortedParkingPlaces;
	/**
	 * Hash map which contains all the specific parking place of certain parking lot.
	 */
	private HashMap <Integer,Parking_Places> parkingPlacesMap;
	/**
	 * Hash map which contains all saving parking places.
	 */
	private HashMap<Integer,Reservation> reservation;
	/**
	 * Variable which hold the defualt parking lot.
	 */
	private Parking_Lot parkingLot;
	/**
	 * counter  which counts the right place of the order or subscribe check in at the list.
	 */
	private int count=0;
	/**
	 * variable which holds the current order or subscribe that has checked in. 
	 */
	private Object checkIn;
	
	/**
	 * This method insert into parkingPlaceMap all the parking place from specific parking lot.
	 */
	public void getParkingLotParkingPalces() {
		parkingPlacesMap=new HashMap<Integer,Parking_Places>();
		for(Parking_Places parkingplace:parkingPlacesList)
			if(parkingplace.getIdparkinglot()==parkingLot.getIdparkinglot())
					parkingPlacesMap.put(parkingplace.getColumn(), parkingplace);
	}
	
	/**
	 * This method gets all the orders and subscribes which made checking in. 
	 */
	public void getCheckedInOrders(){
		Order checkedInOrder;
		Subscribe checkedInSubscribe;
		checkInorderMap=new HashMap<Integer,Object>();
		checkInsubscribeMap=new HashMap<Integer,Object>();
		Set<Entry<Integer, Order>>orderEntry=orderMap.entrySet();
		Iterator<Entry<Integer, Order>> orderIterator=orderEntry.iterator();
		Set<Entry<Integer, Subscribe>>subscribeEntry=subscribeMap.entrySet();
		Iterator<Entry<Integer, Subscribe>> subscribeIterator=subscribeEntry.iterator();
		while(orderIterator.hasNext() || subscribeIterator.hasNext()){
			
			if(orderIterator.hasNext()){
				checkedInOrder=orderIterator.next().getValue();
				if(checkedInOrder.getStatus().equals("checked in") && 
				checkedInOrder.getIdparking()==parkingLot.getIdparkinglot())
					checkInorderMap.put(checkedInOrder.getIdorder(),checkedInOrder);
			}
			
			
			
			if(subscribeIterator.hasNext()){
				checkedInSubscribe=subscribeIterator.next().getValue();
				if(checkedInSubscribe.getStatus().equals("checked in") && checkedInSubscribe.getIdparking()==
				parkingLot.getIdparkinglot())
					checkInsubscribeMap.put(checkedInSubscribe.getSubscribeNum(),checkedInSubscribe);
			}
		}
		
		if(checkIn instanceof Order)
		checkInorderMap.put(((Order) checkIn).getIdorder(), checkIn);
		
		else if(checkIn instanceof Subscribe)
			checkInsubscribeMap.put( ((Subscribe) checkIn).getSubscribeNum(), checkIn);
	}
	/**
	 * This method suppose to sort the orders and the subscribes checking in. 
	 * @throws ParseException
	 */
	public void sortOrders() throws ParseException{
		
		while(checkInorderMap.size()!=0 ||checkInsubscribeMap.size()!=0){
			Object tempOrder,tempSubscribe = null,minOrder = null;
			Date tempOrderDate=null,minOrderDate=null,tempSubscribeDate=null;
			Set<Entry<Integer, Object>>orderEntry=checkInorderMap.entrySet();
			Iterator<Entry<Integer, Object>> orderIterator=orderEntry.iterator();
			Set<Entry<Integer, Object>>subscribeEntry=checkInsubscribeMap.entrySet();
			Iterator<Entry<Integer, Object>> subscribeIterator=subscribeEntry.iterator();
			
			if(orderIterator.hasNext()){
			minOrder=orderIterator.next().getValue();
			minOrderDate=StringToDate(((Order) minOrder).getDepartureDate(),((Order) minOrder).getDepartureTime());
			}
			
			else if(subscribeIterator.hasNext()){
				minOrder=subscribeIterator.next().getValue();
				if(((Subscribe)minOrder).getSubscribeType().equals("partially")){
					Date currentDate=new Date();
					SimpleDateFormat timedateparser=new SimpleDateFormat("yyyy-MM-dd");
					String dateCurrent=timedateparser.format(currentDate);
					minOrderDate=StringToDate(dateCurrent,
					((Subscribe)minOrder).getDepartureTime());
				}
				
				else{
					
					Date endParkingDate=StringToDate(((Subscribe)minOrder).getEndDate(), "00:00:00");
					minOrderDate=addDays(endParkingDate, 14);
				}
				
			}
			
			while(orderIterator.hasNext() || subscribeIterator.hasNext()){
				
				if(orderIterator.hasNext()){	
				tempOrder=orderIterator.next().getValue();
				tempOrderDate=StringToDate(((Order) tempOrder).getDepartureDate(),
				((Order) tempOrder).getDepartureTime());
				
					if(tempOrderDate.before(minOrderDate)){
					minOrder=tempOrder;
					minOrderDate= tempOrderDate;
					}
				}
				
				if(subscribeIterator.hasNext()){
					tempSubscribe=subscribeIterator.next().getValue();
					
					if(((Subscribe)tempSubscribe).getSubscribeType().equals("partially")){
						Date currentDate=new Date();
						SimpleDateFormat timedateparser=new SimpleDateFormat("yyyy-MM-dd");
						String dateCurrent=timedateparser.format(currentDate);
						tempSubscribeDate=StringToDate(dateCurrent,
						((Subscribe)tempSubscribe).getDepartureTime());
					}
					
					else{
						Date endParkingDate=StringToDate(((Subscribe)tempSubscribe).getEndDate(), "00:00:00");
						tempSubscribeDate=addDays(endParkingDate, 14);
					}
						
						if(tempSubscribeDate.before(minOrderDate)){
						minOrder=tempSubscribe;
						minOrderDate= tempSubscribeDate;
						}
				}
			}
			
			if(minOrder instanceof Order){
				checkInorderMap.remove(((Order) minOrder).getIdorder());
				findOptimParkingPlace(minOrderDate,minOrder);
			}
			
			else{
				checkInsubscribeMap.remove(((Subscribe) minOrder).getSubscribeNum());
				findOptimParkingPlace(minOrderDate,minOrder);
			}
		}
	}
	
	/**
	 * This method find the optimal place for the current car which got to the parking lot and for the cars
	 * which recently park at the parking lot by the counter used in the method above. 
	 * @param orderDeaprture-The departure date of the current car which want to get into the parking lot.
	 * @param order- An object which receives the order or the  subscribe details and thus initialize as object.
	 * @throws ParseException
	 */
	public void findOptimParkingPlace(Date orderDeaprture,Object order) throws ParseException{
		int flag=0;
		
		while( count<sortedParkingPlaces.size() && flag==0){
				
				if(sortedParkingPlaces.get(count)==null){
					Parking_Places parkingplace=parkingPlacesMap.get(count+1);
			
					if(parkingplace.getStatus().equals("save") || parkingplace.getStatus().equals("save but occupy")){
					Reservation parkingPlacereservation=reservation.get(parkingPlacesMap.get(count+1).getColumn());
					Date arrival=StringToDate(parkingPlacereservation.getArrivalDate(),
					parkingPlacereservation.getArrivalTime());
					
					if(orderDeaprture.before(arrival)){
						sortedParkingPlaces.set(count, parkingplace);
						parkCar(parkingplace,order);
						flag=1;
					}
				}
				
					else if(!parkingplace.getStatus().equals("not working")){
						sortedParkingPlaces.set(count, parkingplace);
						parkCar(parkingplace,order);
						flag=1;
					}
				}
				
			count++;
			}
		}
	
	/**
	 * This method initializes the sorted parking places array list to null.
	 */
	public void sortedParkingPlaceInIt() {
		sortedParkingPlaces=new ArrayList<Parking_Places>();
		Set<Entry<Integer, Parking_Places>> parkingPlaceEntry=parkingPlacesMap.entrySet();
		Iterator<Entry<Integer, Parking_Places>> ParkingPlaceIterator=parkingPlaceEntry.iterator();
		while(ParkingPlaceIterator.hasNext()){
			sortedParkingPlaces.add(null);
			ParkingPlaceIterator.next();
		}
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
	 * This method send to robot the right coordinates to park the car.
	 * @param parkingplace-the exact parking place.
	 * @param order-the order to initialize for the parking place.
	 */
	public void parkCar(Parking_Places parkingplace,Object order) {
		
		if(order instanceof Order){
			
			if(parkingplace.getStatus().equals("save") ||parkingplace.getStatus().equals("save but occupy")){
				Object[] updateParkingPlace={"UPDATE  vcp_db.parking_place SET status=?,idorder=?,subScribeNum=?"
				+ " WHERE idparking=? and parking_place.column=?;" 
				,"save but occupy",((Order) order).getIdorder(),null,parkingplace.getIdparkinglot()
				,parkingplace.getColumn()};
				sendQueryToServer(updateParkingPlace);
				parkingplace.setIdorder(((Order) order).getIdorder());
				parkingplace.setStatus("save but occupy");
				parkingplace.setSubscribeNum(null);
			}
		
			else{
				Object[] updateParkingPlace={"UPDATE  vcp_db.parking_place SET status=?,idorder=?,subScribeNum=?"
				+ " WHERE idparking=? and parking_place.column=?;" 
				,"occupy",((Order) order).getIdorder(),null,parkingplace.getIdparkinglot(),parkingplace.getColumn()};
				sendQueryToServer(updateParkingPlace);
				parkingplace.setIdorder(((Order) order).getIdorder());
				parkingplace.setStatus("occupy");
				parkingplace.setSubscribeNum(null);
			}
		}
		
		else if(order instanceof Subscribe){
			
			if(parkingplace.getStatus().equals("save") || parkingplace.getStatus().equals("save but occupy")){
			Object[] updateParkingPlace={"UPDATE  vcp_db.parking_place SET status=?,subScribeNum=?,idorder=?"
			+ " WHERE idparking=? and parking_place.column=?;" 
			,"save but occupy",((Subscribe) order).getSubscribeNum(),null,parkingplace.getIdparkinglot()
			,parkingplace.getColumn()};
			sendQueryToServer(updateParkingPlace);
			parkingplace.setSubscribeNum(((Subscribe)order).getSubscribeNum());
			parkingplace.setStatus("save but occupy");
			parkingplace.setIdorder(null);
			}
			
			else{
				Object[] updateParkingPlace={"UPDATE  vcp_db.parking_place SET status=?,subScribeNum=?,idorder=?"
				+ " WHERE idparking=? and parking_place.column=?;" 
				,"occupy",((Subscribe) order).getSubscribeNum(),null,
				parkingplace.getIdparkinglot(),parkingplace.getColumn()};
				sendQueryToServer(updateParkingPlace);
				parkingplace.setSubscribeNum(((Subscribe)order).getSubscribeNum());
				parkingplace.setStatus("occupy");
				parkingplace.setIdorder(null);
			}
		
		}
		
	}
	
	/**
	 * This method remove the car from the parking lot when the clinet checling out.
	 * @param parkingplace-the exact parking place.
	 * @param order-the order to initialize for the parking place.
	 */
	public void removeCarFromLot(Parking_Places parkingplace,Object order){
		
		if(order instanceof Order){
			
			if(parkingplace.getStatus().equals("save") || parkingplace.getStatus().equals("occupy")){
				Object[] updateParkingPlace={"UPDATE  vcp_db.parking_place SET status=?,idorder=?"
				+ " WHERE idparking=? and parking_place.column=?;" 
				,"vaccant",null,parkingplace.getIdparkinglot()
				,parkingplace.getColumn()};
				sendQueryToServer(updateParkingPlace);
				parkingplace.setIdorder(null);
				parkingplace.setStatus("vaccant");
				parkingplace.setIdorder(null);
			}
			
			else if(parkingplace.getStatus().equals("save but occupy")){
				Object[] updateParkingPlace={"UPDATE  vcp_db.parking_place SET status=?,idorder=?"
						+ " WHERE idparking=? and parking_place.column=?;" 
						,"save",null,parkingplace.getIdparkinglot()
						,parkingplace.getColumn()};
						sendQueryToServer(updateParkingPlace);
						parkingplace.setIdorder(null);
						parkingplace.setStatus("save");
						parkingplace.setIdorder(null);
			}
		}
		
		else if(order instanceof Subscribe){
			
			if(parkingplace.getStatus().equals("save") || parkingplace.getStatus().equals("occupy")){
			Object[] updateParkingPlace={"UPDATE  vcp_db.parking_place SET status=?,subScribeNum=?"
			+ " WHERE idparking=? and parking_place.column=?;" 
			,"vaccant",null,parkingplace.getIdparkinglot()
			,parkingplace.getColumn()};
			sendQueryToServer(updateParkingPlace);
			parkingplace.setSubscribeNum(null);
			parkingplace.setStatus("vaccant");
			}
			
			else if(parkingplace.getStatus().equals("save but occupy")){
				Object[] updateParkingPlace={"UPDATE  vcp_db.parking_place SET status=?,subScribeNum=?"
				+ " WHERE idparking=? and parking_place.column=?;" 
				,"save",null,parkingplace.getIdparkinglot(),parkingplace.getColumn()};
				sendQueryToServer(updateParkingPlace);
				parkingplace.setSubscribeNum(((Subscribe)order).getSubscribeNum());
				parkingplace.setStatus("save");
			}
		
		}
		
	}
	
	/**
	 * This method finds the accurate place where the car is in and sending it to the robot.
	 * @param checkOut-The checkout details.
	 */
	public void findCar(Object checkOut) {
		for(Parking_Places parkingplace: parkingPlacesList){
			if(checkOut instanceof Order){
				if(parkingplace.getIdorder()==((Order)checkOut).getIdorder())
					removeCarFromLot(parkingplace, checkOut);
			}
			
			else if(checkOut instanceof Subscribe){
				if(parkingplace.getSubscribeNum()==((Subscribe)checkOut).getSubscribeNum())
					removeCarFromLot(parkingplace,checkOut);
			}
		}
		
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
}
