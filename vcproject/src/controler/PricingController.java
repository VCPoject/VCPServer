package controler;

import java.util.ArrayList;

import entity.Pricing;


public class PricingController extends Controller {
	
	private Float occasionalPayment = null;
	private Float oneTimePayment = null;
	
	public PricingController() {
		super();
	}
	/**
	 * PricingController is a controller to manage the parking pricing
	 * @param host to connect to server side
	 * @param port to connect to server side
	 */
	public PricingController(String host, int port) {
		super(host, port);
	}
	public PricingController(String host) {
		super(host);
	}
	/**
	 * getOccasional is getting the pricing parking for occasional client(for one hour).
	 * @return payment for one hour for occasional client
	 */
	public Float getOccasional() {
		if(occasionalPayment==null){
			Object[] getPriceOccasional = {"SELECT `pricing`.`occasional` FROM `vcp_db`.`pricing`;"};
			sendQueryToServer(getPriceOccasional);
			Object result = getResult().get(0);
			if(result != null){
				setOccasional((Float)result);
			}
			closeConnection();
		}
		return occasionalPayment;
	}
	
	/**
	 * setOccasional is set the payment for one hour of occasional client by given amount.
	 * @param occasionalPayment is the payment for one hour of occasional client
	 */
	public void setOccasional(Float occasionalPayment) {
		this.occasionalPayment = occasionalPayment;
	}
	
	/**
	 * getOneTime is getting the pricing parking for one time client(for one hour).
	 * @return payment for one hour for one time client
	 */
	public Float getOneTime() {
		if(oneTimePayment==null){
			Object[] getPriceOneTime = {"SELECT `pricing`.`oneTime` FROM `vcp_db`.`pricing`;"};
			sendQueryToServer(getPriceOneTime);
			Object result = getResult().get(0);
			if(result != null){
				setOneTime((Float)result);
			}
			closeConnection();
		}
		return oneTimePayment;
	}
	/**
	 * setOneTime is set the payment for one hour of one time client by given amount.
	 * @param oneTimePayment is the payment for one hour of one time client
	 */
	public void setOneTime(Float oneTimePayment) {
		this.oneTimePayment = oneTimePayment;
	}
	
	/**
	 * changePrice is set a request for changing the parking pricing.
	 * @param occasional is the payment for one hour for occasional client
	 * @param oneTime is the payment for one hour for one time client
	 * @param empId the employee that want to change pricing
	 */
	public void changePrice(Float occasional, Float oneTime, Integer empId ){
		Object[] addChangePrice = {"INSERT INTO `vcp_employ`.`change_pricing`(`employId`,`occasional`,`oneTime`)"
				+ " VALUES (?,?,?);",empId,occasional,oneTime};
		sendQueryToServer(addChangePrice);
	}
	
	/**
	 * updateChangePriceStatus is updating the answer of the manager in DB.
	 * @param status is the status to set for the change pricing request
	 * @param idChangePricing is the number of the request
	 * @return true in update succeeded else false
	 */
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
	
	/**
	 * updatePricing is updating the the pricing of parking.
	 * @param occasional is the payment for one hour for occasional client
	 * @param oneTime is the payment for one hour for one time client
	 * @param pricing entity to be update
	 * @return
	 */
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
	
	/**
	 * getPricingRequests is getting all the request for changing the pricing of parking.
	 * @return all the pricing request
	 */
	public ArrayList<Object> getPricingRequests(){
		Object[] pricingRequests = {"SELECT * FROM vcp_employ.change_pricing WHERE status = 'open';"};
		sendQueryToServer(pricingRequests);
		return getResult();
	}
	

}
