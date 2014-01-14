package controler;

import entity.Car;
import entity.ClientEntity;
import entity.Order;

public class MakeOrderController extends Controller {

	/**
	 * MakeOrderController is a controller of orders
	 * @param host to connect to server side
	 * @param port to connect to server side
	 */
	public MakeOrderController(String host, int port) {
		super(host, port);
	}
	
	/**
	 * addCarToDB is adding the order car to the data base with giver car entity
	 * @param car to add to data base
	 */
	public void addCarToDB(Car car) {
		sendQueryToServer(car);
	}

	/**
	 * addNewClient is adding new client that make order by given client entity
	 * @param newClient to add to DB
	 */
	public void addNewClient(ClientEntity newClient) {
		sendQueryToServer(newClient);
	}

	/**
	 * addNewOrder is adding new order to data base by given order entity 
	 * @param order to add to data base
	 */
	public void addNewOrder(Order order) {
		sendQueryToServer(order);
	}
	
	/**
	 * UpdateOrderCheckout is updating the order status to checked out
	 * @param order to be update in data base
	 */
	public void UpdateOrderCheckout(Order order) {
		String updateOrder = "UPDATE `vcp_db`.`order` SET `checkOutDate` = ?, `checkOutTime` = ?, `status` = ? WHERE `idorder` = ?;";
		order.setQuery(updateOrder);
		sendQueryToServer(order);
	}
	
	/**
	 * UpdateOrderCheckout is updating the order status to checked in
	 * @param order to be update in data base
	 */
	public void UpdateOrder(Order order) {
		String updateOrder = "UPDATE `vcp_db`.`order` SET `checkInDate` = ?, `checkInTime` = ?, `status` = ? WHERE `idorder` = ?;";
		order.setQuery(updateOrder);
		sendQueryToServer(order);
	}
	
	/**
	 * searchClient is search for a client by given query
	 * @param clientQuery is a query to be send to server to search a client
	 */
	public void searchClient(Object[] clientQuery){
		sendQueryToServer(clientQuery);
	}
	
	/**
	 * searchCar is search for a car by given query
	 * @param CarQuery is a query to be send to server to search a car
	 */
	public void searchCar(Object[] CarQuery){
		sendQueryToServer(CarQuery);
	}
	
}
