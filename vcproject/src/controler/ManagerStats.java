package controler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;


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
	private ArrayList<Integer> notWork = new ArrayList<Integer>();
	private ArrayList<Integer> lateToPark = new ArrayList<Integer>();
	private ArrayList<Integer> abortedParking = new ArrayList<Integer>();

	private VcpInfo vcpInfo;
	public ManagerStats(String host, int port,VcpInfo vcpInfo){
		super();

		this.vcpInfo=vcpInfo;
	}
	
	protected void getRowData(Date start,Date end){
		Timestamp startDate = new Timestamp(start.getTime());
		Timestamp endDate = new Timestamp(end.getTime());
		Object[] obj ={"SELECT * FROM vcp_employ.daily_statistic WHERE date < ? AND date > ?;",endDate.toString(),startDate.toString()};
		sendQueryToServer(obj);
		for(int i=1;i<getResult().size();i++)
		{
			
			impOrder.add(Integer.parseInt(getResult().get(i).toString()));
			i++;
			cancelOrder.add(Integer.parseInt(getResult().get(i).toString()));
			i++;
			memberCount.add(Integer.parseInt(getResult().get(i).toString()));
			i++;
			memHasMorCar.add(Integer.parseInt(getResult().get(i).toString()));
			i++;
			lateCount.add(Integer.parseInt(getResult().get(i).toString()));
			i++;
			i++;
		}
		Object[] lateTOParking = {"SELECT COUNT(idorder) FROM vcp_db.order WHERE checkInTime > arrivalTime"
				+ " GROUP BY idparking ORDER BY idparking;"};
		sendQueryToServer(lateTOParking);
		if(!getResult().get(0).equals("No Result")){}
		for(int i=0;i<getResult().size();i++)
			lateToPark.add(Integer.parseInt(getResult().get(i++).toString()));
		Object[] ParkingAboted = {"SELECT COUNT(idorder) FROM vcp_db.order WHERE status ='Aborted' "
				+ "GROUP BY idparking ORDER BY idparking;"};
			sendQueryToServer(ParkingAboted);
			if(!getResult().get(0).equals("No Result")){}
			for(int i=0;i<getResult().size();i++)
			abortedParking.add(Integer.parseInt(getResult().get(i++).toString()));
	}
	
	
	public float MakeMedian(ArrayList<Integer> arr) {

		if (arr.size() % 2 == 1)
			return(((arr.get(arr.size()-1)/2)+1)); 
		return((((arr.get(arr.size()-1)/2+1))+(arr.get((arr.size()-1)/2)))/2);

	}

	public float MakeAverage(ArrayList<Integer> arr) {
		int sum = 0, average = 0;
		for (int i = 0; i < arr.size(); i++)
			sum += arr.get(i);
		average = sum / arr.size() ;
		return(average);
	}
	
	public int[] Incidence(ArrayList<Integer> arr) {
		boolean flag=true;
		brr= new ArrayList<Node>();
			for (int i = 0; i <arr.size() ; i++) {
			for(int j=0;j <brr.size();j++){
					if(arr.get(i)==brr.get(j).data){
					brr.get(j).count++;
					flag=false;
					}
					}		
			if(flag){
			Node e = new Node();
			e.data=arr.get(i);
			e.count=0;
			brr.add(e);
			}
			flag=true;
		}
		int[] a= new int[10];
		for(int i=0;i<brr.size();i++){
			a[brr.get(i).data/10]++;
			
		}
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
		temp = Math.sqrt(temp)/arr.size();
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
					notWork.add(Integer.parseInt(l.toString()));
				}
			}
		if(i<=7){
			Long l = (endSum-startSum)/(60 * 60 * 1000);
			notWork.add(Integer.parseInt(l.toString()));
		}
		
		return notWork; 
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
		int[] temp;
		Vector<Vector<Object>> result = new Vector<Vector<Object>>();
		Vector<Object> row=new Vector<Object>(12);
		row.add("Implemented Oreders");
		row.add(MakeMedian(impOrder));
		row.add(StiatTekken(impOrder));
		temp = Incidence(impOrder);
		for(int i = 0 ;i<temp.length;i++)
			row.add(temp[i]);
		result.add(row);
		row=new Vector<Object>(12);
		row.add("Canceld Orders");
		row.add(MakeMedian(cancelOrder));
		row.add(StiatTekken(cancelOrder));
		temp = Incidence(cancelOrder);
		for(int i = 0 ;i<temp.length;i++)
			row.add(temp[i]);
		result.add(row);	
		row=new Vector<Object>(12);
		row.add("Not Working Parking");
		row.add(MakeMedian(notWork));
		row.add(StiatTekken(notWork));
		temp = Incidence(notWork);
		for(int i = 0 ;i<temp.length;i++)
			row.add(temp[i]);
		result.add(row);	
		return result;
	}
	
	public Vector<String> obtainFields(){
		Vector<String> s= new Vector<String>(12);
		s.add(" ");
		s.add("Median");
		s.add("sd");
		s.add("Decile1");
		s.add("Decile2");
		s.add("Decile3");
		s.add("Decile4");
		s.add("Decile5");
		s.add("Decile6");
		s.add("Decile7");
		s.add("Decile8");
		s.add("Decile9");
		s.add("Decile10");
		return s;
	}
	
	
	
	public Vector<Vector<Object>> AbnormalWorking(){
		getRowData(new Date(0), new Date());
		int[] temp;
		Vector<Vector<Object>> result = new Vector<Vector<Object>>();
		Vector<Object> row=new Vector<Object>(13);
		row.add("Canceld Orders");
		row.add(MakeMedian(cancelOrder));
		row.add(StiatTekken(cancelOrder));
		temp = Incidence(cancelOrder);
		for(int i = 0 ;i<temp.length;i++)
			row.add(temp[i]);
		result.add(row);
		row=new Vector<Object>(13);
		row.add("Number of Late");
		row.add(MakeMedian(lateCount));
		row.add(StiatTekken(lateCount));
		temp = Incidence(lateCount);
		for(int i = 0 ;i<temp.length;i++)
			row.add(temp[i]);
		result.add(row);
		row=new Vector<Object>(13);
		row.add("Canceld Orders By Parking");
		row.add(MakeMedian(lateToPark));
		row.add(StiatTekken(lateToPark));
		temp = Incidence(lateToPark);
		for(int i = 0 ;i<temp.length;i++)
			row.add(temp[i]);
		result.add(row);
		row=new Vector<Object>(13);
		row.add("Aborted Orders By Parking");
		row.add(MakeMedian(abortedParking));
		row.add(StiatTekken(abortedParking));
		temp = Incidence(abortedParking);
		for(int i = 0 ;i<temp.length;i++)
			row.add(temp[i]);
		result.add(row);
		return result;
	}
	
	public Vector<Vector<Object>> Preformence(){
		
		int numOfSubscribed;
		int numWithMorThenOneCar;
		numOfSubscribed=vcpInfo.getAllSubscribed().size();
		Vector<Vector<Object>> result = new Vector<Vector<Object>>();
		Vector<Object> row=new Vector<Object>(2);
		
		Object[] obj={"SELECT count(idclient) AS id FROM "
		+ "(SELECT idclient FROM vcp_db.subscribe "
		+ "group by idclient HAVING count(carNum) >'1')"
		+ " AS clientWithMoreCar ;" };
		sendQueryToServer(obj);
		if(!getResult().get(0).equals("No Result")){}
		numWithMorThenOneCar=Integer.parseInt(getResult().get(0).toString());
		row.add("Number Of Subscribes");
		row.add(numOfSubscribed);
		result.add(row);
		row = new Vector<Object>(2);
		row.add("Num Of Subscribe With More then One Car");
		row.add(numWithMorThenOneCar);
		result.add(row);
		return result;
	}

}
