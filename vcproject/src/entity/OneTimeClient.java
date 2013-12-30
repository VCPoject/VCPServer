package entity;

public class OneTimeClient extends ClientEntity {

	
	public OneTimeClient() {
		super();
	}

	@Override
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
