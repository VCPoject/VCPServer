package entity;

public interface Entity {
	public String getQuery();
	public void setQuery(String command);
	public Object[] toObject();
}
