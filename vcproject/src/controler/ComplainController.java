package controler;

public class ComplainController extends Controller {

	private String open;
	private String idFromTable;

	public ComplainController(String host, int port) {
		super(host, port);
		this.open="open";

	}

	public void checkValidity(int id, int car, String complain) {
		Object[] sqlsMsg = {
				"SELECT `car`.`carNum`,`car`.`idclient`FROM `vcp_db`.`car` WHERE "
						+ " `car`.`idclient`= ? AND `car`.`carNum` = ?", id,
				car };
		sendQueryToServer(sqlsMsg);
		if (getResult().get(0).equals("No Result")) {
			showWarningMsg("Your id and car number are not matched");
		} else {
			Object[] insertComplain = {
					"INSERT INTO `vcp_db`.`complain` (`idclient`, `description`,`status`) VALUES (?,?,?);",
					id,complain,this.open };
			sendQueryToServer(insertComplain);
			Object[] Msg = {"SELECT `complain`.`complainNum`, FROM `vcp_db`.`complain` WHERE "
							+ " `complain`.`idclient`= ? ", id};
			sendQueryToServer(Msg);
			idFromTable=getResult().get(0).toString();
			showSeccussesMsg("Your Complain ID is: "  + idFromTable + " , "
					+ "Costumer Service will take care of it\n"
					+ " you can view the progress in the 'Complain Follow UP' screen "
					+ "by Selecting your iD");
		}

	}

}
