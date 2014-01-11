package controler;

import entity.Car;
import entity.ClientEntity;
import entity.Order;

public class MakeOrderController extends Controller {

	public MakeOrderController(String host, int port) {
		super(host, port);
	}
	
	public void addCarToDB(Car car) {
		sendQueryToServer(car);
	}

	public void addNewClient(ClientEntity newClient) {
		sendQueryToServer(newClient);
	}

	public void addNewOrder(Order order) {
		sendQueryToServer(order);
	}
	
	public void UpdateOrderCheckout(Order order) {
		String updateOrder = "UPDATE `vcp_db`.`order` SET `checkOutDate` = ?, `checkOutTime` = ?, `status` = ? WHERE `idorder` = ?;";
		order.setQuery(updateOrder);
		sendQueryToServer(order);
	}
	
	public void UpdateOrder(Order order) {
		String updateOrder = "UPDATE `vcp_db`.`order` SET `checkInDate` = ?, `checkInTime` = ?, `status` = ? WHERE `idorder` = ?;";
		order.setQuery(updateOrder);
		sendQueryToServer(order);
	}
	
	public void searchClient(Object[] clientQuery){
		sendQueryToServer(clientQuery);
	}
	
	public void searchCar(Object[] CarQuery){
		sendQueryToServer(CarQuery);
	}
	
}
