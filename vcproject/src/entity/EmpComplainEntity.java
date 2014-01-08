package entity;


public class EmpComplainEntity implements Entity {
	private String query;
	private String status;
 
	public String getQuery() {
		
		return query;
	}

 
	public void setQuery(String command) {
		this.query=command;
		
	}

 
	public Object[] toObject() {
		Object[] obj = {getQuery(), getStatus()};
		return obj;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
