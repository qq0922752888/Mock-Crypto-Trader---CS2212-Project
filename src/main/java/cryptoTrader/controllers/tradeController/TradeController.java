package cryptoTrader.controllers.tradeController;

import cryptoTrader.models.tradeModel.TradeModel;
/**
 * This class is coded to link TradeModel and TradeView classes in Controller system.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 *This class implements RequestTrade class as an interface.
 */
public class TradeController implements RequestTrade{
	
	private static TradeController instance; 
	/**
	 * If there is no instance created before, this method creates a new TradeController object, and returns it as instance.
	 * @return instance, the TradeController object.
	 */
	public static TradeController getInstance() {
		if (instance == null)
			instance = new TradeController();

		return instance;
	}
	/**
	 * This method initiates the trade by calling startTrading method.
	 */
	@Override
	public void initiateTrade() {
		TradeModel.getInstance().startTrading(); 
	}
}
