package controler;

import entity.ComplainFuEntity;

public class ComplainFuController extends Controller {

	private ComplainFuEntity complainEntity;
	
	public ComplainFuController(String host, int port) {
		super(host, port);
		// TODO Auto-generated constructor stub
	}

	public boolean checkValidity(int idnum, int carnum) {
		Object[] sqlsMsg = {
				"SELECT `car`.`carNum`,`car`.`idclient`FROM `vcp_db`.`car` WHERE "
						+ " `car`.`idclient`= ? AND `car`.`carNum` = ?", idnum,
				carnum };
		sendQueryToServer(sqlsMsg);
		if (getResult().get(0).equals("No Result")) {
			showWarningMsg("Your id and car number are not matched, Please Try Again");
			return false;
		} else {
			showSeccussesMsg("Please Choose a complain By the ID that given to you when you open a Complain");
			return true;
		}	
	}

	public String[] getComplains(int idnum) {
		complainEntity = new ComplainFuEntity();
		complainEntity.getQuery();
		Object[] sqlsMsg = {"SELECT `complain`.`complainNum` FROM `vcp_db`.`complain` WHERE "
				+ " `complain`.`idclient`= ?  AND `complain`.`status` = 'open'",idnum };
		sendQueryToServer(sqlsMsg);
		if (getResult().get(0).equals("No Result"))
		{
			
			return new String[]{getResult().get(0).toString()};
		}
		else{
			String[] pickedString = new String[getResult().size()];
			for(int i=0;i<getResult().size();i++)
			{
				pickedString[i]=getResult().get(i).toString();
			}
			return pickedString;
		}
	}

	public void getSelectedItem(String selectedItem) {
		
		// TODO need customer service Table in DB
		
	}
	
	

}
