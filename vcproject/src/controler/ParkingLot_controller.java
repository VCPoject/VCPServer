package controler;

import java.util.ArrayList;
import entity.Parking_Lot;
import entity.Parking_Places;

public class ParkingLot_controller extends Controller{
	ArrayList<Parking_Lot> parkingLotList=new ArrayList<Parking_Lot>();
	ArrayList<Parking_Places> parkingPlaces=new ArrayList<Parking_Places>();
	private Parking_Lot p;
	private Parking_Places parkingplace;
	
	public ParkingLot_controller(String host, int port) {
		super(host, port);
	}
	
	public void setParkingLotEntity(ArrayList<Object>  result){
		p=new Parking_Lot();
		p.setIdparkinglot(Integer.parseInt(result.get(0).toString()));
		p.setDepth(Integer.parseInt(result.get(1).toString()));
		p.setHight(Integer.parseInt(result.get(2).toString()));
		p.setDepth(Integer.parseInt(result.get(3).toString()));
		p.setStatus(result.get(4).toString());
		parkingLotList.add(p);
	}
	
	public void setParkingPlaceEntity(ArrayList<Object>  result){
		int i=0;
		
		while(i<result.size()){
		parkingplace=new Parking_Places();
		parkingplace.setIdparkinglot(Integer.parseInt(result.get(i++).toString()));
		parkingplace.setIdorder(Integer.parseInt(result.get(i++).toString()));
		parkingplace.setFloor(Integer.parseInt(result.get(i++).toString()));
		parkingplace.setRow(Integer.parseInt(result.get(i++).toString()));
		parkingplace.setColumn(Integer.parseInt(result.get(i++).toString()));
		parkingplace.setStatus(result.get(i++).toString());
		parkingPlaces.add(parkingplace);
		}
		
	}
	
	public ArrayList<Parking_Places>  getVaccantParkingPlaces(int parkinglotId){
		ArrayList<Object> result = null;
		Object[] sqlmsg={"SELECT * FROM vcp_db.parking_place WHERE status=? AND idparking=? ;","vaccant",parkinglotId};
		sendQueryToServer(sqlmsg);
		result = getResult();
		closeConnection();
		setParkingPlaceEntity(result);
		return parkingPlaces;
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
	
	public ArrayList<Parking_Places> getAllparkinLotplaces(int parkinglotId){
		ArrayList<Object> result = null;
		Object[] sqlmsg={"SELECT * FROM vcp_db.parking_place WHERE idparking=?;",parkinglotId};
		sendQueryToServer(sqlmsg);
		result = getResult();
		closeConnection();
		setParkingPlaceEntity(result);
		return parkingPlaces;
	}
	
	public void updateParkingPlaceAsnotWorking(int parkinglotId,int floor,int line,int parkingPlaceNum){
		ArrayList<Object> result = null;
		Object[] sqlmsg={ "UPDATE  vcp_db.parking_place SET status=? WHERE idparking=? and floor=? and line=?"
		+" "+ "and parkingNum=?;" ,"not working",parkinglotId,floor,line,parkingPlaceNum};
		sendQueryToServer(sqlmsg);
		result=getResult();
		closeConnection();
		if(result.get(0).equals("done")) 
			showSeccussesMsg("Parking Place has been signed up as not working");
			
		else
			showWarningMsg("Couldn't signed up Parking place as not working");
	}
	
	public void updateParkingLotAsNotWorking(int parkinglotId){
		ArrayList<Object> result = null;
		Object[] sqlmsg={ "UPDATE  vcp_db.parking_place SET status=? WHERE idparking=?;" ,"not working",parkinglotId};
		sendQueryToServer(sqlmsg);
		result=getResult();
		closeConnection();
		if(result.get(0).equals("done")) 
			showSeccussesMsg("Parking Place has been signed up as not working");
			
		else
			showWarningMsg("Couldn't signed up Parking lot as not working");
	}
	
}
