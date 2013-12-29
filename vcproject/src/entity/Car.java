package entity;

public class Car implements Entity {

	private int carNum;
	private ClientEntity client;
	private String query;
	
	
	public Car() {
		super();
	}
	
	public int getCarNum() {
		return carNum;
	}

	public void setCarNum(int carNum) {
		this.carNum = carNum;
	}

	public ClientEntity getClient() {
		return this.client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
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
		Object[] obj = {getQuery(),getCarNum(),getClient().getIdClient()};
		return obj;
	}

}
