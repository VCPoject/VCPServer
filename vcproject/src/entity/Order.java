package entity;

public class Order implements Entity {
	private Car car;
	private ClientEntity client;
	private int idparking;
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

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}

	public int getIdparking() {
		return idparking;
	}

	public void setIdparking(int idparking) {
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
			Object[] obj = { getQuery(), getCar().getCarNum(), getClient().getIdClient(),
					getIdparking(), getArrivalDate(), getArrivalTime(),getDepartureDate(),getDepartureTime(),
					getStatus(), getType() };
			return obj;
		} else if(this.getType().equals("one time")) {
			Object[] obj = { getQuery(), getCar().getCarNum(), getClient().getIdClient(),
					getIdparking(), getArrivalDate(), getArrivalTime(),
					getDepartureDate(), getDepartureTime(),
					getStatus(), getType() };
			return obj;
		}
		return null;
	}

}
