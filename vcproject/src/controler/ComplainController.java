package controler; 

import entity.ComplainEntity;

public class ComplainController extends Controller {

	private String open;
	private ComplainEntity comEnt;
	private VcpInfo vcpInfo;
	public ComplainController(String host, int port, VcpInfo vcpInfo) {
		super(host, port);
		this.vcpInfo =  vcpInfo;
		this.open="open";

	}

	public boolean checkValidity(int id, int car, String complain) {
		Object[] sqlsMsg = {
				"SELECT `car`.`carNum`,`car`.`idclient`FROM `vcp_db`.`car` WHERE "
						+ " `car`.`idclient`= ? AND `car`.`carNum` = ?", id,
				car };
		sendQueryToServer(sqlsMsg);
		if (getResult().get(0).equals("No Result")) {
			showWarningMsg("Your id and car number are not matched");
			return false;
		} else {

			Object[] insertComplain = {
					"INSERT INTO `vcp_db`.`complain` (`idclient`, `description`,`status`,`lot_id`) VALUES (?,?,?,?);",
					id,complain,this.open,vcpInfo.getDefultParkingLot().getIdparkinglot() };
			sendQueryToServer(insertComplain);
			comEnt=new ComplainEntity();
			comEnt.setcarNum(car);
			comEnt.setDescription(complain);
			comEnt.setidNum(id);
			return true;
			
		}

	}

	public void sendAck(int idnum, String complain) {
		Object[] Msg = {"SELECT `complain`.`complainNum` FROM `vcp_db`.`complain` WHERE "
				+ " `complain`.`idclient`= ? AND `complain`.`description` = ? AND `complain`.`status` = ?;"
				, idnum, complain,this.open};
		if(getResult().get(0).toString().equals("done"))
		sendQueryToServer(Msg);
			comEnt.setComplainNum(getResult().get(0).toString());
		showSeccussesMsg("Your Complain ID is: "  + comEnt.getComplainNum() + "  , "
		+ " Please save to view the Complain progress.\n"
		+ " you can view the progress in the 'Complain Follow UP' screen "
		+ "by Selecting your iD");
		
	}

}
