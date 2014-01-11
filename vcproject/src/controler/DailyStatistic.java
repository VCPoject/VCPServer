package controler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class DailyStatistic extends Controller {

	public DailyStatistic() {
		super();
	}

	public DailyStatistic(String host, int port) {
		super(host, port);
	}

	public DailyStatistic(String host) {
		super(host);
	}
	
	public Vector<Vector<Object>> getDailyStatistic(Date date){
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		Integer columnLength = 7;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Object[] daily = {"SELECT * FROM vcp_employ.daily_statistic WHERE date = ?'%';", format.format(date)};
			sendQueryToServer(daily);
			ArrayList<Object> result = getResult();
			if(!result.get(0).equals("No Result")){
				Vector<Object> row = new Vector<Object>(columnLength);
				for (Object value : result) {
					if (!value.equals("no value")){
						if(row.size() == 6){
							DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
							String dateAndTime = dateFormat.format(value);
							row.add(dateAndTime);
						}else
							row.add(value);
					}
					else
						row.add("0");
					if(row.size() == 7){
						data.add(row);
						row = new Vector<Object>(columnLength);
					}
				}
			}else
				throw new Exception("There no statistic in DB");
			return data;
		} catch (Exception e) {
			showWarningMsg(e.getMessage());
		}
		return null;
	}

}
