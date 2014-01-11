package controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;

import ocsf.server.MySqlConnection;
import entity.WeeklyStatsEntityTime;

public class WeeklyStatsTime extends TimerTask {

	private WeeklyStatsEntityTime weekEntity;
	private String dbIp;
	private String dbUser;
	private String dbPassword;
	private int port;
	private ArrayList<Object> result;
	
	public WeeklyStatsTime(int port, String dbIp, String dbUser,
			String dbPassword) {
		super();
		this.dbIp = dbIp;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
		this.port = port;
	}
	
	@Override
	public void run(){
		try{
			weekEntity=new WeeklyStatsEntityTime();
			getRowData();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
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
		Timestamp time = new Timestamp(date.getTime());
		Timestamp time1 = new Timestamp(date.getTime());
		weekEntity.setQuery("SELECT * FROM vcp_employ.daily_statistic WHERE date < ? AND date > ?;");
		weekEntity.setDate(time);
		weekEntity.setoldDate(time1);
		result=sendToSQL(weekEntity.toObject());
		for(int i=0;i<result.size();i++)
		System.out.print(result.get(i).toString()+ " ");
		int j=0;
		for(int i=0;i<result.size();i++)
		{
			i++;
			impOrder[j]=Integer.parseInt(result.get(i).toString()) ;
			hitpalgutimpOrder[Integer.parseInt(result.get(i++).toString())/10]++;
			cancelOrder[j]=Integer.parseInt(result.get(i).toString());
			hitpalgutcancelOrder[Integer.parseInt(result.get(i++).toString())/10]++;
			memberCount[j]=Integer.parseInt(result.get(i).toString());
			hitpalgutmemberCount[Integer.parseInt(result.get(i++).toString())/10]++;
			memHasMorCar[j]=Integer.parseInt(result.get(i).toString());
			hitpalgutmemHasMorCar[Integer.parseInt(result.get(i++).toString())/10]++;
			lateCount[j]=Integer.parseInt(result.get(i).toString());
			hitpalgutlateCount[Integer.parseInt(result.get(i++).toString())/10]++;
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
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)","Recived Orders",MakeAverage(weekEntity.getImpOrder()),MakeMedian(weekEntity.getImpOrder()),weekEntity.getHitpalgutimpOrder()[0],weekEntity.getHitpalgutimpOrder()[1],
				weekEntity.getHitpalgutimpOrder()[2],weekEntity.getHitpalgutimpOrder()[3],weekEntity.getHitpalgutimpOrder()[4],weekEntity.getHitpalgutimpOrder()[5],weekEntity.getHitpalgutimpOrder()[6],
						weekEntity.getHitpalgutimpOrder()[7],weekEntity.getHitpalgutimpOrder()[8],weekEntity.getHitpalgutimpOrder()[9]};
		result=sendToSQL(obj);	
		if(result.get(0).equals("done")){}
		Object[] obj1 = {"INSERT INTO vcp_employ.weekly_reports (name,avg,median,decile1,decile2,decile3,decile4,decile5,decile6,decile7,decile8,decile9,decile10) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)","Canceld Orders",MakeAverage(weekEntity.getCancelOrder()),MakeMedian(weekEntity.getCancelOrder()),weekEntity.getHitpalgutcancelOrder()[0],weekEntity.getHitpalgutcancelOrder()[1],
				weekEntity.getHitpalgutcancelOrder()[2],weekEntity.getHitpalgutcancelOrder()[3],weekEntity.getHitpalgutcancelOrder()[4],weekEntity.getHitpalgutcancelOrder()[5],weekEntity.getHitpalgutcancelOrder()[6],
						weekEntity.getHitpalgutcancelOrder()[7],weekEntity.getHitpalgutcancelOrder()[8],weekEntity.getHitpalgutcancelOrder()[9]};
		result=sendToSQL(obj1);	
		if(result.get(0).equals("done")){}
		Object[] obj2 = {"INSERT INTO vcp_employ.weekly_reports (name,avg,median,decile1,decile2,decile3,decile4,decile5,decile6,decile7,decile8,decile9,decile10) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)","Number Of Members",MakeAverage(weekEntity.getMemberCount()),MakeMedian(weekEntity.getMemberCount()),weekEntity.getHitpalgutmemberCount()[0],weekEntity.getHitpalgutmemberCount()[1],
				weekEntity.getHitpalgutmemberCount()[2],weekEntity.getHitpalgutmemberCount()[3],weekEntity.getHitpalgutmemberCount()[4],weekEntity.getHitpalgutmemberCount()[5],weekEntity.getHitpalgutmemberCount()[6],
						weekEntity.getHitpalgutmemberCount()[7],weekEntity.getHitpalgutmemberCount()[8],weekEntity.getHitpalgutmemberCount()[9]};
		result=sendToSQL(obj2);
		if(result.get(0).equals("done")){}
		Object[] obj3 = {"INSERT INTO vcp_employ.weekly_reports (name,avg,median,decile1,decile2,decile3,decile4,decile5,decile6,decile7,decile8,decile9,decile10) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)","Members with Multiple Cars",MakeAverage(weekEntity.getMemHasMorCar()),MakeMedian(weekEntity.getMemHasMorCar()),weekEntity.getHitpalgutmemHasMorCar()[0],weekEntity.getHitpalgutmemHasMorCar()[1],
				weekEntity.getHitpalgutmemHasMorCar()[2],weekEntity.getHitpalgutmemHasMorCar()[3],weekEntity.getHitpalgutmemHasMorCar()[4],weekEntity.getHitpalgutmemHasMorCar()[5],weekEntity.getHitpalgutmemHasMorCar()[6],
						weekEntity.getHitpalgutmemHasMorCar()[7],weekEntity.getHitpalgutmemHasMorCar()[8],weekEntity.getHitpalgutmemHasMorCar()[9]};
		result=sendToSQL(obj3);
		if(result.get(0).equals("done")){}
		Object[] obj4 = {"INSERT INTO vcp_employ.weekly_reports (name,avg,median,decile1,decile2,decile3,decile4,decile5,decile6,decile7,decile8,decile9,decile10) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)","Late to Park",MakeAverage(weekEntity.getLateCount()),MakeMedian(weekEntity.getLateCount()),weekEntity.getHitpalgutlateCount()[0],weekEntity.getHitpalgutlateCount()[1],
				weekEntity.getHitpalgutlateCount()[2],weekEntity.getHitpalgutlateCount()[3],weekEntity.getHitpalgutlateCount()[4],weekEntity.getHitpalgutlateCount()[5],weekEntity.getHitpalgutlateCount()[6],
						weekEntity.getHitpalgutlateCount()[7],weekEntity.getHitpalgutlateCount()[8],weekEntity.getHitpalgutlateCount()[9]};
		sendToSQL(obj4);	
		
	}
	
	public ArrayList<Object> sendToSQL(Object[] msg) {
		MySqlConnection toDB = new MySqlConnection(dbIp, dbUser, dbPassword);
		toDB.update(toDB.getConn(), (Object[]) msg);
		return toDB.getResult();
	}

	public int getPort() {
		return port;
	}




}