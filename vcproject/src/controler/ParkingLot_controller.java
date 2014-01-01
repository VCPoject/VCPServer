package controler;

import java.util.ArrayList;

import entity.Parking_Lot;
import entity.Parking_Places;

public class ParkingLot_controller extends Controller{
	private ArrayList<Parking_Lot> parkingLotList;
	private ArrayList<Parking_Places> parkingPlaces;
	private ArrayList<Parking_Places> vaccantParkingPlaces=new ArrayList<Parking_Places>();
	private ArrayList<Parking_Lot> availableParkingLots=new ArrayList<Parking_Lot>();
	
	public ParkingLot_controller(ArrayList<Parking_Lot> parkingLot,String host,int port) {
		super(host, port);
		this.parkingLotList=parkingLot;
	}

	
	public ParkingLot_controller(String host, int port, ArrayList<Parking_Places> parking_places) {
		super(host, port);
		this.parkingPlaces=parking_places; 
	}
	
	 
	public ArrayList<Parking_Places>  getVaccantParkingPlaces(int parkinglotId){
		
		for(Parking_Places parkingplace:parkingPlaces){
			if(parkingplace.getIdparkinglot()==parkinglotId && parkingplace.getStatus().equals("vaccant"))
				vaccantParkingPlaces.add(parkingplace);
		}
		
		return vaccantParkingPlaces;
	}
	
	public void saveParkingPlace(int parkinglotId,int floor,int line,int parkingPlaceNum){
		ArrayList<Object> result = null;
		Object[] sqlmsg={ "UPDATE  vcp_db.parking_place SET status=? WHERE idparking=? and floor=? and line=?"
		+" "+ "and parkingNum=?;" ,"save",parkinglotId,floor,line,parkingPlaceNum};
		sendQueryToServer(sqlmsg);
		result=getResult();
		closeConnection();
		if(result.get(0).equals("done")) 
			showSeccussesMsg("Parking Place has been saved");
			
		else
			showWarningMsg("Couldn't save Parking Palce");
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
			if(parkingLot.getStatus().equals("available"))
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
	
	public void updateparkingLotAsAlt(int fullParkinglotId,int altParkinglotId){
		ArrayList<Object> result = null;
		Object[] sqlmsg={ "UPDATE vcp_db.parking_lot SET alt_parking_lot=? WHERE idparking=?;" ,altParkinglotId,
		fullParkinglotId};
		sendQueryToServer(sqlmsg);
		result=getResult();
		closeConnection();
		
		if(result.get(0).equals("done")) {
			showSeccussesMsg("Parking Lot has been signed up as alternative parking lot");
			for(Parking_Lot parkingLot:parkingLotList)
				if(parkingLot.getIdparkinglot()==fullParkinglotId)
					parkingLot.setAltparkinglot(altParkinglotId);
		}
		
		else
			showWarningMsg("Couldn't signed up Parking lot as alternative parking lot");
		
	}
}
