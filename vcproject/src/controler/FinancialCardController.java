package controler;

import java.util.ArrayList;

import entity.FinancialCard;

public class FinancialCardController extends Controller {
	
	/**
	 * financialCard is a instance of the entity that holds the info about the client financial card
	 */
	private FinancialCard financialCard;
	
	/**
	 * FinancialCardController update and get info to and from the server about client financial card
	 * @param host to connect to server
	 */
	public FinancialCardController(String host) {
		super(host);
	}

	public FinancialCardController(String host, int port) {
		super(host, port);
	}
	
	/**
	 * getFinancialCard get financial of give client
	 * @param idclient is the id of the client
	 * @return financial card instance of the given client
	 */
	public FinancialCard getFinancialCard(Integer idclient){
		if(financialCard == null){
			financialCard = new FinancialCard();
			Object[] findFCard = {"SELECT * FROM `vcp_employ`.`financial_card` WHERE idclient = ?;",idclient};
			sendQueryToServer(findFCard);
			ArrayList<Object> result = null;
			result = getResult();
			boolean res = result.get(0).equals("No Result");
			if(!res){
				String id = result.get(0).toString();
				financialCard.setIdClient(Integer.parseInt(id));
				String amount = result.get(1).toString();
				financialCard.setAmount(Float.parseFloat(amount));
				return financialCard;
			}
		}
		return null;
	}
	
	/**
	 * updateFinancialCard is update financial card of client by given financial card entity
	 * @param fCard is exist financial card
	 * @return true if update succeeded else false
	 */
	public boolean updateFinancialCard(FinancialCard fCard){
		String fCardUpdate = "UPDATE `vcp_employ`.`financial_card` SET `amount` = ? WHERE idclient = ?;";
		fCard.setQuery(fCardUpdate);
		sendQueryToServer(fCard);
		if(getResult().get(0).equals("done"))
			return true;
		return false;
		
	}

}
