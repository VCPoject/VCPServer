package controler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CancelOrderController extends Controller {

	public CancelOrderController(String host, int port){
		super(host, port);
		
	}
	
	public String calculatePrice(int idnum,int carnum){
		Object[] sqlsMsg = {
				"SELECT `car`.`carNum`,`car`.`idclient`FROM `vcp_db`.`car` WHERE "
						+ " `car`.`idclient`= ? AND `car`.`carNum` = ?", idnum,
				carnum };
		sendQueryToServer(sqlsMsg);
		if (getResult().get(0).equals("No Result")) {
			showWarningMsg("Your id and car number are not matched");
			return "false";
		}
		else{
			Object[] Msg = {
					"SELECT `order`.`arrivalDate`,`order`.`arrivalTime`,`order`.`type`"
					+ ",`order`.`departureTime`,`order`.`departureDate` FROM `vcp_db`.`order` WHERE "
							+ " `order`.`idclient`= ? AND `order`.`carNum` = ?", idnum,carnum };
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				Date dateArrival = null;
				Date dateDeparture = null;
				String dateANDtime = dateFormat.format(date);
				try {
					date = dateFormat.parse(dateANDtime);
					dateArrival = dateFormat.parse(getResult().get(0).toString()+" "+getResult().get(1).toString());
					dateDeparture = dateFormat.parse(getResult().get(3).toString()+" "+getResult().get(4).toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				long diffArrival = date.getTime() - dateArrival.getTime();       
				long diffHoursArrival = diffArrival / (60 * 60 * 1000);
				long diffDeparture = dateArrival.getTime() - dateDeparture.getTime();
				long diffHoursDeparture = diffDeparture / (60 * 60 * 1000);
				if (diffHoursArrival>=3){
					if(getResult().get(2).equals("temp"))
						
					else
				}
				else if(diffHoursArrival<=3 && diffHoursArrival>=1){
					
				}
				else if(diffHoursArrival<=1){
					
				}
				
			}
	}
	
	
}
