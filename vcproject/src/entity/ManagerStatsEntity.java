package entity;

import java.util.ArrayList;

import controler.ManagerStats.Node;

public class ManagerStatsEntity {
	private float median;
	private float avg;
	private ArrayList<Node> incidence;
	private double StiatTekken;
	
	
	public double getStiatTekken() {
		return StiatTekken;
	}
	public void setStiatTekken(double stiatTekken) {
		StiatTekken = stiatTekken;
	}
	public ArrayList<Node> getIncidence() {
		return incidence;
	}
	public void setIncidence(ArrayList<Node> brr) {
		this.incidence = brr;
	}
	public float getMedian() {
		return median;
	}
	public void setMedian(float median) {
		this.median = median;
	}
	public float getAvg() {
		return avg;
	}
	public void setAvg(float avg) {
		this.avg = avg;
	}

}
