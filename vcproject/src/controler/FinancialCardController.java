package controler;

import entity.FinancialCard;

public class FinancialCardController extends Controller {
	
	private FinancialCard financialCard;
	
	public FinancialCardController(String host) {
		super(host);
	}

	public FinancialCardController(String host, int port) {
		super(host, port);
	}
	
	public FinancialCard getFinancialCard(int idclient){
		if(financialCard == null){
			financialCard = new FinancialCard();
			Object[] findFCard = {"SELECT * FROM `vcp_employ`.`financial_card` WHERE idclient = ?;"};
		}
		
		return financialCard;
	}

}
