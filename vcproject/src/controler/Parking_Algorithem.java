package controler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import entity.Order;
import entity.Parking_Lot;
import entity.Parking_Places;
import entity.Reservation;

public class Parking_Algorithem extends Controller{
	private ArrayList <Parking_Places> parkingPlacesList;
	private VcpInfo vcpInfo; 
	private HashMap<Integer, Order>  orderMap;
	private ArrayList<Parking_Places> sortedParkingPlaces=null;
	private HashMap<Integer,Reservation> reservation;
	private Parking_Lot parkingLot;
	private Order order;
	private Parking_Places parkingPlace;
	private int parkingLotSize;
	
	
	public Parking_Algorithem(VcpInfo vcpInfo,Order order,Parking_Lot parkingLot) throws ParseException{
		this.vcpInfo=vcpInfo;
		this.parkingPlacesList=vcpInfo.getParkingPlaces();
		this.parkingLot=parkingLot;
		parkingLotSize=parkingLot.getDepth()*parkingLot.getHight()*parkingLot.getWidth();
		this.reservation=vcpInfo.getReservation();
		this.order=order;
		this.orderMap=vcpInfo.getAllOrders();
		sortedParkingPlaceInIt();
		fillParkingPlaces();
		fillSavedandNotWorkingPlaces();
		
	}
	
	public void setParkingPlace(Parking_Places parkingPlace){
		this.parkingPlace=parkingPlace;
	}
	
	public Parking_Places getParkingPalce(){
		return parkingPlace;
	}
	
	public void fillParkingPlaces(){
		for(int i=0;i<sortedParkingPlaces.size();i++)
			if(sortedParkingPlaces.get(i)==null)
				sortedParkingPlaces.remove(i);
		
		for(int i=sortedParkingPlaces.size();i<parkingLotSize;i++)
			sortedParkingPlaces.add(null);
	}
	
	public void fillSavedandNotWorkingPlaces(){
		for(Parking_Places parkingplace:parkingPlacesList)
			if(parkingplace.getIdparkinglot()==parkingLot.getIdparkinglot())
				if(parkingplace.getStatus().equals("save") || parkingplace.getStatus().equals(" not working"))
					sortedParkingPlaces.set(parkingplace.getColumn(), parkingplace);
	}
	
	public Integer[] findOptimParkingPlace() throws ParseException{
		
		if(sortedParkingPlaces==null){//If the parking lot is empty
			int count=0;
			
			do{//Find parking place for car.
				if(parkingPlacesList.get(count).getStatus().equals("vaccant")){
					sortedParkingPlaces.set(count,parkingPlacesList.get(count));
					parkCar(parkingPlacesList.get(count));
					Integer[] coordinate={parkingPlacesList.get(count).getIdparkinglot(),
						parkingPlacesList.get(count).getIdparkinglot(),parkingPlacesList.get(count).getFloor(),
						parkingPlacesList.get(count).getRow(),parkingPlacesList.get(count).getColumn()};
					return coordinate;
					}
					
				else if(parkingPlacesList.get(count).equals("save")){
					Reservation parkingPlacereservation=reservation.get(parkingPlacesList.get(count).getColumn());
					String departureTime=parkingPlacereservation.getArrivalDate()+" "+
					parkingPlacereservation.getArrivalTime();
					Date ArrivalDate=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(departureTime);
					String orderDate=order.getDepartureDate()+" "+order.getDepartureTime();
					Date orderDeaprture=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(orderDate);
					
					if(orderDeaprture.after(ArrivalDate)){
						sortedParkingPlaces.set(count,parkingPlacesList.get(count));
						parkCar(parkingPlacesList.get(count));
					}
				}
		
		count++;
		}while(sortedParkingPlaces.get(count)!=null);
	}
		
		else{
			for(int i=0;i<sortedParkingPlaces.size();i++){
				
			String parkingOrder=orderMap.get(sortedParkingPlaces.get(i).getIdorder()).getDepartureDate()+" "+
			orderMap.get(sortedParkingPlaces.get(i).getIdorder()).getDepartureTime();
			Date departureParkingOrder=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(parkingOrder);
			ArrayList<Parking_Places> tempPlace=new ArrayList<Parking_Places>();
			String currOrder=this.order.getDepartureDate()+" "+this.order.getDepartureTime();
			Date departureCurrOrder=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(currOrder);
				if(departureCurrOrder.before(departureParkingOrder)){
					for(int j=i;j<sortedParkingPlaces.size();j++){
						if(sortedParkingPlaces.get(j)!=null)
							tempPlace.add(sortedParkingPlaces.get(j));
					}
					swapParkingplaces(i,tempPlace);
				}
			}
			
			if(sortedParkingPlaces.size()!=parkingLotSize){
				parkCar(sortedParkingPlaces.get(sortedParkingPlaces.size()));
				Integer[] coordinate={parkingPlacesList.get(sortedParkingPlaces.size()).getIdparkinglot(),
						parkingPlacesList.get(sortedParkingPlaces.size()).getIdparkinglot()
						,parkingPlacesList.get(sortedParkingPlaces.size()).getFloor()
						,parkingPlacesList.get(sortedParkingPlaces.size()).getRow()
						,parkingPlacesList.get(sortedParkingPlaces.size()).getColumn()};
					return coordinate;
			}
		}
		
		
		return null;
	}
	
