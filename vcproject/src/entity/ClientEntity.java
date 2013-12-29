package entity;

public class ClientEntity extends SystemUser {

	private int idClient;
	private Car car;

	public ClientEntity() {
		super();
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public Car getCar() {
		return car;
	}

	public void setCarNum(Car car) {
		this.car = car;
	}

	@Override
	public Object[] toObject() {
		if (getFirstName() != null && getLastName() != null) {
			Object[] obj = {getQuery(), getIdClient(), getFirstName(), getLastName(),
					getEmail() };
			return obj;
		} else {
			Object[] obj = {getQuery(), getIdClient(), getEmail() };
			return obj;
		}

	}

}
