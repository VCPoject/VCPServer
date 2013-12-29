package entity;

public class Parking_Lot {
	private int idparkinglot;
	private int width;
	private int depth;
	private int hight;
	private int idorder;
	private String status;
	
	public Parking_Lot(){
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIdorder() {
		return idorder;
	}

	public void setIdorder(int idorder) {
		this.idorder = idorder;
	}


	public int getIdparkinglot() {
		return idparkinglot;
	}

	public void setIdparkinglot(int idparkinglot) {
		this.idparkinglot = idparkinglot;
	}

	public void setDepth(int depth) {
		this.depth= depth;
		
	}
	
	public void setWidth(int width){
		this.width=width;
	}

	public void setHight(int hight) {
		this.hight=hight;
		
	}
}