	public void sortedParkingPlaceInIt() {
		sortedParkingPlaces=new ArrayList<Parking_Places>();
			for(Parking_Places parkingPlace:parkingPlacesList){
				
				if(parkingPlace.getIdparkinglot()==parkingLot.getIdparkinglot()){
					
					if(parkingPlace.getStatus().equals("occupy"))
						sortedParkingPlaces.add(parkingPlace);
					
					else
						sortedParkingPlaces.add(null);
				}
			}
	}

	public void swapParkingplaces(int index,ArrayList<Parking_Places> tempPlace) throws ParseException{
		int count=0;
		
		for(int j=index;j<sortedParkingPlaces.size()-1;j++){
				if(sortedParkingPlaces.get(j)!=null)
					sortedParkingPlaces.set(j,tempPlace.get(count));
					parkCar(sortedParkingPlaces.get(j));
					count++;
		}
		
		do{
			
			if(parkingPlace.getIdparkinglot()==parkingLot.getIdparkinglot()){
				if(parkingPlacesList.get(index+1).getStatus().equals("vaccant"))
					sortedParkingPlaces.set(index+1,tempPlace.get(count));
					parkCar(sortedParkingPlaces.get(index+1));
			}
			
			else if(parkingPlacesList.get(index+1).getStatus().equals("save")){
				SimpleDateFormat departureTimeparser=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Reservation reservationOrder=reservation.get(parkingPlacesList.get(index+1).getIdorder());
				Date ArrivalDate=departureTimeparser.parse(reservationOrder.getArrivalDate()+" "+
				reservationOrder.getDepartureTime());
				SimpleDateFormat tempTimeparser=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Order tempOrder=orderMap.get(tempPlace.get(count).getIdorder());
				Date tempDate=tempTimeparser.parse(tempOrder.getDepartureDate()+" "+tempOrder.getDepartureTime());
				
				if(tempDate.before(ArrivalDate)){
					sortedParkingPlaces.set(index+1,tempPlace.get(count));
					parkCar(sortedParkingPlaces.get(index+1));
				}
			}
			
			index++;
		}while(sortedParkingPlaces.get(index+1)==null);
			
	}
	
	
		
	public Parking_Places parkCar(Parking_Places parkingplace) {
		ArrayList<Object> result=null;
		Object[] updateParkingPlace={"UPDATE  vcp_db.parking_place SET status=?,idorder=?"
				+ " WHERE idparking=? and parking_place.column=?;" 
				,"occipuy",order.getIdorder(),parkingplace.getIdparkinglot(),parkingplace.getColumn()};
		sendQueryToServer(updateParkingPlace);
		result=getResult();
		parkingplace.setIdorder(order.getIdorder());
		parkingplace.setStatus("occupy");
		return parkingplace;
	}
	
	
}
