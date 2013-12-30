package entity;

public class ComplainEntity {
	private String complainNum;
	private int idNum;
	private int carNum;
	private String description;
	
	
	public ComplainEntity ()
	{
		
	}
	

	public String getComplainNum() {
		return complainNum;
	}
	
	public int getidNum() {
		return idNum;
	}
	
	public int getcarNum() {
		return carNum;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setComplainNum(String complainNum) {
		this.complainNum=complainNum;
	}
	
	public void setidNum(int idNum) {
		this.idNum=idNum;
	}
	
	public void setcarNum(int carNum) {
		this.carNum=carNum;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}
	
}
