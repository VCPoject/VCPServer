package entity;

public class FinancialCard implements Entity {

	private String query;
	private ClientEntity client;
	private Float amount;
	
	@Override
	public String getQuery() {
		return query;
	}

	@Override
	public void setQuery(String command) {
		this.query = command;

	}

	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Override
	public Object[] toObject() {
		Object[] obj = {getQuery(),getClient().getIdClient(),getAmount() };
		return obj;
	}

}
