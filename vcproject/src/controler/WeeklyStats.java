package controler;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;
import entity.WeeklyStatsEntity;

public class WeeklyStats extends Controller {

	private WeeklyStatsEntity weekEntity;

	
	
	//private float Incidence;

	public WeeklyStats(String host, int port) {
		super();
		weekEntity=new WeeklyStatsEntity();

	}
	
	public void getRowData(){
		int[] impOrder = new int[7];
		int[] cancelOrder= new int[7];
		int[] memberCount= new int[7];
		int[] memHasMorCar= new int[7];
		int[] lateCount= new int[7];
		int[] hitpalgutimpOrder = new int [10];
		int[] hitpalgutcancelOrder = new int [10];
		int[] hitpalgutmemberCount = new int [10];
		int[] hitpalgutmemHasMorCar = new int [10];
		int[] hitpalgutlateCount = new int [10];
		Date date= new Date();
		weekEntity.setoldDate(new Timestamp(date.getTime()));
		weekEntity.setQuery("SELECT * FROM vcp_employ.daily_statistic WHERE date < ? AND date > ?");
		weekEntity.setDate(new Timestamp(date.getTime()));
		sendQueryToServer(weekEntity.toObject());
		int j=0;
		for(int i=0;i<getResult().size();i++)
		{
			i++;
			impOrder[j]=(int) getResult().get(i);
			hitpalgutimpOrder[(int) getResult().get(i++)/10]++;
			cancelOrder[j]=(int) getResult().get(i);
			hitpalgutcancelOrder[(int) getResult().get(i++)/10]++;
			memberCount[j]=(int) getResult().get(i);
			hitpalgutmemberCount[(int) getResult().get(i++)/10]++;
			memHasMorCar[j]=(int) getResult().get(i);
			hitpalgutmemHasMorCar[(int) getResult().get(i++)/10]++;
			lateCount[j]=(int) getResult().get(i);
			hitpalgutlateCount[(int) getResult().get(i)/10]++;
			j++;
		}
		weekEntity.setImpOrder(impOrder);
		weekEntity.setCancelOrder(cancelOrder);
		weekEntity.setMemberCount(memberCount);
		weekEntity.setMemHasMorCar(memHasMorCar);
		weekEntity.setLateCount(lateCount);
		weekEntity.setHitpalgutimpOrder(hitpalgutimpOrder);
		weekEntity.setHitpalgutcancelOrder(hitpalgutcancelOrder);
		weekEntity.setHitpalgutmemberCount(hitpalgutmemberCount);
		weekEntity.setHitpalgutmemHasMorCar(hitpalgutmemHasMorCar);
		weekEntity.setHitpalgutlateCount(hitpalgutlateCount);
		updateDataBase();
	}

	public float MakeMedian(int arr[]) {

		if (arr.length % 2 == 1)
			return ((float)arr[((arr.length/2 )+1)]);
		return( (float)((arr[(arr.length/2 + 1)] + arr[(arr.length/2 )]) / 2));

	}

