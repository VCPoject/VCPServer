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
	private ArrayList<Integer> NotWork = new ArrayList<Integer>();
	private ManagerStatsEntity statsEntity;
	public ManagerStats(){
		super();
		statsEntity= new ManagerStatsEntity();
	}
	
	protected void getRowData(Date start,Date end){
		Timestamp startDate = new Timestamp(start.getTime());
		Timestamp endDate = new Timestamp(end.getTime());
		Object[] obj ={"SELECT * FROM vcp_employ.daily_statistic WHERE date < ? AND date > ?;",endDate.toString(),startDate.toString()};
		sendQueryToServer(obj);
		for(int i=0;i<getResult().size();i++)
		{
			i++;
			impOrder.add(Integer.parseInt(getResult().get(i++).toString()));
			cancelOrder.add(Integer.parseInt(getResult().get(i++).toString()));
			memberCount.add(Integer.parseInt(getResult().get(i++).toString()));
			memHasMorCar.add(Integer.parseInt(getResult().get(i++).toString()));
			lateCount.add(Integer.parseInt(getResult().get(i).toString()));
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
	

	public ArrayList<Integer> CalculetedNotWork(Date s,Date e){
		Timestamp startDate = new Timestamp(s.getTime());
		Timestamp endDate = new Timestamp(e.getTime());
		int i=0;
		long startSum=0;
		long endSum=0;
		Object[] obj ={"SELECT startDate,endDate FROM vcp_db.not_working_places WHERE endDate < ? AND startDate > ?;",endDate.toString(),startDate.toString()};
		sendQueryToServer(obj);
		if(!getResult().get(0).equals("No Result"))
			for(i=0;i>getResult().size();i++){
				startDate=(Timestamp)getResult().get(i++);
				endDate = (Timestamp)getResult().get(i);
				startSum= startSum + startDate.getTime();
				endSum= endSum + endDate.getTime();
				if(i%7==0){
					Long l = (endSum-startSum)/(60 * 60 * 1000);
					NotWork.add(Integer.parseInt(l.toString()));
				}
			}
		if(i<=7){
			Long l = (endSum-startSum)/(60 * 60 * 1000);
			NotWork.add(Integer.parseInt(l.toString()));
		}
		
		return NotWork; 
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
		getRowData(start,end);
		CalculetedNotWork(start,end);
		Vector<Vector<Object>> result = new Vector<Vector<Object>>();
		Vector<Object> row=new Vector<Object>(12);
		row.add(MakeMedian(impOrder));
		row.add(StiatTekken(impOrder));
		Collections.addAll(row, Incidence(impOrder));
		result.add(row);
		row=new Vector<Object>(12);
		row.add(MakeMedian(cancelOrder));
		row.add(StiatTekken(cancelOrder));
		Collections.addAll(row, Incidence(cancelOrder));
		result.add(row);	
		row=new Vector<Object>(12);
		row.add(MakeMedian(NotWork));
		row.add(StiatTekken(NotWork));
		Collections.addAll(row, Incidence(NotWork));
		result.add(row);	
		return result;
	}
	
	public Vector<String> obtainFields(){
		Vector<String> s= new Vector<String>(12);
		s.add("MakeMedian");
		s.add("Standard deviation");
		s.add("Frequency Distribution Decile1");
		s.add("Frequency Distribution Decile2");
		s.add("Frequency Distribution Decile3");
		s.add("Frequency Distribution Decile4");
		s.add("Frequency Distribution Decile5");
		s.add("Frequency Distribution Decile6");
		s.add("Frequency Distribution Decile7");
		s.add("Frequency Distribution Decile8");
		s.add("Frequency Distribution Decile9");
		s.add("Frequency Distribution Decile10");
		return s;
	}

}
