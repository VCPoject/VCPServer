package controler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class Parking_Algorithem extends Controller{
	private ArrayList <Parking_Places> parkingPlacesList;
	private HashMap<Integer, Order>  orderMap;
	private HashMap<Integer, Object>  checkInorderMap;
	private HashMap<Integer,Subscribe>subscribeMap;
	private HashMap<Integer,Object>checkInsubscribeMap;
	private ArrayList<Parking_Places> sortedParkingPlaces;
	private HashMap <Integer,Parking_Places> parkingPlacesMap;
	private HashMap<Integer,Reservation> reservation;
	private Parking_Lot parkingLot;
	private int count=0;
	private Object checkIn;
	
	public Parking_Algorithem(VcpInfo vcpInfo,Object checkIn) throws ParseException{
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
	
	public Parking_Algorithem(Object checkOut,VcpInfo vcpInfo){
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
	

	public void getParkingLotParkingPalces() {
		parkingPlacesMap=new HashMap<Integer,Parking_Places>();
		for(Parking_Places parkingplace:parkingPlacesList)
			if(parkingplace.getIdparkinglot()==parkingLot.getIdparkinglot())
					parkingPlacesMap.put(parkingplace.getColumn(), parkingplace);
	}
	
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
				if(checkedInOrder.getStatus().equals("checked in"))
				checkInorderMap.put(checkedInOrder.getIdorder(),checkedInOrder);
			}
			
			
			
			if(subscribeIterator.hasNext()){
				checkedInSubscribe=subscribeIterator.next().getValue();
				if(checkedInSubscribe.getStatus().equals("checked in"))
					checkInsubscribeMap.put(checkedInSubscribe.getSubscribeNum(),checkedInSubscribe);
			}
		}
		
		if(checkIn instanceof Order)
		checkInorderMap.put(((Order) checkIn).getIdorder(), checkIn);
		
		else if(checkIn instanceof Subscribe)
			checkInsubscribeMap.put( ((Subscribe) checkIn).getSubscribeNum(), checkIn);
	}
	
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
				
				else
					minOrderDate=StringToDate(((Subscribe)minOrder).getEndDate(), "24:00:00");
				
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
					
					else
						tempSubscribeDate=StringToDate(((Subscribe)tempSubscribe).getEndDate(), "24:00:00");
					
						
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
	
		
	public void sortedParkingPlaceInIt() {
		sortedParkingPlaces=new ArrayList<Parking_Places>();
		Set<Entry<Integer, Parking_Places>> parkingPlaceEntry=parkingPlacesMap.entrySet();
		Iterator<Entry<Integer, Parking_Places>> ParkingPlaceIterator=parkingPlaceEntry.iterator();
		while(ParkingPlaceIterator.hasNext()){
			sortedParkingPlaces.add(null);
			ParkingPlaceIterator.next();
		}
	}
	
	public Date StringToDate(String strDate,String strTime) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date departDate = format.parse(strDate+" "+strTime);
		return departDate;
	}


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
	
}
