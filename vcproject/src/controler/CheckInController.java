package controler;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import entity.Order;
import entity.Parking_Lot;
import entity.Subscribe;

public class CheckInController extends Controller {

	private VcpInfo vcpInfo;
	private RegisterController registerController;
	Parking_Algorithem parkingAlgorithem;

	public CheckInController(String host, int port, VcpInfo vcpInfo) {
		super(host, port);
		this.vcpInfo = vcpInfo;
	}

	public Order getCarOrder(Integer carNum) throws Exception {
		Order order;
		Set<Entry<Integer, Order>> orderEntry=getVcpInfo().getAllOrders().entrySet();
		Iterator<Entry<Integer, Order>> odrderIterator=orderEntry.iterator();
		while(odrderIterator.hasNext()){
			order=odrderIterator.next().getValue();
			if (order.getCar().equals(carNum)){
				if (!order.getStatus().equals("checked in"))
					return order;
				else
					throw new Exception("Cant fint order");
			}
		}
		throw new Exception("There is no order on car number: " + carNum);
	}

	public Subscribe getSubscribeByNum(Integer memberID, Integer carNum)
			throws Exception {
		for (Subscribe subscribe : getVcpInfo().getAllSubscribed()) {
			if (subscribe.getSubscribeNum().equals(memberID))
				if (subscribe.getCarNum().equals(carNum))
					if (!getRegisterController().isExpired(subscribe))
						return subscribe;
					else
						throw new Exception("Subscribe is expired");
				else
					throw new Exception(
							"Car number is not assign to member id: "
									+ memberID);
		}
		throw new Exception("Member id " + memberID + " is not exists.");
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

	public Integer[] Algo(VcpInfo vcpInfo,Order order,Parking_Lot parkingLot) throws ParseException {
		Integer[] coordinate=getParking_Algorithem(order, parkingLot).findOptimParkingPlace();
		return coordinate;
	}
	
	public Parking_Algorithem getParking_Algorithem(Order order,Parking_Lot parkingLot) throws ParseException{
		if(parkingAlgorithem==null)
			parkingAlgorithem=new Parking_Algorithem(getVcpInfo(), order, parkingLot);
	
		return parkingAlgorithem;
	}
}
