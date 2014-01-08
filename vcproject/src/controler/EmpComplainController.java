package controler;

import java.util.Vector;

import entity.EmpComplainEntity;

public class EmpComplainController extends Controller {

	private EmpComplainEntity empCompEntity;
	public EmpComplainController(String host, int port) {
		super();
		empCompEntity=new EmpComplainEntity();
	}
	public Vector<Vector<Object>> obtainValues() {
		empCompEntity.setQuery("SELECT `complain`.`complainNum`, `complain`.`idclient`, `complain`.`description`, `complain`.`status`,"
				+ " `complain`.`date` FROM `vcp_db`.`complain` WHERE `status` = ? order by `date`;");
		empCompEntity.setStatus("open");
		sendQueryToServer(empCompEntity.toObject());
		Vector<Vector<Object>> result = new Vector<Vector<Object>>();
		Vector<Object> row=new Vector<Object>(5);
		int i=1;
		for(Object value : getResult()){
			if(!value.equals("no value"))
				row.add(value);
			else
				row.add("0");
			if(i%5==0){
				result.add(row);
				i=1;
				row = new Vector<Object>(5);
			}
			else
				i++;
		}
		return result;

	}

public Vector<String> obtainFields(){
	Vector<String> s= new Vector<String>(5);
	s.add("Complain Id");
	s.add("Client Id");
	s.add("Complain Description");
	s.add("Status");
	s.add("Date Created");
	return s;
}


public void complainReplay(Object idNum,Object idclient,String refound,String answer){
	try {
		float f=Float.parseFloat(refound);
		Object[] obj = {
				"UPDATE  `vcp_db`.`complain` SET `complain`.`replay` = ? , `complain`.`refound` = ?  , `complain`.`status` = ?  WHERE `complain`.`complainNum` = ?  ;",answer,f ,"close" ,Integer.parseInt(idNum.toString()) };
		sendQueryToServer(obj);
		if(!getResult().get(0).equals("done"))
			throw new Exception("Error");
		Object[] obj1 = {"SELECT `financial_card`.`amount` FROM `vcp_employ`.`financial_card` WHERE `financial_card`.`idclient` = ?  ;",Integer.parseInt(idclient.toString())};
		sendQueryToServer(obj1);
		String amount=getResult().get(0).toString();
		if(amount.equals("No Result")){
			Object[] obj2={"INSERT INTO `vcp_employ`.`financial_card` (`idclient`,`amount`) VALUES(?,?) ;",Integer.parseInt(idclient.toString()),Float.parseFloat(refound)};
			sendQueryToServer(obj2);
			if(!getResult().get(0).equals("done"))
				throw new Exception("Error");
		}
		else{
		Object[] moneyCard = {
				"UPDATE  `vcp_employ`.`financial_card` SET  amount = ? WHERE idclient = ? ;",Float.parseFloat(amount.toString())+Float.parseFloat(refound),Integer.parseInt(idclient.toString()) };
		sendQueryToServer(moneyCard);
		if(!getResult().get(0).equals("done"))
			throw new Exception("Error");
		}
		showSeccussesMsg("Update is Done");
		
	} catch (Exception e) {
		showWarningMsg(e.getMessage());
	}

}
}
