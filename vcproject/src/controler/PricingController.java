package controler;

import java.util.ArrayList;

import entity.Pricing;


public class PricingController extends Controller {
	
	private Float occasional = null;
	private Float oneTime = null;
	
	public PricingController() {
		super();
	}
	public PricingController(String host, int port) {
		super(host, port);
	}
	public PricingController(String host) {
		super(host);
	}
	public Float getOccasional() {
		if(occasional==null){
			Object[] getPriceOccasional = {"SELECT `pricing`.`occasional` FROM `vcp_db`.`pricing`;"};
			sendQueryToServer(getPriceOccasional);
			Object result = getResult().get(0);
			if(result != null){
				setOccasional((Float)result);
			}
			closeConnection();
		}
		return occasional;
	}
	public void setOccasional(Float occasional) {
		this.occasional = occasional;
	}
	public Float getOneTime() {
		if(oneTime==null){
			Object[] getPriceOneTime = {"SELECT `pricing`.`oneTime` FROM `vcp_db`.`pricing`;"};
			sendQueryToServer(getPriceOneTime);
			Object result = getResult().get(0);
			if(result != null){
				setOneTime((Float)result);
			}
			closeConnection();
		}
		return oneTime;
	}
	public void setOneTime(Float oneTime) {
		this.oneTime = oneTime;
	}
	
	public void changePrice(Float occasional, Float oneTime, Integer empId ){
		Object[] addChangePrice = {"INSERT INTO `vcp_employ`.`change_pricing`(`employId`,`occasional`,`oneTime`)"
				+ " VALUES (?,?,?);",empId,occasional,oneTime};
		sendQueryToServer(addChangePrice);
	}
	
	public boolean updateChangePriceStatus(String status, int idChangePricing ){
		Object[] updatePriceStatus = {"UPDATE `vcp_employ`.`change_pricing` SET `status` = ? WHERE `idchange_pricing` = ?;",
				status,idChangePricing};
		sendQueryToServer(updatePriceStatus);
		Object result = getResult().get(0);
		if(result.equals("done")){
			return true;
		}
		return false;
	}
	
	public boolean updatePricing(Float occasional, Float oneTime, Pricing pricing){
		Object[] updatepricing = {"UPDATE `vcp_db`.`pricing` SET `occasional` = ?, `oneTime` = ? WHERE idpricing = '1' ;",
				occasional , oneTime};
		sendQueryToServer(updatepricing);
		Object result = getResult().get(0);
		if(result.equals("done")){
			pricing.setOccasional(occasional);
			pricing.setOneTime(oneTime);
			return true;
		}
		return false;
		
	}
	
	public ArrayList<Object> getPricingRequests(){
		Object[] pricingRequests = {"SELECT * FROM vcp_employ.change_pricing WHERE status = 'open';"};
		sendQueryToServer(pricingRequests);
		return getResult();
	}
	

}
