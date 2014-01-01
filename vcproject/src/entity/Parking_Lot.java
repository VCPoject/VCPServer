package entity; 

public class Parking_Lot  {
	private Integer idparkinglot;
	private Integer width;
	private Integer depth;
	private Integer hight;
	private Integer altparkinglot;
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

	
	public int getIdparkinglot() {
		return idparkinglot;
	}

	public void setIdparkinglot(Integer idparkinglot) {
		this.idparkinglot = idparkinglot;
	}

	public void setDepth(Integer depth) {
		this.depth= depth;
		
	}
	
	public int getDepth(){
		return depth;
	}
	
	public void setWidth(int width){
		this.width=width;
	}
	
	public int getWidth(){
		return width;
	}

	public void setHight(int hight) {
		this.hight=hight;
		
	}
	
	public int getHight(){
		return hight;
	}

	public Integer getAltparkinglot() {
		return altparkinglot;
	}

	public void setAltparkinglot(int altparkinglot) {
		this.altparkinglot = altparkinglot;
	}
}
