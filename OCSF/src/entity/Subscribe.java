package entity;

public class Subscribe {
	
	private String firstName;
	private String lastName;
	private String email;
	private Integer subscribeNum;
	private String endDate;

	public Subscribe() {
		super();
	}

	public Integer getSubscribeNum() {
		return subscribeNum;
	}

	public void setSubscribeNum(Integer subscribeNum) {
		this.subscribeNum = subscribeNum;
	}
	
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
