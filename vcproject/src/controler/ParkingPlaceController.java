package controler;

import entity.Parking_Places;

public class ParkingPlaceController extends Controller {

	private VcpInfo vcpInfo;
	/**
	 * ParkingPlaceController is the controller that manage all the parking places
	 * @param host to connect to server side
	 * @param port to connect to server side
	 * @param vcpInfo is holding info that was download at startup
	 */
	public ParkingPlaceController(String host, int port, VcpInfo vcpInfo) {
		super(host, port);
		this.vcpInfo = vcpInfo;
	}
	
	/**
	 * getParkingPlaceByCoordinate is searching parking place by given coordinate
	 * @param coordinate holds info about parking lot number,floor number,row number and column number
	 * @return instance of Parking_Places if exist else null
	 */
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
	
	/**
	 * updateParkingPlace is update the status of give parking place entity.
	 * @param pPlace is entity of parking places
	 */
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
