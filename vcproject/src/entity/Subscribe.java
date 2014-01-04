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

	public String getSubscribType() {
		return subscribType;
	}

	public void setSubscribType(String subscribType) {
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
		if (getSubscribType().equals("Partial")) {
			Object[] obj = { getQuery(), getIdClient(),
					getCarNum(), getIdparking(), getStartDate(),
					getSubscribType(), getCustomerType(), getDepartureTime() };
			return obj;
		} else {
			Object[] obj = { getQuery(), getIdClient(),
					getCarNum(), getStartDate(), getSubscribType(),
					getCustomerType() };
			return obj;
		}
	}

}
