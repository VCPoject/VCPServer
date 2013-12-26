package controler;

public class MakeOrderController extends Controller {

	public MakeOrderController(String host, int port) {
		super(host, port);
	}

	public void addCarToDB(int carNum, int idclient) {
		Object[] addCar = {
				"INSERT INTO `vcp_db`.`car` (`carNum`, `idclient`) VALUES (?,?);",
				carNum, idclient };
		sendQueryToServer(addCar);
	}

	public void addNewClient(int idclient, String email) {
		Object[] addClient = {
				"INSERT INTO `vcp_db`.`client` (`idclient`, `email`) VALUES(?,?);",
				idclient, email };
		sendQueryToServer(addClient);

	}

	public void addNewOrder(Object[] orderInfo) {
		//Object[] addOrderMsg = null;
		if (orderInfo.length == 6) {
			Object[] addOrderMsg = {"INSERT INTO `vcp_db`.`order` (`carNum`,`idparking`,`arrivalDate`,`arrivalTime`,`status`,`type`) VALUES (?,?,?,?,?,?);",orderInfo[0], orderInfo[1], orderInfo[2], orderInfo[3],orderInfo[4], orderInfo[5] };
			sendQueryToServer(addOrderMsg);
		}
		else{
			Object[] addOrderMsg = {"INSERT INTO `vcp_db`.`order` (`idorder`,`carNum`,`idparking`,`arrivalDate`,`arrivalTime`,`departureDate`,`departureTime`,`status`,`type`) VALUES (?,?,?,?,?);",orderInfo[0], orderInfo[1], orderInfo[2], orderInfo[3],orderInfo[4], orderInfo[5], orderInfo[6],orderInfo[7],orderInfo[8] };
			sendQueryToServer(addOrderMsg);
		}
	}
}
