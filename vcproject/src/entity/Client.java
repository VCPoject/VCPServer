package entity;

public abstract class Client extends SystemUser {
	
	private int idClient;
	private Car car;
	
	public Client() {
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
	public abstract Object[] toObject();

}
