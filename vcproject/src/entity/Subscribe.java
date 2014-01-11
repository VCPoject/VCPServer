package entity;

public class Subscribe extends ClientEntity {

	private Integer subscribeNum;
	private Integer carNum;
	private Integer idparking;
	private Integer entriesDay;
	private String startDate;
	private String subscribType;
	private String customerType;
	private String departureTime;
	private String endDate;
	private String status;

	public Subscribe() {
		super();
	}

	public Integer getSubscribeNum() {
		return subscribeNum;
	}

	public void setSubscribeNum(Integer subscribeNum) {
		this.subscribeNum = subscribeNum;
	}

	public Integer getCarNum() {
		return carNum;
	}

	public void setCarNum(Integer carNum) {
		this.carNum = carNum;
	}

	public Integer getIdparking() {
		return idparking;
	}

	public void setIdparking(Integer idparking) {
		this.idparking = idparking;
	}

	public Integer getEntriesDay() {
		return entriesDay;
	}

	public void setEntriesDay(Integer entriesDay) {
		this.entriesDay = entriesDay;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getSubscribeType() {
		return subscribType;
	}

	public void setSubscribeType(String subscribType) {
		this.subscribType = subscribType;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	@Override
	public Object[] toObject() {
		if (getSubscribeType().equals("partially")) {
			Object[] obj = { getQuery(), getIdClient(),
					getCarNum(), getIdparking(), getStartDate(),getEndDate(),
					getSubscribeType(), getCustomerType(), getDepartureTime(),getStatus() };
			return obj;
		} else {
			Object[] obj = { getQuery(), getIdClient(),
					getCarNum(), getStartDate(),getEndDate(), getSubscribeType(),
					getCustomerType(),getStatus() };
			return obj;
		}
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
