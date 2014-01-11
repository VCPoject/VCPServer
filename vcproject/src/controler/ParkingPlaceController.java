package controler;

import entity.Parking_Places;

public class ParkingPlaceController extends Controller {

	private VcpInfo vcpInfo;
	public ParkingPlaceController(String host, int port, VcpInfo vcpInfo) {
		super(host, port);
		this.vcpInfo = vcpInfo;
	}
	
	public Parking_Places getParkingPlaceByCoordinate(Integer[] coordinate) throws Exception{
		for(Parking_Places pPlace: getVcpInfo().getParkingPlaces()){
			if(pPlace.getIdparkinglot().equals(coordinate[0]))
				if(pPlace.getFloor().equals(coordinate[1]))
					if(pPlace.getRow().equals(coordinate[2]))
						if(pPlace.getColumn().equals(coordinate[3]))
							return pPlace;
		}
		throw new Exception("Cant find parking place");
	}
	
	public void updateParkingPlace(Parking_Places pPlace){
		String updatePPlace = "UPDATE `vcp_db`.`parking_place` SET `idorder` = ?,`status` = ? WHERE `idparking` = ? AND `floor` = ? AND `row` = ? AND `column` = ?;";
		pPlace.setQuery(updatePPlace);
		sendQueryToServer(pPlace);
		closeConnection();
	}

	public VcpInfo getVcpInfo() {
		return vcpInfo;
	}

}
