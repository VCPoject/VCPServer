package controler;

import java.util.Date;

import entity.Order;
import entity.Subscribe;

public class CheckInController extends Controller {

	private VcpInfo vcpInfo;
	private RegisterController registerController;

	public CheckInController(String host, int port, VcpInfo vcpInfo) {
		super(host, port);
		this.vcpInfo = vcpInfo;
	}

	public Order getCarOrder(Integer carNum) throws Exception {
		for (Order order : getVcpInfo().getAllOrders()) {
			if (order.getCar().equals(carNum))
				if (!order.getStatus().equals("implement"))
					return order;
				else
					throw new Exception("Cant fint order");
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

	public Integer[] Algo(Date date) {
		Integer idParkingLot = 1;
		Integer floor = 1;
		Integer row = 2;
		Integer column = 3;
		Integer[] coordinate = { idParkingLot, floor, row, column };
		return coordinate;
	}

}
