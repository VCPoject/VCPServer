package entity;

public class FinancialCard implements Entity {

	private String query;
	private Integer idclient;
	private Float amount = (float) 0;
	
	@Override
	public String getQuery() {
		return query;
	}

	@Override
	public void setQuery(String command) {
		this.query = command;

	}

	public Integer getIdClient() {
		return idclient;
	}

	public void setIdClient(Integer idclient) {
		this.idclient = idclient;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Override
	public Object[] toObject() {
		if(getQuery().contains("INSERT")){
			Object[] obj = {getQuery(),getIdClient(),getAmount() };
			return obj;
		}else if(getQuery().contains("UPDATE")){
			Object[] obj = {getQuery(),getIdClient(),getAmount() };
			return obj;
		}else{
			Object[] obj = {getQuery(),getIdClient(),getAmount() };
			return obj;
		}
	}
}
