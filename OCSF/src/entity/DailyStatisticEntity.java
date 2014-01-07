package entity;

public class DailyStatisticEntity implements Entity {

	private Integer statisticNum;
	private Integer implementOrders;
	private Integer canceledOrders;
	private Integer memberCount;
	private Integer memberCarsCount;
	private Integer lateCount;
	private String date;
	private String query;
	
	@Override
	public String getQuery() {
		return query;
	}

	@Override
	public void setQuery(String command) {
		this.query = command;
	}

	public Integer getStatisticNum() {
		return statisticNum;
	}

	public void setStatisticNum(Integer statisticNum) {
		this.statisticNum = statisticNum;
	}

	public Integer getImplementOrders() {
		return implementOrders;
	}

	public void setImplementOrders(Integer implementOrders) {
		this.implementOrders = implementOrders;
	}

	public Integer getCanceledOrders() {
		return canceledOrders;
	}

	public void setCanceledOrders(Integer canceledOrders) {
		this.canceledOrders = canceledOrders;
	}

	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	public Integer getMemberCarsCount() {
		return memberCarsCount;
	}

	public void setMemberCarsCount(Integer memberCarsCount) {
		this.memberCarsCount = memberCarsCount;
	}

	public Integer getLateCount() {
		return lateCount;
	}

	public void setLateCount(Integer lateCount) {
		this.lateCount = lateCount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public Object[] toObject() {
		Object[] obj = {getQuery(),getStatisticNum(),getImplementOrders(),getCanceledOrders(),getMemberCount(),getMemberCarsCount(),getLateCount(),getDate()};
		return obj;
	}

}
