package controler;

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
		if(!Float.isNaN(occasional)){
			Object[] getPriceOccasional = {"SELECT `pricing`.`occasional` FROM `vcp_db`.`pricing`;"};
			sendQueryToServer(getPriceOccasional);
			Object result = getResult().get(0);
			if(result != null){
				setOccasional((Float)result);
			}
		}
		return occasional;
	}
	public void setOccasional(float occasional) {
		this.occasional = occasional;
	}
	public Float getOneTime() {
		if(!Float.isNaN(oneTime)){
			Object[] getPriceOneTime = {"SELECT `pricing`.`oneTime` FROM `vcp_db`.`pricing`;"};
			sendQueryToServer(getPriceOneTime);
			Object result = getResult().get(0);
			if(result != null){
				setOneTime((Float)result);
			}
		}
		return oneTime;
	}
	public void setOneTime(float oneTime) {
		this.oneTime = oneTime;
	}
	

}
