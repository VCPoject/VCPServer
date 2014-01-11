package controler;

import java.util.ArrayList;

import entity.FinancialCard;

public class FinancialCardController extends Controller {
	
	private FinancialCard financialCard;
	
	public FinancialCardController(String host) {
		super(host);
	}

	public FinancialCardController(String host, int port) {
		super(host, port);
	}
	
	public FinancialCard getFinancialCard(Integer idclient){
		if(financialCard == null){
			financialCard = new FinancialCard();
			Object[] findFCard = {"SELECT * FROM `vcp_employ`.`financial_card` WHERE idclient = ?;",idclient};
			sendQueryToServer(findFCard);
			ArrayList<Object> result = null;
			result = getResult();
			boolean res = result.get(0).equals("No Result");
			if(!res){
				financialCard.setIdClient((Integer)result.get(0));
				financialCard.setAmount((Float)result.get(1));
				return financialCard;
			}
		}
		return null;
	}
	
	public boolean updateFinancialCard(FinancialCard fCard){
		String fCardUpdate = "UPDATE `vcp_employ`.`financial_card` SET `amount` = ? WHERE `idclient` = ?;";
		fCard.setQuery(fCardUpdate);
		sendQueryToServer(fCard);
		if(getResult().get(0).equals("done"))
			return true;
		return false;
		
	}

}
