package entity;

public class Car implements Entity {

	private int carNum;
	private int idClient;
	private String query;
	
	
	public Car(int carNum, int idClient) {
		super();
		this.carNum = carNum;
		this.idClient = idClient;
	}
	
	public int getCarNum() {
		return carNum;
	}

	public void setCarNum(int carNum) {
		this.carNum = carNum;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
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
		// TODO Auto-generated method stub
		return null;
	}

}
