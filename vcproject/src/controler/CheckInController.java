package controler;  

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import entity.Order;
import entity.Subscribe;

public class CheckInController extends Controller {

	private VcpInfo vcpInfo;
	private RegisterController registerController;
	private Parking_Algorithem parkingAlgorithem;
	private int fullPositionCounter;


	public CheckInController(String host, int port, VcpInfo vcpInfo) {
		super(host, port);
		this.vcpInfo = vcpInfo;
	}

	public Order getCarOrder(Integer carNum) throws Exception {
        Order order;
        Set<Entry<Integer, Order>> orderEntry = getVcpInfo().getAllOrders()
                        .entrySet();
        Iterator<Entry<Integer, Order>> odrderIterator = orderEntry.iterator();
        while (odrderIterator.hasNext()) {
                order = odrderIterator.next().getValue();
                if (order.getCar().equals(carNum)) {
                        if (!order.getStatus().equals("checked in"))
                                return order;
                        else
					throw new Exception("Cant find order");
                }
        }
        throw new Exception("There is no order on car number: " + carNum);
}

	public Subscribe getSubscribeByNum(Integer memberID, Integer carNum) throws Exception {
		
		
				Subscribe subscribe=getVcpInfo().getAllSubscribed().get(memberID);
				
				if (subscribe.getCarNum().equals(carNum)){
					
					if (!getRegisterController().isExpired(subscribe))
						return subscribe;
					else
						throw new Exception("Subscribe is expired");
				}
				else
					throw new Exception("Car number is not assign to member id: " + memberID);
	}
	
	public void updateSubscribeAscheckedin(Subscribe subscribe){
		
		if(subscribe.getSubscribeType().equals("partially")){
		Object[] updateSubscribe={"UPDATE  vcp_db.subscribe SET status=?,entriesDay=?"
		+ " WHERE subscribeNum=?;","checked in",(subscribe.getEntriesDay())+1,subscribe.getSubscribeNum()};
		subscribe.setStatus("checked in");
		subscribe.setEntriesDay((subscribe.getEntriesDay() +1));
		sendQueryToServer(updateSubscribe);
		}
		
		else{
			Object[] updateSubscribe={"UPDATE  vcp_db.subscribe SET status=?"
			+ " WHERE subscribeNum=?;","checked in",subscribe.getSubscribeNum()};
			sendQueryToServer(updateSubscribe);
			closeConnection();
			subscribe.setStatus("checked in");
		}
		
	}

	public VcpInfo getVcpInfo() {
		return vcpInfo;
	}

	public RegisterController getRegisterController() {
		if (registerController == null) {
			registerController = new RegisterController(getHost(), getPort());
		}
		return registerController;
	}

	public void Algo(Object order) throws ParseException {
		Object orderType;
		
		if(order instanceof Order){
		orderType=(Order) order;
		getParking_Algorithem(orderType);
		}
		
		else if(order instanceof Subscribe){
			orderType=(Subscribe) order;
			getParking_Algorithem(orderType);
		}
		parkingAlgorithem=null;
	}
	
	public Parking_Algorithem getParking_Algorithem(Object order) throws ParseException{
		
		if (parkingAlgorithem == null)
			parkingAlgorithem=new Parking_Algorithem(getVcpInfo(), order);
		
		return parkingAlgorithem;
	}
	
	public long getHoursDiff(Order order) {
		String dateStart = order.getArrivalDate() + " " + order.getArrivalTime();
		String dateStop = order.getDepartureDate() + " " + order.getDepartureTime();
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
		
		return diffHours;
	}

	public int getFullPositionCounter() {
		return fullPositionCounter;
	}
}
