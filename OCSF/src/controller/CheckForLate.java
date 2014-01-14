package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import entity.Order;
import ocsf.server.MySqlConnection;

public class CheckForLate extends TimerTask {
	private String dbIp;
	private String dbUser;
	private String dbPassword;
	private int port;
	private AlreadySendReminder alreadySendReminder;
	
	/**
	 * CheckForLate is checking for late to parking place and email them about it.
	 * @param port for connect to DB
	 * @param dbIp is host to connect to DB
	 * @param dbUser is DB user
	 * @param dbPassword is DB password
	 * @param alreadySendReminder holds all orders that have been mailed
	 */
	public CheckForLate(int port, String dbIp, String dbUser,String dbPassword, AlreadySendReminder alreadySendReminder) {
		super();
		this.dbIp = dbIp;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
		this.port = port;
		this.alreadySendReminder = alreadySendReminder;
	}
	@Override
	public void run() {
		ArrayList<Order> OrdersList = new ArrayList<Order>();
		ArrayList<Object> lateList = getLateList();
		for(int i = 0 ; i<lateList.size(); i++){
			if(!lateList.get(0).equals("No Result"))
			{
				Order order = new Order();
				order.setIdorder(Integer.parseInt(lateList.get(i++).toString()));
				order.setFirstName(lateList.get(i++).toString());
				order.setLastName(lateList.get(i++).toString());
				order.setArrivalDate(lateList.get(i++).toString());
				order.setArrivalTime(lateList.get(i++).toString());
				order.setEmail(lateList.get(i).toString());
				OrdersList.add(order);
			}
		}
		
		for(Order lateOrder: OrdersList)
			try {
				if(isLate(lateOrder)){
					if(!getAlreadySendReminder().getOrders().containsKey(lateOrder.getIdorder())){
						String subject = "You are been late to your Order";
						String text = "Dir " + lateOrder.getFirstName() + " " + lateOrder.getLastName() + ".\n"
								+ "You are been late to your order. Do you want to abort your order?";
						SendMailSSL sendMail = new SendMailSSL();
						sendMail.sendMail(lateOrder.getEmail(), subject, text);
						if (sendMail.sendMail(lateOrder.getEmail(), subject,text)) {
							System.out.println("Server send email to email:" + lateOrder.getEmail());
						}
						getAlreadySendReminder().setOrders(lateOrder);
						Order orderLate = getAlreadySendReminder().getOrders().get(lateOrder.getIdorder());
						Object[] abortOrder = {"UPDATE `vcp_db`.`order` SET `status` = ? WHERE `idorder` = ?;" , "Late" ,orderLate.getIdorder()};
						sendToSQL(abortOrder);
					}else{
						Order orderToAbort = getAlreadySendReminder().getOrders().get(lateOrder.getIdorder());
						if(toAbort(orderToAbort)){
							Object[] abortOrder = {"UPDATE `vcp_db`.`order` SET `status` = ? WHERE `idorder` = ?;" , "Aborted" ,orderToAbort.getIdorder()};
							sendToSQL(abortOrder);
							 getAlreadySendReminder().getOrders().remove(orderToAbort.getIdorder());
						}
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
		

	}
	
	/**
	 * getLateList is getting all the orders that are been late from DB
	 * @return all the orders that are been late
	 */
	public ArrayList<Object> getLateList() {
		Object[] getSubscribe = { "SELECT * FROM vcp_db.emaillate;" };
		return sendToSQL(getSubscribe);
	}
	
	/**
	 * sendToSQL send queries to DB and get the result
	 * @param msg is the query to be send to DB
	 * @return result from DB
	 */
	public ArrayList<Object> sendToSQL(Object[] msg) {
		MySqlConnection toDB = new MySqlConnection(dbIp, dbUser, dbPassword);
		toDB.update(toDB.getConn(), (Object[]) msg);
		return toDB.getResult();
	}
	
	/**
	 * isLate check if order is been late to parking place by given order entity
	 * @param order is order entity
	 * @return true if order is late else false
	 * @throws ParseException
	 */
	public boolean isLate(Order order) throws ParseException{
		Date toDay = new Date();
		String orderDateStr = order.getArrivalDate() + " " + order.getArrivalTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date orderDate = format.parse(orderDateStr);
		if(toDay.after(orderDate))
			return true;
		return false;
	}
	public AlreadySendReminder getAlreadySendReminder() {
		return alreadySendReminder;
	}
	
	/**
	 * toAbort is check if order is been late for 30 minutes and abort the order if it is.
	 * @param orderToAbort is order entity
	 * @return true if order is late for 30 minutes else false
	 */
	public boolean toAbort(Order orderToAbort) throws ParseException{
		Date toDay = new Date();
		String orderDateStr = orderToAbort.getArrivalDate() + " " + orderToAbort.getArrivalTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date orderDate = format.parse(orderDateStr);
		orderDate = addMin(orderDate, 30);
		if(toDay.after(orderDate)){
			getAlreadySendReminder().getOrders().remove(orderToAbort.getIdorder());
			if(!CheckIfArrived(orderToAbort)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * CheckIfArrived is check if late order arrive to parking place
	 * @param orderToAbort is order entity
	 * @return true if the late order arrived to parking place
	 */
	private boolean CheckIfArrived(Order orderToAbort) {
		Object[] isArrived = {"SELECT `order`.`status` FROM `vcp_db`.`order` WHERE idorder = ?;" , orderToAbort.getIdorder()};
		
		if(sendToSQL(isArrived).get(0).equals("Late"))
			return false;
		return true;
	}
	/**
	 * addMin add minutes to given date by given minutes to add
	 * @param date of order
	 * @param min to be add to order date
	 * @return a date after adding minutes
	 */
	public Date addMin(Date date, int min){
		Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(date); // sets calendar time/date
	    cal.add(Calendar.MINUTE, min); // adds minuts
	    return cal.getTime(); // returns new date object, one hour in the future
	}
	public int getPort() {
		return port;
	}

}
