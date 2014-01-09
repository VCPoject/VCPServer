package controler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;

import entity.ManagerStatsEntity;

public class ManagerStats extends Controller {
	
	public class Node{
		int data;
		int count;
	}
	
	
	private ArrayList<Node> brr= new ArrayList<Node>();
	private ArrayList<Integer> impOrder= new ArrayList<Integer>();
	private ArrayList<Integer>cancelOrder= new ArrayList<Integer>();
	private ArrayList<Integer>memberCount= new ArrayList<Integer>();
	private ArrayList<Integer>memHasMorCar= new ArrayList<Integer>();
	private ArrayList<Integer>lateCount= new ArrayList<Integer>();
	private ManagerStatsEntity statsEntity;
	public ManagerStats(){
		super();
		statsEntity= new ManagerStatsEntity();
	}
	
	protected void getRowData(){
		
		Object[] obj ={"SELECT * FROM vcp_employ.daily_statistic WHERE date < ? AND date > ?;"};
		sendQueryToServer(obj);
		for(int i=0;i<getResult().size();i++)
		{
			i++;
			impOrder.add((int) getResult().get(i++));
			cancelOrder.add((int) getResult().get(i++));
			memberCount.add((int) getResult().get(i++));
			memHasMorCar.add((int) getResult().get(i++));
			lateCount.add((int) getResult().get(i));
		}
	}
	
	
	public float MakeMedian(ArrayList<Integer> arr) {

		if (arr.size() % 2 == 1)
			return((arr.get(arr.size()/2)+1)); 
		return(((arr.get(arr.size()/2+1))+(arr.get(arr.size()/2)))/2);

	}

	public float MakeAverage(ArrayList<Integer> arr) {
		int sum = 0, average = 0;
		for (int i = 0; i < arr.size(); i++)
			sum += arr.get(i);
		average = sum / arr.size() ;
		return(average);
	}
	
	public int[] Incidence(ArrayList<Integer> arr) {
		for (int i = 0; i <brr.size() ; i++) 
			for(int j=0;j <arr.size();j++){
				if(arr.get(j)==brr.get(i).data)
					brr.get(i).count++;
				else 
				{
					Node e = new Node();
					e.data=arr.get(j);
					e.count=0;
					brr.add(e);
				}
					
			}
		int[] a= new int[brr.size()];
		for(int i=0;i<brr.size();i++)
			a[i]=brr.get(i).count;
		return(a);
	}
	public double StiatTekken(ArrayList<Integer> arr){
		
		int sum = 0;
		for(int i=0;i<arr.size();i++)
		sum = sum + arr.get(i);
		float avg = sum/arr.size();
		double temp=0;
		for(int i=0;i<arr.size();i++)
		 temp = temp + (arr.get(i)-avg)*(arr.get(i)-avg);
		temp = temp/arr.size();
		return(temp);
	}
	

	public Vector<Object> CalculetedNotWork(){
		
		long startSum=0;
		long endSum=0;
		Timestamp startDate;
		Timestamp endDate;
		ArrayList<Long> start = new ArrayList<Long>();
		Object[] obj ={"SELECT startDate,endDate FROM vcp_db.not_working_places WHERE endDate < ? AND startDate > ?;"};
		sendQueryToServer(obj);
		if(!getResult().get(0).equals("No Result"))
			for(int i=0;i>getResult().size();i++){
				startDate=(Timestamp)getResult().get(i++);
				endDate = (Timestamp)getResult().get(i);
				startSum= startSum + startDate.getTime();
				endSum= endSum + endDate.getTime();
				if(i%7==0){
					start.add((endSum-startSum)/(60 * 60 * 1000));
				}
				if(i<=7){
					start.add((endSum-startSum)/(60 * 60 * 1000));
				}
			}
		Vector<Object> row=new Vector<Object>(start.size());
		Collections.addAll(row, start);
		return row; 
	}
	
	public Vector<String> obtainFieldsForNotWork(){
		Vector<String> s= new Vector<String>(impOrder.size());
		for (int i=0;i<impOrder.size();i++)
		{
			String str= "Week No' " + i;
			s.add(str);
		}

		return s;
	}
	
	public Vector<Vector<Object>> ActivityReport(Date start,Date end){
		getRowData();
		Vector<Vector<Object>> result = new Vector<Vector<Object>>();
		Vector<Object> row=new Vector<Object>(12);
		row.add(MakeMedian(impOrder));
		row.add(StiatTekken(impOrder));
		Collections.addAll(row, StiatTekken(impOrder));
		result.add(row);
		row=new Vector<Object>(12);
		row.add(MakeMedian(cancelOrder));
		row.add(StiatTekken(cancelOrder));
		Collections.addAll(row, StiatTekken(cancelOrder));
		result.add(row);	

		return result;
	}

}
