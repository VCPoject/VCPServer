package controler;

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
	
	/*public Object[] getDailyStatistic(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Object[] daily = {"SELECT * FROM vcp_employ.daily_statistic WHERE date LIKE ?;", format.format(date)};
		ArrayList<Object> result = getResult();
		if(!result.get(0).equals("No Result")){
			Vector<String> cols;
			String[] columnNames ={"Change number", "Employee ID", "Occasional", "One-Time", "Date", "Status"}; 
			int columnLength = columnNames.length;
			cols = new Vector<String>(columnLength);
			for (int i = 0; i < columnLength; i++)
				cols.add(columnNames[i]);
		}
		
	}*/

}
