package entity;

public interface Entity {
	/**
	 * @return the query to to be send to DB
	 */
	public String getQuery();
	/**
	 * setQuery for sent query to be send to DB
	 * @param command is the query to send to DB
	 */
	public void setQuery(String command);
	/**
	 * toObject is convert all info of entity to array
	 * @return all the info of the entity
	 */
	public Object[] toObject();
}
