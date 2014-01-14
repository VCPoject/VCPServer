package controler;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import entity.CancelOrderEntity;
import entity.Pricing;

public class CancelOrderController extends Controller {

	private Pricing pricing;
	private CancelOrderEntity coEntity;
	public CancelOrderController(String host, int port,Pricing pricing,CancelOrderEntity coEntity){
		super(host, port);
		this.pricing=pricing;
		this.coEntity = coEntity;
	}
	/**
	 * calculate price of refound and show it to user
	 * @param selectedItem
	 * @return String
	 */
	public String calculatePrice(String selectedItem){
			String[] parts= selectedItem.split("'");
			int part1 = Integer.parseInt(parts[1]);
			Object[] Msg = {
					"SELECT `order`.`arrivalDate`,`order`.`arrivalTime`,`order`.`type`"
					+ ",`order`.`departureTime`,`order`.`departureDate` FROM `vcp_db`.`order` WHERE "
							+ " `order`.`status` != ? AND `order`.`idorder`= ?;","Aborted",part1 };
			sendQueryToServer(Msg);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				Date dateArrival = null;
				Date dateDeparture = null;
				String dateANDtime = dateFormat.format(date);
				try {
					date = dateFormat.parse(dateANDtime);
					String s = getResult().get(0).toString()+" "+getResult().get(1).toString();
					dateArrival = dateFormat.parse(s);
					String s1 = getResult().get(4).toString()+" "+getResult().get(3).toString();
					dateDeparture = dateFormat.parse(s1);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				long diffArrival = Math.abs(date.getTime() - dateArrival.getTime());       
				long diffHoursArrival = diffArrival / (60 * 60 * 1000);
				long diffDeparture = Math.abs(dateArrival.getTime() - dateDeparture.getTime());
				long diffHoursDeparture = diffDeparture / (60 * 60 * 1000);
				if (diffHoursArrival>=3){
					if(getResult().get(2).equals("temp")){
						Float cash = ((float)4*diffHoursDeparture);
						applyDB(part1,cash);
						return cash.toString();
					}
					else{
						Float cash = ((float)4*diffHoursDeparture);
						applyDB(part1,cash);
						return cash.toString();
					} 
				}
				else if(diffHoursArrival<=3 && diffHoursArrival>=1){
					if(getResult().get(2).equals("temp")){
						Float cash = (float) (4*diffHoursDeparture*0.5);
						applyDB(part1,cash);
						return cash.toString();
					}
					else{
						Float cash = (float)(4*diffHoursDeparture*0.5);
						applyDB(part1,cash);
						return cash.toString();
					}
				}
				
				else if(diffHoursArrival<=1){
					if(getResult().get(2).equals("temp")){
						Float cash = (float)(0);
						applyDB(part1,cash);
						return cash.toString();
					}
					else{
						Float cash = (float)(0);
						applyDB(part1,cash);
						return cash.toString();
					}
				}
				
			
		return "done";
	}
	/**
	 * update DB with refound
	 * @param part1
	 * @param cash
	 */
	private void applyDB(int part1,float cash){
		
		Object[] insertMoney = {
				"UPDATE  vcp_employ.financial_card SET amount = ? WHERE idclient = ? ;",
					cash,coEntity.getUserid() };
		sendQueryToServer(insertMoney);
		Object[] delOrder ={
				"UPDATE  vcp_db.order SET status = ?  WHERE idorder = ?;" ,"Aborted",part1};
		sendQueryToServer(delOrder);
	}
/**
 * check if the user id match the car id
 * @param idnum
 * @param carnum
 * @return TRUE OR FALSE
 */
	public boolean checkID(int idnum, int carnum) {
		Object[] sqlsMsg = {
				"SELECT `car`.`carNum`,`car`.`idclient`FROM `vcp_db`.`car` WHERE "
						+ " `car`.`idclient`= ? AND `car`.`carNum` = ?;", idnum,
				carnum };
		sendQueryToServer(sqlsMsg);
		if (getResult().get(0).equals("No Result")) {
			showWarningMsg("Your id and car number are not matched");
			return false;
		}
		return true;
	}
/**
 * return all open orders for the user to show on GUI
 * @param idnum
 * @returnArrayList<String>
 */
	public ArrayList<String> getOrders(int idnum) {
		Object[] sqlsMsg = {
				"SELECT `order`.`idorder`,`order`.`arrivalDate`"
					+ "FROM `vcp_db`.`order` WHERE "
							+ "`order`.`status` != ? AND `order`.`idclient`= ?","Aborted", idnum };
		sendQueryToServer(sqlsMsg);
		ArrayList<String> s = new ArrayList<String>(); 
		for(int i=0;i<getResult().size()-1;i++)
			s.add("Order ID: '"+getResult().get(i).toString() +"' Order Date: '" +getResult().get(i+1).toString()+"'");
		return s;
	}
	
	
}
