package controler;

import java.util.ArrayList;

import entity.Parking_Lot;

public class ParkingLot_controller extends Controller{
	private ArrayList<Parking_Lot> parkingLotList=new ArrayList<Parking_Lot>();
	private Parking_Lot p;
	
	public ParkingLot_controller(String host, int port) {
		super(host, port);
	}
	
	public void setEntity(ArrayList<Object>  result){
		p=new Parking_Lot();
		p.setIdparkinglot(Integer.parseInt(result.get(0).toString()));
		p.setDepth(Integer.parseInt(result.get(1).toString()));
		p.setHight(Integer.parseInt(result.get(2).toString()));
		p.setWidth(Integer.parseInt(result.get(3).toString()));
		p.setStatus(result.get(4).toString());
		parkingLotList.add(p);
	}
	
	public int getNumOfParkingPlaces(int num){
		ArrayList<Object> result = null;
		Object[] sqlmsg={"SELECT * FROM vcp_db.parking_lot WHERE idparking=?;",num};
		sendQueryToServer(sqlmsg);
		result = getResult();
		setEntity(result);
		closeConnection();
		return Integer.parseInt(result.get(3).toString());
	}
	

}
