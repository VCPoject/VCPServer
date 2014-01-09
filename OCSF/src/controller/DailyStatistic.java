package controller; 

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;

import ocsf.server.MySqlConnection;
import entity.DailyStatisticEntity;

public class DailyStatistic extends TimerTask {
	
	private String dbIp;
	private String dbUser;
	private String dbPassword;
	private int port;
	
	public DailyStatistic(int port, String dbIp, String dbUser, String dbPassword) {
		this.dbIp = dbIp;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
		this.port = port;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			DailyStatisticEntity dStatistic = new DailyStatisticEntity();
			dStatistic.setDate(getTodayDate());
			ArrayList<Object> todayOrder = getTodayOrders();
			for(int i=0; i<todayOrder.size();i++){
				/*arrivalDate
				arrivalTime
				checkInDate
				checkInTime
				status*/
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
          

	}
	
	public String getTodayDate(){
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}
	
	public ArrayList<Object> getTodayOrders(){
		
		Object[] getOrders = {"SELECT `order`.`arrivalDate`,`order`.`arrivalTime`,"
				+ "`order`.`checkInDate`,`order`.`checkInTime`,`order`.`status`"
				+ ",`order`.`type` FROM `vcp_db`.`order`" + " WHERE arrivalTime = ?;",getTodayDate()};
		return sendToSQL(getOrders);
		
	}
	
	public ArrayList<Object> sendToSQL(Object[] msg){
		MySqlConnection toDB = new MySqlConnection(dbIp,dbUser,dbPassword);
		toDB.update(toDB.getConn(), (Object[]) msg);
		return toDB.getResult();
	}

	public int getPort() {
		return port;
	}
	
	public Date StringToDate(String strDate) throws ParseException{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.parse(strDate);
	}

}
