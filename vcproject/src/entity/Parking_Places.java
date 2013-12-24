package entity;

public class Parking_Places {
	private int idparkinglot;
	private int floor;
	private int line;
	private int parkingNum;
	private int idorder;
	private String status;
	
	public Parking_Places(){
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIdorder() {
		return idorder;
	}

	public void setIdorder(int idorder) {
		this.idorder = idorder;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getparkingNum() {
		return parkingNum;
	}

	public void setparkingNum(int parkingNum) {
		this.parkingNum = parkingNum;
	}

	public int getIdparkinglot() {
		return idparkinglot;
	}

	public void setIdparkinglot(int idparkinglot) {
		this.idparkinglot = idparkinglot;
	}
}
