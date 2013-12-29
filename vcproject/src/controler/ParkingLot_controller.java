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
		parkingplace.setIdparkinglot(Integer.parseInt(result.get(i).toString()));
		i++;
		parkingplace.setIdorder(Integer.parseInt(result.get(i).toString()));
		i++;
		parkingplace.setFloor(Integer.parseInt(result.get(i).toString()));
		i++;
		parkingplace.setRow(Integer.parseInt(result.get(i).toString()));
		i++;
		parkingplace.setColumn(Integer.parseInt(result.get(i).toString()));
		i++;
		parkingplace.setStatus(result.get(i).toString());
		i++;
		parkingPlaces.add(parkingplace);
		}
		
	}
	
	public ArrayList<Parking_Places>  getVaccantParkingPlaces(int parkinglotId){
		ArrayList<Object> result = null;
		Object[] sqlmsg={"SELECT * FROM vcp_db.parking_place WHERE status=? AND idparking=? ;","vaccant",parkinglotId};
		sendQueryToServer(sqlmsg);
		result = getResult();
		setParkingPlaceEntity(result);
		return parkingPlaces;
	}
	
	public void saveParkingPlace(int parkinglotId,int floor,int line,int parkingPlaceNum){
		ArrayList<Object> result = null;
		Object[] sqlmsg={ "UPDATE  vcp_db.parking_place SET status=? WHERE idparking=? and floor=? and line=?"
		+" "+ "and parkingNum=?;" ,"save",parkinglotId,floor,line,parkingPlaceNum};
		sendQueryToServer(sqlmsg);
		result=getResult();
		if(result.get(0).equals("done")) 
			showSeccussesMsg("Parking Place has been saved");
			
		else
			showWarningMsg("Couldn't save Parking Palce");
	}
	
}
