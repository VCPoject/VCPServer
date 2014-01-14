package controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class Scheduler {
	private static String dbIp;
	private static String dbUser;
	private static String dbPassword;
	private static int port;
	private static DailyStatistic dailyStatistic;
	private static ResubscribeReminder resubscribeReminder;
	private static WeeklyStatsTime weekTask;
	private static AlreadySendReminder alreadySendReminder;
	private static CheckForLate checkForLate;
	
	private final static long ONCE_PER_DAY = 1000*60*60*24;
    private final static int MIDNIGHT = 23;
    private final static int ZERO_MINUTES = 59;
    private final static int ONE_MINUTE = 1000*60;
    private final static long ONCE_PER_WEEK = 1000*60*60*24*7;
	
	/**
	 * Scheduler is a controller the manage all the scheduled tasks:
	 * Daily report,subscribe expired ,orders late, weekly reports
	 * @param port for connect to DB
	 * @param dbIp is host to connect to DB
	 * @param dbUser is DB user
	 * @param dbPassword is DB password
	 */
	public Scheduler(int port, String dbIp, String dbUser, String dbPassword) {
		super();
		Scheduler.dbIp = dbIp;
		Scheduler.dbUser = dbUser;
		Scheduler.dbPassword = dbPassword;
		Scheduler.port = port;
		startDailyTask();
		startSubscribeCheck();
		startLateCheck();
		startWeeklyTask();
	}

	/**
	 * startDailyTask is starting the daily statistics, checking every day at midnight
	 */
	public static void startDailyTask(){
		dailyStatistic = new DailyStatistic(port, dbIp, dbUser, dbPassword);
        Timer timer = new Timer();  
        timer.schedule(dailyStatistic,getMidnight().getTime(),ONCE_PER_DAY);
    }
	
	/**
	 * startSubscribeCheck is starting the subscribe reminder, checking every day at midnight
	 */
	public static void startSubscribeCheck(){
		resubscribeReminder = new ResubscribeReminder(port, dbIp, dbUser, dbPassword,getAlreadySendReminder());
        Timer timer = new Timer();  
        timer.schedule(resubscribeReminder,getMidnight().getTime(),ONCE_PER_DAY);
    }
	/**
	 * startLateCheck is starting the order late task, checking every one minute
	 */
	public static void startLateCheck(){
		checkForLate = new CheckForLate(port, dbIp, dbUser, dbPassword,getAlreadySendReminder());
        Timer timer = new Timer(); 
        Date fromNow = new Date();
        timer.schedule(checkForLate, fromNow, ONE_MINUTE);
	}
	/**
	 * startWeeklyTask is starting the weekly statistic, checking every one week at midnight
	 */
	public static void startWeeklyTask(){
		weekTask = new WeeklyStatsTime(port, dbIp, dbUser, dbPassword);
        Timer timer = new Timer();  
        timer.schedule(weekTask,getMidnight().getTime(),ONCE_PER_WEEK);
    }
	
	
	/**
	 * @return midnight date
	 */
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

	public static AlreadySendReminder getAlreadySendReminder() {
		if(alreadySendReminder == null)
			alreadySendReminder = new AlreadySendReminder();
		return alreadySendReminder;
	}
}
