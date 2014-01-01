package entity;

public class ClientEntity extends SystemUser {

	private Integer idClient;

	public ClientEntity() {
		super();
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}
	
	public Object[] toObject() {
		if (getFirstName() != null && getLastName() != null) {
			Object[] obj = {getQuery(), getIdClient(), getFirstName(), getLastName(),
					getEmail() };
			return obj;
		} else {
			Object[] obj = {getQuery(), getIdClient(), getEmail() };
			return obj;
		}

	}

}
