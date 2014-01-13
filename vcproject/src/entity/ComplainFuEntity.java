package entity;
/**
 * entity for complain follow up
 * @author H_B
 *
 */
public class ComplainFuEntity {
	private String Complains;
	private String status;
	private String query; 
	private String description;
	private int idNum;

	public void setDescription(String description) {
		this.description = description;
	}
	
	private String getDescription() {
		return description;
	}
	
	public int getIdNum() {
		return idNum;
	}
	
	private Object getId() {

		return null;
	}

	public void setIdNum(int idNum) {
		this.idNum = idNum;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	private String getStatus() {
		return status;
	}

	public String getComplains() {
		return Complains;
	}

	public void setComplains(String complains) {
		this.Complains = complains;
	}
	
	public Object[] toObject() {
		Object[] toSend = {getQuery(),getComplains(),getDescription(),getId(),getStatus()};
		return toSend;
	}
	
	public void setQuery(String query) {
		this.query=query;
	}

	public String getQuery() {
		return query;
	}
}
