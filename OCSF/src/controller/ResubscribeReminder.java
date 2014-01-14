package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import ocsf.server.MySqlConnection;
import entity.Subscribe;

public class ResubscribeReminder extends TimerTask {

	private String dbIp;
	private String dbUser;
	private String dbPassword;
	private int port;
	private AlreadySendReminder alreadySendReminder;

	/**
	 * ResubscribeReminder is checking for subscribes that have 14 days left and email them about it.
	 * @param port for connect to DB
	 * @param dbIp is host to connect to DB
	 * @param dbUser is DB user
	 * @param dbPassword is DB password
	 * @param alreadySendReminder holds all subscribes that have been mailed
	 */
	public ResubscribeReminder(int port, String dbIp, String dbUser,
			String dbPassword, AlreadySendReminder alreadySendReminder) {
		super();
		this.dbIp = dbIp;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
		this.port = port;
		this.alreadySendReminder = alreadySendReminder;
	}

	public void run() {
		ArrayList<Subscribe> subscribeList = new ArrayList<Subscribe>();
		ArrayList<Object> result = getSubscribeList();
		for (int i = 0; i < result.size(); i++) {
			Subscribe subscribe = new Subscribe();
			subscribe.setSubscribeNum(Integer.parseInt(result.get(i++)
					.toString()));
			subscribe.setEndDate(result.get(i++).toString());
			subscribe.setFirstName(result.get(i++).toString());
			subscribe.setLastName(result.get(i++).toString());
			subscribe.setEmail(result.get(i).toString());
			subscribeList.add(subscribe);
		}

		for (Subscribe sendSubscribe : subscribeList) {
			if (isNeedToSend(sendSubscribe)) {
				if (!getAlreadySendReminder().getSubscribe().containsKey(sendSubscribe.getSubscribeNum())) {
					String subject = "Your VCP member is going to be expierd";
					String text = "Dir " + sendSubscribe.getFirstName() + " " + sendSubscribe.getLastName() + ".\n"
							+ "Your VCP member number: " + sendSubscribe.getSubscribeNum()	+ " is going to expired in 14 days."
							+ "\n please consider make resubscribe";
					SendMailSSL sendMail = new SendMailSSL();
					sendMail.sendMail(sendSubscribe.getEmail(), subject, text);
					if (sendMail.sendMail(sendSubscribe.getEmail(), subject,text)) {
						System.out.println("Server send email to email:" + sendSubscribe.getEmail());
					}
				}
			}
		}

	}

	public ArrayList<Object> getSubscribeList() {
		Object[] getSubscribe = { "SELECT * FROM vcp_db.emailsubscribed;" };
		return sendToSQL(getSubscribe);
	}

	public ArrayList<Object> sendToSQL(Object[] msg) {
		MySqlConnection toDB = new MySqlConnection(dbIp, dbUser, dbPassword);
		toDB.update(toDB.getConn(), (Object[]) msg);
		return toDB.getResult();
	}

	/**
	 * isNeedToSend is checking if there is a need to send email to inform the subscribe that 
	 * his member is going to end in 14 days
	 * @param subscribe entity
	 * @return true if need to send email to subscribe
	 */
	public boolean isNeedToSend(Subscribe subscribe) {
		Date toDay = new Date();
		toDay = addDays(toDay, 14);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date endDateSubscribe;
		try {
			endDateSubscribe = format.parse(subscribe.getEndDate());
			if (endDateSubscribe.before(toDay)) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * addDays add to given date more days by given days.
	 * @param date instance
	 * @param days to add to date
	 * @return date with add number of given days
	 */
	public Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		return cal.getTime();
	}
	
	public int getPort() {
		return port;
	}

	public AlreadySendReminder getAlreadySendReminder() {
		return alreadySendReminder;
	}

}
