package controller;

import java.util.Calendar;
import java.util.Timer;

public class Scheduler {
	private static String dbIp;
	private static String dbUser;
	private static String dbPassword;
	private static int port;
	private static DailyStatistic task;
	
	private final static long ONCE_PER_DAY = 1000*60*60*24;
    private final static int MIDNIGHT = 24;
    private final static int ZERO_MINUTES = 0;
	
	public Scheduler(int port, String dbIp, String dbUser, String dbPassword) {
		super();
		Scheduler.dbIp = dbIp;
		Scheduler.dbUser = dbUser;
		Scheduler.dbPassword = dbPassword;
		Scheduler.port = port;
		startDailyTask();
	}

	public static void startDailyTask(){
		task = new DailyStatistic(port, dbIp, dbUser, dbPassword);
        Timer timer = new Timer();  
        timer.schedule(task,getMidnight().getTime(),ONCE_PER_DAY);
    }
	
	private static Calendar getMidnight(){
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, MIDNIGHT);
		today.set(Calendar.MINUTE, ZERO_MINUTES);
		today.set(Calendar.SECOND, 0);
       return today;
      }

	public String getDbIp() {
		return dbIp;
	}

	public String getDbUser() {
		return dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public int getPort() {
		return port;
	}
}
