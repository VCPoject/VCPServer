package controler;


import java.util.Vector;


public class WeeklyStats extends Controller {





	public WeeklyStats(String host, int port) {
		super();


	}
	

	
	public Vector<Vector<Object>> toVector() {
		Vector<Vector<Object>> result = new Vector<Vector<Object>>();
		Vector<Object> row=new Vector<Object>(13);
		
		Object[] obj ={"SELECT name,avg,median,decile1,decile2,decile3,decile4,decile5,decile6,decile7,decile8,decile9,decile10"
				+ " FROM vcp_employ.weekly_reports ORDER BY date DESC;"};
		sendQueryToServer(obj);
		int j=1;
		for(int i=0;i<getResult().size();i++){
			System.out.print(getResult().get(i).toString()+ " ");
			if(!getResult().get(i).equals("no value"))
				row.add(getResult().get(i));
			else
				row.add("0");
			if(j%13==0){
				result.add(row);
				row = new Vector<Object>(13);
				
			}
			j++;
			if(j%(13*6)==0)
				break;
		}

		return result;
	}
	
	
	public Vector<String> obtainFields(){
		Vector<String> s= new Vector<String>(12);
		s.add(" ");
		s.add("Avarage");
		s.add("Median");
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
	
	




}