package entity;

import java.sql.Timestamp;

public class WeeklyStatsEntity {
	
	private String query;
	private Timestamp date;
	private Timestamp oldDate;
	private int[] impOrder;
	private int[] cancelOrder;
	private int[] memberCount;
	private int[] memHasMorCar;
	private int[] lateCount;
	private int[] hitpalgutimpOrder ;
	private int[] hitpalgutcancelOrder;
	private int[] hitpalgutmemberCount;
	private int[] hitpalgutmemHasMorCar;
	private int[] hitpalgutlateCount;
	private float avg;
	private float median;
	
	public float getAvg() {
		return avg;
	}


	public void setAvg(float avg) {
		this.avg = avg;
	}


	public float getMedian() {
		return median;
	}


	public void setMedian(float median) {
		this.median = median;
	}


	public int[] getImpOrder() {
		return impOrder;
	}


	public int[] getHitpalgutimpOrder() {
		return hitpalgutimpOrder;
	}


	public void setHitpalgutimpOrder(int[] hitpalgutimpOrder) {
		this.hitpalgutimpOrder = hitpalgutimpOrder;
	}


	public int[] getHitpalgutcancelOrder() {
		return hitpalgutcancelOrder;
	}


	public void setHitpalgutcancelOrder(int[] hitpalgutcancelOrder) {
		this.hitpalgutcancelOrder = hitpalgutcancelOrder;
	}


	public int[] getHitpalgutmemberCount() {
		return hitpalgutmemberCount;
	}


	public void setHitpalgutmemberCount(int[] hitpalgutmemberCount) {
		this.hitpalgutmemberCount = hitpalgutmemberCount;
	}


	public int[] getHitpalgutmemHasMorCar() {
		return hitpalgutmemHasMorCar;
	}


	public void setHitpalgutmemHasMorCar(int[] hitpalgutmemHasMorCar) {
		this.hitpalgutmemHasMorCar = hitpalgutmemHasMorCar;
	}


	public int[] getHitpalgutlateCount() {
		return hitpalgutlateCount;
	}


	public void setHitpalgutlateCount(int[] hitpalgutlateCount) {
		this.hitpalgutlateCount = hitpalgutlateCount;
	}


	public void setImpOrder(int[] impOrder) {
		this.impOrder = impOrder;
	}


	public int[] getCancelOrder() {
		return cancelOrder;
	}


	public void setCancelOrder(int[] cancelOrder) {
		this.cancelOrder = cancelOrder;
	}


	public int[] getMemberCount() {
		return memberCount;
	}


	public void setMemberCount(int[] memberCount) {
		this.memberCount = memberCount;
	}


	public int[] getMemHasMorCar() {
		return memHasMorCar;
	}


	public void setMemHasMorCar(int[] memHasMorCar) {
		this.memHasMorCar = memHasMorCar;
	}


	public int[] getLateCount() {
		return lateCount;
	}


	public void setLateCount(int[] lateCount) {
		this.lateCount = lateCount;
	}


 
	public String getQuery() {
		
		return query;
	}

 
	public void setQuery(String command) {
		this.query=command;
		
	}

 
	public Object[] toObject() {
		Object[] obj = {getQuery(), getDate().toString(),getoldDate().toString()};
		return obj;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public Timestamp getoldDate() {
		return oldDate;
	}

	public void setoldDate(Timestamp oldDateCalc) {
		long sevenDays = 1 * 24 * 60 * 60 * 1000*7;
		oldDateCalc.setTime(oldDateCalc.getTime() - sevenDays);
		this.oldDate = oldDateCalc;
	}

}



