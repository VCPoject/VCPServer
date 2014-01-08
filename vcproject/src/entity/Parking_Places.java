package entity;

public class Parking_Places implements Entity {
	private Integer idparkinglot;
	private Integer floor;
	private Integer row;
	private Integer column;
	private Integer idorder;
	private Integer subscribeNum;
	private String status;
	private String query; 
	
	public Parking_Places(){
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIdorder() {
		return idorder;
	}

	public void setIdorder(Integer idorder) {
		this.idorder = idorder;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	public Integer getIdparkinglot() {
		return idparkinglot;
	}

	public void setIdparkinglot(Integer idparkinglot) {
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

	

	public void setSubscribeNum(Integer subscribeNum) {
		this.subscribeNum = subscribeNum;
	}

	@Override
	public Object[] toObject() {
		if(getQuery().contains("UPDATE")){
			Object[] toSend = {getQuery(),getIdorder(),getSubscribeNum(),getStatus(),getIdparkinglot(),getFloor(),getRow(),getColumn()};
			return toSend;
		}
		
		Object[] toSend = {getQuery(),getIdorder(),getSubscribeNum(),getFloor(),getRow(),getColumn(),getStatus()};
		return toSend;
	}

	public int getSubscribeNum() {
		return subscribeNum;
	}

	public void setSubscribeNum(int subscribeNum) {
		this.subscribeNum = subscribeNum;
	}
}
