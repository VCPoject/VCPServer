package entity;

public class Reservation {
	private int parkingLotNum;
	private int parkingPlaceNum;
	private int floorNum;
	private int lineNum;
	private String arrivalDate;
	private String arrivalTime;
	private String departureDate;
	private String departureTime;
	private String query;
	
	public Reservation(){}

	public int getParkingLotNum() {
		return parkingLotNum;
	}	


	public void setParkingLotNum(int parkingLotNum) {
		this.parkingLotNum = parkingLotNum;
	}


	public int getParkingPlaceNum() {
		return parkingPlaceNum;
	}


	public void setParkingPlaceNum(int parkingPlaceNum) {
		this.parkingPlaceNum = parkingPlaceNum;
	}


	public int getFloorNum() {
		return floorNum;
	}


	public void setFloorNum(int floorNum) {
		this.floorNum = floorNum;
	}


	public int getLineNum() {
		return lineNum;
	}


	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}


	public String getArrivalDate() {
		return arrivalDate;
	}


	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}


	public String getArrivalTime() {
		return arrivalTime;
	}


	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


	public String getDepartureDate() {
		return departureDate;
	}


	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}


	public String getDepartureTime() {
		return departureTime;
	}


	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	public Object[] toObject() {
	Object obj[]={getQuery(),getParkingPlaceNum(),getParkingLotNum(),getFloorNum(),
				  getLineNum(),getArrivalDate(),getArrivalDate(),getDepartureDate(),getDepartureTime()};
	return obj;
	}
	

}
