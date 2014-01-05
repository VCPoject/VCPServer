package entity;

public class Order implements Entity {
	private Integer carNum;
	private Integer idclient;
	private Integer idorder;
	private Integer idparking;
	private String arrivalDate;
	private String arrivalTime;
	private String departureDate;
	private String departureTime;
	private String checkInDate;
	private String checkInTime;
	private String checkOutDate;
	private String checkOutTime;
	private String status;
	private String type;
	private String query;

	public Order() {

	}

	public Integer getCar() {
		return carNum;
	}

	public void setCar(Integer carNum) {
		this.carNum = carNum;
	}

	public Integer getClient() {
		return idclient;
	}

	public void setClient(Integer idclient) {
		this.idclient = idclient;
	}

	public Integer getIdparking() {
		return idparking;
	}

	public void setIdparking(Integer idparking) {
		this.idparking = idparking;
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

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIdorder() {
		return idorder;
	}

	public void setIdorder(Integer idorder) {
		this.idorder = idorder;
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
		if (this.getType().equals("temp")) {
			Object[] obj = { getQuery(), getCar(), getClient(),
					getIdparking(), getArrivalDate(), getArrivalTime(),getDepartureDate(),getDepartureTime(),
					getStatus(), getType() };
			return obj;
		} else if(this.getType().equals("one time")) {
			Object[] obj = { getQuery(), getCar(), getClient(),
					getIdparking(), getArrivalDate(), getArrivalTime(),
					getDepartureDate(), getDepartureTime(),
					getStatus(), getType() };
			return obj;
		}
		
		return null;
	}

}
