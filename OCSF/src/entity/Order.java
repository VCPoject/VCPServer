package entity;

public class Order {
	
	private Integer idorder;
	private String firstName;
	private String lastName;
	private String arrivalDate;
	private String arrivalTime;
	private String email;
	
	public Order() {
		super();
	}

	public Integer getIdorder() {
		return idorder;
	}

	public void setIdorder(Integer idorder) {
		this.idorder = idorder;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
