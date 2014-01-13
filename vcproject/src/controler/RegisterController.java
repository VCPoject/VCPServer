package controler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import entity.Subscribe;

public class RegisterController extends Controller {

	public RegisterController(String host, int port) {
		super(host, port);
	}

	public void addNewSubscribe(Subscribe newSubscribed) {
		sendQueryToServer(newSubscribed);
	}

	public void updateResubscribe(Subscribe reSubscribed) {
		Object[] updateResubscribe = {
				"UPDATE `vcp_db`.`subscribe` SET `idparking` = ?, `startDate` = ?, `endDate` = ?, `subscribType` = ?"
						+ ",`customerType` = ?, `leavingTime` = ?  WHERE `subscribeNum` = ?;",
				reSubscribed.getIdparking(), reSubscribed.getStartDate(),reSubscribed.getEndDate(),
				reSubscribed.getSubscribeType(), reSubscribed.getCustomerType(),
				reSubscribed.getDepartureTime(), reSubscribed.getSubscribeNum() };
		sendQueryToServer(updateResubscribe);

	}

	/* Check if subscribe is expired- if expired return true else false */
	public boolean isExpired(Subscribe subscribe) {
		String time = "00:00:00";
		String dateStart = subscribe.getStartDate() + " " + time;
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStop = dateFormat.format(date) + " " + time;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Get msec from each, and subtract.
		long diff = d2.getTime() - d1.getTime();
		long diffHours = diff / (60 * 60 * 1000);
		long diffDays = diffHours / 24;
		if (diffDays > 28)
			return true;
		else
			return false;
	}
	
	/**
	 * addDays is adding to give date number of x days
	 * @param date to be add
	 * @param days to add to date
	 * @return
	 */
	public Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

}
