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

	public DailyStatistic(int port, String dbIp, String dbUser,
			String dbPassword) {
		this.dbIp = dbIp;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			DailyStatisticEntity dStatistic = new DailyStatisticEntity();
			dStatistic.setDate(getTodayDate());
			Integer numberOfImplement = 0;
			Integer late = 0;
			Integer aborted = 0;
			ArrayList<Object> todayOrder = getTodayOrders();
			for (int i = 0; i < todayOrder.size(); i++) {
				String status = todayOrder.get(i++).toString();
				if (status.equals("checked in")) {
					numberOfImplement++;
					String arrivalDate = todayOrder.get(i++).toString();
					String arrivalTime = todayOrder.get(i++).toString();
					Date arrival = StringToDate(arrivalDate + " " + arrivalTime);
					String checkInDate = todayOrder.get(i++).toString();
					String checkInTime = todayOrder.get(i).toString();
					Date checkIn = StringToDate(checkInDate + " " + checkInTime);
					if (checkIn.after(arrival))
						late++;
				} else if (status.equals("Aborted")) {
					aborted++;
					i = i + 5;
				} else {
					i = i + 5;
				}
			}
			Integer numberOfSubscribe = Integer.parseInt(getNumberOfSubscribe().get(0).toString());
			dStatistic.setMemberCount(numberOfSubscribe);
			Integer subscribeWithMoreCar = Integer
					.parseInt(getSubscribeWithMoreCars().get(0).toString());
			dStatistic.setMemberCarsCount(subscribeWithMoreCar);
			setDailyStatistic(dStatistic);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public String getTodayDate() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	public ArrayList<Object> getNumberOfSubscribe() {
		Object[] getsubscribe = { "SELECT count(carNum) FROM vcp_db.subscribe;" };
		return sendToSQL(getsubscribe);
	}

	public ArrayList<Object> getSubscribeWithMoreCars() {
		Object[] moreThenOneCars = { "SELECT count(idclient) AS id FROM "
				+ "(SELECT idclient FROM vcp_db.subscribe "
				+ "group by idclient HAVING count(carNum) >'1')"
				+ " AS clientWithMoreCar ;" };
		return sendToSQL(moreThenOneCars);
	}

	public ArrayList<Object> getTodayOrders() {

		Object[] getOrders = {
				"SELECT `order`.`status`,`order`.`arrivalDate`,`order`.`arrivalTime`,"
						+ "`order`.`checkInDate`,`order`.`checkInTime`"
						+ ",`order`.`type` FROM `vcp_db`.`order`"
						+ " WHERE arrivalDate = ?;", getTodayDate() };
		return sendToSQL(getOrders);

	}

	public void setDailyStatistic(DailyStatisticEntity dStatistic) {
		String insertDaily = "INSERT INTO `vcp_employ`.`daily_statistic` "
				+ "(`implementOrders`,`canceledOrders`,`memberCount`,`memberMoreCarsCount`,`lateCount`)"
				+ " VALUES (?,?,?,?,?);";
		dStatistic.setQuery(insertDaily);
		sendToSQL(dStatistic.toObject());
	}

	public ArrayList<Object> sendToSQL(Object[] msg) {
		MySqlConnection toDB = new MySqlConnection(dbIp, dbUser, dbPassword);
		toDB.update(toDB.getConn(), (Object[]) msg);
		return toDB.getResult();
	}

	public int getPort() {
		return port;
	}

	public Date StringToDate(String strDate) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.parse(strDate);
	}

}
