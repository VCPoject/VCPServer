package entity;

public class Pricing implements Entity {

	private Float occasional;
	private Float oneTime;
	private String query;
	
	public Float getOccasional() {
		return occasional;
	}

	public void setOccasional(Float occasional) {
		this.occasional = occasional;
	}

	public Float getOneTime() {
		return oneTime;
	}

	public void setOneTime(Float oneTime) {
		this.oneTime = oneTime;
	}

	public Float getPartiallyOneCar() {
		return 60*getOneTime();
	}

	public Float getPartially(Integer numOfCars) {
		return (float) (60*getOneTime()*numOfCars*0.9);
	}

	public Float getFullOneCar() {
		return 70*getOneTime();
	}

	public Float getFull(int numOfCars) {
		return 70*getOneTime()*numOfCars;
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
		Object[] obj = {getQuery(),getOccasional(),getOneTime()};
		return obj;
	}

}
