package entity;

public class Parking_Places implements Entity {
	private int idparkinglot;
	private int floor;
	private int row;
	private int column;
	private int idorder;
	private String status;
	private String query; 
	private int subscribeNum;
	
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

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getIdparkinglot() {
		return idparkinglot;
	}

	public void setIdparkinglot(int idparkinglot) {
		this.idparkinglot = idparkinglot;
	}

	@Override
	public String getQuery() {
		return query;
	}

	@Override
	public void setQuery(String command) {
		this.query = command;
	}

	@Override
	public Object[] toObject() {
		Object[] toSend = {getQuery(),getIdorder(),getFloor(),getRow(),getColumn(),getStatus()};
		return toSend;
	}

	public int getSubscribeNum() {
		return subscribeNum;
	}

	public void setSubscribeNum(int subscribeNum) {
		this.subscribeNum = subscribeNum;
	}
}