	public float MakeAverage(int  arr[]) {
		int sum = 0, average = 0;
		for (int i = 0; i < arr.length; i++)
			sum += arr[i];
		average = sum / arr.length ;
		return average;
	}
	
	
	private void updateDataBase(){
		
		Object[] obj = {"INSERT INTO vcp_employ.weekly_reports (name,avg,median,decile1,decile2,decile3,decile4,decile5,decile6,decile7,decile8,decile9,decile10) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)","recived_order",MakeAverage(weekEntity.getImpOrder()),MakeMedian(weekEntity.getImpOrder()),weekEntity.getHitpalgutimpOrder()[0],weekEntity.getHitpalgutimpOrder()[1],
				weekEntity.getHitpalgutimpOrder()[2],weekEntity.getHitpalgutimpOrder()[3],weekEntity.getHitpalgutimpOrder()[4],weekEntity.getHitpalgutimpOrder()[5],weekEntity.getHitpalgutimpOrder()[6],
						weekEntity.getHitpalgutimpOrder()[7],weekEntity.getHitpalgutimpOrder()[8],weekEntity.getHitpalgutimpOrder()[9]};
		sendQueryToServer(obj);	
		if(getResult().get(0).equals("done")){}
		Object[] obj1 = {"INSERT INTO vcp_employ.weekly_reports (name,avg,median,decile1,decile2,decile3,decile4,decile5,decile6,decile7,decile8,decile9,decile10) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)","cancel_order",MakeAverage(weekEntity.getCancelOrder()),MakeMedian(weekEntity.getCancelOrder()),weekEntity.getHitpalgutcancelOrder()[0],weekEntity.getHitpalgutcancelOrder()[1],
				weekEntity.getHitpalgutcancelOrder()[2],weekEntity.getHitpalgutcancelOrder()[3],weekEntity.getHitpalgutcancelOrder()[4],weekEntity.getHitpalgutcancelOrder()[5],weekEntity.getHitpalgutcancelOrder()[6],
						weekEntity.getHitpalgutcancelOrder()[7],weekEntity.getHitpalgutcancelOrder()[8],weekEntity.getHitpalgutcancelOrder()[9]};
		sendQueryToServer(obj1);	
		if(getResult().get(0).equals("done")){}
		Object[] obj2 = {"INSERT INTO vcp_employ.weekly_reports (name,avg,median,decile1,decile2,decile3,decile4,decile5,decile6,decile7,decile8,decile9,decile10) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)","member_count",MakeAverage(weekEntity.getMemberCount()),MakeMedian(weekEntity.getMemberCount()),weekEntity.getHitpalgutmemberCount()[0],weekEntity.getHitpalgutmemberCount()[1],
				weekEntity.getHitpalgutmemberCount()[2],weekEntity.getHitpalgutmemberCount()[3],weekEntity.getHitpalgutmemberCount()[4],weekEntity.getHitpalgutmemberCount()[5],weekEntity.getHitpalgutmemberCount()[6],
						weekEntity.getHitpalgutmemberCount()[7],weekEntity.getHitpalgutmemberCount()[8],weekEntity.getHitpalgutmemberCount()[9]};
		sendQueryToServer(obj2);
		if(getResult().get(0).equals("done")){}
		Object[] obj3 = {"INSERT INTO vcp_employ.weekly_reports (name,avg,median,decile1,decile2,decile3,decile4,decile5,decile6,decile7,decile8,decile9,decile10) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)","member_more_car",MakeAverage(weekEntity.getMemHasMorCar()),MakeMedian(weekEntity.getMemHasMorCar()),weekEntity.getHitpalgutmemHasMorCar()[0],weekEntity.getHitpalgutmemHasMorCar()[1],
				weekEntity.getHitpalgutmemHasMorCar()[2],weekEntity.getHitpalgutmemHasMorCar()[3],weekEntity.getHitpalgutmemHasMorCar()[4],weekEntity.getHitpalgutmemHasMorCar()[5],weekEntity.getHitpalgutmemHasMorCar()[6],
						weekEntity.getHitpalgutmemHasMorCar()[7],weekEntity.getHitpalgutmemHasMorCar()[8],weekEntity.getHitpalgutmemHasMorCar()[9]};
		sendQueryToServer(obj3);
		if(getResult().get(0).equals("done")){}
		Object[] obj4 = {"INSERT INTO vcp_employ.weekly_reports (name,avg,median,decile1,decile2,decile3,decile4,decile5,decile6,decile7,decile8,decile9,decile10) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)","mamber_late",MakeAverage(weekEntity.getLateCount()),MakeMedian(weekEntity.getLateCount()),weekEntity.getHitpalgutlateCount()[0],weekEntity.getHitpalgutlateCount()[1],
				weekEntity.getHitpalgutlateCount()[2],weekEntity.getHitpalgutlateCount()[3],weekEntity.getHitpalgutlateCount()[4],weekEntity.getHitpalgutlateCount()[5],weekEntity.getHitpalgutlateCount()[6],
						weekEntity.getHitpalgutlateCount()[7],weekEntity.getHitpalgutlateCount()[8],weekEntity.getHitpalgutlateCount()[9]};
		sendQueryToServer(obj4);	
		
		
	}
	
	public Vector<Vector<Object>> toVector() {
		Vector<Vector<Object>> result = new Vector<Vector<Object>>();
		Vector<Object> row=new Vector<Object>(12);
		/*
		Object[] obj ={"SELECT * FROM vcp_employ.weekly_statistic ORDER BY date;"};
		sendQueryToServer(obj);
		for(int i=2;i<getResult().size();i++){
			if(!getResult().get(i).equals("no value"))
				row.add(getResult().get(i));
			else
				row.add("0");
			if(i%13==0){
				result.add(row);
				row = new Vector<Object>(12);
				i=i+2;
			}
		}
		*/
		
		
		row.add(MakeAverage(weekEntity.getImpOrder()));
		row.add(MakeMedian(weekEntity.getImpOrder()));
		Collections.addAll(row, weekEntity.getHitpalgutimpOrder());
		result.add(row);
		row=new Vector<Object>(12);
		row.add(MakeAverage(weekEntity.getCancelOrder()));
		row.add(MakeMedian(weekEntity.getImpOrder()));
		Collections.addAll(row, weekEntity.getHitpalgutcancelOrder());
		result.add(row);
		row=new Vector<Object>(12);
		row.add(MakeAverage(weekEntity.getMemberCount()));
		row.add(MakeMedian(weekEntity.getImpOrder()));
		Collections.addAll(row, weekEntity.getHitpalgutmemberCount());
		result.add(row);
		row=new Vector<Object>(12);
		row.add(MakeAverage(weekEntity.getMemHasMorCar()));
		row.add(MakeMedian(weekEntity.getImpOrder()));
		Collections.addAll(row, weekEntity.getHitpalgutmemHasMorCar());
		result.add(row);
		row=new Vector<Object>(12);
		row.add(MakeAverage(weekEntity.getLateCount()));
		row.add(MakeMedian(weekEntity.getImpOrder()));
		Collections.addAll(row, weekEntity.getHitpalgutlateCount());
		result.add(row);
		return result;
	}
	
	
	public Vector<String> obtainFields(){
		Vector<String> s= new Vector<String>(5);
		s.add("recived_order");
		s.add("cancel_order");
		s.add("member_count");
		s.add("member_more_car");
		s.add("member_late");
		return s;
	}
	
	




}