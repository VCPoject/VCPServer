package controler;

import java.util.ArrayList;

import entity.Parking_Places;

public class ParkingLot_controller extends Controller{
	private ArrayList<Parking_Places> parkingPlacesList=new ArrayList<Parking_Places>();
	
	public ParkingLot_controller(String host, int port) {
		super(host, port);
	}
	
	public void setEntity(ArrayList<Object>  result){
		Parking_Places p=new Parking_Places();
		p.setIdparkinglot(Integer.parseInt(result.get(0).toString()));
		p.setIdorder(Integer.parseInt(result.get(1).toString()));
		p.setFloor((Integer.parseInt(result.get(2).toString())));
		p.setRow(Integer.parseInt(result.get(3).toString()));
		p.setColumn(Integer.parseInt(result.get(3).toString()));
		p.setStatus(result.get(3).toString());
		parkingPlacesList.add(p);
	}
	
	public int getNumOfParkingPlaces(int num){
		ArrayList<Object> result = null;
		Object[] sqlmsg={"SELECT * FROM vcp_db.parking_lot WHERE idparkinglot=?};",num};
		sendQueryToServer(sqlmsg);
		result = getResult();
		setEntity(result);
		closeConnection();
		return Integer.parseInt(result.get(0).toString());
	}
	

}
