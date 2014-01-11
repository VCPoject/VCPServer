package controler;  

import java.text.ParseException;
import java.util.*;
import java.util.Map.Entry;

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
		Set<Entry<Integer, Order>> orderEntry=getVcpInfo().getAllOrders().entrySet();
		Iterator<Entry<Integer, Order>> odrderIterator=orderEntry.iterator();
		while(odrderIterator.hasNext())
			if (odrderIterator.next().getValue().equals(carNum)){
				if (!odrderIterator.next().getValue().equals("checked in"))
					return odrderIterator.next().getValue();
			if (order.getCar().equals(carNum)) {
				if (!order.getStatus().equals("checked in"))
				else
					throw new Exception("Cant fint order");
			}
		}
		throw new Exception("There is no order on car number: " + carNum);
	}

	public Subscribe getSubscribeByNum(Integer memberID, Integer carNum) throws Exception {
		Subscribe subscribe = getVcpInfo().getAllSubscribed().get(memberID);
		if (subscribe == null)
			throw new Exception("Member id " + memberID + " is not exists.");
		if (subscribe.getCarNum().equals(carNum))
			if (!getRegisterController().isExpired(subscribe))
				return subscribe;
			else
				throw new Exception("Subscribe is expired");
		else
			throw new Exception("Car number is not assign to member id: " + memberID);

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

	public Integer[] Algo(VcpInfo vcpInfo, Order order, Parking_Lot parkingLot)
			throws ParseException {
		Integer[] coordinate = getParking_Algorithem(order, parkingLot)
				.findOptimParkingPlace();
		return coordinate;
	}

	public Parking_Algorithem getParking_Algorithem(Order order,
			Parking_Lot parkingLot) throws ParseException {
		if (parkingAlgorithem == null)
			parkingAlgorithem = new Parking_Algorithem(getVcpInfo(), order,
					parkingLot);

		return parkingAlgorithem;
	}
}
