package entity;

public class Car implements Entity {

	private Integer carNum;
	private Integer idclient;
	private String query;
	
	
	public Car() {
		super();
	}
	
	public Integer getCarNum() {
		return carNum;
	}

	public void setCarNum(Integer carNum) {
		this.carNum = carNum;
	}

	public Integer getClient() {
		return this.idclient;
	}

	public void setClient(Integer client) {
		this.idclient = client;
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
		Object[] obj = {getQuery(),getCarNum(),getClient()};
		return obj;
	}
	
	public boolean equals(Car car) {
		if(car.getCarNum().equals(this.getCarNum()) && car.getClient().equals(this.getClient()))
			return true;
		return false;
		
	}

}
