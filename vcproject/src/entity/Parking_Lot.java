package entity; 

public class Parking_Lot  {
	private Integer idparkinglot;
	private Integer width;
	private Integer depth;
	private Integer hight;
	private String status;
	
	public Parking_Lot(){
		
	}

	public Integer getWidth() {
		return width;
	}

	public Integer getDepth() {
		return depth;
	}

	public Integer getHight() {
		return hight;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIdparkinglot() {
		return idparkinglot;
	}

	public void setIdparkinglot(Integer idparkinglot) {
		this.idparkinglot = idparkinglot;
	}

	public void setDepth(Integer depth) {
		this.depth= depth;
		
	}
	
	public void setWidth(Integer width){
		this.width=width;
	}

	public void setHight(Integer hight) {
		this.hight=hight;
		
	}
}
