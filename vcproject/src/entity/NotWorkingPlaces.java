package entity;

public class NotWorkingPlaces {
	private int parkingLotid;
	private int parkingPlaceNum;
	private int floorNum;
	private int lineNum;
	private String startDate;
	private String endDate;
	private String query;
	
	public NotWorkingPlaces(){}
	
	public int getParkingLotid() {
		return parkingLotid;
	}
	public void setParkingLotid(int parkingLotid) {
		this.parkingLotid = parkingLotid;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}

	public Object[] toObject(){
		Object obj[]={getQuery(),getParkingPlaceNum(),getParkingLotid(),getFloorNum(),
				  getLineNum()};
	return obj;
	}
}
