package entity;

public abstract class ClientEntity extends SystemUser {

	private Integer idClient;
	private Car car;

	public ClientEntity() {
		super();
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public Car getCar() {
		return car;
	}

	public void setCarNum(Car car) {
		this.car = car;
	}

}
