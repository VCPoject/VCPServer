package entity;

public class TempClient extends ClientEntity {

	
	
	public TempClient() {
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
