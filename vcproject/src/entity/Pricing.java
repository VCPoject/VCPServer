package entity;

public class Pricing implements Entity {

	private float occasional;
	private float oneTime;
	private String query;
	
	public float getOccasional() {
		return occasional;
	}

	public void setOccasional(float occasional) {
		this.occasional = occasional;
	}

	public float getOneTime() {
		return oneTime;
	}

	public void setOneTime(float oneTime) {
		this.oneTime = oneTime;
	}

	public float getPartiallyOneCar() {
		return 60*getOneTime();
	}

	public float getPartially(int numOfCars) {
		return (float) (60*getOneTime()*numOfCars*0.9);
	}

	public float getFullOneCar() {
		return 70*getOneTime();
	}

	public float getFull(int numOfCars) {
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
		return null;
	}

}
